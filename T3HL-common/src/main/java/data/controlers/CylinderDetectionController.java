package data.controlers;

import connection.Connection;
import data.Table;
import data.XYO;
import pfg.config.Configurable;
import utils.HLInstance;
import utils.Log;
import utils.container.Module;
import utils.math.*;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Gère la communication avec le processus de détection de cylindres basé sur l'Intel D435i
 */
public class CylinderDetectionController implements Module {

    //
    private static final String REGEX = "^(?<angleX>([0-9]+(\\.[0-9]*)?)) (?<angleY>([0-9]+(\\.[0-9]*)?)) (?<angleZ>([0-9]+(\\.[0-9]*)?)) (?<shapeCount>[0-9]+) (?<shape>((?<type>[a-z]+) (?<color>[a-z]+) (?<height>([0-9]+(\\.[0-9]*)?)) (?<localX>([0-9]+(\\.[0-9]*)?)) (?<localY>([0-9]+(\\.[0-9]*)?)) (?<localZ>([0-9]+(\\.[0-9]*)?)) (?<globalX>([0-9]+(\\.[0-9]*)?)) (?<globalY>([0-9]+(\\.[0-9]*)?)) (?<globalZ>([0-9]+(\\.[0-9]*)?))) ?)*";
    private static final String SHAPE_REGEX = "(?<shape>((?<type>[a-z]+) (?<color>[a-z]+) (?<height>([0-9]+(\\.[0-9]*)?)) (?<localX>([0-9]+(\\.[0-9]*)?)) (?<localY>([0-9]+(\\.[0-9]*)?)) (?<localZ>([0-9]+(\\.[0-9]*)?)) (?<globalX>([0-9]+(\\.[0-9]*)?)) (?<globalY>([0-9]+(\\.[0-9]*)?)) (?<globalZ>([0-9]+(\\.[0-9]*)?))) ?)";
    private static final Pattern pattern = Pattern.compile(REGEX, Pattern.MULTILINE);
    private static final Pattern shapePattern = Pattern.compile(SHAPE_REGEX, Pattern.MULTILINE);
    private final Rectangle tableBB;
    private final Table table;
    private final List<Cup> cylinders = new LinkedList<>();

    @Configurable
    private boolean usingCylinderDetection;

    @Configurable
    private boolean symetry;

    @Configurable("cylinderRadius")
    private int cupRadius;

    private Listener listener;

    public CylinderDetectionController(Listener listener, Table table) {
        this.listener = listener;
        this.table = table;
        tableBB = new Rectangle(new InternalVectCartesian(0f, table.getWidth()/2), table.getLength(), table.getWidth());
    }

    @Override
    public void onInit(HLInstance hl) {
        hl.async("Init CylinderDetection Module", () -> {
            if(!usingCylinderDetection) {
                return;
            }
            Log.LIDAR_PROCESS.debug("Controller lancé : en attente du listener...");

            /* TODO: Launch process
            Log.LIDAR_PROCESS.debug("Démarrage du processus DetectorCylinder...");
            try {
                Process lidarProcess = new ProcessBuilder(lidarProcessPath).start();

                // force l'extinction du programme quand la VM s'arrête
                Runtime.getRuntime().addShutdownHook(new Thread(lidarProcess::destroyForcibly));
                new CopyIOThread(lidarProcess, Log.LIDAR_PROCESS).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            */
            Module.waitWhileTrue(() -> !Connection.CYLINDER_DETECTION.isInitiated());
            Log.CYLINDER_DETECTION.debug("Processus OK");
            listener.registerMessageHandler(Channel.CYLINDERS, this::handleCylinders);
        });
    }

    private void handleCylinders(String message) {
        final Matcher matcher = pattern.matcher(message);

        if (matcher.find()) { // message valide
            XYO currentXYO = XYO.getRobotInstance();
            cylinders.clear();
            float orientationX = Float.parseFloat(matcher.group("angleX"));
            float orientationY = Float.parseFloat(matcher.group("angleY"));
            float orientationZ = Float.parseFloat(matcher.group("angleZ"));

            int shapeCount = Integer.parseInt(matcher.group("shapeCount"));
            int shapeStart = matcher.end("shapeCount")+1;
            String shapes = message.substring(shapeStart);
            Matcher shapeMatcher = shapePattern.matcher(shapes);
            for (int i = 0; i < shapeCount; i++) {
                if(shapeMatcher.find()) {
                    String type = shapeMatcher.group("type");
                    String color = shapeMatcher.group("color");
                    // *1000 pour convertir en mm
                    float height = Float.parseFloat(shapeMatcher.group("height"))*1000;
                    float localX = Float.parseFloat(shapeMatcher.group("localX"))*1000;
                    float localY = Float.parseFloat(shapeMatcher.group("localY"))*1000;
                    float localZ = Float.parseFloat(shapeMatcher.group("localZ"))*1000;
                    float globalX = Float.parseFloat(shapeMatcher.group("globalX"))*1000;
                    float globalY = Float.parseFloat(shapeMatcher.group("globalY"))*1000;
                    float globalZ = Float.parseFloat(shapeMatcher.group("globalZ"))*1000;

                    double length = Math.sqrt(localX*localX+localZ*localZ);
                    double angle = Math.atan2(localZ, localX);
                    VectPolar localPosition = new VectPolar(length, angle);
                    if(symetry) {
                        localPosition.setA(-localPosition.getA());
                    }

                    localPosition.setA(Calculs.modulo(localPosition.getA() + currentXYO.getOrientation(), Math.PI));
                    localPosition.plus(currentXYO.getPosition());

                    // on ajoute l'obstacle que s'il est dans la table
                    if(tableBB.isInShape(localPosition)) {
                        cylinders.add(new Cup(localPosition, cupRadius, Cup.Color.valueOf(color.toUpperCase())));
                        Log.CYLINDER_DETECTION.warning("Cylinder detection at " + localPosition);
                    }
                }
            }

            table.updateCylinders(cylinders);
        }

    }
}

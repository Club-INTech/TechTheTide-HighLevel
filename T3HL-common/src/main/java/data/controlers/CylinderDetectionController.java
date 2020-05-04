package data.controlers;

import connection.Connection;
import pfg.config.Configurable;
import utils.HLInstance;
import utils.Log;
import utils.container.Module;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Gère la communication avec le processus de détection de cylindres basé sur l'Intel D435i
 */
public class CylinderDetectionController implements Module {

    //
    private static final String REGEX = "^(?<angleX>([0-9]+(\\.[0-9]*)?)) (?<angleY>([0-9]+(\\.[0-9]*)?)) (?<angleZ>([0-9]+(\\.[0-9]*)?)) (?<shapeCount>[0-9]+) (?<shape>(?<type>[a-z]+ (?<color>[a-z]+) (?<height>([0-9]+(\\.[0-9]*)?)) (?<localX>([0-9]+(\\.[0-9]*)?)) (?<localY>([0-9]+(\\.[0-9]*)?)) (?<localZ>([0-9]+(\\.[0-9]*)?)) (?<globalX>([0-9]+(\\.[0-9]*)?)) (?<globalY>([0-9]+(\\.[0-9]*)?)) (?<globalZ>([0-9]+(\\.[0-9]*)?))) ?)+";
    private static final Pattern pattern = Pattern.compile(REGEX, Pattern.MULTILINE);

    @Configurable
    private boolean usingCylinderDetection;
    private Listener listener;

    public CylinderDetectionController(Listener listener) {
        this.listener = listener;
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
            // TODO: découpage et récupération des infos depuis matcher
            System.out.println("Full match! "+message);
        }

    }
}

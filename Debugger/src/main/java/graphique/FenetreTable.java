package graphique;

import LogRegex.Regex;
import data.Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class FenetreTable extends JFrame {

    String FileImageRobot = "Debugger/src/main/java/graphique/ressources/RobotsVignette.png";
    public static TableVisualisation robot = new TableVisualisation();
    //    String logfile = "Debugger/src/main/java/LogsTests/testLogsComNuc1.log";
    //    String logfile = "Debugger/src/main/java/LogsTests/testLogsLidar.log";

/*======================================== fenêtre avec les logs écrits  ==========================================*/

    private Dimension dimensionsEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    public final int TABLE_PIXEL_WIDTH = 982; //en pixels
    public final int TABLE_PIXEL_HEIGHT = 690; //en pixels
    public final int LOG_PIXEL_WIDTH = 450; //largeur de la fenètre de log en pixels
    public final int LOG_PIXEL_HEIGHT = TABLE_PIXEL_HEIGHT; //hauteur de la fenètre de log en pixels


    public FenetreTable(Boolean principal, String logFile) throws Exception {
        setTitle("Debugger : Table de Jeu");
        setSize(TABLE_PIXEL_WIDTH, TABLE_PIXEL_HEIGHT);
//        setLocationRelativeTo(null);
        setLocation((dimensionsEcran.width-TABLE_PIXEL_WIDTH-LOG_PIXEL_WIDTH)/2, (dimensionsEcran.height-TABLE_PIXEL_HEIGHT)/2);
        setResizable(false);
        setAlwaysOnTop(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Pour personnaliser l'icone de la fenêtre
        Image icone = Toolkit.getDefaultToolkit().getImage(FileImageRobot);
        setIconImage(icone);
        robot.setPrincipal(principal);

        initBoutonPasAPas();
        initBoutonPlay();

        System.out.println(play.getText());

        setContentPane(robot);
        setVisible(true);
        Regex.regex(logFile);


    }


/*======================================= boutons marche/arrêt et pas à pas=========================================*/

    private JButton play = new JButton("Play");
    private JButton stop = new JButton("Stop");
    private ButtonGroup playStop = new ButtonGroup();
    private JButton pasAPas = new JButton("PAP");

    private static Boolean boutonPlay = false;
    public static Boolean boutonPasAPas = false;

    ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            boutonPlay = true;
            boutonPasAPas = false;
        }
    };

    ActionListener listener1 = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            boutonPlay = false;
            boutonPasAPas = false;
        }
    };

    ActionListener listener2 = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            boutonPasAPas = true;
            boutonPlay =  false;
        }
    };

    public void initBoutonPlay(){
        play.setBounds(465-45, 599, 70, 30);
        stop.setBounds(465+26, 599, 70, 30);

        robot.add(play);
        robot.add(stop);


        /*
        try {
            if (play.getText().equals("Play")) {
                play.addActionListener(listener);
                play.setText("Stop");
            } else if (play.getText().equals("Stop")) {
                play.addActionListener(listener1);
                play.setText("Play");
            }
        }catch (Exception e) {
            System.out.println("erreur Configuration" + e.getMessage());
        }
        */

        play.addActionListener(listener);
        stop.addActionListener(listener1);

        playStop.add(play);
        playStop.add(stop);

    }

    public void initBoutonPasAPas(){
        pasAPas.setBounds(465-10, 629,70,30);

        robot.add(pasAPas);

        pasAPas.addActionListener(listener2);

        playStop.add(pasAPas);

    }

    public static boolean getboutonPlay (){
        return boutonPlay;
    }

    public static boolean getboutonPasAPas(){
        return boutonPasAPas;
    }
}


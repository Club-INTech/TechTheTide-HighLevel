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
    private JButton pasAPas = new JButton("PAP");

    private static Boolean boutonPlay = false;
    public static Boolean boutonPasAPas = false;

    ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (play.getText().equals("Play")) {
                boutonPlay = true;
                boutonPasAPas = false;
                play.setText("Stop");
            }
            else if (play.getText().equals("Stop")) {
                boutonPlay = false;
                boutonPasAPas = false;
                play.setText("Play");
            }

        }
    };

    ActionListener listener1 = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            boutonPasAPas = true;
            boutonPlay =  false;
        }
    };

    public void initBoutonPlay(){
        play.setBounds(455, 599, 70, 30);

        robot.add(play);

        play.addActionListener(listener);
    }

    public void initBoutonPasAPas(){
        pasAPas.setBounds(455, 629,70,30);

        robot.add(pasAPas);

        pasAPas.addActionListener(listener1);
    }

    public static boolean getboutonPlay (){
        return boutonPlay;
    }

    public static boolean getboutonPasAPas(){
        return boutonPasAPas;
    }
}


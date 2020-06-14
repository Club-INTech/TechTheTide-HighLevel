package graphique;

import LogRegex.Regex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class FenetreTable extends JFrame {

    String FileImageRobot = "Debugger/src/main/java/graphique/ressources/RobotsVignette.png";
    public static TableVisualisation robot = new TableVisualisation();

    private JButton play = new JButton("Play");
    private JButton stop = new JButton("stop");
    private ButtonGroup playStop = new ButtonGroup();
    private JButton pasAPas = new JButton("PAP");

    private static Boolean boutonPlay = false;
    public static Boolean boutonPasAPas = false;

/*
    ItemListener listener = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            int state = itemEvent.getStateChange();
            boutonPlay = (state == 1);
            boutonPasAPas = false;
        }
    };

 */

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




//    String logfile = "Debugger/src/main/java/LogsTests/testLogsComNuc1.log";
//    String logfile = "Debugger/src/main/java/LogsTests/testLogsLidar.log";

    public FenetreTable(Boolean principal, String logFile) throws Exception {
        setTitle("Debugger : Table de Jeu");
        setSize(982, 690);
        setLocationRelativeTo(null);
        setResizable(false);
        setAlwaysOnTop(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Pour personnaliser l'icone de la fenêtre
        Image icone = Toolkit.getDefaultToolkit().getImage(FileImageRobot);
        setIconImage(icone);

        initBoutonPasAPas();
        initBoutonPlay();

        setContentPane(robot);
        setVisible(true);
        Regex.regex(logFile);


    }

    public void initBoutonPlay(){
        play.setBounds(1730, 1655, 50, 50);
        stop.setBounds(300, 1600, 50, 50);

        robot.add(play);
        robot.add(stop);

        /*
        try {
            if (play.getText().equals("play")) {
                play.addActionListener(listener);
                play.setText("stop");
            } else if (play.getText().equals("play")) {
                play.addActionListener(listener1);
                play.setText("play");
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
        pasAPas.setBounds(1300, 1600, 100, 50);

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


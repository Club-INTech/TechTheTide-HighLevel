package graphique;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class FenetreDemarrage extends JDialog{

    private static Boolean principalBool = true;
    private String logFile;

    private JPanel panel = new JPanel(null);
    private JTextField text = new JTextField("lien du log");
    private JToggleButton principal = new JToggleButton("principal");
    private JToggleButton secondaire = new JToggleButton("secondaire");
    private ButtonGroup choixRobot = new ButtonGroup();
    private JButton valider = new JButton("Valider");


    ItemListener listener = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            int state = itemEvent.getStateChange();
            principalBool = (state == 1);
        }
    };

    public FenetreDemarrage (){

        super(null, Dialog.ModalityType.APPLICATION_MODAL);

        setTitle("Débugger");
        setSize(500,300);
        setLocationRelativeTo(null);
        setResizable(false);
        setAlwaysOnTop(false);

        ImageIcon image = new ImageIcon("Debugger/src/main/java/graphique/ressources/bouee_SailTheWorld.png");
        setIconImage(image.getImage());

//        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setModal(true);

        initZoneDeTexte();
        initChoixRobot();
        initButtonValider();

        setContentPane(panel);

        setVisible(true);
    }

    public void initZoneDeTexte(){
        text.setBounds(100, 150, 300, 30);
        panel.add(text);
    }

    public void initChoixRobot(){
        principal.setBounds(50, 50, 150, 50);
        secondaire.setBounds(300, 50, 150, 50);

        panel.add(principal);
        panel.add(secondaire);

        principal.addItemListener(listener);

        choixRobot.add(principal);
        choixRobot.add(secondaire);

    }

    public void initButtonValider(){
        valider.setBounds(200, 200, 100, 50);
        panel.add(valider);
        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                logFile = text.getText();
                dispose();
            }
        });
    }

    public static boolean getChoixRobot (){
        return principalBool;
    }

    public String getLogFile(){
        return logFile;
    }

//    private JTextPane test = new JTextPane();

}

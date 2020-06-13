package graphique;

import javax.swing.*;
import java.awt.*;


public class FenetreLog extends JFrame {
    private static JTextArea textArea = new JTextArea(43,43);
    private static JScrollPane scrollPanel = new JScrollPane(textArea);
    private static JScrollBar scrollBar = scrollPanel.getVerticalScrollBar();
    private JPanel panel = new JPanel();
    private Dimension dimensionsEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

    private static String text = new String();

    String FileImageRobot = "Debugger/src/main/java/graphique/ressources/RobotsVignette.png";

    private final int TABLE_PIXEL_WIDTH = 982; //en pixels
    private final int TABLE_PIXEL_HEIGHT = 690; //en pixels
    private final int LOG_PIXEL_WIDTH = 500; //largeur de la fenètre de log en pixels
    private final int LOG_PIXEL_HEIGHT = TABLE_PIXEL_HEIGHT; //hauteur de la fenètre de log en pixels

    public void initFenetreLog() {
        textArea.setEditable(false);
        textArea.setText(text);

        panel.add(scrollPanel);
    }

    public FenetreLog(){

        setTitle("Débugger : Logs traités");
        setSize(LOG_PIXEL_WIDTH,LOG_PIXEL_HEIGHT);
//        setLocationRelativeTo(null);
        setResizable(false);
        setAlwaysOnTop(true);
        setLocation((dimensionsEcran.width+TABLE_PIXEL_WIDTH-LOG_PIXEL_WIDTH)/2 +1, (dimensionsEcran.height-TABLE_PIXEL_HEIGHT)/2);

        Image icone = Toolkit.getDefaultToolkit().getImage(FileImageRobot);
        setIconImage(icone);

        scrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        initFenetreLog();

        setContentPane(panel);
        setVisible(true);
    }

    public void setText(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }

    public static void addLogTextln(String textToAdd) {
        text = text + "\n" + textToAdd;
        textArea.setText(text);
//        textArea.setCaretPosition(100);
        scrollDown();
    }

    public static void scrollDown(){
        int max = scrollBar.getMaximum();
        System.out.println(scrollBar.getValue());
        if (scrollBar.getValue() >= max-1000) {

            scrollBar.setValue(max);
        }

    }


}

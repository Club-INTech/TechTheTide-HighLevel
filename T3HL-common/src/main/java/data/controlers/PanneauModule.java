package data.controlers;

import com.panneau.Panneau;
import pfg.config.Configurable;
import utils.ConfigData;
import utils.HLInstance;
import utils.Log;
import utils.container.Module;

import java.io.IOException;

/**
 * Module & Thread qui gère la """comm""" avec le panneau: score et sélection de couleur au début du match
 */
public class PanneauModule implements Module {

    private Panneau panel;
    @Configurable
    private long scoreUpdatePeriod;
    @Configurable
    private String couleur;
    private HLInstance hl;
    @Configurable
    private int ledProgramPort;
    @Configurable
    private int ledCount; //TODO: inutilisé
    @Configurable
    private boolean using7Segments;

    public PanneauModule(HLInstance hl) {
        this.hl = hl;
    }

    public void setPanel(Panneau panel) {
        this.panel = panel;
    }

    public Panneau getPanneau() {
        if (panel == null) {
            panel = new Panneau(ledCount, ledProgramPort, using7Segments); //TODO: mauvaise signatire du constructeur
            if (using7Segments) {
                Log.STRATEGY.debug("Appel au constructeur du panneau avec " + ledCount + " leds et l'ecran de score");
            } else {
                Log.STRATEGY.debug("Appel au constructeur du panneau avec " + ledCount + " leds, sans l'ecran de score");
            }
            panel.addListener(teamColor -> {
                couleur = panel.getTeamColor().toString().toLowerCase();
                hl.getConfig().override(ConfigData.COULEUR, couleur);
                hl.updateConfig(hl.getConfig());
            });
            panel.setLeds(Panneau.LedColor.NOIR);

        }
        return panel;
    }

    public String getCouleur() {
        return couleur;
    }

    public void printScore(int score) throws IOException {
        if (using7Segments) {
            panel.printScore(score);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        panel.setLeds(Panneau.LedColor.NOIR);
        panel.printScore(8888);
        super.finalize();
    }
}

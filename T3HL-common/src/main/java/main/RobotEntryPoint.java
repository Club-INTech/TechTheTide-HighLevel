/**
 * Copyright (c) 2018, INTech.
 * this file is part of INTech's HighLevel.
 *
 * INTech's HighLevel is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * INTech's HighLevel is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with it.  If not, see <http://www.gnu.org/licenses/>.
 **/
package main;

import ai.AIModule;
import com.panneau.Panneau;
import connection.ConnectionManager;
import data.Table;
import data.controlers.*;
import locomotion.UnableToMoveException;
import orders.OrderWrapper;
import robot.Robot;
import scripts.ScriptManager;
import utils.ConfigData;
import utils.HLInstance;
import utils.Log;
import utils.MatchTimer;
import utils.container.ContainerException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author nayth
 */
public abstract class RobotEntryPoint {

    protected HLInstance hl;
    protected ScriptManager scriptManager;
    protected ConnectionManager connectionManager;
    protected OrderWrapper orderWrapper;
    protected Listener listener;
    protected DataController dataController;
    protected LidarController lidarController;
    protected Table table;
    protected Robot robot;
    protected AIModule ai;
    protected PanneauModule panneauService;

    public void entryPoint(HLInstance hl, Class<? extends Robot> robotClass, Class<? extends ScriptManager> scriptManagerClass) throws ContainerException {
        this.hl = hl;
        hl.module(robotClass); // charge l'instance du robot (Master ou Slave selon le robot). Nécessaire pour que .module(Robot.class) ne plante pas: il faut une instance de Robot.class qui existe déjà (c'est une classe abstraite)
        initServices(hl, robotClass, scriptManagerClass);
        preLLConnection();
        waitForAllConnectionsReady();
        Log.COMMUNICATION.debug("Connection established, starting match");

        try {
            act();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /// ========== INSERER LE CODE ICI POUR TESTER LES SCRIPTS ========== ///
        while(robot != null) {
            System.out.println("Finished!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        HLInstance.resetInstance();
    }

    protected abstract void preLLConnection() throws ContainerException;

    protected abstract void act() throws UnableToMoveException;

    protected void waitForAllConnectionsReady() {
        while (!connectionManager.areMandatoryConnectionsInitiated()) {
            try {
                if(panneauService.getPanneau() != null && hl.getConfig().getBoolean(ConfigData.USING_PANEL)) {
                    float f = (float) Math.min(1, Math.sin(System.currentTimeMillis()/1000.0 * Math.PI)*0.5f+0.5f);
                    //leds.fillColor(new LEDs.RGBColor(f, 0f, 1f-f));
                    panneauService.getPanneau().setLeds(Panneau.LedColor.NOIR); //FIXME: On n'a pls de RGB, donc c'est soit jaune, soit bleu, soit rien du tuot (ou jaune et bleu à la limite)
                }

                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(panneauService.getPanneau() != null && hl.getConfig().getBoolean(ConfigData.USING_PANEL)) {
            resetColorToTeamColor(panneauService.getPanneau());
        }
    }

    protected void waitForColorSwitch() {
        if( ! hl.getConfig().getBoolean(ConfigData.USING_PANEL)) {
            panneauService.setPanel(null);
            return;
        }
        Panneau panneau = panneauService.getPanneau();
        if(panneau != null) {
            try {
                try {
                    panneauService.printScore(5005);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Panneau.TeamColor initialColor = panneau.getTeamColor();
                /*
                FIXME: ici aussi, problème de pas RGB
                LEDs.RGBColor waitingColor1 = new LEDs.RGBColor(0.5f, 0.5f, 0.0f);
                LEDs.RGBColor waitingColor2 = new LEDs.RGBColor(0.5f, 0.0f, 0.5f);
                */
                // on attend une première activation du switch
                while(initialColor == panneau.getTeamColor()) {
                    try {
                        panneauService.printScore(5005);
                        //leds.fillColor(waitingColor1);
                        TimeUnit.MILLISECONDS.sleep(100);
                        panneauService.printScore(550);
                        //leds.fillColor(waitingColor2);
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                resetColorToTeamColor(panneau);

                initialColor = panneau.getTeamColor();
                // on attend une deuxième activation du switch ou 5s
                long delay = 5000;
                long start = System.currentTimeMillis();
                while(initialColor == panneau.getTeamColor() && (System.currentTimeMillis() - start) <= delay) {
                    try {
                        panneauService.printScore((int) (delay-(System.currentTimeMillis()-start)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    TimeUnit.MILLISECONDS.sleep(1);
                }

                resetColorToTeamColor(panneau);
                Log.STRATEGY.warning("Couleur: "+panneau.getTeamColor());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void resetColorToTeamColor(Panneau panneau) {
        //FIXME: ces opérations sont censée être déjà réalisées dans la lib du panneau
        switch (panneau.getTeamColor()) {
            case JAUNE:
                panneau.setLeds(Panneau.LedColor.JAUNE);
                break;

            case BLEU:
                panneau.setLeds(Panneau.LedColor.BLEU);
                break;
        }
    }
    protected void initServices(HLInstance hl, Class<? extends Robot> robotClass, Class<? extends ScriptManager> scriptManagerClass) {
        try {
            // trouve la couleur
            panneauService = hl.module(PanneauModule.class);
            if(hl.getConfig().getBoolean(ConfigData.USING_PANEL)) {
                if(panneauService.getPanneau() != null) {
                    if(panneauService.getPanneau().getTeamColor()== Panneau.TeamColor.BLEU) {
                        hl.getConfig().override(ConfigData.COULEUR, "bleu");
                    } else {
                        hl.getConfig().override(ConfigData.COULEUR, "jaune");
                    }
                } else {
                    Log.STRATEGY.critical("PAS DE PANNEAU");
                }
            } else {
                // FIXME
                // FIXME
                // FIXME
                // FIXME    PREVOIR QUELQUE CHOSE POUR NE PAS LANCER LE ROBOT DANS CE MODE POUR UN MATCH
                // FIXME
                // FIXME
                // FIXME
                // FIXME
                panneauService.setPanel(null);
                Log.STRATEGY.critical("PAS DE PANNEAU");
            }
//            container.getConfig().override(ConfigData.COULEUR, "jaune");
            Log.STRATEGY.debug("Couleur: "+ hl.getConfig().get(ConfigData.COULEUR));
            scriptManager = hl.module(scriptManagerClass);
            connectionManager = hl.module(ConnectionManager.class);
            orderWrapper = hl.module(OrderWrapper.class);
            listener = hl.module(Listener.class);
            dataController = hl.module(DataController.class);
            table = hl.module(Table.class);
            table.initObstacles();
            lidarController = hl.module(LidarController.class);
            robot = hl.module(robotClass);
            ai = hl.module(AIModule.class);
            MatchTimer timer = hl.module(MatchTimer.class);
            timer.start();
        } catch (ContainerException e) {
            e.printStackTrace();
        }
    }

}

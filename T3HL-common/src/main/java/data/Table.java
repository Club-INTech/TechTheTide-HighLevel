/**
 * Copyright (c) 2018, INTech.
 * this file is part of INTech's HighLevel.
 * <p>
 * INTech's HighLevel is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * INTech's HighLevel is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with it.  If not, see <http://www.gnu.org/licenses/>.
 **/

package data;

import data.table.*;
import pfg.config.Configurable;
import utils.Log;
import utils.container.Module;
import utils.math.*;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Classe représentant la table et gérant les obstacles
 * @author rem
 */
public class Table implements Module {
    /**
     * Graphe
     */
    private Graphe graphe;

    /**
     * Liste des obstacles fixes
     */
    private final ArrayList<Obstacle> fixedObstacles;

    private final ArrayList<Obstacle> temporaryObstacles;

    /**
     * Obstacle mobile simulé
     */

    private MobileCircularObstacle simulatedObstacle;

    /**
     * Liste des obstacles mobiles. SYNCHRONISER LES ACCES!
     */
    private final ConcurrentLinkedQueue<MobileCircularObstacle> mobileObstacles;

    /**
     * Longueur de la table (en x, en mm)
     */
    @Configurable("tableX")
    private int length;

    /**
     * Longueur de la table (en y, en mm)
     */
    @Configurable("tableY")
    private int height;

    /**
     * Rayon du robot
     */
    @Configurable
    private int robotRay;

    /**
     * Rayon du buddy robot !
     */
    @Configurable
    private int buddyRay;

    /**
     * Rayon des robots adverses
     */
    @Configurable
    private int ennemyRay;

    /**
     * Limite lorque l'on compare deux positions
     */
    @Configurable("vectorComparisonThreshold")
    private int compareThreshold;

    /**
     * Distance de marge pour les obstacles
     */
    private int obstacleMargin = 20;

    // ====================================================================================
    // |  Variables temporaires pour éviter d'instancier des millions d'objets par match  |
    // ====================================================================================

    /**
     * Liste temporaires des obstacles mobiles, utilisée pour pouvoir écrire tous les obstacles d'un coup
     */
    private final List<MobileCircularObstacle> mobileObstacleBuffer;

    /**
     * Rampes et balance (dans une variable pour que le lidar détecte pas le mat de la balance)
     */
    private StillCircularRectangularObstacle balanceAndRamps;

    @Configurable
    private boolean openTheGate;

    /**
     * Constructeur de la table
     */
    public Table() {
        this.fixedObstacles = new ArrayList<>();
        this.mobileObstacles = new ConcurrentLinkedQueue<>();
        this.mobileObstacleBuffer = new ArrayList<>();
        this.temporaryObstacles = new ArrayList<>();
    }

    /**
     * Initialisation des obstacles fixes de la table
     */
    public void initObstaclesNoInit() {
        // Bords de la table

        Vec2 vecteurTableBordDroit = new InternalVectCartesian(1500,1000);
        Rectangle formeTableBordDroit = new Rectangle(vecteurTableBordDroit, 2*robotRay,2000);
        Obstacle tableBordDroit = new StillRectangularObstacle(formeTableBordDroit);
        this.addFixedObstacleNoGraphChange(tableBordDroit);

        Vec2 vecteurTableBordGauche = new InternalVectCartesian(-1500,1000);
        Rectangle formeTableBordGauche = new Rectangle(vecteurTableBordGauche, 2*robotRay,2000);
        Obstacle tableBordGauche = new StillRectangularObstacle(formeTableBordGauche);
        this.addFixedObstacleNoGraphChange(tableBordGauche);

        Vec2 vecteurTableBordHaut = new InternalVectCartesian(0,2000);
        Rectangle formeTableBordHaut = new Rectangle(vecteurTableBordHaut, 3000,2*robotRay);
        Obstacle tableBordHaut = new StillRectangularObstacle(formeTableBordHaut);
        this.addFixedObstacleNoGraphChange(tableBordHaut);

        Vec2 vecteurTableBordBas = new InternalVectCartesian(0,0);
        Rectangle formeTableBordBas = new Rectangle(vecteurTableBordBas, 3000,2*robotRay);
        Obstacle tableBordBas = new StillRectangularObstacle(formeTableBordBas);
        this.addFixedObstacleNoGraphChange(tableBordBas);

        //Verres

        Vec2 positionVerre1 = new VectCartesian(300, 400);
        Circle formeVerre1 = new Circle(positionVerre1,36 + robotRay);
        Obstacle verre1 = new StillCircularObstacle(formeVerre1);
        this.addTemporaryObstacle(verre1);

        Vec2 positionVerre2 = new VectCartesian(300, 1200);
        Circle formeVerre2 = new Circle(positionVerre2,36 + robotRay);
        Obstacle verre2 = new StillCircularObstacle(formeVerre2);
        this.addTemporaryObstacle(verre2);

        Vec2 positionVerre3 = new VectCartesian(450, 510);
        Circle formeVerre3 = new Circle(positionVerre3,36 + robotRay);
        Obstacle verre3 = new StillCircularObstacle(formeVerre3);
        this.addTemporaryObstacle(verre3);

        Vec2 positionVerre4 = new VectCartesian(450, 1080);
        Circle formeVerre4 = new Circle(positionVerre4,36 + robotRay);
        Obstacle verre4 = new StillCircularObstacle(formeVerre4);
        this.addTemporaryObstacle(verre4);

        Vec2 positionVerre5 = new VectCartesian(670, 100);
        Circle formeVerre5 = new Circle(positionVerre5,36 +robotRay);
        Obstacle verre5 = new StillCircularObstacle(formeVerre5);
        this.addTemporaryObstacle(verre5);

        Vec2 positionVerre6 = new VectCartesian(950, 400);
        Circle formeVerre6 = new Circle(positionVerre6,36 + robotRay);
        Obstacle verre6 = new StillCircularObstacle(formeVerre6);
        this.addTemporaryObstacle(verre6);

        Vec2 positionVerre7 = new VectCartesian(1005, 1955);
        Circle formeVerre7 = new Circle(positionVerre7,36 +robotRay);
        Obstacle verre7 = new StillCircularObstacle(formeVerre7);
        this.addTemporaryObstacle(verre7);

        Vec2 positionVerre8 = new VectCartesian(1065, 1650);
        Circle formeVerre8 = new Circle(positionVerre8,36+ robotRay);
        Obstacle verre8 = new StillCircularObstacle(formeVerre8);
        this.addTemporaryObstacle(verre8);

        Vec2 positionVerre9 = new VectCartesian(1100, 800);
        Circle formeVerre9 = new Circle(positionVerre9,36+robotRay);
        Obstacle verre9 = new StillCircularObstacle(formeVerre9);
        this.addTemporaryObstacle(verre9);

        Vec2 positionVerre10 = new VectCartesian(1270, 1200);
        Circle formeVerre10 = new Circle(positionVerre10,36+robotRay);
        Obstacle verre10 = new StillCircularObstacle(formeVerre10);
        this.addTemporaryObstacle(verre10);

        Vec2 positionVerre11 = new VectCartesian(3000-1665, 1650);
        Circle formeVerre11 = new Circle(positionVerre11,36+robotRay);
        Obstacle verre11 = new StillCircularObstacle(formeVerre11);
        this.addTemporaryObstacle(verre11);

        Vec2 positionVerre12 = new VectCartesian(3000-1605, 1955);
        Circle formeVerre12 = new Circle(positionVerre12,36+robotRay);
        Obstacle verre12 = new StillCircularObstacle(formeVerre12);
        this.addTemporaryObstacle(verre12);

        Vec2 positionVerre13 = new VectCartesian(1605, 1955);
        Circle formeVerre13 = new Circle(positionVerre13,36 +robotRay);
        Obstacle verre13 = new StillCircularObstacle(formeVerre13);
        this.addTemporaryObstacle(verre13);

        Vec2 positionVerre14 = new VectCartesian(1665, 1650);
        Circle formeVerre14 = new Circle(positionVerre14,36 +robotRay);
        Obstacle verre14 = new StillCircularObstacle(formeVerre14);
        this.addTemporaryObstacle(verre14);

        Vec2 positionVerre15 = new VectCartesian(1730, 1200);
        Circle formeVerre15 = new Circle(positionVerre15,36 +robotRay);
        Obstacle verre15 = new StillCircularObstacle(formeVerre15);
        this.addTemporaryObstacle(verre15);

        Vec2 positionVerre16 = new VectCartesian(1900, 800);
        Circle formeVerre16 = new Circle(positionVerre16,36 +robotRay);
        Obstacle verre16 = new StillCircularObstacle(formeVerre16);
        this.addTemporaryObstacle(verre16);

        Vec2 positionVerre17 = new VectCartesian(1935, 1650);
        Circle formeVerre17 = new Circle(positionVerre17,36 +robotRay);
        Obstacle verre17 = new StillCircularObstacle(formeVerre17);
        this.addTemporaryObstacle(verre17);

        Vec2 positionVerre18 = new VectCartesian(1995, 1955);
        Circle formeVerre18 = new Circle(positionVerre18,36 +robotRay);
        Obstacle verre18 = new StillCircularObstacle(formeVerre18);
        this.addTemporaryObstacle(verre18);

        Vec2 positionVerre19 = new VectCartesian(2050, 400);
        Circle formeVerre19 = new Circle(positionVerre19,36 +robotRay);
        Obstacle verre19 = new StillCircularObstacle(formeVerre19);
        this.addTemporaryObstacle(verre19);

        Vec2 positionVerre20 = new VectCartesian(2330, 100);
        Circle formeVerre20 = new Circle(positionVerre20,36 +robotRay);
        Obstacle verre20 = new StillCircularObstacle(formeVerre20);
        this.addTemporaryObstacle(verre20);

        Vec2 positionVerre21 = new VectCartesian(2550, 510);
        Circle formeVerre21 = new Circle(positionVerre21,36 +robotRay);
        Obstacle verre21 = new StillCircularObstacle(formeVerre21);
        this.addTemporaryObstacle(verre21);

        Vec2 positionVerre22 = new VectCartesian(2550, 1080);
        Circle formeVerre22 = new Circle(positionVerre22,36 +robotRay);
        Obstacle verre22 = new StillCircularObstacle(formeVerre22);
        this.addTemporaryObstacle(verre22);

        Vec2 positionVerre23 = new VectCartesian(2700, 400);
        Circle formeVerre23 = new Circle(positionVerre23,36 +robotRay);
        Obstacle verre23 = new StillCircularObstacle(formeVerre23);
        this.addTemporaryObstacle(verre23);

        Vec2 positionVerre24 = new VectCartesian(2700, 1200);
        Circle formeVerre24 = new Circle(positionVerre24,36 +robotRay);
        Obstacle verre24 = new StillCircularObstacle(formeVerre24);
        this.addTemporaryObstacle(verre24);


        //Tasseaux

        Vec2 vecteurPetitTasseauBleu = new VectCartesian(900,1925);
        Rectangle formePetitTasseauBleu = new Rectangle(vecteurPetitTasseauBleu, 2*robotRay + 22,150 + 2*robotRay);
        Obstacle petitTasseauBleu = new StillRectangularObstacle(formePetitTasseauBleu);
        this.addFixedObstacleNoGraphChange(petitTasseauBleu);

        Vec2 vecteurPetitTasseauJaune = new VectCartesian(2100,1925);
        Rectangle formePetitTasseauJaune = new Rectangle(vecteurPetitTasseauJaune, 2*robotRay + 22,150 + 2*robotRay);
        Obstacle petitTasseauJaune = new StillRectangularObstacle(formePetitTasseauJaune);
        this.addFixedObstacleNoGraphChange(petitTasseauJaune);

        Vec2 vecteurGrandTasseau = new VectCartesian(1500,1850);
        Rectangle formeGrandTasseau = new Rectangle(vecteurGrandTasseau, 2*robotRay + 22,300 + 2*robotRay);
        Obstacle grandTasseau= new StillRectangularObstacle(formeGrandTasseau);
        this.addFixedObstacleNoGraphChange(grandTasseau);

    }

    /**
     * Initialisation des obstacles fixes de la table
     */
    public void initObstacles() {
        initObstaclesNoInit();
        this.updateTableAfterFixedObstaclesChanges();
    }

    /**
     * Met à jour la table à partir d'une liste de points représentant le centre les obstacles détectés
     * @param points    liste des centres des obstacles
     */
    public void updateMobileObstacles(List<Vec2> points) {
        MobileCircularObstacle obstacle;
        Vec2 point;
      //  Log.LIDAR.debug("Mise à jour des Obstacle...");

        mobileObstacleBuffer.clear();
        synchronized (mobileObstacles) {
            Iterator<MobileCircularObstacle> mobileObstacleIterator = mobileObstacles.iterator();

            while (mobileObstacleIterator.hasNext()) {
                obstacle = mobileObstacleIterator.next();
                Iterator<Vec2> pointIterator = points.iterator();
                while (pointIterator.hasNext()) {
                    point = pointIterator.next();
                    if (obstacle.isInObstacle(point)) {
                        obstacle.update(point);
                   //     Log.LIDAR.debug("MàJ de l'obstacle mobile : " + obstacle);
                        pointIterator.remove();
                    }
                }
                if (obstacle.getOutDatedTime() < System.currentTimeMillis()) {
            //        Log.LIDAR.debug("Mort de l'obstacle mobile : " + obstacle);
                    mobileObstacleIterator.remove();
                }
            }
        }

        for (Vec2 pt : points) {
            int ray = ennemyRay;
            if (pt.distanceTo(XYO.getBuddyInstance().getPosition()) < compareThreshold) {
                ray = buddyRay;
            }
            MobileCircularObstacle obst = new MobileCircularObstacle(pt, ray+robotRay);
     //       Log.LIDAR.debug("Obstacle mobile ajouté : " + obst);
            mobileObstacleBuffer.add(obst);
        }

        synchronized (mobileObstacles) {
            mobileObstacles.addAll(mobileObstacleBuffer); // on envoie tout d'un coup
        }

        if (this.graphe != null) {
            try {
                //this.graphe.writeLock().lock();
                this.graphe.update();
                this.graphe.setUpdated(true);
            } catch (ConcurrentModificationException e) {
                e.printStackTrace(); // eh
            } finally {
                //this.graphe.writeLock().unlock();
            }
        } else {
       //     Log.LIDAR.warning("Graphe non instancié");
        }
        Log.LIDAR.debug("Mise à jour des obstacles terminées");
    }

    /**
     * @param point  position à tester
     * @return true si le point est dans un obstacle fixe
     */
    public boolean isPositionInFixedObstacle(Vec2 point) {
        return isPositionInFixedObstacle(point, true);
    }

    /**
     * @param point  position à tester
     * @param checkTemporary on vérifie aussi les obstacles temporaires?
     * @return true si le point est dans un obstacle fixe
     */
    public boolean isPositionInFixedObstacle(Vec2 point, boolean checkTemporary) {
        Iterator<Obstacle> iterator = fixedObstacles.iterator();
        Obstacle obstacle;
        while (iterator.hasNext()) {
            obstacle = iterator.next();
            if (obstacle.isInObstacle(point)) {
                return true;
            }
        }

        if(checkTemporary) {
            iterator = temporaryObstacles.iterator();
            while (iterator.hasNext()) {
                obstacle = iterator.next();
                if (obstacle.isInObstacle(point)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Trouve l'obstacle potentiel dans la position
     * @param point la position à tester
     * @return <pre>Optional.empty()</pre> s'il n'y a aucun obstacle, <pre>Optional.of(some)</pre> avec <pre>some</pre> un obstacle s'il y en a un
     */
    public Optional<MobileCircularObstacle> findMobileObstacleInPosition(Vec2 point) {
        Iterator<MobileCircularObstacle> iterator = mobileObstacles.iterator();
        MobileCircularObstacle obstacle;
        while (iterator.hasNext()) {
            obstacle = iterator.next();
            if (obstacle.isInObstacle(point)) {
                return Optional.of(obstacle);
            }
        }
        return Optional.empty();
    }

    /**
     * Trouve l'obstacle potentiel dans la position
     * @param point la position à tester
     * @return <pre>Optional.empty()</pre> s'il n'y a aucun obstacle, <pre>Optional.of(some)</pre> avec <pre>some</pre> un obstacle s'il y en a un
     */
    public Optional<Obstacle> findFixedObstacleInPosition(Vec2 point) {
        Iterator<Obstacle> iterator = fixedObstacles.iterator();
        Obstacle obstacle;
        while (iterator.hasNext()) {
            obstacle = iterator.next();
            if (obstacle.isInObstacle(point)) {
                return Optional.of(obstacle);
            }
        }

        iterator = temporaryObstacles.iterator();
        while (iterator.hasNext()) {
            obstacle = iterator.next();
            if (obstacle.isInObstacle(point)) {
                return Optional.of(obstacle);
            }
        }
        return Optional.empty();
    }

    /**
     * @param point position à tester
     * @return true si le point est dans un obstacle mobile
     */
    public boolean isPositionInMobileObstacle(Vec2 point) {
        Iterator<MobileCircularObstacle> iterator = mobileObstacles.iterator();
        MobileCircularObstacle obstacle;
        synchronized (mobileObstacles) {
            while (iterator.hasNext()) {
                obstacle = iterator.next();
                if (obstacle.isInObstacle(point)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Sert à savoir si un segment intersecte l'un des obstacles
     * @param segment   segment à tester
     * @return true si le segment intersecte l'un des obstacles fixes
     */
    public boolean intersectAnyFixedObstacle(Segment segment) {
        return intersectObstacle(segment, fixedObstacles);
    }

    /**
     * Sert à savoir si un segment intersecte l'un des obstacles mobiles
     * @param segment   segment à tester
     * @return true si le segment intersecte l'un des obstacles mobiles
     */
    public boolean intersectAnyMobileObstacle(Segment segment) {
        return intersectObstacle(segment, mobileObstacles);
    }

    /**
     * @param segment   segment à tester
     * @param obstacles liste d'obstacles à tester
     * @return true si le segment intersecte l'un des obstacles
     */
    private boolean intersectObstacle(Segment segment, Collection<? extends Obstacle> obstacles) {
        Iterator<? extends Obstacle> iterator = obstacles.iterator();
        Obstacle obstacle;
        synchronized (obstacles) {
            while (iterator.hasNext()) {
                obstacle = iterator.next();
                if (obstacle.intersect(segment)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Sert à savoir si un cercle intersecte l'un des obstacles mobiles
     * @param circle   cercle à tester
     * @return true si le cercle intersecte l'un des obstacles mobiles
     */
    public boolean intersectAnyMobileObstacle(Circle circle) {
        Iterator<MobileCircularObstacle> iterator = mobileObstacles.iterator();
        MobileCircularObstacle obstacle;
        synchronized (mobileObstacles) {
            while (iterator.hasNext()) {
                obstacle = iterator.next();
                if (obstacle.getPosition().distanceTo(circle.getCenter()) < ((Circle) obstacle.getShape()).getRadius() + circle.getRadius()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void addFixedObstacleNoGraphChange(Obstacle obstacle) {
        if (obstacle instanceof MobileCircularObstacle) {
            throw new IllegalArgumentException("L'obstacle ajouté n'est pas fixe !");
        }
        synchronized (this.fixedObstacles) {
            this.fixedObstacles.add(obstacle);
            Log.TABLE.debug("ajout de l'obstacle " + obstacle);
        }
    }

    /**
     * Ajoute un obstacle fixe à la table et met à jour le graphe
     * ATTENTION : méthode coûteuse car le graphe doit être recalculé
     * @param obstacle  nouvel obstacle
     */
    public void addFixedObstacle(Obstacle obstacle) {
        addFixedObstacleNoGraphChange(obstacle);
        updateTableAfterFixedObstaclesChanges();
    }

    public void updateTableAfterFixedObstaclesChanges() {
        if (this.graphe != null) {
            try {
                //this.graphe.writeLock().lock();
                this.graphe.reInit();
            } finally {
                //this.graphe.writeLock().unlock();
            }
        } else {
            Log.LIDAR.warning("Graphe non instancié");
        }
    }

    /**
     * Retire un obstacle fixe de la table sans metre à jour le graphe
     * @param obstacle
     */
    public void removeFixedObstacleNoReInit(Obstacle obstacle) {
        if (obstacle instanceof MobileCircularObstacle) {
            throw new IllegalArgumentException("L'obstacle ajouté n'est pas fixe !");
        }
        synchronized(this.fixedObstacles) {
            this.fixedObstacles.remove(obstacle);
        }
        Log.TABLE.debug("suppression de l'obstacle " + obstacle);
    }

    /**
     * Retire un obstacle fixe de la table et met à jour le graphe
     * ATTENTION : méthode coûteuse car le graphe doit être recalculé
     * @param obstacle  obstacle à retirer
     */
    public void removeFixedObstacle(Obstacle obstacle) {
        removeTemporaryObstacle(obstacle);
        if (graphe != null) {
            this.graphe.reInit();
        } else {
            Log.LIDAR.warning("Graphe non instancié");
        }
    }

    /**
     * Ajoutes un obstacle temporaire sur la table (ex: palets de la zone de départ qui peuvent être retirés)
     * @param obstacle L'obstacle à retirer
     */
    public void addTemporaryObstacle(Obstacle obstacle) {
        synchronized (temporaryObstacles) {
            temporaryObstacles.add(obstacle);
        }
    }

    /**
     * Retires un obstacle temporaire de la table
     * @param obstacle
     */
    public void removeTemporaryObstacle(Obstacle obstacle) {
        synchronized (temporaryObstacles) {
            temporaryObstacles.remove(obstacle);
        }
    }

    public boolean removeAnyIntersectedMobileObstacle(Vec2 center, int ray) {
        Iterator<MobileCircularObstacle> iterator = mobileObstacles.iterator();
        MobileCircularObstacle obstacle;
        int i=0;
        synchronized (mobileObstacles) {
            while (iterator.hasNext()) {
                obstacle = iterator.next();
                if (obstacle.getPosition().distanceTo(center) < ((Circle) obstacle.getShape()).getRadius() + ray) {
                    removeTemporaryObstacle(obstacle);
                    i=1;
                }
            }
        }
        if(i==1){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Ajoute l'obstacle mobile SIMULÉ à la liste des obstacles mobiles
     */
    public void SIMULATEDaddMobileObstacle() {
        this.simulatedObstacle = new MobileCircularObstacle(new InternalVectCartesian(0, -1000), ennemyRay +robotRay);
        this.simulatedObstacle.setLifeTime(100000);
        this.mobileObstacles.add(this.simulatedObstacle);
    }

    /**
     * Déplace l'obstacle mobile SIMULÉ à la table
     * @param newPosition nouvelle position de l'obstacle SIMULÉ
     */
    public void SIMULATEDmoveMobileObstacle(Vec2 newPosition) {
        if (this.graphe != null) {
            try {
                //this.graphe.writeLock().lock();
                this.simulatedObstacle.update(newPosition);
                this.graphe.update();
                this.graphe.setUpdated(true);
            } catch (ConcurrentModificationException e) {
                e.printStackTrace(); // eh
            } finally {
                //this.graphe.writeLock().unlock();
            }
        } else {
            Log.LIDAR.warning("Graphe non instancié");
        }
    }


    /**
     * Getters & Setters
     */
    public Graphe getGraphe() {
        return this.graphe;
    }

    public void setGraphe(Graphe graphe) {
        this.graphe = graphe;
    }

    public ArrayList<Obstacle> getFixedObstacles() {
        return fixedObstacles;
    }

    public ArrayList<Obstacle> getTemporaryObstacles() {
        return temporaryObstacles;
    }

    public ConcurrentLinkedQueue<MobileCircularObstacle> getMobileObstacles() {
        return mobileObstacles;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return height;
    }

    public MobileCircularObstacle getSimulatedObstacle() {
        return simulatedObstacle;
    }

    public boolean isPositionInBalance(Vec2 center) {
        return Math.abs(center.getX()) < 1030 && center.getY() > 2000-400;
    }

    public void removeAllTemporaryObstacles() {
        synchronized (temporaryObstacles) {
            temporaryObstacles.clear();
        }
    }
}

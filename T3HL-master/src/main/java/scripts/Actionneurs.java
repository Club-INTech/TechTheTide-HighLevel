package scripts;

import data.XYO;
import locomotion.UnableToMoveException;
import pfg.config.Configurable;
import utils.HLInstance;
import utils.math.Vec2;
import utils.math.VectCartesian;

import java.util.concurrent.TimeUnit;

public class Actionneurs {


// @author Pierre, last modification 14/01/20

    public class ScriptRecupGobeletsNonAgressive extends Script {
        @Configurable
        private int robotRay;
        /**
         * Construit un script
         *
         * @param hl le container
         */
        protected ScriptRecupGobeletsNonAgressive(HLInstance hl) {
            super(hl);
        }

        @Override
        public void execute(int version) {



            async("Parallel action", () -> {

                while(true){
                    table.removeAnyIntersectedTemporaryObstacle(XYO.getRobotInstance().getPosition());
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            try {




                turnTowards(-Math.PI/2);
                //225 640
                moveLengthwise(185,false);
                //225 455
                turnTowards(0);
                moveLengthwise(225,false);
                //430 455
                turnTowards(-Math.PI/2);
                moveLengthwise(275,false);
                //450 180
                turnTowards(0);
                moveLengthwise(575,false);
                //1025 180
                turnTowards(Math.PI/2);
                moveLengthwise(920,false);
                //1025 1100
                turnTowards(0);
                moveLengthwise(775,false);
                //1800 1100
                turnTowards(Math.PI/2);
                if(table.isPositionInMobileObstacle(new VectCartesian(1800,1300))){
                    //On regarde s'il est toujours au bout de 2,5 secondes
                    for(int i=0;i<4;i++){
                        TimeUnit.MILLISECONDS.sleep(2500);
                        try {
                            moveLengthwise(700, false);
                            //1800 1800
                            moveLengthwise(-500, false);
                            //1800 1300
                            break;
                        } catch (UnableToMoveException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else {

                    moveLengthwise(700, false);
                    //1800 1800
                    moveLengthwise(-500, false);
                    //1800 1300


                }
                followPathTo(new VectCartesian(670,1300));
                //670 1300




            } catch (UnableToMoveException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public Vec2 entryPosition(int version) { return new VectCartesian(225,640); }

        @Override
        public void finalize(Exception e) {

        }
    }

}

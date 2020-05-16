package traitementLogs.LogsCommunications.ComBuddy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexComBuddy {

    static public void regexComBuddy (String log) {
        Matcher position = Pattern.compile("confirmOrder cxyo").matcher(log);
        if (position.find()){
            String ordercxyo = log.substring(149);
            //System.out.println("ordercxyo = " + ordercxyo);
            //TODO : mettre la position sur le simulateur

        }
        else {
            int icrochet = log.substring(3).indexOf("]") ;
            String action = log.substring(3).substring(icrochet + 2 );
          //  System.out.println("action = " + action);
        }
    }
}


package traitementLogs.LogsDeplacement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDeplacement {

    static public void regexDeplacement (String log) {

        Matcher MoveLengthwise = Pattern.compile("Move lengthwise").matcher(log);
        Matcher EndofMoveLengthwise = Pattern.compile("End of move lengthwise").matcher(log);

        if (MoveLengthwise.find()) {
            int iend = log.substring(125).indexOf("\u001B") ; // ! \\ ATTENTION: après la distance, dans le log y a un caractère spécial (voir les logs sous gedit)
            String distance = log.substring(125).substring(0, iend);
            int d = Integer.parseInt(distance);
             System.out.println("d= " +d);
        }

        else if (EndofMoveLengthwise.find()) {


        }
    }

}

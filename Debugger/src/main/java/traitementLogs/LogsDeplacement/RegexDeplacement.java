package traitementLogs.LogsDeplacement;

import graphique.Robot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDeplacement {

    static public void regexDeplacement (String log) {
        Matcher MoveLengthwise = Pattern.compile("Move lengthwise").matcher(log);
        if (MoveLengthwise.find()) {
            Robot.Move(log);
        }
    }

    static public int MoveLengthWiseFonction (String log) {
        int iend = log.substring(125).indexOf("\u001B") ; // ! \\ ATTENTION: après la distance, dans le log y a un caractère spécial (voir les logs sous gedit)
        String distance = log.substring(125).substring(0, iend);
        return Integer.parseInt(distance);
    }
}

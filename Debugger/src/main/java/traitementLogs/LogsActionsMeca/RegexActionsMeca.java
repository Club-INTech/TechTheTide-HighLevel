package traitementLogs.LogsActionsMeca;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexActionsMeca {

    static public void regexActions(String log) {
        Matcher turnTowards = Pattern.compile("Sent to LL: t").matcher(log);
         if (turnTowards.find()) {
         int iend = log.substring(111).indexOf("\u001B") ; // ! \\ ATTENTION: après la valeur de rotation, dans le log y a un caractère spécial (voir les logs sous gedit)
         String turn = log.substring(111).substring(0, iend);
         double t = Double.parseDouble(turn);
         System.out.println("t= " + t);
         }
    }
}

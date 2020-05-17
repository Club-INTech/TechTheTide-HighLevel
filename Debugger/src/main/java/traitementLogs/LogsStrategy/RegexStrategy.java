package traitementLogs.LogsStrategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexStrategy {
    static public void regexStrategy (String log) {
        Matcher Configuration = Pattern.compile("couleur").matcher(log);

        if (Configuration.find()) {
            //String couleur = log.substring(156);
            //System.out.println("couleur = " + couleur);
        }
    }
}

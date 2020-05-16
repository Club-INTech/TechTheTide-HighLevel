package traitementLogs.LogsCommunications.ComNUC;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexComNuc {

    static public void regexComNuc (String log) {
        Matcher Configuration = Pattern.compile("Configuration de l'ecueil commun :").matcher(log);

        if (Configuration.find()) {
            String compo = log.substring(156);
            System.out.println("composition = " + compo);
                if (compo == "RVRVV"){
                    System.out.println("RVRVV");
                }
                else if (compo == "RVVRV"){
                    System.out.println("RVVRV");
                }
                else if (compo == "RRVVV"){
                    System.out.println("RRVVV");
                }
                else if (compo == "VRRVR"){
                    System.out.println("VRRVR");
                }
                else if (compo == "VRVRR"){
                    System.out.println("VRVRR");
                }
                else if(compo == "VVRRR"){
                    System.out.println("VVRRR");
                }
                else{
                    System.out.println("erreur: composition impossible");
                }
        }
    }
}


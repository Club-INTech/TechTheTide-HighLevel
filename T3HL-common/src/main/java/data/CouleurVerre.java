package data;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public enum CouleurVerre {
    VERT(),
    ROUGE(),

    PAS_DE_VERRE(),
    ;




    static CompletableFuture<CouleurVerre> couleurVerRecue;
    //static CouleurPalet couleurVerRecue;

    static {
        couleurVerRecue = new CompletableFuture<>();
    }

    public static void setCouleurVerRecu(String couleurRecue) {
        switch (couleurRecue) {
            case "vert":
                couleurVerRecue.complete(CouleurVerre.VERT);
                break;
            case "rouge":
                couleurVerRecue.complete(CouleurVerre.ROUGE);
                break;
            default:
                couleurVerRecue.complete(CouleurVerre.PAS_DE_VERRE);
                break;
        }
    }

    public static CouleurVerre getCouleurVerRecu(){
        CouleurVerre couleurVerre = CouleurVerre.PAS_DE_VERRE;
        try {
            couleurVerre = couleurVerRecue.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return couleurVerre ;
    }


}


package data;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public enum CouleurVerre {
    VERT("v"),
    ROUGE("r"),
    RANDOM("a"),
    PAS_DE_VERRE(""),

    ;
    //Nom du gobelet
    private String nom;

    CouleurVerre(String nom){
        this.nom = nom;
    }

    static CompletableFuture<CouleurVerre> couleurGobRecue;
     //  static CouleurVerre couleurGobRecue;

    static {
        couleurGobRecue = new CompletableFuture<>();
    }

    public static void setCouleurGobRecu(String couleurRecue) {
        switch (couleurRecue) {
            case "vert":
                couleurGobRecue.complete(CouleurVerre.VERT);
                break;
            case "rouge":
                couleurGobRecue.complete(CouleurVerre.ROUGE);
                break;
            case "inconnu":
                couleurGobRecue.complete(CouleurVerre.RANDOM);
                break;
            default:
                couleurGobRecue.complete(CouleurVerre.PAS_DE_VERRE);
                break;
        }
    }

    public static CouleurVerre getCouleurGobRecu(){
        CouleurVerre couleurVerre = CouleurVerre.PAS_DE_VERRE;
        try {
            couleurVerre = couleurGobRecue.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return couleurVerre ;
    }

    public String getNom(){
        return this.nom;
    }
}

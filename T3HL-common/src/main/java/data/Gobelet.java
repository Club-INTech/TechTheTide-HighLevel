package data;

public enum Gobelet {

    ROUGE_1_ZONE_DEPART_US(0,CouleurVerre.ROUGE, false,false),
    ROUGE_2_ZONE_DEPART_US(1,CouleurVerre.ROUGE, false,false),
    VERT_1_ZONE_DEPART_US(2,CouleurVerre.VERT, false,false),
    VERT_2_ZONE_DEPART_US(3,CouleurVerre.VERT, false,false),

    ROUGE_1_PETIT_PORT_US(4,CouleurVerre.ROUGE, false,false),
    VERT_1_PETIT_PORT_US(5,CouleurVerre.VERT, false,false),
//    ROUGE_2_PETIT_PORT_US(5,CouleurVerre.ROUGE, false,false),
//    VERT_2_PETIT_PORT_US(7,CouleurVerre.VERT, false,false),

    ROUGE_1_CENTRE_US(6,CouleurVerre.ROUGE,false,false),
    ROUGE_2_CENTRE_US(7,CouleurVerre.ROUGE,false,false),
    VERT_1_CENTRE_US(8,CouleurVerre.VERT,false,false),
    VERT_2_CENTRE_US(9,CouleurVerre.VERT,false,false),

    ROUGE_1_CENTRE_ENNEMI(10,CouleurVerre.ROUGE,false,false),
    ROUGE_2_CENTRE_ENNEMI(11,CouleurVerre.ROUGE,false,false),
    VERT_1_CENTRE_ENNEMI(12,CouleurVerre.VERT,false,false),
    VERT_2_CENTRE_ENNEMI(13,CouleurVerre.VERT,false,false),

    VERT_1_ECUEIL_US(14,CouleurVerre.VERT, false,false),
    ROUGE_1_ECUEIL_US(15,CouleurVerre.ROUGE, false,false),
    VERT_2_ECUEIL_US(16,CouleurVerre.VERT, false,false),
    ROUGE_2_ECUEIL_US(17,CouleurVerre.ROUGE, false,false),
    VERT_3_ECUEIL_US(18,CouleurVerre.VERT, false,false),

    ROUGE_1_ECUEIL_COMMUN_US(19,CouleurVerre.ROUGE, false,false),
    RANDOM_1_ECUEIL_COMMUN_US(20,CouleurVerre.RANDOM, false,false),
    RANDOM_2_ECUEIL_COMMUN_US(21,CouleurVerre.RANDOM, false,false),
    RANDOM_3_ECUEIL_COMMUN_US(22,CouleurVerre.RANDOM, false,false),
    VERT_5_ECUEIL_COMMUN_US(23,CouleurVerre.VERT, false,false),

    ROUGE_1_ECUEIL_COMMUN_ENNEMI(24,CouleurVerre.ROUGE, false,false),
    RANDOM_1_ECUEIL_COMMUN_ENNEMI(25,CouleurVerre.RANDOM, false,false),
    RANDOM_2_ECUEIL_COMMUN_ENNEMI(26,CouleurVerre.RANDOM, false,false),
    RANDOM_3_ECUEIL_COMMUN_ENNEMI(27,CouleurVerre.RANDOM, false,false),
    VERT_5_ECUEIL_COMMUN_ENNEMI(28,CouleurVerre.VERT, false,false),

    ;
    private int id;
    private CouleurVerre couleur;
    private boolean verre_pris;
    private boolean verre_marque;

    /**
     * Constructeur
     * @param id id du verre
     * @param couleur couleur du verre
     * @param verre_pris si le verre a été pris (ie enlevé de son emplacement de base)
     * @param verre_marque si le verre a été marqué (ie des points ont été marqué grâce à ce palet)
     */

    Gobelet(int id, CouleurVerre couleur, boolean verre_pris , boolean verre_marque) {
        this.couleur = couleur;
        this.verre_pris = verre_pris;
        this.verre_marque = verre_marque;
        this.id = id;
    }

    /**
     * Renvoie l'id du verre
     * @return renvoie l'id du verre
     */
    public int getId(){
        return this.id;
    }

    /**
     * Renvoie la couleur du verre
     * @return renvoie un objet CouleurVerre correspondant à la couleur du gobelet
     */
    public CouleurVerre getCouleur(){
        return this.couleur;
    }

    /**
     * Renvoie le verre correspondant à l'id donné
     * @param id id du verre à rechercher
     * @return renvoie le verre correspondant à l'id, ou null si aucun verre n'est trouvé
     */
    public static Gobelet getVerreById(int id){
        for (Gobelet verre : values()){
            if (verre.id == id){
                return verre;
            }
        }
        return null;
    }

    /**
     * Renvoie si le verre a été pris
     * @return true si le verre a été pris, false sinon
     */
    public boolean getVerrePris(){ return this.verre_pris; }

    /**
     * Set si le verre a été pris
     * @param valeur true si le verre a été pris, false sinon
     */
    public void setVerrePris(boolean valeur){ this.verre_pris = valeur; }

    /**
     * Renvoie si le verre a été marqué
     * @return true si le verre a été marqué, false sinon
     */
    public boolean getVerreMarque(){ return this.verre_marque; }

    /**
     * Set si le verre a été marqué
     * @param valeur true si le verre a été marqué, false sinon
     */
    public void setVerreMarque(boolean valeur){ this.verre_marque =valeur; }

}

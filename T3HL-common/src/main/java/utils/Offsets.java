package utils;

import pfg.config.BaseConfigInfo;
import pfg.config.Config;
import pfg.config.ConfigInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Liste des offsets disponibles pour les scripts
 * @author mikail, jglrxavpok
 */
public interface Offsets {

    /*
        ATTENTION! S'il y a des valeurs dans config.txt, les valeurs en dessous seront écrasées! (Ce sont des valeurs par défaut)
     */


    /*      MASTER      */
    ConfigInfo<Integer> GOBELETS_PORT_X_JAUNE_RED_SUP = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> GOBELETS_PORT_Y_JAUNE_RED_SUP = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> GOBELETS_PORT_X_JAUNE_VERT_SUP = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> GOBELETS_PORT_Y_JAUNE_VERT_SUP = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> GOBELETS_PORT_X_JAUNE_RED_INF = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> GOBELETS_PORT_Y_JAUNE_RED_INF = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> GOBELETS_PORT_X_JAUNE_VERT_INF = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> GOBELETS_PORT_Y_JAUNE_VERT_INF = new BaseConfigInfo<>(1, Integer.TYPE);

    ConfigInfo<Integer> GOBELETS_PORT_X_BLEU_VERT_SUP = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> GOBELETS_PORT_Y_BLEU_VERT_SUP = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> GOBELETS_PORT_X_BLEU_RED_SUP = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> GOBELETS_PORT_Y_BLEU_RED_SUP = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> GOBELETS_PORT_X_BLEU_VERT_INF = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> GOBELETS_PORT_Y_BLEU_VERT_INF = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> GOBELETS_PORT_X_BLEU_RED_INF = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> GOBELETS_PORT_Y_BLEU_RED_INF = new BaseConfigInfo<>(1, Integer.TYPE);

    ConfigInfo<Integer> GOBELETS_MILIEU_X_JAUNE_RED_SUP = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> GOBELETS_MILIEU_Y_JAUNE_RED_SUP = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> GOBELETS_MILIEU_X_JAUNE_VERT_SUP = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> GOBELETS_MILIEU_Y_JAUNE_VERT_SUP = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> GOBELETS_MILIEU_X_JAUNE_RED_INF = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> GOBELETS_MILIEU_Y_JAUNE_RED_INF = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> GOBELETS_MILIEU_X_JAUNE_VERT_INF = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> GOBELETS_MILIEU_Y_JAUNE_VERT_INF = new BaseConfigInfo<>(1, Integer.TYPE);

    ConfigInfo<Integer> GOBELETS_MILIEU_X_BLEU_VERT_SUP = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> GOBELETS_MILIEU_Y_BLEU_VERT_SUP = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> GOBELETS_MILIEU_X_BLEU_RED_SUP = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> GOBELETS_MILIEU_Y_BLEU_RED_SUP = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> GOBELETS_MILIEU_X_BLEU_VERT_INF = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> GOBELETS_MILIEU_Y_BLEU_VERT_INF = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> GOBELETS_MILIEU_X_BLEU_RED_INF = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> GOBELETS_MILIEU_Y_BLEU_RED_INF = new BaseConfigInfo<>(1, Integer.TYPE);

    ConfigInfo<Integer> GOBELETS_PETIT_PORT_X_JAUNE_RED = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> GOBELETS_PETIT_PORT_Y_JAUNE_RED = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> GOBELETS_PETIT_PORT_X_JAUNE_VERT = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> GOBELETS_PETIT_PORT_Y_JAUNE_VERT = new BaseConfigInfo<>(1, Integer.TYPE);

    ConfigInfo<Integer> GOBELETS_PETIT_PORT_X_BLEU_RED = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> GOBELETS_PETIT_PORT_Y_BLEU_RED = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> GOBELETS_PETIT_PORT_X_BLEU_VERT = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> GOBELETS_PETIT_PORT_Y_BLEU_VERT = new BaseConfigInfo<>(1, Integer.TYPE);

    ConfigInfo<Integer> GIROUETTE_X = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> GIROUETTE_Y = new BaseConfigInfo<>(1, Integer.TYPE);

    ConfigInfo<Integer> PHARE_X_JAUNE = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> PHARE_Y_JAUNE = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> PHARE_X_BLEU = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> PHARE_Y_BLEU = new BaseConfigInfo<>(1, Integer.TYPE);

    ConfigInfo<Integer> MANCHE1_X_JAUNE = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> MANCHE1_Y_JAUNE = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> MANCHE2_X_JAUNE = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> MANCHE2_Y_JAUNE = new BaseConfigInfo<>(1, Integer.TYPE);

    ConfigInfo<Integer> MANCHE1_X_BLEU = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> MANCHE1_Y_BLEU = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> MANCHE2_X_BLEU = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> MANCHE2_Y_BLEU = new BaseConfigInfo<>(1, Integer.TYPE);

    /*      SECONDAIRE       */

    ConfigInfo<Integer> MILIEU_ECUEIL_COMMUN_X_JAUNE = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> MILIEU_ECUEIL_COMMUN_Y_JAUNE = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> MILIEU_ECUEIL_COMMUN_X_BLEU = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> MILIEU_ECUEIL_COMMUN_Y_BLEU = new BaseConfigInfo<>(1, Integer.TYPE);

    ConfigInfo<Integer> MILIEU_ECUEIL_PRIVE_X_JAUNE = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> MILIEU_ECUEIL_PRIVE_Y_JAUNE = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> MILIEU_ECUEIL_PRIVE_X_BLEU = new BaseConfigInfo<>(1, Integer.TYPE);
    ConfigInfo<Integer> MILIEU_ECUEIL_PRIVE_Y_BLEU = new BaseConfigInfo<>(1, Integer.TYPE);

    ConfigInfo<?>[] ALL_VALUES = ConfigInfo.findAllIn(Offsets.class);
    Map<ConfigInfo<?>, Object> OFFSET_CACHE = new HashMap<>();

    static ConfigInfo<?>[] values() {
        return ALL_VALUES;
    }

    static void loadFromConfig(Config config) {
        for(ConfigInfo<?> offset : values()) {
            OFFSET_CACHE.put(offset, config.get(offset));
        }
    }

    @SuppressWarnings("unchecked cast")
    static <Type> Type get(ConfigInfo<Type> offset) {
        return (Type)OFFSET_CACHE.get(offset);
    }

}

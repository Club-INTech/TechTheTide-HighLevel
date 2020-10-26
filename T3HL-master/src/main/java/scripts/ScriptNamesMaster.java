package scripts;

import utils.HLInstance;
import utils.container.ContainerException;

public enum ScriptNamesMaster implements ScriptNames {
    HOMOLOGATION(ScriptHomologation.class, "homologation"),
    RECUP_GOBELETS_NON_AGRESSIVE(ScriptRecupGobeletsNonAgressive.class, "ScriptRecupGobeletsNonAgressive"),
    RECUPFACULTATIF(ScriptPrincipalOtherSide.class, "ScriptOtherSide"),
    RELEVER_MANCHES_A_AIR(ScriptRecupGobeletsNonAgressive.class, "ScriptReleverManchesAAir")
    ;
    private Class<? extends Script> scriptClass;
    private String scriptName;

    ScriptNamesMaster(Class<? extends Script> scriptClass, String scriptName){
        this.scriptClass = scriptClass;
        this.scriptName = scriptName;
    }

    @Override
    public Script createScript(HLInstance hl) throws ContainerException {
        return hl.module(scriptClass);
    }

    /**
     * Renvoie le nom du script
     * @return String du nom du script
     */
    public String getName(){
        return this.scriptName;
    }

}

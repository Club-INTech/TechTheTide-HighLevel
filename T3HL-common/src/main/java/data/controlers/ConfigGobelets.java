package data.controlers;

import utils.ConfigData;
import utils.HLInstance;
import utils.container.Module;
import utils.container.ModuleThread;

public class ConfigGobelets implements Module {
    HLInstance hl;

    private void handleConfig(String config) {
        hl.getConfig().override(ConfigData.CONFIG_ECUEIL, config);
    }

    @Override
    public void onInit(HLInstance hl) {
        hl.module(Listener.class).registerMessageHandler(Channel.CONFIG_ECEUIL,this::handleConfig);
    }
}

package engcake.excavator;

import engcake.excavator.settings.OreExcavatorSettings;
import necesse.engine.modLoader.ModSettings;
import necesse.engine.modLoader.annotations.ModEntry;

@ModEntry
public class OreExcavator {
    public ModSettings initSettings() {
        return new OreExcavatorSettings();
    }
}

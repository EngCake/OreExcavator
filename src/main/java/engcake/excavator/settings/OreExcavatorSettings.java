package engcake.excavator.settings;

import necesse.engine.modLoader.ModSettings;
import necesse.engine.save.LoadData;
import necesse.engine.save.SaveData;

public class OreExcavatorSettings extends ModSettings {
    public boolean test;

    @Override
    public void addSaveData(SaveData saveData) {
        SaveData oreExcavatorSettings = new SaveData("ORE_EXCAVATOR");
        oreExcavatorSettings.addBoolean("test", false);
    }

    @Override
    public void applyLoadData(LoadData loadData) {
        LoadData oreExcavatorSettings = loadData.getFirstLoadDataByName("ORE_EXCAVATOR");
        if (oreExcavatorSettings != null) {
            this.test = oreExcavatorSettings.getBoolean("test");
        }
    }
}

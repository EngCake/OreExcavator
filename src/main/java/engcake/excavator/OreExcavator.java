package engcake.excavator;

import necesse.engine.Settings;
import necesse.engine.input.Control;
import necesse.engine.localization.message.StaticMessage;
import necesse.engine.modLoader.annotations.ModEntry;

@ModEntry
public class OreExcavator {
        public static Control EXCAVATE_CONTROL = new Control(
                        0,
                        "engcake.excavator.excavate",
                        new StaticMessage("Ore Excavator"),
                        "Excavator");

        public void init() {
                Control.addModControl(EXCAVATE_CONTROL);
        }
}

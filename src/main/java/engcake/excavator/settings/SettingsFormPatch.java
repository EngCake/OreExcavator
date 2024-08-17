package engcake.excavator.settings;

import necesse.engine.modLoader.annotations.ModConstructorPatch;
import necesse.engine.network.client.Client;
import necesse.gfx.forms.presets.SettingsForm;
import net.bytebuddy.asm.Advice;

@ModConstructorPatch(
        target = SettingsForm.class,
        arguments = {
                Client.class,
        }
)
public class SettingsFormPatch {
        @Advice.OnMethodExit
        public static void OnConstructorExit(@Advice.This SettingsForm self) {
                OreExcavatorSettingsForm.addForm(self);
        }
}

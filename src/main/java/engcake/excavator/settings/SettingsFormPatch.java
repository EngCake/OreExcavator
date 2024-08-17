package engcake.excavator.settings;

import necesse.engine.modLoader.annotations.ModConstructorPatch;
import necesse.engine.network.client.Client;
import necesse.gfx.forms.Form;
import necesse.gfx.forms.presets.SettingsForm;
import net.bytebuddy.asm.Advice;

@ModConstructorPatch(
        target = SettingsForm.class,
        arguments = {
                Client.class,
        }
)
public class OreExcavatorSettingsFormPatch {
        private static Form 

        @Advice.OnMethodExit
        public static void OnConstructorExit(Client client) {

        }
}

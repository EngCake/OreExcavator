package engcake.excavator.settings;

import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.engine.window.GameWindow;
import necesse.engine.window.WindowManager;
import necesse.gfx.forms.Form;
import necesse.gfx.forms.presets.SettingsForm;
import net.bytebuddy.asm.Advice;

@ModMethodPatch(
        target = SettingsForm.class,
        name = "onWindowResized",
        arguments = {
                GameWindow.class
        }
)
public class SettingsFormWindowResizePatch {
    @Advice.OnMethodExit
    public static void onWindowResizeExit(
            @Advice.This SettingsForm self,
            @Advice.Argument(0) GameWindow window
    ) {
        Form form = OreExcavatorSettingsForm.addForm(self);
        form.setPosMiddle(
                WindowManager.getWindow().getHudWidth() / 2,
                WindowManager.getWindow().getHudHeight() / 2
        );
    }
}

package engcake.excavator.settings;

import engcake.excavator.utils.CollectionsUtils;
import engcake.excavator.utils.ReflectionUtils;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.gfx.forms.Form;
import necesse.gfx.forms.components.FormTextButton;
import necesse.gfx.forms.position.FormFixedPosition;
import necesse.gfx.forms.presets.SettingsForm;
import net.bytebuddy.asm.Advice;

@ModMethodPatch(
        target = SettingsForm.class,
        name = "setupMenuForm",
        arguments = {}
)
public class SetupMenuFormPatch {
    public static Form mainMenu;

    @Advice.OnMethodExit
    public static void onSetupMenuForm(@Advice.This SettingsForm self) {
        mainMenu = ReflectionUtils.getPrivateField(
                self,
                SettingsForm.class,
                "mainMenu"
        );
        mainMenu.setHeight(400);

        FormTextButton backButton = (FormTextButton) CollectionsUtils.getLastElement(
                mainMenu.getComponentList()
        );
        FormFixedPosition position = ReflectionUtils.getPrivateField(
                backButton,
                FormTextButton.class,
                "position"
        );
        ReflectionUtils.setPrivateField(
            position,
            FormFixedPosition.class,
            "y",
            360
        );

        mainMenu.addComponent(new OreExcavatorSettingsButton(self, mainMenu));
    }


}

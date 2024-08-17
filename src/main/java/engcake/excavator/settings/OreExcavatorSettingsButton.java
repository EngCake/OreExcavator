package engcake.excavator.settings;

import necesse.gfx.forms.Form;
import necesse.gfx.forms.components.localComponents.FormLocalTextButton;
import necesse.gfx.forms.presets.SettingsForm;

public class OreExcavatorSettingsButton extends FormLocalTextButton {
    public OreExcavatorSettingsButton(SettingsForm settingsForm, Form mainMenu) {
        super(
                "settingsui",
                "oreExcavator",
                4,
                320,
                mainMenu.getWidth() - 8
        );

        onClicked(e -> OreExcavatorSettingsForm.makeCurrent(settingsForm));
    }
}

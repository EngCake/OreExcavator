package engcake.excavator.settings;

import necesse.gfx.forms.Form;
import necesse.gfx.forms.components.localComponents.FormLocalLabel;
import necesse.gfx.forms.components.localComponents.FormLocalTextButton;
import necesse.gfx.forms.presets.SettingsForm;
import necesse.gfx.gameFont.FontOptions;

public class OreExcavatorSettingsForm {
    public static Form oreExcavatorForm;

    public static Form addForm(SettingsForm settingsForm) {
        if (oreExcavatorForm == null) {
            oreExcavatorForm = settingsForm.addComponent(
                    new Form("oreExcavator", 400, 100)
            );
        }
        else {
            oreExcavatorForm.clearComponents();
        }

        oreExcavatorForm.addComponent(
                new FormLocalLabel(
                        "settingsui",
                        "oreExcavator",
                        new FontOptions(20),
                        0,
                        SetupMenuFormPatch.mainMenu.getWidth() / 2,
                        10
                )
        );

        FormLocalTextButton backButton = oreExcavatorForm.addComponent(
                new FormLocalTextButton(
                        "ui",
                        "backbutton",
                        4,
                        50,
                        SetupMenuFormPatch.mainMenu.getWidth() - 8
                )
        );
        backButton.onClicked(e -> settingsForm.subMenuBackPressed());

        return oreExcavatorForm;
    }

    public static void makeCurrent(SettingsForm settingsForm) {
        settingsForm.makeCurrent(oreExcavatorForm);
    }
}

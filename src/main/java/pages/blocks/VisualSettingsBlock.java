package pages.blocks;

import framework.app.BasePage;
import framework.app.annotations.Collection;
import framework.app.annotations.Name;
import framework.app.annotations.Optional;
import lombok.Getter;

@Name("Visual Settings")
@Getter
public class VisualSettingsBlock extends BasePage {

    @Name("Mode")
    private final String mode = "//html";
    @Name("Theme switcher")
    private final String themeSwitcher = "//theme-switcher/button";
    @Name("Localization dropdown")
    private final String localizationDropdown = "//div[@dropdown]";
    @Optional
    @Collection
    @Name("Localizations")
    private final String localizations = "//locale-switcher/div/span";

}

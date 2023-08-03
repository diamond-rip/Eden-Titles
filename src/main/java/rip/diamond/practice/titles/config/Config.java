package rip.diamond.practice.titles.config;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import rip.diamond.practice.titles.EdenTitles;
import rip.diamond.practice.util.BasicConfigFile;

import java.util.List;

@AllArgsConstructor
public enum Config {

    DISABLED_KITS("disabled-kits", ImmutableList.of("arcade", "tntrun", "your_kit_name_here_not_display_name")),
    MENU_TITLE("menu.title", "&e&lYour Titles"),
    MENU_ICON_MATERIAL("menu.icon.material", "PAPER"),
    MENU_ICON_NAME("menu.icon.name", "&e&l{kit-display-name}"),
    MENU_ICON_LORE("menu.icon.lore", ImmutableList.of("", "&eTitle: &d{title}", "", "&eClick to equip!")),
    MENU_ICON_LORE_NO_PERMISSION("menu.icon.lore-no-permission", ImmutableList.of("", "&eTitle: &d{title}", "", "&cRequires &dMaster &cto equip!")),
    ;

    @Getter private final String path;
    @Getter private final Object defaultValue;

    public String toString() {
        String str = EdenTitles.INSTANCE.getConfigFile().getString(path);
        if (str.equals(path)) {
            return defaultValue.toString();
        }
        return str;
    }


    public List<String> toStringList() {
        List<String> str = EdenTitles.INSTANCE.getConfigFile().getStringList(path);
        if (str.isEmpty() || str.get(0).equals(path)) {
            return (List<String>) defaultValue;
        }
        if (str.get(0).equals("null")) {
            return ImmutableList.of();
        }
        return str;
    }

    public boolean toBoolean() {
        return Boolean.parseBoolean(toString());
    }

    public int toInteger() {
        return Integer.parseInt(toString());
    }

    public double toDouble() {
        return Double.parseDouble(toString());
    }

    public static void loadDefault() {
        BasicConfigFile configFile = EdenTitles.INSTANCE.getConfigFile();

        for (Config config : Config.values()) {
            String path = config.getPath();
            String str = configFile.getString(path);
            if (str.equals(path)) {
                configFile.getConfiguration().set(path, config.getDefaultValue());
            }
        }

        configFile.load();
    }

}

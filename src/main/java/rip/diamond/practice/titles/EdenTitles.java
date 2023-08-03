package rip.diamond.practice.titles;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import rip.diamond.practice.titles.config.Config;
import rip.diamond.practice.util.BasicConfigFile;

public class EdenTitles extends JavaPlugin {

    public static EdenTitles INSTANCE;

    @Getter private BasicConfigFile configFile;

    @Override
    public void onEnable() {
        INSTANCE = this;

        this.configFile = new BasicConfigFile(this, "config.yml");

        Config.loadDefault();
    }
}

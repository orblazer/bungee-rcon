package fr.orblazer.bungeeRcon;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * The configuration of plugin
 *
 * @author orblazer
 */
public class Config {
    private final File configFile;
    private Configuration config;

    public Config(Plugin plugin) {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        configFile = new File(plugin.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(plugin.getResourceAsStream("config.yml"));
        } else {
            try {
                config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(plugin.getDataFolder(), "config.yml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void save() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the rcon port
     *
     * @return The port
     */
    public int getPort() {
        return config.getInt("port", 25578);
    }

    /**
     * Get the rcon password
     *
     * @return The password
     */
    public String getPassword() {
        return config.getString("password", "");
    }

    /**
     * Check if rcon response is colored
     *
     * @return The response is colored or not
     */
    public boolean isColored() {
        return config.getBoolean("colored", true);
    }
}

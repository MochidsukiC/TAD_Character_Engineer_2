package jp.houlab.mochidsuki.tad_character_engineer_2;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public final class Main extends JavaPlugin {

    public static Plugin plugin;
    public static FileConfiguration config;
    @Override
    public void onEnable() {
        // Plugin startup logic

        //Command
        getCommand("tce").setExecutor(new CommandListener());




        //Event
        getServer().getPluginManager().registerEvents(new Listener(),this);
        plugin = this;

        //config
        saveDefaultConfig();
        config = getConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

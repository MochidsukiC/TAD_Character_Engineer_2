package jp.houlab.mochidsuki.tad_character_engineer_2;


import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static jp.houlab.mochidsuki.tad_character_engineer_2.Main.plugin;

public class CommandListener implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(s.equalsIgnoreCase("tce")){
            if(strings[0].equalsIgnoreCase("abi")){
                new DummyPlayerCardRunner((Player) commandSender).runTaskTimer(plugin,0,1);
            }
        }

        return false;
    }
}


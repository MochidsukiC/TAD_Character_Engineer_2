package jp.houlab.mochidsuki.tad_character_engineer_2;

import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;

public class V{
    static public HashMap<Player, ItemDisplay> cardList = new HashMap<>();
    static public HashMap<ItemDisplay, BukkitTask> cardSystem = new HashMap<>();
}

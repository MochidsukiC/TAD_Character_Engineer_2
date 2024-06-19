package jp.houlab.mochidsuki.tad_character_engineer_2;

import org.bukkit.Material;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import javax.swing.text.html.parser.Entity;

import static jp.houlab.mochidsuki.tad_character_engineer_2.Main.plugin;

public class Listener implements org.bukkit.event.Listener {
    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent event){
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            switch (event.getMaterial()) {
                case DUNE_ARMOR_TRIM_SMITHING_TEMPLATE: { //マテリアルはあとで決定する
                    new DummyPlayerCardRunner(event.getPlayer()).runTaskTimer(plugin, 0, 1);
                    break;
                }
                case STONE_BUTTON:{
                    Ultimate.startUlt(event.getPlayer());
                    break;
                }
                case TNT:{
                    Ultimate.spawnExplosion(event.getPlayer());
                    break;
                }
                case ENDER_EYE:{
                    Ultimate.teleportPlayer(event.getPlayer());
                    break;
                }

            }
        }
    }
}

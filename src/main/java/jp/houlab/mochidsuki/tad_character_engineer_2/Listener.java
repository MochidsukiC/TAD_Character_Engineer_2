package jp.houlab.mochidsuki.tad_character_engineer_2;

import org.bukkit.Material;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import javax.swing.text.html.parser.Entity;

public class Listener implements org.bukkit.event.Listener {
    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent event){
        switch (event.getMaterial()) {
            case IRON_HOE: { //マテリアルはあとで決定する
                if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    ItemDisplay itemDisplay = event.getPlayer().getWorld().spawn(event.getPlayer().getLocation().add(0,1.5,0), ItemDisplay.class);
                    itemDisplay.setItemStack(new ItemStack(Material.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE));

                }
            }
        }
    }
}

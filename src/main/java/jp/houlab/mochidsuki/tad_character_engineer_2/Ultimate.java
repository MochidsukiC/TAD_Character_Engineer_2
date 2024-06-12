package jp.houlab.mochidsuki.tad_character_engineer_2;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.HashMap;

public class Ultimate {
    static public boolean startUlt(Player player){
        if(V.cardList.containsKey(player)){
            ItemStack tnt = new ItemStack(Material.TNT);
            ItemStack ender_eye = new ItemStack(Material.ENDER_EYE);
            ItemMeta tntItemMeta = tnt.getItemMeta();
            ItemMeta ender_eyeItemMeta = ender_eye.getItemMeta();
            tntItemMeta.addEnchant(Enchantment.LUCK,1,true);
            ender_eyeItemMeta.addEnchant(Enchantment.LUCK,1,true);
            tnt.setItemMeta(tntItemMeta);

            ender_eye.setItemMeta(ender_eyeItemMeta);
            player.getInventory().addItem(tnt,ender_eye);

            return true;
        }else {
            return false;
        }
    }

    static public boolean spawnExplosion(Player player){
        if(V.cardList.containsKey(player)){
            ItemDisplay itemDisplay = V.cardList.get(player);
            Location location = itemDisplay.getLocation();
            boolean isExploded = location.getWorld().createExplosion(itemDisplay,location.clone().add(0,1,0),4f,false,false);
            location.getWorld().spawnParticle(Particle.EXPLOSION_HUGE,location.clone().add(0,1,0),3);
            location.getWorld().spawnParticle(Particle.FIREWORKS_SPARK,location.clone().add(0,1,0),30,0,0,0,10);
            location.getWorld().spawnParticle(Particle.FLAME,location.clone().add(0,1,0),30,0,0,0,10);
            location.getWorld().spawnParticle(Particle.FLASH,location.clone().add(0,1,0),5);
            location.getWorld().spawnParticle(Particle.LAVA,location.clone().add(0,1,0),30,0,0,0,10);
            //location.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION,location.clone().add(0,1,0),30,0,0,0,2);
            V.cardList.remove(player);
            itemDisplay.remove();
            return isExploded;
        }else {
            return false;
        }
    }
    static public boolean teleportPlayer(Player player){
        if(V.cardList.containsKey(player)){
            //behindSpawnParticle
            {
                Location location = player.getLocation().clone();
                for(int y = 0;y < 20;y++){
                    for (int i = 0;i<360;i++){
                        if(y % 4 == 0) {
                            location.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, location.clone().add(new Vector(1, y / 10, 0).rotateAroundY(Math.toRadians(i))), 5, new Particle.DustTransition(Color.BLUE, Color.BLUE, 1));
                        }else {
                            location.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, location.clone().add(new Vector(1, y / 10, 0).rotateAroundY(Math.toRadians(i))), 5, new Particle.DustTransition(Color.WHITE, Color.WHITE, 1));
                        }
                    }
                }
            }
            //teleport system
            ItemDisplay itemDisplay = V.cardList.get(player);
            Location location = itemDisplay.getLocation();
            player.teleport(location.clone().add(0,2,0));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING,40,0));
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,40,3));

            //afterSpawnParticle
            for(int y = 0;y < 20;y++){
                for (int i = 0;i<360;i++){
                    if(y % 4 == 0) {
                        location.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, location.clone().add(new Vector(1, y / 10, 0).rotateAroundY(Math.toRadians(i))), 5, new Particle.DustTransition(Color.BLUE, Color.BLUE, 1));
                    }else {
                        location.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, location.clone().add(new Vector(1, y / 10, 0).rotateAroundY(Math.toRadians(i))), 5, new Particle.DustTransition(Color.WHITE, Color.WHITE, 1));
                    }
                }
            }
            return true;
        }else {
            return false;
        }
    }
}

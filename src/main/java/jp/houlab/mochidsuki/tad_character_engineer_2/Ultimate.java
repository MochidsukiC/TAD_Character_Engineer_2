package jp.houlab.mochidsuki.tad_character_engineer_2;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

import java.util.HashMap;

import static jp.houlab.mochidsuki.tad_character_engineer_2.Main.config;

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

    static public void spawnExplosion(Player player){
        if(V.cardList.containsKey(player)){
            ItemDisplay itemDisplay = V.cardList.get(player);
            Location location = itemDisplay.getLocation();


            new BukkitRunnable(){
                public int i;
                {
                i = 0;
                location.getWorld().playSound(location, Sound.ENTITY_CREEPER_PRIMED,1,1);
                }
                @Override
                public void run() {
                    location.getWorld().spawnParticle(Particle.SMOKE_NORMAL,location,10,0,0,0,0.1);
                    player.playSound(player,Sound.BLOCK_NOTE_BLOCK_PLING,1f,2f);
                    i++;
                    if(i>=config.getInt("ExplosionPrepareTime")){
                        location.getWorld().createExplosion(itemDisplay,location.clone().add(0,1,0),4f,false,false);
                        location.getWorld().spawnParticle(Particle.EXPLOSION_HUGE,location.clone().add(0,1,0),3);
                        location.getWorld().spawnParticle(Particle.FIREWORKS_SPARK,location.clone().add(0,1,0),500,0,0,0,1);
                        location.getWorld().spawnParticle(Particle.FLAME,location.clone().add(0,1,0),500,0,0,0,1);
                        location.getWorld().spawnParticle(Particle.FLASH,location.clone().add(0,1,0),5);
                        location.getWorld().spawnParticle(Particle.LAVA,location.clone().add(0,1,0),500,1,1,1,1);
                        //location.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION,location.clone().add(0,1,0),30,0,0,0,2);
                        V.cardList.remove(player);
                        itemDisplay.remove();
                        cancel();
                    }

                }
            }.runTaskTimer(Main.plugin,0,1);




        }else {
            return;
        }
    }
    static public boolean teleportPlayer(Player player){
        if(V.cardList.containsKey(player)){
            //behindSpawnParticle
            {
                Location location = player.getLocation().clone();
                for(double y = 0;y < 30;y++){
                    for (int i = 0;i<360;i++){
                        if(y % 4 == 0) {
                            location.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, location.clone().add(new Vector(0.6, y / 10, 0).rotateAroundY(Math.toRadians(i))), 1, new Particle.DustTransition(Color.AQUA, Color.BLUE, 1));
                        }else {
                            location.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, location.clone().add(new Vector(0.6, y / 10, 0).rotateAroundY(Math.toRadians(i))), 1, new Particle.DustTransition(Color.WHITE, Color.WHITE, 1));
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
            for(double y = 0;y < 30;y++){
                for (int i = 0;i<360;i++){
                    if(y % 4 == 0) {
                        location.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, location.clone().add(new Vector(0.6, y / 10 +2, 0).rotateAroundY(Math.toRadians(i))), 1, new Particle.DustTransition(Color.AQUA, Color.BLUE, 1));
                    }else {
                        location.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, location.clone().add(new Vector(0.6, y / 10 +2, 0).rotateAroundY(Math.toRadians(i))), 1, new Particle.DustTransition(Color.WHITE, Color.WHITE, 1));
                    }
                }
            }
            return true;
        }else {
            return false;
        }
    }
}

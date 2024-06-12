package jp.houlab.mochidsuki.tad_character_engineer_2;

import jp.houlab.mochidsuki.toweraandd.CommandListener;
import jp.houlab.mochidsuki.toweraandd.TowerAandD;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;

import static jp.houlab.mochidsuki.tad_character_engineer_2.Main.plugin;
import static jp.houlab.mochidsuki.toweraandd.TowerAandD.config;
import static jp.houlab.mochidsuki.toweraandd.V.*;

public class DummyPlayerCardRunner extends BukkitRunnable {

    ItemDisplay itemDisplay;
    Vector spawmVector;
    Player player;
    int times;
    Location location;
    float pitch;
    float yaw;


    public DummyPlayerCardRunner(Player player){
        this.itemDisplay = player.getWorld().spawn(player.getEyeLocation(), ItemDisplay.class);
        itemDisplay.setItemStack(new ItemStack(Material.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE));
        this.spawmVector = player.getEyeLocation().getDirection();
        this.player = player;
        location = player.getEyeLocation().clone();
        pitch = player.getPitch();
        yaw = player.getYaw();

        Transformation transformation = itemDisplay.getTransformation();
        transformation.getLeftRotation().rotateLocalX((float) Math.toRadians(pitch-90)).rotateLocalY((float) Math.toRadians(yaw));
        itemDisplay.setTransformation(transformation);


        V.cardList.put(player,itemDisplay);
    }

    @Override
    public void run() {
        itemDisplay.teleport(location.add(spawmVector));
        Transformation transformation = itemDisplay.getTransformation();
        transformation.getLeftRotation().rotateZ((float) Math.toRadians(201));
        itemDisplay.setTransformation(transformation);
        times++;

        if(itemDisplay.getLocation().getWorld().getBlockAt(itemDisplay.getLocation()).getBlockData().getMaterial() != Material.AIR || times > 600){
            new DummyPlayerSystem(itemDisplay,player).runTaskTimer(plugin,0,1);
            cancel();
        }
    }
}

class DummyPlayerSystem extends BukkitRunnable{
    public int times;
    public ItemDisplay itemDisplay;
    public Player player;
    public DummyPlayerSystem(ItemDisplay itemDisplay,Player player){
        this.itemDisplay = itemDisplay;
        this.player = player;
    }

    @Override
    public void run() {
        for(int i = 0; i < spawnLocation.size();i++){
            Location location = spawnLocation.get(i);
            if(itemDisplay.getLocation().distance(location) < 3){
                CommandListener.spawnScoreCalculator(player,i);
            }
        }



        times++;
        if(times > Main.config.getInt("DummyPlayerLivingTime")*20){
            itemDisplay.remove();
            V.cardList.remove(player);
            cancel();

        }
    }
}
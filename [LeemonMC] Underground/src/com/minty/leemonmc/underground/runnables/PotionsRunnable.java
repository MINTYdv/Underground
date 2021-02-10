package com.minty.leemonmc.underground.runnables;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_9_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.minty.leemonmc.underground.core.Underground;

import net.minecraft.server.v1_9_R2.EnumItemSlot;
import net.minecraft.server.v1_9_R2.PacketPlayOutEntityEquipment;

public class PotionsRunnable extends BukkitRunnable {

	private Underground main = Underground.getInstance();
	private Map<Player, Boolean> firstTimes = new HashMap<>();
	
	@Override
	public void run()
	{
		for(Player player : Bukkit.getOnlinePlayers())
		{
			if(player.hasPotionEffect(PotionEffectType.INVISIBILITY))
			{
				if(getFirstTime(player) == false)
				{
					getFirstTimes().remove(player);
   					getFirstTimes().put(player, true);
   					
   					for(Player target : Bukkit.getOnlinePlayers())
   					{
   	                    CraftPlayer c = (CraftPlayer) target;
   	                    PacketPlayOutEntityEquipment helmet = new PacketPlayOutEntityEquipment(player.getEntityId(), EnumItemSlot.HEAD, null);
   	                    PacketPlayOutEntityEquipment chestplate = new PacketPlayOutEntityEquipment(player.getEntityId(), EnumItemSlot.CHEST, null);
   	                    PacketPlayOutEntityEquipment leggings = new PacketPlayOutEntityEquipment(player.getEntityId(), EnumItemSlot.LEGS, null);
   	                    PacketPlayOutEntityEquipment boots = new PacketPlayOutEntityEquipment(player.getEntityId(), EnumItemSlot.FEET, null);
   	                    
   	                    c.getHandle().playerConnection.sendPacket(helmet);
   	                    c.getHandle().playerConnection.sendPacket(chestplate);
   	                    c.getHandle().playerConnection.sendPacket(leggings);
   	                    c.getHandle().playerConnection.sendPacket(boots);
   					}
				}
			} else 
			{
				if(getFirstTime(player) == true)
				{
					getFirstTimes().remove(player);
					getFirstTimes().put(player, false);
					
					for(Player target : Bukkit.getOnlinePlayers())
					{
	                    CraftPlayer c = (CraftPlayer) target;
	                    
	                    PacketPlayOutEntityEquipment helmet = new PacketPlayOutEntityEquipment(player.getEntityId(), EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(player.getInventory().getHelmet()));
	                    PacketPlayOutEntityEquipment chestplate = new PacketPlayOutEntityEquipment(player.getEntityId(), EnumItemSlot.CHEST, CraftItemStack.asNMSCopy(player.getInventory().getChestplate()));
	                    PacketPlayOutEntityEquipment leggings = new PacketPlayOutEntityEquipment(player.getEntityId(), EnumItemSlot.LEGS, CraftItemStack.asNMSCopy(player.getInventory().getLeggings()));
	                    PacketPlayOutEntityEquipment boots = new PacketPlayOutEntityEquipment(player.getEntityId(), EnumItemSlot.FEET, CraftItemStack.asNMSCopy(player.getInventory().getBoots()));
	                    
	                    c.getHandle().playerConnection.sendPacket(helmet);
	                    c.getHandle().playerConnection.sendPacket(chestplate);
	                    c.getHandle().playerConnection.sendPacket(leggings);
	                    c.getHandle().playerConnection.sendPacket(boots);
					}
				}
			}
		}
	}
	
	private boolean getFirstTime(Player player) {
		if(!getFirstTimes().containsKey(player))
		{
			getFirstTimes().put(player, true);
		}
		
		return getFirstTimes().get(player);
	}
	
	public Map<Player, Boolean> getFirstTimes() {
		return firstTimes;
	}

}

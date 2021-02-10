package com.minty.leemonmc.underground.listeners;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.minty.leemonmc.basics.core.GameState;
import com.minty.leemonmc.underground.core.Underground;
import com.minty.leemonmc.underground.core.UndergroundData;

public class ItemsListeners implements Listener {

	private Underground main = Underground.getInstance();
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e)
	{
		Player player = e.getPlayer();
		
		ItemStack it = e.getItem();
		if(it == null) return;
		if(player.getGameMode() != GameMode.SURVIVAL) return;
		if(main.getGameApi().getGameManager().getCurrentState() != GameState.PLAYING) return;
		
		if(it.getType() == Material.FIREBALL)
		{
			  Location loc = player.getLocation();
			  Vector vector = loc.getDirection();
			  vector.multiply(2);
			  Entity f = loc.getWorld().spawnEntity(loc.add(vector), EntityType.FIREBALL);
			  f.setVelocity(loc.getDirection().multiply(0.5));
			  if(player.getInventory().getItemInMainHand().getAmount() <= 1) player.getInventory().setItemInMainHand(null);
			  else player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
		}
		
	}
	
	@EventHandler
	public void onEnderPearl(PlayerTeleportEvent e)
	{
		Player player = e.getPlayer();
		
		if(player.getGameMode() != GameMode.SURVIVAL) return;
		if(main.getGameApi().getGameManager().getCurrentState() != GameState.PLAYING) return;
		
		if(e.getCause() == TeleportCause.ENDER_PEARL)
		{
			UndergroundData data = main.getUndergroundDataHandler().getPlayerData(player);
			if(!data.isSpawned()) {
				e.setCancelled(true);
				return;
			}
		}
	}
	
}

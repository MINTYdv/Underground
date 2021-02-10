package com.minty.leemonmc.underground.runnables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.minty.leemonmc.basics.core.GameState;
import com.minty.leemonmc.basics.core.cache.Account;
import com.minty.leemonmc.core.util.Title;
import com.minty.leemonmc.games.core.Team;
import com.minty.leemonmc.underground.core.Underground;

public class TrackerRunnable extends BukkitRunnable {

	private Underground main = Underground.getInstance();
	private double radius = 500;
	private final static Title title = new Title();
	
	@Override
	public void run()
	{
		for(Player player : main.getGameApi().getGameManager().getPlayingPlayersList())
		{
			if(player.getInventory().getItemInMainHand() == null) return;
			if(main.getGameApi().getGameManager().getCurrentState() != GameState.PLAYING) return;
			if(player.getGameMode() != GameMode.SURVIVAL) return;
			
			ItemStack it = player.getInventory().getItemInMainHand();
			
			if(it.getType() != Material.COMPASS) return;
			
			Location location = player.getLocation();
			World world = location.getWorld();
			
			Collection<Entity> nearbyEntities = world.getNearbyEntities(location, radius, radius, radius);
			List<Player> playersNear = new ArrayList<>();
			
			for(Entity entity : nearbyEntities)
			{
				if(entity instanceof Player)
				{
					Player target = (Player) entity;
					String UUID = target.getUniqueId().toString();
					Account account = main.getApi().getAccountManager().getAccount(UUID);
					
					if(!account.isModEnabled())
					{
						Team playerTeam = main.getGameApi().getTeamsManager().getTeam(player);
						Team targetTeam = main.getGameApi().getTeamsManager().getTeam(target);
						if(!targetTeam.getName().equalsIgnoreCase(playerTeam.getName()))
						{
							playersNear.add(target);
						}
					}
				}
			}
			
			if(playersNear == null || playersNear.size() < 1) return;
			Player nearestPlayer = playersNear.get(0);
			String nearestPlayerUUID = nearestPlayer.getUniqueId().toString();
			Account nearestPlayerAccount = main.getApi().getAccountManager().getAccount(nearestPlayerUUID);
			
			double distance = nearestPlayer.getLocation().distance(player.getLocation());
			
			title.sendActionBar(player, "§6Traqueur: §7Le joueur §6" + nearestPlayerAccount.getNickedName() + " §7est à §6" + (int) distance + "m §7!");
			player.setCompassTarget(nearestPlayer.getLocation());
		}
	}

}

package com.minty.leemonmc.underground.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import com.minty.leemonmc.basics.core.GameState;
import com.minty.leemonmc.basics.core.cache.Account;
import com.minty.leemonmc.core.stats.StatsData;
import com.minty.leemonmc.core.stats.StatsDataHandler;
import com.minty.leemonmc.core.util.Title;
import com.minty.leemonmc.games.core.Team;
import com.minty.leemonmc.games.rewards.GameReward;
import com.minty.leemonmc.games.rewards.GameRewardType;
import com.minty.leemonmc.underground.core.Underground;
import com.minty.leemonmc.underground.core.UndergroundData;
import com.minty.leemonmc.underground.handlers.core.GameManager;
import com.minty.leemonmc.underground.handlers.trading.handlers.StuffHandler;

import io.netty.util.internal.ThreadLocalRandom;

public class UndergroundGameListeners implements Listener {

	private Underground main;
	private Title title = new Title();
	
	public UndergroundGameListeners(Underground _main) {
		main = _main;
		title = new Title();
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e)
	{
		Block block = e.getBlock();
		Player player = e.getPlayer();
		Team team = main.getGameApi().getTeamsManager().getTeam(player);
		
		if(player.getGameMode() != GameMode.SURVIVAL) return;
		if(!main.getUndergroundDataHandler().getPlayerData(player).isSpawned()) return;
		if(!main.getGameApi().getGameManager().getPlayingPlayersList().contains(player)) return;
		
		if(GameManager.getPlacedBlocks().contains(block)) {
			e.setCancelled(false);
		} else {
			e.setCancelled(true);
		}
		
		if(block.getType() == Material.STONE || block.getType() == Material.COBBLESTONE || block.getType() == Material.MOSSY_COBBLESTONE)
		{
			if(main.getBasesHandler().getBlueBase().contains(block) || main.getBasesHandler().getRedBase().contains(block))
			{
				player.sendMessage("§7│ §cTu dois t'éloigner un peu plus du centre de la grotte pour creuser !");
				player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
				return;
			} 
			
			block.setType(Material.AIR);
			
			Location location = block.getLocation().add(0.5, 0.5, 0.5);
			player.getWorld().spawnEntity(location, EntityType.EXPERIENCE_ORB);

			player.spawnParticle(Particle.REDSTONE, location, 0, 255,0,0);
			int randomNum = ThreadLocalRandom.current().nextInt(1, 10 + 1);
			main.getUndergroundDataHandler().getPlayerData(player).addExperience(randomNum);
			
		} else
		{
			if(block.getType() == Material.REDSTONE_BLOCK)
			{
				if(team.getArmorColor() == Color.RED)
				{
					player.sendMessage("§7│ §cTu ne peux pas casser ton propre totem !");
					return;
				}
				
				block.setType(Material.COAL_BLOCK);
				Team adversaires = main.getGameApi().getTeamsManager().getTeamByColor(Color.RED);
				adversaires.removePoint();
				main.getGameManager().removeTotemBlock(adversaires, team);
				main.getGameManager().playerKilled(player);
				
				for(Player pls : adversaires.getAlivePlayers())
				{
					pls.playSound(pls.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1f, 1f);
					title.sendTitle(pls, 1, 60, 1, "", "§f» §cTotem attaqué ! §f«");
				}
				Bukkit.broadcastMessage("§6§lUnderground §f» §7L'équipe " + adversaires.getTag() + adversaires.getName() + " §7a perdu un bloc de son totem !");
			}
			
			if(block.getType() == Material.LAPIS_BLOCK)
			{
				if(team.getArmorColor() == Color.BLUE)
				{
					player.sendMessage("§7│ §cTu ne peux pas casser ton propre totem !");
					return;
				}
				
				block.setType(Material.COAL_BLOCK);
				Team adversaires = main.getGameApi().getTeamsManager().getTeamByColor(Color.BLUE);
				adversaires.removePoint();
				main.getGameManager().removeTotemBlock(adversaires, team);
				main.getGameManager().playerKilled(player);
				
				for(Player pls : adversaires.getAlivePlayers())
				{
					pls.playSound(pls.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1f, 1f);
					title.sendTitle(pls, 1, 60, 1, "", "§f» §cTotem attaqué ! §f«");
				}
				Bukkit.broadcastMessage("§6§lUnderground §f» §7L'équipe " + adversaires.getTag() + adversaires.getName() + " §7a perdu un bloc de son totem !");
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlace(BlockPlaceEvent e) {
		Player player = e.getPlayer();
		
		if(player.getGameMode() != GameMode.SURVIVAL) return;
		Block block = e.getBlock();
		Location loc = e.getBlock().getLocation();
		
		if(main.getBasesHandler().getBlueTotem().contains(loc) || main.getBasesHandler().getRedTotem().contains(loc))
		{
			player.sendMessage("§7│ §cTu dois t'éloigner un peu plus du totem pour poser des blocs !");
			player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
			e.setCancelled(true);
			return;
		}
		
		GameManager.getPlacedBlocks().add(block);
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e)
	{
		e.setDeathMessage("");
		try {
			Player player = (Player) e.getEntity();
			String UUID = player.getUniqueId().toString();
			Account playerAccount = main.getApi().getAccountManager().getAccount(UUID);
			
			UndergroundData data = main.getUndergroundDataHandler().getPlayerData(player);
			data.addDeath();

			Team playerTeam = main.getGameApi().getTeamsManager().getTeam(player);
			
			try {
				Player killer = player.getKiller();
				String killerUUID = killer.getUniqueId().toString();
				killer.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 1));
				
				Account killerAccount = main.getApi().getAccountManager().getAccount(killerUUID);
				
				if(killer == null || killerAccount.isModEnabled())
				{
					if(playerAccount.getSetting("global_gender").equalsIgnoreCase("FEMALE"))
					{
						e.setDeathMessage(playerTeam.getTag() + playerAccount.getNickedName() + " §7est morte.");
					} else {
						e.setDeathMessage(playerTeam.getTag() + playerAccount.getNickedName() + " §7est mort." );
					}
				} else
				{
					Team kTeam = main.getApi().getGameApi().getTeamsManager().getTeam(killer);
					
					if(playerAccount.getSetting("global_gender").equalsIgnoreCase("FEMALE"))
					{
						e.setDeathMessage(playerTeam.getTag() + playerTeam.getName() + " " + player.getName() + " §7a été tuée par " + kTeam.getTag() + kTeam.getName() + " " + killerAccount.getNickedName());
					} else {
						e.setDeathMessage(playerTeam.getTag() + playerTeam.getName() + " " + player.getName() + " §7a été tué par " + kTeam.getTag() + kTeam.getName() + " " + killerAccount.getNickedName());
					}

					StatsData killerData = StatsDataHandler.getPlayerStats(killer);
					killerData.addStat("kills", 1);
					
					UndergroundData undergroundData = main.getUndergroundDataHandler().getPlayerData(killer);
					undergroundData.addKill();
					undergroundData.addExperience(50);
					
					Title title = new Title();
					title.sendActionBar(killer, "§eGain§6: §e+5 pulpes §8(§7Kill§8)");
					killer.playSound(killer.getLocation(), Sound.BLOCK_NOTE_PLING, 1f, 1f);
					main.getGameApi().getGameRewardsManager().getPlayerRewards(killer).addReward(new GameReward(GameRewardType.KILLS, 5, 0));
				}
				
				StatsData victimData = StatsDataHandler.getPlayerStats(player);
				victimData.addStat("deaths", 1);
				
				player.spigot().respawn();
			} catch(Exception err) {
				err.printStackTrace();
			}
			
		} catch(Exception err) {
			// Ignore it <3
		}
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e)
	{
		Player player = e.getPlayer();
		
		main.getGameManager().playerKilled(player);
		player.getInventory().clear();
		StuffHandler.stuffPlayer(player);
		UndergroundData data = main.getUndergroundDataHandler().getPlayerData(player);
		
		data.setExperience(0);
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		
		UndergroundData data = main.getUndergroundDataHandler().getPlayerData(player);
		
		if(data.isSpawned() == false) e.setCancelled(true);
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e)
	{
		if(e.getEntityType() == EntityType.VILLAGER) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onVillager(PlayerInteractAtEntityEvent e) {
		if(e.getRightClicked().getType() == EntityType.VILLAGER && main.getGameApi().getGameManager().getCurrentState() == GameState.PLAYING)
		{
			e.setCancelled(true);
			main.getTradesHandler().openMainTradeMenu(e.getPlayer());
		}
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e)
	{
		if(!(e.getDamager() instanceof Player)) {
			return;
		}
		
		if(!(e.getEntity() instanceof Player)) {
			return;
		}
		
		Player victim = (Player) e.getEntity();
		Player damager = (Player) e.getDamager();
		
		UndergroundData victimData = main.getUndergroundDataHandler().getPlayerData(victim);
		UndergroundData damagerData = main.getUndergroundDataHandler().getPlayerData(damager);
		
		if(!victimData.isSpawned()) {
			damager.sendMessage("§7│ §cTu ne peux pas attaquer un joueur qui n'a pas encore réapparu !");
			e.setCancelled(true);
			return;
		}
		
		if(!damagerData.isSpawned()) {
			damager.sendMessage("§7│ §cTu ne peux pas attaquer lorsque tu n'as pas encore réapparu !");
			e.setCancelled(true);
			return;
		}
	}
	
}

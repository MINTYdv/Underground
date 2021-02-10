package com.minty.leemonmc.underground.handlers.core;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_9_R2.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.SpawnEgg;

import com.minty.leemonmc.basics.core.GameState;
import com.minty.leemonmc.games.LeemonGame;
import com.minty.leemonmc.games.core.Team;
import com.minty.leemonmc.underground.core.Underground;

import net.minecraft.server.v1_9_R2.NBTTagCompound;

public class TeamMobsHandler implements Listener {

	private static Map<Entity, Team> teamEntities = new HashMap<>();
	private Underground main = Underground.getInstance();
	
    @SuppressWarnings("deprecation")
	@EventHandler
    public void onSpawn(PlayerInteractEvent e)
    {
    	Player player = e.getPlayer();
    	ItemStack itemStack = e.getItem();
    	
    	if(itemStack == null) return;
    	if(itemStack.getType() != Material.MONSTER_EGG) return;
    	if(player.getGameMode() != GameMode.SURVIVAL) return;
    	if(main.getGameApi().getGameManager().getCurrentState() != GameState.PLAYING) return;
    	
    	Action action = e.getAction();
    	if(action == null || action != Action.RIGHT_CLICK_BLOCK) return;
    	
    	net.minecraft.server.v1_9_R2.ItemStack cis = CraftItemStack.asNMSCopy(itemStack);
    	if(!cis.hasTag()) {
    		return;
    	}

    	NBTTagCompound tag = cis.getTag();
    	if(tag == null);
    	NBTTagCompound entityTag = tag.getCompound("EntityTag");
    	if(entityTag == null);
    	
    	if (entityTag.hasKey("id") && entityTag.getString("id").equalsIgnoreCase("Silverfish"))
    	{
    		e.setCancelled(true);
    		double x = e.getClickedBlock().getLocation().getX() + 0.5;
    		double y = e.getClickedBlock().getLocation().getY() + 1;
    		double z = e.getClickedBlock().getLocation().getZ() + 0.5;
    		Location location = new Location(player.getLocation().getWorld(), x, y, z);
    		
    		Entity entity = player.getLocation().getWorld().spawnEntity(location, EntityType.SILVERFISH);
    		Team team = main.getGameApi().getTeamsManager().getTeam(player);
    		spawnMob(entity, team);
    		
    		if(player.getInventory().getItemInMainHand().getAmount() <= 1)
    		{
    			player.getInventory().setItemInMainHand(null);
    		} else
    		{
            	player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
            }
    	}
    }
	
    @EventHandler
    public void onSilverfishChange(EntityChangeBlockEvent e)
    {
    	Entity entity = e.getEntity();
    	if(entity.getType() == EntityType.SILVERFISH) {
    		e.setCancelled(true);
    	}
    }
    
    @EventHandler
    public void onTarget(EntityTargetEvent e)
    {
    	Entity entity = e.getEntity();
    	if(!(e.getTarget() instanceof Player)) {
    		return;
    	}
    	
    	if(!getTeamEntities().containsKey(entity)) {
    		return;
    	}
    	
    	Player player = (Player) e.getTarget();
    	Team team = main.getGameApi().getTeamsManager().getTeam(player);

    	if(getEntityTeam(entity) == team) {
    		e.setCancelled(true);
    	}
    }
    
	public static void spawnMob(Entity entity, Team team)
	{
		if(getTeamEntities().containsKey(entity)) {
			getTeamEntities().remove(entity);
		}
		
		getTeamEntities().put(entity, team);
	}
	
	public static Team getEntityTeam(Entity ent)
	{
		if(getTeamEntities().containsKey(ent))
		{
			return getTeamEntities().get(ent);
		}
		
		return null;
	}
	
	public static Map<Entity, Team> getTeamEntities() {
		return teamEntities;
	}
	
}

package com.minty.leemonmc.underground.listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import com.minty.leemonmc.underground.core.Underground;
import com.minty.leemonmc.underground.handlers.core.GameManager;
public class TNTListeners implements Listener
{

	private Underground main = Underground.getInstance();
	
	@EventHandler
	public void onExplode(EntityExplodeEvent event)
	{
        ArrayList<Block> blockListToNotExplode = new ArrayList<Block>();
        
        for(Block block : event.blockList())
        {
        	if(main.getBasesHandler().getRedBase().contains(block.getLocation()))
        	{
        		blockListToNotExplode.add(block);
        		continue;
        	}
        	
        	if(main.getBasesHandler().getBlueBase().contains(block.getLocation()))
        	{
        		blockListToNotExplode.add(block);
        		continue;
        	}
        	
        	if(block.getLocation().getY() <= 156) {
        		blockListToNotExplode.add(block);
        		continue;
        	}
        	
        	if(block.getType() != Material.STONE && block.getType() != Material.COBBLESTONE && block.getType() != Material.MOSSY_COBBLESTONE && !GameManager.getPlacedBlocks().contains(block))
        	{
        		blockListToNotExplode.add(block);
        		continue;
        	}
    
        }
        
        event.blockList().removeAll(blockListToNotExplode);
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onPlace(BlockPlaceEvent e)
	{
		Block block = e.getBlock();
		
		if(block.getType().equals(Material.TNT))
		{
            block.setType(Material.AIR);
            Location loc = block.getLocation();
            loc.setX(loc.getX() + 0.5);
            loc.setZ(loc.getZ() + 0.5);
            block.getLocation().getWorld().spawn(loc, TNTPrimed.class);
            return;
        }
		
	}
	
}

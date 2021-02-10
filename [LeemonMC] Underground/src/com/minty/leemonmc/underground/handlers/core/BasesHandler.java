package com.minty.leemonmc.underground.handlers.core;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import com.minty.leemonmc.core.blocks.cuboid.Cuboid;
import com.minty.leemonmc.underground.core.Underground;

public class BasesHandler {

	private Underground main = Underground.getInstance();

	public Cuboid getRedTotem()
	{
		int redTotemX = main.getConfig().getInt("zones.red.totem.x");
		int redTotemY = main.getConfig().getInt("zones.red.totem.y");
		int redTotemZ = main.getConfig().getInt("zones.red.totem.z");
		
		int redTotemX2 = main.getConfig().getInt("zones.red.totem.x2");
		int redTotemY2 = main.getConfig().getInt("zones.red.totem.y2");
		int redTotemZ2 = main.getConfig().getInt("zones.red.totem.z2");
		
		World world = Bukkit.getWorld("world");
		
		Location location1 = new Location(world, redTotemX, redTotemY, redTotemZ);
		Location location2 = new Location(world, redTotemX2, redTotemY2, redTotemZ2);
		
		return new Cuboid(location1, location2);
	}
	
	public Cuboid getBlueTotem()
	{
		int blueTotemX = main.getConfig().getInt("zones.blue.totem.x");
		int blueTotemY = main.getConfig().getInt("zones.blue.totem.y");
		int blueTotemZ = main.getConfig().getInt("zones.blue.totem.z");
		
		int blueTotemX2 = main.getConfig().getInt("zones.blue.totem.x2");
		int blueTotemY2 = main.getConfig().getInt("zones.blue.totem.y2");
		int blueTotemZ2 = main.getConfig().getInt("zones.blue.totem.z2");
		
		World world = Bukkit.getWorld("world");
		
		Location location1 = new Location(world, blueTotemX, blueTotemY, blueTotemZ);
		Location location2 = new Location(world, blueTotemX2, blueTotemY2, blueTotemZ2);
		
		return new Cuboid(location1, location2);
	}
	
	/* 
	 * Zones : Base
	 * */
	
	public Cuboid getBlueBase()
	{
		int blueBaseX = main.getConfig().getInt("zones.blue.base.x");
		int blueBaseY = main.getConfig().getInt("zones.blue.base.y");
		int blueBaseZ = main.getConfig().getInt("zones.blue.base.z");
		
		int blueBaseX2 = main.getConfig().getInt("zones.blue.base.x2");
		int blueBaseY2 = main.getConfig().getInt("zones.blue.base.y2");
		int blueBaseZ2 = main.getConfig().getInt("zones.blue.base.z2");
		
		World world = Bukkit.getWorld("world");
		
		Location location1 = new Location(world, blueBaseX, blueBaseY, blueBaseZ);
		Location location2 = new Location(world, blueBaseX2, blueBaseY2, blueBaseZ2);
		
		return new Cuboid(location1, location2);
	}
	
	public Cuboid getRedBase()
	{
		int redBaseX = main.getConfig().getInt("zones.red.base.x");
		int redBaseY = main.getConfig().getInt("zones.red.base.y");
		int redBaseZ = main.getConfig().getInt("zones.red.base.z");
		
		int redBaseX2 = main.getConfig().getInt("zones.red.base.x2");
		int redBaseY2 = main.getConfig().getInt("zones.red.base.y2");
		int redBaseZ2 = main.getConfig().getInt("zones.red.base.z2");
		
		World world = Bukkit.getWorld("world");
		
		Location location1 = new Location(world, redBaseX, redBaseY, redBaseZ);
		Location location2 = new Location(world, redBaseX2, redBaseY2, redBaseZ2);

		return new Cuboid(location1, location2);
	}

}

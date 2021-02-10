package com.minty.leemonmc.underground.handlers.core;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import com.minty.leemonmc.underground.core.Underground;
import com.minty.leemonmc.underground.core.UndergroundData;

public class UndergroundDataHandler {

	private Underground main;
	
	private Map<Player, UndergroundData> playersDatas = new HashMap<>();
	
	public UndergroundDataHandler(Underground _main)
	{
		main = _main;
	}
	
	public UndergroundData getPlayerData(Player player)
	{
		if(!getPlayersDatas().containsKey(player)) {
			getPlayersDatas().put(player, new UndergroundData());
		}
		return getPlayersDatas().get(player);
	}
	
	public Map<Player, UndergroundData> getPlayersDatas() {
		return playersDatas;
	}
	
}

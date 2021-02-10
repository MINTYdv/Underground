package com.minty.leemonmc.underground.runnables;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.minty.leemonmc.underground.core.Underground;
import com.minty.leemonmc.underground.core.UndergroundData;

public class ExpRunnable extends BukkitRunnable {

	private Underground main = Underground.getInstance();
	
	@Override
	public void run()
	{
		for(Player player : main.getGameApi().getGameManager().getPlayingPlayersList())
		{
			UndergroundData data = main.getUndergroundDataHandler().getPlayerData(player);
			player.setLevel(data.getExperience());
		}
	}
	
}

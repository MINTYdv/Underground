package com.minty.leemonmc.underground.core;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.minty.leemonmc.core.CoreMain;
import com.minty.leemonmc.games.LeemonGame;
import com.minty.leemonmc.underground.handlers.core.BasesHandler;
import com.minty.leemonmc.underground.handlers.core.GameManager;
import com.minty.leemonmc.underground.handlers.core.SbHandler;
import com.minty.leemonmc.underground.handlers.core.TeamMobsHandler;
import com.minty.leemonmc.underground.handlers.core.UndergroundDataHandler;
import com.minty.leemonmc.underground.handlers.trading.handlers.TradesHandler;
import com.minty.leemonmc.underground.handlers.trading.handlers.VillagersHandler;
import com.minty.leemonmc.underground.listeners.ItemsListeners;
import com.minty.leemonmc.underground.listeners.TNTListeners;
import com.minty.leemonmc.underground.listeners.UndergroundCoreListeners;
import com.minty.leemonmc.underground.listeners.UndergroundGameListeners;
import com.minty.leemonmc.underground.runnables.ExpRunnable;
import com.minty.leemonmc.underground.runnables.PlayTimeRunnable;
import com.minty.leemonmc.underground.runnables.PotionsRunnable;
import com.minty.leemonmc.underground.runnables.TrackerRunnable;

public class Underground extends JavaPlugin {

	private CoreMain api = (CoreMain) Bukkit.getPluginManager().getPlugin("LeemonCore");
	private LeemonGame gameApi = (LeemonGame) Bukkit.getPluginManager().getPlugin("LeemonGame");
	private static Underground instance;
	
	private UndergroundDataHandler undergroundDataHandler = new UndergroundDataHandler(this);
	private GameManager gameManager = new GameManager(this);;
	private static SbHandler scoreboardHandler;
	private VillagersHandler villagersHandler;
	private TradesHandler tradesHandler;
	private BasesHandler basesHandler;
	private TeamMobsHandler teamMobsHandler;
	
	@Override
	public void onEnable()
	{
		saveDefaultConfig();
		
		System.out.println("[Underground] Plugin actif !");
		
		registerReferences();
		registerListeners();
		registerRunnables();
	}
	
	private void registerReferences()
	{
		// Register all the plugin's references
		instance = this;
		api = (CoreMain) Bukkit.getPluginManager().getPlugin("LeemonCore");
		
		gameManager = new GameManager(this);
		scoreboardHandler = new SbHandler();
		villagersHandler = new VillagersHandler();
		tradesHandler = new TradesHandler(this);
		undergroundDataHandler = new UndergroundDataHandler(this);
		basesHandler = new BasesHandler();
		teamMobsHandler = new TeamMobsHandler();
	}
	
	private void registerListeners()
	{
		// Register all the plugin's listeners
		Bukkit.getPluginManager().registerEvents(new UndergroundCoreListeners(this), this);
		Bukkit.getPluginManager().registerEvents(new UndergroundGameListeners(this), this);
		
		Bukkit.getPluginManager().registerEvents(getTeamMobsHandler(), this);
		Bukkit.getPluginManager().registerEvents(new TNTListeners(), this);
		Bukkit.getPluginManager().registerEvents(new ItemsListeners(), this);
	}
	
	@SuppressWarnings("deprecation")
	private void registerRunnables()
	{
		Bukkit.getServer().getScheduler().runTaskTimer(this, new TrackerRunnable(), 5L, 5L);
		Bukkit.getServer().getScheduler().runTaskTimer(this, new ExpRunnable(), 5L, 5L);
		Bukkit.getServer().getScheduler().runTaskTimer(this, new PotionsRunnable(), 10L, 10L);
		Bukkit.getServer().getScheduler().runTaskTimer(this, new PlayTimeRunnable(), 20L * 60, 20L * 60L); // Each minute
		
        new BukkitRunnable() {
        	@Override
        	public void run()
        	{
        		updateScoreboardforAll();
        	}
        }.runTaskTimer(this, 0, 20);

	}
	
	@SuppressWarnings("static-access")
	private void updateScoreboardforAll()
	{
		for(Player player : Bukkit.getOnlinePlayers()) {
			getScoreboardHandler().updateScoreboard(player);
		}

	}
	
	@Override
	public void onDisable()
	{
		System.out.println("[Underground] Plugin inactif !");
	}
	
	/* 
	 * Getters & Setters
	 * */
	
	public CoreMain getApi() {
		if(api == null) {
			api = (CoreMain) Bukkit.getPluginManager().getPlugin("LeemonCore");
		}
		return api;
	}
	
	public static Underground getInstance() {
		return instance;
	}
	
	public UndergroundDataHandler getUndergroundDataHandler() {
		return undergroundDataHandler;
	}
	
	public static SbHandler getScoreboardHandler() {
		return scoreboardHandler;
	}
	
	public GameManager getGameManager() {
		return gameManager;
	}
	
	public VillagersHandler getVillagersHandler() {
		return villagersHandler;
	}
	
	public BasesHandler getBasesHandler() {
		return basesHandler;
	}
	
	public TradesHandler getTradesHandler() {
		return tradesHandler;
	}
	
	public TeamMobsHandler getTeamMobsHandler() {
		return teamMobsHandler;
	}
	
	public LeemonGame getGameApi() {
		return gameApi;
	}
	
}

package me.Jhim.TacklArena;

import java.sql.SQLException;

import me.Jhim.TacklArena.Arena.ArenaEvents;
import me.Jhim.TacklArena.Arena.ArenasCommand;
import me.Jhim.TacklArena.Profile.KillTexts.KillTextsConfig;
import me.Jhim.TacklArena.Profile.KillTexts.KillTextsMain;
import me.Jhim.TacklArena.Profile.ProfileCommand;
import me.Jhim.TacklArena.Profile.ProfileEvents;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.Jhim.TackArena.MySQL.MySQL;
import me.Jhim.TackArena.MySQL.SQLFunctions;
import me.Jhim.TacklArena.Arena.ArenaConfig;
import me.Jhim.TacklArena.Arena.ArenaManager;
import me.Jhim.TacklArena.Class.ClassConfig;
import me.Jhim.TacklArena.Class.ClassMain;
import me.Jhim.TacklArena.Class.Classes.Botanist;
import me.Jhim.TacklArena.Class.Classes.Commander;
import me.Jhim.TacklArena.Class.Classes.Heavy;
import me.Jhim.TacklArena.Class.Classes.Sniper;
import me.Jhim.TacklArena.Class.Classes.Spy;
import me.Jhim.TacklArena.Class.Classes.Technician;
import me.Jhim.TacklArena.Class.Events.BotanistEvents;
import me.Jhim.TacklArena.Class.Events.ClassEvents;
import me.Jhim.TacklArena.Class.Events.CommanderEvents;
import me.Jhim.TacklArena.Class.Events.HeavyEvents;
import me.Jhim.TacklArena.Class.Events.SniperEvents;
import me.Jhim.TacklArena.Class.Events.SpyEvents;
import me.Jhim.TacklArena.Class.Events.TechnicianEvents;
import me.Jhim.TacklArena.Config.ConfigReload;
import me.Jhim.TacklArena.Match.MatchEvents;
import me.Jhim.TacklArena.PAPI.SpigotExpansion;
import me.Jhim.TacklArena.Queues.CasualQueue;
import me.Jhim.TacklArena.Queues.QueueEvents;
import me.Jhim.TacklArena.Queues.QueueGUI;

public class Main extends JavaPlugin {
	
	MySQL mysql = new MySQL(this);
	ClassConfig classConfig;
	ArenaConfig arenaConfig;
	KillTextsConfig killTextsConfig;
	public Economy eco;

	@Override
	public void onEnable() {
		if(!setupEco()) {
			System.out.println("There is no vault / economy plugin detected!");
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		classConfig = new ClassConfig(this);
		arenaConfig = new ArenaConfig(this);
		killTextsConfig = new KillTextsConfig(this);
		arenaConfig.reloadConfig(); classConfig.reloadConfig();
		killTextsConfig.reloadConfig();
		this.saveDefaultConfig(); // Creates the config.yml file
		// Classes
		Commander commander = new Commander(classConfig);
		Sniper sniper = new Sniper(classConfig);
		Spy spy = new Spy(classConfig);
		Technician technician = new Technician(classConfig);
		Heavy heavy = new Heavy(classConfig);
		Botanist botanist = new Botanist(classConfig);
		ClassMain cm = new ClassMain(); QueueGUI qg = new QueueGUI();
		ArenaManager am = new ArenaManager(arenaConfig);
		KillTextsMain killTextsMain = new KillTextsMain(this, killTextsConfig);
		CasualQueue cq = new CasualQueue(this, am);
		killTextsMain.createKillTexts();
		am.createArenas();
		MySQLRunnable(mysql);
		registerCommand("queue", qg); registerCommand("class", cm);
		registerCommand("tacklarena", new ConfigReload(this, classConfig, arenaConfig, killTextsConfig));
		registerCommand("arenas", new ArenasCommand());
		registerCommand("profile", new ProfileCommand(this));
		registerEvent(new QueueEvents(qg, cq));
		registerEvent(new ClassEvents(cm, commander, sniper, spy, technician, heavy, botanist));
		registerEvent(new CommanderEvents(classConfig)); registerEvent(new SniperEvents(classConfig));
		registerEvent(new SpyEvents(classConfig, this)); registerEvent(new TechnicianEvents(classConfig));
		registerEvent(new HeavyEvents(classConfig)); registerEvent(new BotanistEvents(classConfig));
		registerEvent(new MatchEvents(cm)); registerEvent(new ArenaEvents());
		registerEvent(new MainEvents()); registerEvent(new ProfileEvents(killTextsMain));
		
		new SpigotExpansion().register(); // PAPI
	}
	
	@Override
	public void onDisable() {
	}
	
	private void registerEvent(Listener event) {
		this.getServer().getPluginManager().registerEvents(event, this);
	}
	
	private void registerCommand(String className, CommandExecutor command) {
		this.getCommand(className).setExecutor(command);
	}
	
	private void MySQLRunnable(MySQL mysql) {
		SQLFunctions sqlutils = new SQLFunctions();
		BukkitRunnable init = new BukkitRunnable() {
			@Override
			public void run() {
				try {
					mysql.openConnection();
					sqlutils.setupStatistics();
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		};
		init.runTaskAsynchronously(this);
		
		BukkitRunnable updates = new BukkitRunnable() {
			@Override
			public void run() {
				try {
					sqlutils.updatePlayerStatistics();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		};
		updates.runTaskTimerAsynchronously(this, 6000, 6000);
	}

	private boolean setupEco() {
		RegisteredServiceProvider<Economy> economy = getServer()
				.getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		if(economy != null)
			eco = economy.getProvider();
		return (economy != null);
	}
	
}

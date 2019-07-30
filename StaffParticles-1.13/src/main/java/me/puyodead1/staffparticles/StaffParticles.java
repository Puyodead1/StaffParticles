package me.puyodead1.staffparticles;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.puyodead1.staffparticles.commands.StaffParticlesCommand;

public class StaffParticles extends JavaPlugin {

	public static StaffParticles instance;
	public static HashMap<UUID, Particle> activeParticles = new HashMap<UUID, Particle>();
	public static HashMap<UUID, Integer> activeParticleSize = new HashMap<UUID, Integer>();

	public static StaffParticles getPlugin() {
		return instance;
	}

	public void onEnable() {
		final long started = System.currentTimeMillis();
		instance = this;
		if (!Bukkit.getVersion().contains("1.13") || !Bukkit.getVersion().contains("1.12")) {
			Bukkit.getServer().getConsoleSender().sendMessage(
					Utils.formatText("&cYou installed the wrong plugin version for 1.13/1.12! Plugin disabled!"));
			Bukkit.getServer().getPluginManager().disablePlugin(this);
		} else {
			getConfig().options().copyDefaults(true);
			saveDefaultConfig();

			PluginManager pm = Bukkit.getServer().getPluginManager();
			pm.registerEvents(new Events(), this);

			getCommand("staffparticles").setExecutor(new StaffParticlesCommand());

			Bukkit.getConsoleSender().sendMessage(Utils
					.formatText("&b[&dStaffParticles] Enabled in &e" + (System.currentTimeMillis() - started) + "ms."));
		}
	}

	public void onDisable() {
		activeParticles.clear();
		activeParticleSize.clear();
	}
}

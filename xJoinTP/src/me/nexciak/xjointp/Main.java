package me.nexciak.xjointp;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.nexciak.xjointp.listeners.PlayerJoinListener;
import me.nexciak.xjointp.manager.Manager;

public class Main extends JavaPlugin {
	
    private static Main instance;
    
    public void onEnable() {
        Main.instance = this;
        this.saveDefaultConfig();
        Manager.loadData();
        Bukkit.getPluginManager().registerEvents((Listener)new PlayerJoinListener(), (Plugin)this);
        Bukkit.getConsoleSender().sendMessage(Manager.c("&6xJoinTP &3| &aUruchamianie..."));
        Bukkit.getConsoleSender().sendMessage(Manager.c("&c&m---------------------------------"));
    }
    
    public void onDisable() {
    }
    
    public static Main getInst() {
        return Main.instance;
    }
}

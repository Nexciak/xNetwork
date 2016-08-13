package me.nexciak.xjointp.manager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import me.nexciak.xjointp.Main;

public class Manager {
	
    public static String MESS;
    public static Integer MIN_X;
    public static Integer MAX_X;
    public static Integer MIN_Z;
    public static Integer MAX_Z;
    
    static {
        Manager.MESS = "BRAK WIADOMOSCI";
        Manager.MIN_X = -1000;
        Manager.MAX_X = 1000;
        Manager.MIN_Z = -1000;
        Manager.MAX_Z = 1000;
    }
    
    public static String c(final String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
    
    public static void loadData() {
        final FileConfiguration y = Main.getInst().getConfig();
        Manager.MESS = c(y.getString("message"));
        Manager.MIN_X = y.getInt("minX");
        Manager.MAX_X = y.getInt("maxX");
        Manager.MIN_Z = y.getInt("minZ");
        Manager.MAX_Z = y.getInt("maxZ");
    }
    
    public static int randomAmount(final double minAmount, final double maxAmount) {
        return (int)Math.round(Math.random() * (maxAmount - minAmount) + minAmount);
    }
    
    public static void randomTP(final Player p) {
        Bukkit.getScheduler().runTask((Plugin)Main.getInst(), (Runnable)new Runnable() {
            @Override
            public void run() {
                final double x = Manager.randomAmount(Manager.MIN_X, Manager.MAX_X);
                final double z = Manager.randomAmount(Manager.MIN_Z, Manager.MAX_Z);
                p.teleport(new Location(p.getWorld(), x, (double)(p.getWorld().getHighestBlockYAt((int)x, (int)z) + 1), z));
                Bukkit.getScheduler().runTaskLater((Plugin)Main.getInst(), (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        p.sendMessage(Manager.MESS.replace("%x", new StringBuilder(String.valueOf(x)).toString()).replace("%z", new StringBuilder(String.valueOf(z)).toString()));
                    }
                }, 2L);
            }
        });
    }
}

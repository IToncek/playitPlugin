package me.itoncek.playitplugin;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public final class PlayitGG extends JavaPlugin {
    public static Process pid;

    @Override
    public void onEnable() {
        try {
            Bukkit.getLogger().info("Download begin");
            FileUtils.copyURLToFile(
                    new URL("https://new.playit.gg/downloads/playit-0.7.3-beta-signed.exe"),
                    new File("./plugins/PlayitGG/playit.exe"));
            Bukkit.getLogger().info("Download end");
            Bukkit.getLogger().info("Execution begin");
            pid = Runtime.getRuntime().exec(new File("plugins/PlayitGG/playit.exe").getAbsolutePath());
            Bukkit.getLogger().info(String.valueOf(pid.isAlive()));
            Bukkit.getLogger().info(pid.info().user().get());
            Bukkit.getLogger().info("Exe end");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onDisable() {
        try {
            Runtime.getRuntime().exec("taskkill /F /PID " + pid.pid());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}

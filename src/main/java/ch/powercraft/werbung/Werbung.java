package ch.powercraft.werbung;

import Commands.TitelWerbung;
import org.bukkit.plugin.java.JavaPlugin;

public final class Werbung extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("onEnable is called!");
        // Plugin startup logic
        try {
            this.getCommand("werbung").setExecutor(new TitelWerbung());
            getLogger().info("command 'werbung' was added.");
        } catch(Exception e) {
            getLogger().warning("😖failed to add command 'werbung':");
            getLogger().warning(e.getMessage());
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable is called!");
        // Plugin shutdown logic
    }
}

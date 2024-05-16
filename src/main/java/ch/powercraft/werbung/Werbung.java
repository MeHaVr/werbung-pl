package ch.powercraft.werbung;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class Werbung implements CommandExecutor {

    private JavaPlugin plugin;
    private String prefix;
    private String prefixno;
    private String zeichen;
    private String werbung;
    private String cooldownMessage;
    private int cooldownSeconds;
    private HashMap<Player, Long> cooldowns = new HashMap<>();

    public Werbung(JavaPlugin plugin) {
        this.plugin = plugin;
        this.prefix = plugin.getConfig().getString("prefix");
        this.prefixno = plugin.getConfig().getString("prefixno");
        this.werbung = plugin.getConfig().getString("werbung");
        this.zeichen = plugin.getConfig().getString("zeichen");
        this.cooldownMessage = plugin.getConfig().getString("cooldown_message");
        this.cooldownSeconds = plugin.getConfig().getInt("cooldown_seconds", 2300);
    }

    private void sendTitle(Player player, String text, String subtitle) {
        player.sendTitle(text, subtitle, 10, 300, 10);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Dieser Befehl kann nur von Spielern ausgeführt werden.");
            return true;
        }

        Player player = (Player) sender;

        if (cooldowns.containsKey(player)) {
            long secondsLeft = ((cooldowns.get(player) / 1000) + cooldownSeconds) - (System.currentTimeMillis() / 1000);
            long minutesLeft = secondsLeft / 60; // Umrechnung von Sekunden in Minuten

            if (minutesLeft > 0) {
                player.sendMessage("§f ");
                player.sendMessage(prefixno + ChatColor.translateAlternateColorCodes('&', cooldownMessage.replace("%minutes%", String.valueOf(minutesLeft))));
                player.sendMessage("§f ");
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                return true;
            }
        }

        String playerName = player.getName();
        String text = args.length > 0 ? String.join(" ", args) : "";

        if (text.length() > 40) {
            player.sendMessage(prefix + zeichen);
            return true;
        }

        sendTitle(player, ChatColor.translateAlternateColorCodes('&', "§a" + text), ChatColor.translateAlternateColorCodes('&', ChatColor.GREEN + playerName + werbung));
        player.playSound(player.getLocation(), Sound.ITEM_GOAT_HORN_SOUND_1, 1, 1);

        cooldowns.put(player, System.currentTimeMillis());

        return true;
    }
}
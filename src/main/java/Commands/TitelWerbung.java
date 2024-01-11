package Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import java.util.Map;


public class TitelWerbung implements CommandExecutor {
   // private static Map<String, java.time.LocalDateTime> lastAdd = Map.of();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        try {
            String playerName = sender.getName();
            String text = "";
            String prifix = "§x§4§b§f§b§0§4§lP§x§4§4§e§9§0§4§lo§x§3§e§d§7§0§4§lw§x§3§7§c§5§0§5§le§x§3§0§b§3§0§5§lr§x§2§a§a§1§0§5§lC§x§2§3§8§f§0§5§lr§x§1§c§7§d§0§6§la§x§1§6§6§b§0§6§lf§x§0§f§5§9§0§6§lt§r §x§8§4§8§8§8§7|§r ";

            if(strings.length > 0) {
                text = String.join(" ", strings);

                if(text.length() > 40) {
                    sender.sendMessage(prifix + "§7Der text ist zu lang. höchstens 40 Zeichen");
                    return true;
                }
            }
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
                    "title @a title {\"text\":\""+ text + "\", \"bold\":true, \"color\":\"red\"}");
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
                    "title @a subtitle \"" + ChatColor.GREEN + ChatColor.BOLD + "Schaltet Werbung " + ChatColor.BLUE + ChatColor.BOLD + playerName + "\"");
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
                    "title @a times 1 20s 1");
            //title @a subtitle "test"

           // lastAdd.put(playerName, java.time.LocalDateTime.now());
            //Bukkit.getLogger().info("lastAdd size: " + lastAdd.size());
           //   Bukkit.getLogger().info("clock: " + java.time.LocalDateTime.now().toString());

        } catch(Exception e) {
            Bukkit.getLogger().warning(e.getMessage());
        }

        return true;
    }
}

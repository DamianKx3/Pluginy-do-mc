package DpX.dpxPing;


import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class DpxPing extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if (cmd.getName().equalsIgnoreCase("ping")) {
            Player player = (Player) sender;
            int ping = player.getPing();
            double tps = getServer().getTPS()[0];
            player.sendMessage("TPS: " + String.format("%.2f", tps));
            player.sendMessage("ping: " + ping + " ms");
            return true;
        }
        return false;
    }
}

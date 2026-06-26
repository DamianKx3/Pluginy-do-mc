package DpX.duszpasteXIspy;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
public final class DuszpasteXIspy extends JavaPlugin implements Listener{

    @Override
    public void onEnable() {
        File file = new File("plugins/BlockLogger/logs");
        if(file.exists() == false){
            file.mkdirs();
        }
        Bukkit.getPluginManager().registerEvents(this, this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        String nick = event.getPlayer().getName();
        String block = event.getBlock().getType().toString();
        int x = event.getBlock().getX();
        int y = event.getBlock().getY();
        int z = event.getBlock().getZ();
        String czas = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String txt = "[" + czas + "] " + nick + " PLACED " + block + " "+ x +"#" + y + "#" + z;
        Bukkit.getLogger().info("DEBUG: " + txt);
        savefile(txt);
    }
    @EventHandler
    public void onDestroy(BlockBreakEvent event){
        String nick = event.getPlayer().getName();
        String block = event.getBlock().getType().toString();
        int x = event.getBlock().getX();
        int y = event.getBlock().getY();
        int z = event.getBlock().getZ();
        String czas = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String txt = "[" + czas + "] " + nick + " BROKE " + block + " "+ x +"#" + y + "#" + z;
        savefile(txt);
    }
    public void savefile(String txt){
        String Today = LocalDate.now().toString();
        String path = "plugins/BlockLogger/logs/" + Today + ".txt";
        try {
            FileWriter w = new FileWriter(path,true);
            w.write(txt+"\n");
            w.close();
        }catch (IOException e){

        }


    }
}

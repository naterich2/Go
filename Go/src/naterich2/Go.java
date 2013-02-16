package naterich2;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Go extends JavaPlugin  {
	
	private HashMap<String, HashMap<String, Location>> places = new HashMap<String, HashMap<String, Location>>();

	public void onEnable(){
		this.getLogger().info("[Go] v1.1 has been enabled");
		this.getCommand("Go").setExecutor(new Go_Cmd_Executor(this));
	}
	
	public void onDisable(){
		this.getLogger().info("[Go] v1.1 has been disabled");
	}
	
	public void addPlace(Player p, String name, Location l){
		places.put(p.getName(), new HashMap<String, Location>());
		places.get(p.getName()).put(name, l);
	}
	
	public boolean isSet(Player p, String n){
		if(places.get(p.getName()).get(n) != null)
			return true;
		return false;
	}
	
	public void removePlace(Player p, String n){
		places.get(p.getName()).remove(n);
	}
	
	public Location getLocation(Player p, String n){
		return places.get(p.getName()).get(n);
	}
}

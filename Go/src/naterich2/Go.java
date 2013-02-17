package naterich2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

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
		if(places.get(p.getName())== null)
			places.put(p.getName(), new HashMap<String, Location>());
		if(places.get(p.getName()).get(n) != null)
			return true;
		else 
			return false;
	}
	
	public void removePlace(Player p, String n){
		places.get(p.getName()).remove(n);
	}
	
	public Location getLocation(Player p, String n){
		return places.get(p.getName()).get(n);
	}
	public String List(Player p){
		String str = p.getName()+"'s personal warps: ";
		Set<String> keys = places.get(p.getName()).keySet();
		Object[] keyArr = keys.toArray();
		ArrayList<String> keys2 = new ArrayList<String>();
		for(int i = 0; i<keyArr.length; i++)
			keys2.add(((String)keyArr[i]));
		Collections.sort(keys2);
		for(int i = 0; i<keys2.size(); i++)
			str = str+(keys2.get(i)+", ");
		if(str.equals(p.getName()+"'s personal warps: ")){
			str = p.getName()+" has not set any personal warps yet";
		}
		return str;
	}
}

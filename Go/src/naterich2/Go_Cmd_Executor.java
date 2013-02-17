package naterich2;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Go_Cmd_Executor implements CommandExecutor {
	
	public final Go plugin;
	
	public Go_Cmd_Executor(Go g) {
		plugin = g;
	}
	
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("Go") && args[0].equalsIgnoreCase("List") && args[1] != null){
			s.sendMessage(args[1]);
			return true;
		}
		else if(s instanceof Player){
			Player sender = (Player) s;
			if(cmd.getName().equalsIgnoreCase("Go")){
				if(args[0].equalsIgnoreCase("set")){
					if(args[1]!= null){
						if(plugin.isSet(sender, args[1]))
							sender.sendMessage("This warp has already been set, try another name");
						else{
							plugin.addPlace(sender, args[1], sender.getLocation());
							return true;
						}
					} else {
						return false;
					}
				} else if(args[0].equalsIgnoreCase("del")){
					if(args[1]!=null){
						if(plugin.isSet(sender, args[1])){
							plugin.removePlace(sender, args[1]);
							return true;
						}	
						else 
							s.sendMessage("This personal warp has not been set, you cannot remove a warp that has not been set");
					} else {
						return false;
					}
				} else if(args[0].equalsIgnoreCase("list")) {
					sender.sendMessage(plugin.List(sender));
				} else {
					if(args[0] != null){
						if(plugin.isSet(sender, args[0]) == true){
							sender.teleport(plugin.getLocation(sender, args[0]));
							return true;
						}
						else
							sender.sendMessage("That warp is not set, you cannot teleport to a warp that does not exist");
					} else {
						return false;
					}
				}
			}
		} else {
			s.sendMessage("This command can only be run by a player");
		}
		return false;
	}

}

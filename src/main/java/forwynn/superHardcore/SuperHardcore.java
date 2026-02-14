package forwynn.superHardcore;

import forwynn.superHardcore.listeners.GameMechanics;
import org.bukkit.plugin.java.JavaPlugin;

public final class SuperHardcore extends JavaPlugin
{

	@Override
	public void onEnable()
	{
		getServer().getPluginManager().registerEvents(new GameMechanics(), this);
		getLogger().info("SuperHardcore Enabled");

	}

	@Override
	public void onDisable()
	{
		getLogger().info("SuperHardcore disabled");
	}
}

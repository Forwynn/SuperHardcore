package forwynn.superHardcore.listeners;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class GameMechanics implements Listener
{
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		event.getPlayer()
				.getAttribute(Attribute.MAX_HEALTH)
				.setBaseValue(10);
	}

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event)
	{
		if (event.getDamager() instanceof Monster && event.getEntity() instanceof Player)
			event.setDamage(event.getDamage() * 2.5);

		if (event.getDamager() instanceof Player)
			event.setDamage(event.getDamage() * 0.5);
	}

	@EventHandler
	public void onCreeperSpawn(EntitySpawnEvent event)
	{
		if (event.getEntity() instanceof Creeper creeper)
		{
			creeper.setPowered(true);
		}
	}
}

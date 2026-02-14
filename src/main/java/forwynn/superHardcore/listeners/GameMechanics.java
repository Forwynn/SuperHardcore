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
				.setBaseValue(6);
	}

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event)
	{
		if (event.getDamager() instanceof Monster  && event.getEntity() instanceof Player)
			event.setDamage(event.getDamage() * 3.25);

		if (event.getDamager() instanceof Player)
			event.setDamage(event.getDamage() * 0.4);

	}

	@EventHandler
	public void onCreeperSpawn(EntitySpawnEvent event)
	{
		if (event.getEntity() instanceof Creeper creeper)
		{
			creeper.setPowered(true);
			creeper.setFuseTicks(1);
			creeper.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0.65);
			creeper.getAttribute(Attribute.FOLLOW_RANGE).setBaseValue(10);
		}
	}
}

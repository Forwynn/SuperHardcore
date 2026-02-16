package forwynn.superHardcore.listeners;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class GameMechanics implements Listener
{

	private final Random random = new Random();

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		event.getPlayer()
				.getAttribute(Attribute.MAX_HEALTH)
				.setBaseValue(8);

		event.getPlayer().setHealthScaled(true);
		event.getPlayer().setHealthScale(20.0);
	}


	@EventHandler
	public void onShieldDamage(EntityDamageByEntityEvent event)
	{
		if (!(event.getEntity() instanceof Player)) return;

		if (event.getDamage(EntityDamageEvent.DamageModifier.BLOCKING) < 0)
		{

			double originalDamage = event.getDamage(EntityDamageEvent.DamageModifier.BASE);

			double damageToThru = originalDamage * 0.25;

			event.setDamage(EntityDamageEvent.DamageModifier.BLOCKING, -(originalDamage - damageToThru));
		}
	}

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event)
	{

		if (event.getDamager() instanceof Monster && event.getEntity() instanceof Player)
		{
			event.setDamage(event.getDamage() * 3);
		}

		if (event.getDamager() instanceof Player)
		{
			event.setDamage(event.getDamage() * 0.45);
		}
	}

	@EventHandler
	public void onEntitySpawn(EntitySpawnEvent event)
	{
		if (event.getEntity() instanceof Creeper creeper)
		{
			creeper.setPowered(true);
			creeper.getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0.45);
			creeper.getAttribute(Attribute.KNOCKBACK_RESISTANCE).setBaseValue(10);
		}

		if (event.getEntity() instanceof Spider spider)
		{
			PotionEffectType[] effects = {
					PotionEffectType.SPEED,
					PotionEffectType.STRENGTH,
					PotionEffectType.REGENERATION,
					PotionEffectType.INVISIBILITY
			};
			PotionEffectType randomEffect = effects[random.nextInt(effects.length)];
			spider.addPotionEffect(new PotionEffect(randomEffect, Integer.MAX_VALUE, 2));
		}
	}

	@EventHandler
	public void onSkeletonBurn(EntityCombustEvent event)
	{
		if (event instanceof EntityCombustByBlockEvent || event instanceof EntityCombustByEntityEvent)
		{
			return;
		}

		if (!(event.getEntity() instanceof Skeleton))
		{
			return;
		}

		LivingEntity mob = (LivingEntity) event.getEntity();

		if (mob.getEquipment() != null)
		{
			ItemStack helmet = mob.getEquipment().getHelmet();
			if (helmet != null && helmet.getType() != Material.AIR)
			{
				return;
			}
		}

		event.setCancelled(true);
	}

	@EventHandler
	public void onEndermanWaterDamage(EntityDamageEvent event)
	{
		if (event.getEntity() instanceof Enderman)
		{
			if (event.getCause() == EntityDamageEvent.DamageCause.DROWNING)
			{
				event.setCancelled(true);
			}
		}
	}


}
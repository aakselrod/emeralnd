package net.mcreator.emeralnd.procedures;

import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;

import net.mcreator.emeralnd.entity.WanderingBitcoinerEntity;

import javax.annotation.Nullable;

@EventBusSubscriber
public class WanderingBitcoinerNaturalEntitySpawningConditionProcedure {
	@SubscribeEvent
	public static void onEntitySpawned(EntityJoinLevelEvent event) {
		execute(event, event.getLevel(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ());
	}

	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		return execute(null, world, x, y, z);
	}

	private static boolean execute(@Nullable Event event, LevelAccessor world, double x, double y, double z) {
		return world.canSeeSkyFromBelowWater(BlockPos.containing(x, y, z)) && world instanceof Level _lvl1 && _lvl1.isDay()
				&& !(!world.getEntitiesOfClass(WanderingBitcoinerEntity.class, AABB.ofSize(new Vec3(x, y, z), 128, 128, 128), e -> true).isEmpty());
	}
}

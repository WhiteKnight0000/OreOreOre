package com.whiteknight.oreoreore.game.elements.entity;

import com.whiteknight.oreoreore.game.register.ModUseItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class GarnetGemEntity extends ThrowableItemProjectile {
    public GarnetGemEntity(EntityType<? extends GarnetGemEntity> entityType, Level level) {
        super(entityType, level);
    }

    public GarnetGemEntity(Level level, LivingEntity shooter) {
        super(ModUseItems.GARNET_GEM_ENTITY.get(), shooter, level);
    }

    public GarnetGemEntity(Level level, double x, double y, double z) {
        super(ModUseItems.GARNET_GEM_ENTITY.get(), x, y, z, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ModUseItems.MAGIC_GEM_OF_GARNET.get();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (!this.level().isClientSide) {
            hitLogic(result.getLocation());
            this.discard();
        }
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide) {
            hitLogic(result.getLocation());
            // 着弾時にエンティティをワールドから消去する
            this.discard();
        }
    }

    private static final float RANGE = 10f;

    private void hitLogic(Vec3 loc){
        if(!(this.level() instanceof ServerLevel serverLevel)){
            return;
        }

        //爆発の威力の備考 : （TNTは4.0F、クリーパーは3.0F）

        serverLevel.explode(
                this,
                null,
                new ReducedDamageExplosionCalculator(),
                loc.x,
                loc.y,
                loc.z,
                (RANGE-2)/2f,
                true,
                Level.ExplosionInteraction.NONE
        );
        serverLevel.sendParticles(
                ParticleTypes.EXPLOSION_EMITTER,
                loc.x, loc.y, loc.z,
                1,
                0, 0, 0,
                0.0
        );
        serverLevel.sendParticles(
                ParticleTypes.LAVA,
                loc.x, loc.y, loc.z,
                10,
                2.0, 2.0, 2.0,
                0.0
        );
        AABB boundingBox = AABB.ofSize(loc, RANGE*2, RANGE*2, RANGE*2);
        List<LivingEntity> entities = serverLevel.getEntitiesOfClass(
                LivingEntity.class,
                boundingBox,
                entity -> true
        );
        for (LivingEntity entity : entities) {
            entity.setRemainingFireTicks(200);
        }
    }

    /**
     * ダメージ低減処理
     */
    public static class ReducedDamageExplosionCalculator extends ExplosionDamageCalculator {
        @Override
        public float getEntityDamageAmount(Explosion explosion, Entity entity) {
            return super.getEntityDamageAmount(explosion, entity) * 0.3f;
        }
    }
}

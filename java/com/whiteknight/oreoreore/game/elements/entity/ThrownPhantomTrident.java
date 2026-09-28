package com.whiteknight.oreoreore.game.elements.entity;

import com.whiteknight.oreoreore.game.register.ModUseItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class ThrownPhantomTrident extends ThrownTrident {
    public ThrownPhantomTrident(EntityType<? extends ThrownPhantomTrident> type, Level level) {
        super(type, level);
    }

    public ThrownPhantomTrident(Level level, LivingEntity shooter, ItemStack stack) {
        super(ModUseItems.THROWN_PHANTOM_TRIDENT.get(), level);
        this.setOwner(shooter);
        this.setPos(shooter.getX(), shooter.getEyeY() - 0.1, shooter.getZ());
        this.pickup = AbstractArrow.Pickup.DISALLOWED;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        this.discard();
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        this.discard();
    }
}

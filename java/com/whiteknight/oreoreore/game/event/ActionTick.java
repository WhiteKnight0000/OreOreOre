package com.whiteknight.oreoreore.game.event;

import com.whiteknight.oreoreore.game.elements.item.DoubleJumpArmorItem;
import com.whiteknight.oreoreore.game.util.DoubleJumpPayload;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

public class ActionTick {
    private static boolean hasDoubleJumped = false;
    private static boolean ready = false;

    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;

        if (player == null) return;

        //ダブルジャンプ処理
        boolean isJumping = mc.options.keyJump.isDown();
        boolean wearing = false;
        float coef = 1f;
        if(player.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof DoubleJumpArmorItem equip){
            coef = equip.jump_power;
            wearing = true;
        }
        if(player.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof DoubleJumpArmorItem equip){
            coef = equip.jump_power;
            wearing = true;
        }
        if(player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof DoubleJumpArmorItem equip){
            coef = equip.jump_power;
            wearing = true;
        }if(player.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof DoubleJumpArmorItem equip){
            coef = equip.jump_power;
            wearing = true;
        }

        if (player.onGround()) {
            hasDoubleJumped = false;
            ready = false;
            return;
        }

        if (wearing && isJumping && ready && !hasDoubleJumped) {
            // ジャンプ処理
            Vec3 movement = player.getDeltaMovement();
            player.setDeltaMovement(movement.x, 0.5D*coef, movement.z);
            hasDoubleJumped = true;

            //ダメージ処理
            player.fallDistance = 0.0F; //クライアント側の落下処理
            PacketDistributor.sendToServer(new DoubleJumpPayload()); //サーバー側の落下処理

            //演出
            player.level().addParticle(ParticleTypes.CLOUD, player.getX(), player.getY(), player.getZ(), 0, -0.1, 0);
        }

        ready = !isJumping;
    }
}

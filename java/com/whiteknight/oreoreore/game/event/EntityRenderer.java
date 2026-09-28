package com.whiteknight.oreoreore.game.event;

import com.whiteknight.oreoreore.game.register.ModUseItems;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.entity.ThrownTridentRenderer;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class EntityRenderer {
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModUseItems.GARNET_GEM_ENTITY.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModUseItems.THROWN_PHANTOM_TRIDENT.get(), ThrownTridentRenderer::new);
    }
}

package com.noxcrew.noxesium.mixin.render;

import com.noxcrew.noxesium.feature.render.cache.bossbar.BossBarCache;
import net.minecraft.client.gui.components.BossHealthOverlay;
import net.minecraft.client.gui.components.LerpingBossEvent;
import net.minecraft.network.protocol.game.ClientboundBossEventPacket;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.UUID;

@Mixin(BossHealthOverlay.class)
public class BossHealthOverlayMixin {

    @Shadow @Final private Map<UUID, LerpingBossEvent> events;

    @Inject(method = "update", at = @At(value = "TAIL"))
    private void update(ClientboundBossEventPacket packet, CallbackInfo ci) {
        BossBarCache.getInstance().createCache();
    }
    @Inject(method = "reset", at = @At(value = "TAIL"))
    private void reset(CallbackInfo ci) {
        if (this.events.isEmpty()) return;
        BossBarCache.getInstance().createCache();
    }
}

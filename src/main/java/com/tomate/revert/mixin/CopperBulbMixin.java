package com.tomate.revert.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CopperBulbBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CopperBulbBlock.class)
public abstract class CopperBulbMixin extends Block {
    @Shadow
    public abstract void checkAndFlip(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos);

    public CopperBulbMixin(Properties properties) {
        super(properties);
    }

    @Redirect(method = "neighborChanged", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/CopperBulbBlock;checkAndFlip(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;)V"))
    void tick(CopperBulbBlock instance, BlockState blockState, ServerLevel level, BlockPos blockPos) {
        level.scheduleTick(blockPos, instance, 1);
    }


    @Override
    public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        checkAndFlip(blockState, serverLevel, blockPos);
    }
}
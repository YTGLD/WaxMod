package com.ytgld.the_wax.block.init;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CaveVinesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;

public class BlockBase extends Block {
    public BlockBase(Properties properties) {
        super(properties);
    }
    public static InteractionResult use(final Entity sourceEntity, final BlockState state, final Level level, final BlockPos pos) {
        if ((Boolean)state.getValue(CaveVinesBlock.BERRIES)) {
            if (level instanceof ServerLevel) {
                ServerLevel serverLevel = (ServerLevel)level;
                float pitch = Mth.randomBetween(serverLevel.getRandom(), 0.8F, 1.2F);
                serverLevel.playSound((Entity)null, pos, SoundEvents.WOOD_HIT, SoundSource.BLOCKS, 1.0F, pitch);
                BlockState newState = (BlockState)state.setValue(CaveVinesBlock.BERRIES, false);
                serverLevel.setBlock(pos, newState, 2);
                serverLevel.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(sourceEntity, newState));
            }

            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.PASS;
        }
    }
}

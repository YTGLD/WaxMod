package com.ytgld.the_wax.block;

import com.ytgld.the_wax.WaxMod;
import com.ytgld.the_wax.block.earth.GiantRhizomeBlockEntity;
import com.ytgld.the_wax.block.earth.MelonRootBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class WaxBlockEntityType {
    public static final BlockEntityType<MelonRootBlockEntity> MelonRootBlockEntity_ =
            register("melon_root_block_entity", MelonRootBlockEntity::new, BlockInit.MelonRoot_);
    public static final BlockEntityType<GiantRhizomeBlockEntity> GiantRhizomeBlockEntity_ =
            register("giant_rhizome_block_entity", GiantRhizomeBlockEntity::new, BlockInit.GiantRhizome_);


    private static <T extends BlockEntity> BlockEntityType<T> register(
            String name,
            FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory,
            Block... blocks
    ) {
        Identifier id = Identifier.fromNamespaceAndPath(WaxMod.MOD_ID, name);
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id, FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
    }
    public static void init(){

    }
}

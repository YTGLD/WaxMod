package com.ytgld.the_wax.block.earth;

import net.minecraft.util.StringRepresentable;
import org.jspecify.annotations.NonNull;

public enum GiantRhizomePart implements StringRepresentable {
    ROOT("root"), EXTENSION("extension");

    private final String name;

    GiantRhizomePart(String name) {
        this.name = name;
    }

    @Override
    public @NonNull String getSerializedName() {
        return this.name;
    }
}
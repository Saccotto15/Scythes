package net.thevortex8196.scythes.util;

import net.minecraft.data.client.Model;
import net.minecraft.data.client.TextureKey;
import net.minecraft.util.Identifier;
import net.thevortex8196.scythes.Scythes;

import java.util.Optional;

public class ModModels {
    public static final Model SCYTHE = item("scythe", TextureKey.LAYER0);
    public static final Model FRONT_LIT = item("front_lit", TextureKey.LAYER0);

    private static Model item(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(Identifier.of(Scythes.MOD_ID, "item/" + parent)), Optional.empty(), requiredTextureKeys);
    }
}

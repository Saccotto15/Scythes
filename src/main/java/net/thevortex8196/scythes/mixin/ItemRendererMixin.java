package net.thevortex8196.scythes.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.thevortex8196.scythes.item.custom.ScytheItem;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

    @Shadow
    @Final
    private ItemModels models;

    @Shadow
    public abstract ItemModels getModels();

    @ModifyVariable(
            method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V",
            at = @At(value = "HEAD"),
            argsOnly = true
    )

    public BakedModel renderItem(BakedModel bakedModel, @Local(argsOnly = true) ItemStack stack, @Local(argsOnly = true) ModelTransformationMode renderMode) {
        if (stack.getItem() instanceof ScytheItem && (
                renderMode == ModelTransformationMode.GUI ||
                        renderMode == ModelTransformationMode.GROUND ||
                        renderMode == ModelTransformationMode.FIXED
        )) {
            ModelIdentifier modelId = ModelIdentifier.ofInventoryVariant(
                    Identifier.of(stack.getItem().toString()) // optional: override this logic if needed
            );
            return getModels().getModelManager().getModel(modelId);
        }

        return bakedModel;
    }


    @ModifyVariable(
            method = "getModel",
            at = @At(value = "STORE"),
            ordinal = 1
    )

    public BakedModel getHeldItemModelMixin(BakedModel bakedModel, @Local(argsOnly = true) ItemStack stack) {
        if (stack.getItem() instanceof ScytheItem) {
            ModelIdentifier modelId = ModelIdentifier.ofInventoryVariant(
                    Identifier.of(stack.getItem() + "_3d") // optional: override this logic if needed
            );
            return this.models.getModelManager().getModel(modelId);
        }

        return bakedModel;
    }
}
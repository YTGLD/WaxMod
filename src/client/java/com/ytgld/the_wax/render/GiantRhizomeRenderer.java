package com.ytgld.the_wax.render;

import com.mojang.blaze3d.vertex.*;
import com.ytgld.the_wax.block.earth.GiantRhizome;
import com.ytgld.the_wax.block.earth.GiantRhizomeBlockEntity;
import com.ytgld.the_wax.block.earth.GiantRhizomePart;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.jspecify.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class GiantRhizomeRenderer implements BlockEntityRenderer<GiantRhizomeBlockEntity,GiantRhizomeRendererState> {

    public GiantRhizomeRenderer(BlockEntityRendererProvider.Context context) {

    }

    private void drawLine(VertexConsumer vertexBuilder, Matrix4fc matrix,
                          float x1, float y1, float z1, float x2, float y2, float z2) {
        vertexBuilder.addVertex(matrix, x1, y1, z1).setColor(225, 190, 105, 255).setLight(255).setNormal(2,2,2).setLineWidth(4);
        vertexBuilder.addVertex(matrix, x2, y2, z2).setColor(225, 190, 105, 255).setLight(255).setNormal(2,2,2).setLineWidth(4);
    }

    @Override
    public GiantRhizomeRendererState createRenderState() {
        return new GiantRhizomeRendererState();
    }

    @Override
    public void extractRenderState(GiantRhizomeBlockEntity blockEntity, GiantRhizomeRendererState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
        state.blockEntity = blockEntity;
    }

    @Override
    public void submit(GiantRhizomeRendererState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        if (state.blockEntity.getBlockState().getValue(GiantRhizome.PART) == GiantRhizomePart.ROOT) {
            poseStack.pushPose();
            submitNodeCollector.submitCustomGeometry(poseStack, RenderTypes.lines(), (pose, vertexBuilder) -> {
                Matrix4f matrix = pose.pose();

                drawLine(vertexBuilder, matrix, 0, 0, 0, 2, 0, 0);
                drawLine(vertexBuilder, matrix, 0, 0, 0, 0, 2, 0);

                drawLine(vertexBuilder, matrix, 0, 0, 0, 0, 0, 2);
                drawLine(vertexBuilder, matrix, 2, 0, 0, 2, 2, 0);

                drawLine(vertexBuilder, matrix, 2, 0, 0, 2, 0, 2);
                drawLine(vertexBuilder, matrix, 0, 2, 0, 2, 2, 0);

                drawLine(vertexBuilder, matrix, 0, 2, 0, 0, 2, 2);
                drawLine(vertexBuilder, matrix, 0, 0, 2, 2, 0, 2);

                drawLine(vertexBuilder, matrix, 0, 0, 2, 0, 2, 2);
                drawLine(vertexBuilder, matrix, 2, 0, 2, 2, 2, 2);

                drawLine(vertexBuilder, matrix, 2, 2, 0, 2, 2, 2);
                drawLine(vertexBuilder, matrix, 0, 2, 2, 2, 2, 2);
            });
            poseStack.popPose();
        }
    }
}
package com.mrbysco.lunar.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class MoonHandler {
	private static String moonID = null;
	private static float[] moonColor = null;
	private static Matrix4f moonScale;
	private static ResourceLocation moonTexture;

	public static void colorTheMoon(ClientLevel level, PoseStack poseStack, Matrix4f matrix4f, float f, Camera camera) {
		if (isEventActive()) {
			RenderSystem.setShaderColor(moonColor[0], moonColor[1], moonColor[2], 1.0F);
		}
	}

	public static void setMoon(String eventID, int color, float scale) {
		int r = (color >> 16) & 0xFF;
		int g = (color >> 8) & 0xFF;
		int b = (color >> 0) & 0xFF;
		moonColor = new float[]{(float) r / 255.0F, (float) g / 255.0F, (float) b / 255.0F};
		moonID = eventID;
		if (scale != 1.0F) {
			moonScale = Matrix4f.createScaleMatrix(scale, 1F, scale);
		}
	}

	public static void setMoonTexture(@Nullable ResourceLocation location) {
		moonTexture = location;
	}

	public static void disableMoon() {
		moonColor = null;
		moonID = null;
		moonScale = null;
		moonTexture = null;
	}

	public static boolean isEventActive() {
		return moonID != null && moonColor != null;
	}

	public static boolean isMoonScaled() {
		return moonScale != null;
	}

	public static ResourceLocation getMoonTexture() {
		return moonTexture;
	}

	public static Matrix4f getMoonScale() {
		return moonScale;
	}
}

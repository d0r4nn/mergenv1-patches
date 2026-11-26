package com.d0r4nn.mergenv1.patches.tiktok

import app.morphe.patcher.extensions.InstructionExtensions.addInstruction
import app.morphe.patcher.patch.patch
import com.d0r4nn.mergenv1.patches.shared.Constants.COMPATIBILITY_TIKTOK
import com.d0r4nn.mergenv1.patches.shared.Constants.COMPATIBILITY_TIKTOK_ASIA

private const val BOOTSTRAP_CLASS = "Lcom/sarp/tiktokliquidglass/LiquidGlassBootstrap;"

@Suppress("unused")
val mergenV1Patch = patch(
    name = "MergenV1 UI Enhancer",
    description = "Modern UI & interaction enhancer for stock TikTok and TikTok You (Android 13+): 120Hz spring animations with selectable profiles, swipe gestures, radial action menu, creator peek drawer, quick control sheet, camera-notch alerts, kinetic seekbar, 1:1 scrubbed Danmaku comments, and switchable Obsidian & Liquid Glass themes.",
    default = true
) {
    compatibleWith(
        COMPATIBILITY_TIKTOK,
        COMPATIBILITY_TIKTOK_ASIA
    )

    extendWith("extensions/mergenv1.mpe")

    execute {
        val superOnCreateMatch = AwemeHostApplicationOnCreateFingerprint.instructionMatches.last()
        AwemeHostApplicationOnCreateFingerprint.method.addInstruction(
            superOnCreateMatch.index + 1,
            "invoke-static { p0 }, $BOOTSTRAP_CLASS->init(Landroid/app/Application;)V"
        )
    }
}

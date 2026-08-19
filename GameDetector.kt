package com.example.monkeyhopbot

import android.graphics.Bitmap

/**
 * Extension point for game-specific computer vision.
 *
 * Feed captured frames into detect() and return true when a jump should occur.
 * The current starter returns false because reliable coordinates depend on
 * the exact Monkey Hop version, resolution and orientation.
 */
object GameDetector {
    fun detect(frame: Bitmap): Boolean {
        return false
    }
}

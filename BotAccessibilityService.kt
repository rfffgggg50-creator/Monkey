package com.example.monkeyhopbot

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.graphics.Path
import android.os.Handler
import android.os.Looper
import android.view.accessibility.AccessibilityEvent

class BotAccessibilityService : AccessibilityService() {
    companion object {
        var instance: BotAccessibilityService? = null
    }

    private val handler = Handler(Looper.getMainLooper())
    private var loop: Runnable? = null

    override fun onServiceConnected() {
        super.onServiceConnected()
        instance = this
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {}
    override fun onInterrupt() {}

    fun startBotLoop() {
        stopBotLoop()
        loop = object : Runnable {
            override fun run() {
                if (!BotController.running) return
                // Starter timing-based controller. Replace with detector-triggered
                // calls after calibrating the exact game screen.
                tapCenter()
                handler.postDelayed(this, BotConfig.jumpDelayMs)
            }
        }
        handler.post(loop!!)
    }

    fun stopBotLoop() {
        loop?.let(handler::removeCallbacks)
        loop = null
    }

    private fun tapCenter() {
        val dm = resources.displayMetrics
        val x = dm.widthPixels / 2f
        val y = dm.heightPixels * 0.78f
        val path = Path().apply { moveTo(x, y) }
        val gesture = GestureDescription.Builder()
            .addStroke(GestureDescription.StrokeDescription(path, 0, 60))
            .build()
        dispatchGesture(gesture, null, null)
    }

    override fun onDestroy() {
        stopBotLoop()
        instance = null
        super.onDestroy()
    }
}

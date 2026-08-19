package com.example.monkeyhopbot

object BotController {
    @Volatile var running = false

    fun start() {
        running = true
        BotAccessibilityService.instance?.startBotLoop()
    }

    fun stop() {
        running = false
        BotAccessibilityService.instance?.stopBotLoop()
    }
}

package com.example.monkeyhopbot

import android.app.Activity
import android.content.Intent
import android.media.projection.MediaProjectionManager
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : Activity() {
    private val requestCode = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(40, 40, 40, 40)
        }

        val title = TextView(this).apply {
            text = "🐒 Monkey Hop Bot"
            textSize = 28f
        }

        val info = TextView(this).apply {
            text = "1. Enable Accessibility Service\n2. Start screen capture\n3. Start bot"
            textSize = 17f
            setPadding(0, 20, 0, 20)
        }

        val accessibility = Button(this).apply {
            text = "Enable Accessibility"
            setOnClickListener {
                startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
            }
        }

        val capture = Button(this).apply {
            text = "Allow Screen Capture"
            setOnClickListener {
                val mgr = getSystemService(MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
                startActivityForResult(mgr.createScreenCaptureIntent(), requestCode)
            }
        }

        val delayLabel = TextView(this).apply {
            text = "Jump interval: 550 ms"
        }

        val delay = SeekBar(this).apply {
            max = 900
            progress = 450
            setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(s: SeekBar?, p: Int, fromUser: Boolean) {
                    delayLabel.text = "Jump interval: ${100 + p} ms"
                    BotConfig.jumpDelayMs = 100L + p
                }
                override fun onStartTrackingTouch(s: SeekBar?) {}
                override fun onStopTrackingTouch(s: SeekBar?) {}
            })
        }

        val start = Button(this).apply {
            text = "▶ START BOT"
            setOnClickListener { BotController.start() }
        }

        val stop = Button(this).apply {
            text = "⏹ STOP BOT"
            setOnClickListener { BotController.stop() }
        }

        layout.addView(title)
        layout.addView(info)
        layout.addView(accessibility)
        layout.addView(capture)
        layout.addView(delayLabel)
        layout.addView(delay)
        layout.addView(start)
        layout.addView(stop)
        setContentView(layout)
    }
}

object BotConfig {
    @Volatile var jumpDelayMs: Long = 550L
}

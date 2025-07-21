package com.example.supportcall.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FakeDialerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val number = intent.data?.schemeSpecificPart ?: ""
        val textView = TextView(this).apply {
            text = "Fake Dialer: $number"
            textSize = 22f
            setPadding(32, 120, 32, 32)
        }
        val cancelBtn = Button(this).apply {
            text = "Cancel"
            setOnClickListener {
                setResult(Activity.RESULT_CANCELED)
                finish()
            }
        }
        val layout = android.widget.LinearLayout(this).apply {
            orientation = android.widget.LinearLayout.VERTICAL
            addView(textView)
            addView(cancelBtn)
        }
        setContentView(layout)
    }

    companion object {
        const val ACTION_FAKE_CALL = "android.intent.action.CALL"
        fun newIntent(number: String): Intent =
            Intent(ACTION_FAKE_CALL, Uri.parse("tel:$number"))
    }
} 
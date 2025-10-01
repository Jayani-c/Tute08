package com.example.tute8

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val textView: TextView = findViewById(R.id.txtV1)
        val button: Button = findViewById(R.id.Count)
        val viewModel = ViewModelProvider(this)[MainActivityData::class.java]
//        textView.text = count.toString()

//        button.setOnClickListener {
//            count++
//            textView.text = count.toString()
//        }
        textView.text = viewModel.count.value.toString()
        button.setOnClickListener {
            viewModel.increment()
        }
        viewModel.count.observe(this) {
            textView.text = it.toString()
        }

    }
}



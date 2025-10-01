package com.example.tute8

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {

    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        val button2: Button = findViewById(R.id.OpenMainActivity)
        val button3:Button = findViewById(R.id.Call)
        val button4:Button = findViewById(R.id.SMS)
        val button5:Button = findViewById(R.id.WebBrowser)
        val button6:Button = findViewById(R.id.Email)
        val button7:Button = findViewById(R.id.Camera)

        imageView = findViewById(R.id.imageView)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        button2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        button3.setOnClickListener {
            val number = "+94717123456"
            val uri = Uri.parse("tel:$number")
            val intent = Intent(Intent.ACTION_DIAL, uri)
            startActivity(intent)
        }

        button4.setOnClickListener {
            val number = "+94717123456"
            val smsText = "Welcome to MAD 2025"
            val uri = Uri.parse("smsto:$number")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", smsText)
            startActivity(intent)
        }

        // Button5 – Open Web Browser
        button5.setOnClickListener {
            val url = "https://www.sliit.lk/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        button6.setOnClickListener {
            val mailTo = arrayOf("abc@email.com")
            val subject = "Test Email"
            val mailBody = "This is the test email body"
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "message/rfc822"
                putExtra(Intent.EXTRA_EMAIL, mailTo)
                putExtra(Intent.EXTRA_SUBJECT, subject)
                putExtra(Intent.EXTRA_TEXT, mailBody)
            }
            startActivity(intent)
        }

        val thumbnailLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == RESULT_OK) {
                val data = it.data
                val imageBitmap = data?.extras?.get("data") as? Bitmap
                imageView.setImageBitmap(imageBitmap)
            }
        }

        button7.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            thumbnailLauncher.launch(intent)
        }
    }
}
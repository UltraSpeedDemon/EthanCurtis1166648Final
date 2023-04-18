package com.ultraspeeddemon.ethancurtis1166648final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ultraspeeddemon.ethancurtis1166648final.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //view bucket list button
        binding.viewBucketListButton.setOnClickListener {//when the button is clicked for the create tournament
            //by creating an explicit intent
            val intent = Intent(this, ViewBucketList::class.java)
            startActivity(intent)
        }

        //add bucket list button
        binding.addBucketListItemButton.setOnClickListener {//when the button is clicked for the create tournament
            //by creating an explicit intent
            val intent = Intent(this, AddBucketList::class.java)
            startActivity(intent)
        }
    }
}
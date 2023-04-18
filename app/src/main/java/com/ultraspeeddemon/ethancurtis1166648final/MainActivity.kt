package com.ultraspeeddemon.ethancurtis1166648final

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ultraspeeddemon.ethancurtis1166648final.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




    }
}
package com.ultraspeeddemon.ethancurtis1166648final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ultraspeeddemon.ethancurtis1166648final.databinding.ActivityViewBucketListBinding

class ViewBucketList : AppCompatActivity() {
    private lateinit var binding: ActivityViewBucketListBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewBucketListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        //return to main activity
        binding.backButton.setOnClickListener {//when the button is clicked
            finish() //finish the current activity
        }

        /** not working?
        val viewModel: BucketListViewModel by viewModels() //error for some reason...
        viewModel.getBucketList().observe(this, { bucketList ->
            binding.recyclerView.adapter = BucketListAdapter(this, bucketList, this)
        })
         **/
    }
}
package com.ultraspeeddemon.ethancurtis1166648final

import android.app.usage.NetworkStats.Bucket
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class BucketListViewModel {
        private var bucketList = MutableLiveData<List<BucketList>>() // Create a mutable live data object

        // Connect to the Firestore DB and update "tournaments" with all the tournaments for the
        // logged in user
        init {
            val userID = Firebase.auth.currentUser?.uid

            val db = FirebaseFirestore.getInstance().collection("bucketList") //connect to the tournaments collection
                .whereEqualTo("uid", userID)//description generates the user id for the tournament
                .orderBy("destination")
                .addSnapshotListener { documents, exception ->
                    if (exception != null) { // If there is an exception
                        Log.w("DB_Response", "Listen failed ${exception.code}") // Log the exception
                        return@addSnapshotListener // Return if there is an exception
                    }
                    documents?.let {
                        val bucketListList = ArrayList<BucketList>() // Create an empty list of tournaments
                        for (document in documents) { // Loop through the documents in the collection
                            Log.i("DB_Response", "${document.data}") // Log the data for each document
                            val bucket = document.toObject(BucketList::class.java)// Convert the document to a tournament object
                            bucketListList.add(bucket)// Add the tournament to the list
                        }
                        bucketList.value = bucketListList // Update the live data object with the list of tournaments
                    }
                }
        }

        // Returns the list of tournaments in the system for the logged in user
        fun getBucketList(): LiveData<List<BucketList>> { // Create a function to return tournaments
            return bucketList // Return the tournaments
        }
    }
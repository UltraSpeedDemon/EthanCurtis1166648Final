package com.ultraspeeddemon.ethancurtis1166648final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.ultraspeeddemon.ethancurtis1166648final.databinding.ActivityAddBucketListBinding

class AddBucketList : AppCompatActivity() {

    private lateinit var binding: ActivityAddBucketListBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBucketListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth //get the current user

        //next buttons
        binding.addDestinationButton.setOnClickListener {
            //and store it in Firestore
            var destination = binding.enterDestinationName.text.toString()
            var ranking = binding.top10Spinner.selectedItem.toString()
            var description = binding.enterDescription.text.toString()

            if (destination.isNotEmpty() && description.isNotEmpty()) {
                var uID = auth.currentUser?.uid

                //create a bucketlist and send to Firestore
                var bucketList = BucketList(destination, description, ranking, uID)

                //connect to Firebase Firestore
                //if the collection doesn't exist, it will add it
                //if the collection exists, it will access it

                val db = FirebaseFirestore.getInstance().collection("bucketList")

                //create a unique ID for the document`
                val documentId = db.document().id

                db.document(documentId).set(bucketList)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Bucket List Added", Toast.LENGTH_LONG).show()
                        binding.enterDestinationName.text.clear() //clear the text box
                        binding.enterDescription.text.clear() //clear the text box

                        //read from the DB and log the data
                        db.get().addOnSuccessListener { collection -> //get the collection
                            for (document in collection) {
                                Log.i("Firestore", "${document.id} => ${document.data}")
                            }
                        }
                            .addOnFailureListener { exception -> Log.w("DB_Issue", exception.localizedMessage)
                            } //if there is an error, do nothing
                    }
                    .addOnFailureListener { exception -> Log.w("DB_Issue", exception.localizedMessage)
                    }

                //after clicking the next and creating the bucketlist, go to the next screen
                val intent = Intent(this, MainActivity::class.java) //create the intent and sends user to the main activity
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Destination and Description Is Required!", Toast.LENGTH_LONG).show() //show a toast
            }
        }
    }
}
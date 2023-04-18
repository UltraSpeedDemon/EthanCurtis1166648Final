package com.ultraspeeddemon.ethancurtis1166648final

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BucketListAdapter (val context: Context, // This is the context (activity) that is using this adapter
                            val bucketList: List<BucketList>, // This is the list of bucketlists that we are going to display
                            val itemListener: BucketListItemListener) : RecyclerView.Adapter<BucketListAdapter.BucketListViewHolder>() {


        /**
         * This class is used to allow us to connect / access the elements in the item_tournament layout file
         */
        inner class BucketListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) { // This is the view holder that will hold the view elements
            val bucketListTextView = itemView.findViewById<TextView>(R.id.bucketListTextView) // This is the TextView that will hold the tournament name
        }

        /**
         * This connects to the item_tournament layout file and "inflates" the objects (similar to how ViewBinding inflates the
         * view elements)
         */
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BucketListViewHolder { // This is the method that will create the view holder
            val inflater = LayoutInflater.from(parent.context) // This is the layout inflater that will inflate the layout
            val view = inflater.inflate(R.layout.item_bucketlist, parent, false) // This is the view that will be inflated
            return BucketListViewHolder(view) // This is the view holder that will be returned
        }

        /**
         * Bind (or connect) the data from our tournaments with the view. This is populating
         * the information into the TextView objects in item_tournament.xml
         */
        override fun onBindViewHolder(viewHolder: BucketListViewHolder, position: Int) { // This is the method that will bind the data to the view holder
            val bucketlists = bucketList[position]
            with(viewHolder) {
                bucketListTextView.text = bucketlists.destination
                itemView.setOnClickListener { // This is the click listener for the entire item
                    itemListener.bucketListSelected(bucketList)
                }
            }
        }

        override fun getItemCount(): Int { // This is the method that will return the number of items in the list
            return bucketList.size // This is the number of items in the list
        }

        /**
         * Create a custom interface so that we know all Tournaments in this adapter are able to be selected
         */
        interface BucketListItemListener {
            fun bucketListSelected(bucketList: List<BucketList>)
        }
    }
package com.ultraspeeddemon.ethancurtis1166648final

class BucketList (
        var destination: String? = null,
        var description: String? = null,
        var ranking: String? = null,
        var uid: String? = null,
    ){
        override fun toString(): String {
            if (destination != null) {
                return destination!!
            }
            else{
                return "No Destination"
            }
        }
    }
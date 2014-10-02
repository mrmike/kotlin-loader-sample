package com.moczul.padg

import com.google.gson.annotations.SerializedName

data class Item(SerializedName("id") val id: Long,
                SerializedName("title") val title: String,
                SerializedName("stream_url") val url: String,
                SerializedName("comment_count") val commentCount: Int,
                SerializedName("favoritings_count") val favoritesCount: Int,
                SerializedName("downloadable") val isDownloadable: Boolean,
                SerializedName("sharing") val sharing: String) {

    public fun isShareable() : Boolean {
        return "public".equals(sharing)
    }

}
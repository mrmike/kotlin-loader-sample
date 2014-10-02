package com.moczul.padg

import android.content.Context
import android.content.AsyncTaskLoader
import java.net.URL
import com.google.gson.Gson
import java.io.InputStream
import java.io.InputStreamReader
import java.io.BufferedReader


public class DataLoader(context: Context) : AsyncTaskLoader<Array<Item>>(context) {

    val mGson : Gson = Gson()

    private val API_URL = "http://api.soundcloud.com/users/16197174/favorites.json?client_id=19aba01babd45561b2dd446f352f5c48"

    private var mData: Array<Item>? = null;

    override fun loadInBackground(): Array<Item>? {
        var reader : BufferedReader? = null
        try {
            val connection = URL(API_URL).openConnection()
            val inputStream: InputStream = connection?.getInputStream() as InputStream
            reader = BufferedReader(InputStreamReader(inputStream))

            val items: Array<Item>? = mGson.fromJson(reader, javaClass<Array<Item>>())

            return items
        } finally {
            reader?.close()
        }
    }


    override fun onStartLoading() {
        if (mData != null) {
            deliverResult(mData)
        } else {
            forceLoad()
        }
    }

    override fun deliverResult(data: Array<Item>?) {
        super<AsyncTaskLoader>.deliverResult(data)
        mData = data;
    }
}
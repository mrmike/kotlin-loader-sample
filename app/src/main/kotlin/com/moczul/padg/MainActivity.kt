package com.moczul.padg

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.app.LoaderManager.LoaderCallbacks
import android.content.Loader

public class MainActivity() : Activity(), LoaderCallbacks<Array<Item>> {

    var mAdapter: TrackAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super<Activity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fetchButton = findView<Button>(R.id.fetch_btn)
        val listView = findView<ListView>(R.id.list_view)

        mAdapter = TrackAdapter(this)
        listView?.setAdapter(mAdapter as TrackAdapter)

        fetchButton?.setOnClickListener {
            getLoaderManager()?.initLoader(0, null, this)
        }
    }

    override fun onCreateLoader(loaderId: Int, data: Bundle?): Loader<Array<Item>>? {
        return DataLoader(this)
    }

    override fun onLoadFinished(loader: Loader<Array<Item>>?, data: Array<Item>?) {
        if (data != null) {
            mAdapter?.swapData(data.filter { it.commentCount > 10 }
                    .filter { it.isShareable() }
                    .filter { it.isDownloadable }
                    .filter { it.favoritesCount > 25 })
        }
    }

    override fun onLoaderReset(loader: Loader<Array<Item>>?) {
    }
}
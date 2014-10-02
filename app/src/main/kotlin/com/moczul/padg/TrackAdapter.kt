package com.moczul.padg

import android.widget.BaseAdapter
import android.view.View
import android.view.ViewGroup
import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView

public class TrackAdapter(context: Context) : BaseAdapter() {

    val mInflater = LayoutInflater.from(context)

    var mData: List<Item> = listOf()

    fun swapData(data: List<Item>) {
        mData = data
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return mData.size
    }
    override fun getItem(position: Int): Any? {
        return mData.get(position)
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val view = mInflater.inflate(R.layout.item_track, parent, false)

        val title = view?.findView<TextView>(R.id.title)
        val url = view?.findView<TextView>(R.id.url)

        val item: Item = getItem(position) as Item
        title?.setText(item.title)
        url?.setText(item.url)

        return view
    }

}
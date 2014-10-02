package com.moczul.padg

import android.app.Activity
import android.view.View
import android.view.View.OnClickListener

public fun Activity.findView<T: View>(id: Int): T? = findViewById(id) as? T

public fun View.findView<T: View>(id: Int): T? = findViewById(id) as? T

public fun OnClickListener(action: (View?) -> Unit): OnClickListener {
    return object: OnClickListener {
        override fun onClick(view: View) {
            action(view)
        }
    }
}

public fun View.setOnClickListener(action: (View?) -> Unit): Unit {
    setOnClickListener(OnClickListener(action))
}

package com.sahalnazar.paging3_retrofit_roomdb.util

import android.content.res.Resources

object ExtensionFunctions {

    val Int.dp: Int
        get() = (this * Resources.getSystem().displayMetrics.density).toInt()


}
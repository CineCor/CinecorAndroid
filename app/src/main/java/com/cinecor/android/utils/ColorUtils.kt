package com.cinecor.android.utils

import android.graphics.Color

object ColorUtils {

    fun String.rgba() = Color.parseColor(this.substring(0, 1) + this.substring(7, 9) + this.substring(1, 7))
}

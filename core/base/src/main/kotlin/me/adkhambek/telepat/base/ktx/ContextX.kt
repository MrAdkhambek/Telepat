package me.adkhambek.telepat.base.ktx

import android.content.Context
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt

@ColorInt
public fun Context.getColorFromAttr(
    @AttrRes attrColor: Int,
    typedValue: TypedValue = TypedValue(),
    resolveRefs: Boolean = true,
): Int {
    theme.resolveAttribute(attrColor, typedValue, resolveRefs)
    return typedValue.data
}


public fun Context.getNavigationBarHeight(): Int {
    val id: Int = resources.getIdentifier(
        "navigation_bar_height",
        "dimen",
        "android"
    )

    return if (id > 0) resources.getDimensionPixelSize(id)
    else 0
}
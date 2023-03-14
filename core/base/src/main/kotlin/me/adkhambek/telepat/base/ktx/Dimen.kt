package me.adkhambek.telepat.base.ktx

import android.content.res.Resources

public val Number.dp: Float get() = (this.toFloat() * Resources.getSystem().displayMetrics.density)
public val Number.px: Float get() = (this.toFloat() / Resources.getSystem().displayMetrics.density)

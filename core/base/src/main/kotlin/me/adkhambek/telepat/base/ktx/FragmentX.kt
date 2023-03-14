package me.adkhambek.telepat.base.ktx

import android.app.Activity
import android.graphics.Color
import android.util.TypedValue
import android.view.Window
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope

public inline val Fragment.viewLifecycleScope: LifecycleCoroutineScope
    get() = viewLifecycleOwner.lifecycleScope

@Throws(ClassCastException::class, IllegalArgumentException::class)
public inline fun <reified T : Fragment> FragmentFactory.instantiate(): T {
    val clazz = T::class.java
    return instantiate(requireNotNull(clazz.classLoader), clazz.name) as T
}

@Throws(ClassCastException::class, IllegalArgumentException::class)
public inline fun <reified T : Fragment> FragmentFactory.instantiate(
    body: T.() -> Unit,
): T = instantiate<T>().apply(body)

@Suppress("NOTHING_TO_INLINE")
public inline fun Fragment.getColor(
    @ColorRes id: Int,
): Int = ContextCompat.getColor(requireContext(), id)

@Suppress("NOTHING_TO_INLINE")
public inline fun Fragment.getColorFromAttr(
    @AttrRes attrColor: Int,
    typedValue: TypedValue = TypedValue(),
    resolveRefs: Boolean = true,
): Int = requireContext().getColorFromAttr(
    attrColor = attrColor,
    typedValue = typedValue,
    resolveRefs = resolveRefs
)

@JvmOverloads
public fun Fragment.systemScreenMode(
    isFullscreen: Boolean,
    isTransparent: Boolean,
    isAppearanceLightStatusBars: Boolean = true, // true - black false - white
): Unit = systemScreenMode(
    isFullscreen = isFullscreen,
    isTransparent = isTransparent,
    isAppearanceLightStatusBars = isAppearanceLightStatusBars,
    systemBarColor = getColorFromAttr(android.R.attr.windowBackground)
)

public fun Fragment.systemScreenMode(
    isFullscreen: Boolean,
    isTransparent: Boolean,
    isAppearanceLightStatusBars: Boolean,
    @ColorInt systemBarColor: Int,
) {
    val activity: Activity = this.activity ?: return
    val window: Window = activity.window

    WindowCompat.setDecorFitsSystemWindows(
        window,
        !isFullscreen,
    )

    WindowInsetsControllerCompat(
        window,
        window.decorView,
    ).isAppearanceLightStatusBars = isAppearanceLightStatusBars

    val mSystemBarColor = when {
        isTransparent -> Color.TRANSPARENT
        else -> systemBarColor
    }

    window.statusBarColor = mSystemBarColor
    window.navigationBarColor = mSystemBarColor
}

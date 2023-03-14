package me.adkhambek.telepat.base

import android.app.Activity
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.redmadrobot.inputmask.MaskedTextChangedListener

public fun EditText.maskedTextChangedListener(
    mask: String,
    listener: MaskedTextChangedValueListener,
): TextWatcher {
    return MaskedTextChangedListener(
        field = this,
        format = mask,
        valueListener = listener,
    ).also(::addTextChangedListener)
}

public fun EditText.openKeyboard() {
    requestFocus()
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, 0)
}

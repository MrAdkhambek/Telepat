package me.adkhambek.telepat.base

import com.redmadrobot.inputmask.MaskedTextChangedListener

public fun interface MaskedTextChangedValueListener : MaskedTextChangedListener.ValueListener {
    override fun onTextChanged(maskFilled: Boolean, extractedValue: String, formattedValue: String)
}

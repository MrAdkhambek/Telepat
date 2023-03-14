@file:Suppress("FunctionName")

package com.adkhambek.telepat.ui

import com.github.terrakok.cicerone.androidx.FragmentScreen
import me.adkhambek.telepat.base.ktx.instantiate

object Screens {

    fun Messaging() = FragmentScreen { factory ->
        factory.instantiate<MessagingFragment>()
    }
}
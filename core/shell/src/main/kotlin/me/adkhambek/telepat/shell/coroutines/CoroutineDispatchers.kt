@file:Suppress("PropertyName", "unused")

package me.adkhambek.telepat.shell.coroutines

import kotlin.coroutines.CoroutineContext

interface CoroutineDispatchers {
    val IO: CoroutineContext
    val Main: CoroutineContext
    val Default: CoroutineContext
    val Unconfined: CoroutineContext
}

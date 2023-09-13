@file:Suppress("unused")

package com.adkhambek.di.scope

import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.RUNTIME

@Scope
@MustBeDocumented
@Retention(RUNTIME)
public annotation class ActivityRetainScope

@Scope
@MustBeDocumented
@Retention(RUNTIME)
public annotation class UserScope


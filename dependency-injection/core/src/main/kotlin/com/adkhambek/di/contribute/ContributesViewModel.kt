package com.adkhambek.di.contribute

import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
public annotation class ContributesViewModel(val scope: KClass<*>)

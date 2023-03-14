package com.adkhambek.di.contribute

import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
public annotation class ContributesScreen(val scope: KClass<*>)

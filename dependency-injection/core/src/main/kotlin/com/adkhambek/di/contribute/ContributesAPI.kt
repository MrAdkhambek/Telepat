package com.adkhambek.di.contribute

import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
public annotation class ContributesAPI(val scope: KClass<*>)

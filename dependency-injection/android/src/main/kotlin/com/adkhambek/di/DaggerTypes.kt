package com.adkhambek.di

/**
 * Sugar over multibbindings that helps with Kotlin wildcards.
 * */
public typealias DaggerSet<T> = Set<@JvmSuppressWildcards T>
public typealias DaggerMap<K, V> = Map<K, @JvmSuppressWildcards V>
public typealias DaggerLazy<T> = dagger.Lazy<@JvmSuppressWildcards T>

package com.adkhambek.auth.api

interface UsersProvider {
    suspend operator fun invoke(): List<User>
}
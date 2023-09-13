package com.adkhambek.auth.impl

import com.adkhambek.auth.api.User
import com.adkhambek.auth.api.UsersProvider
import com.thedeanda.lorem.LoremIpsum
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocaleUserProvider @Inject constructor() : UsersProvider {

    override suspend fun invoke(): List<User> {
        val lorem = LoremIpsum.getInstance()

        return listOf(
            User(
                id = 1,
                name = lorem.name,
                photo = "https://picsum.photos/200/200"
            ),
            User(
                id = 2,
                name = lorem.name,
                photo = "https://picsum.photos/200/200"
            ),
            User(
                id = 3,
                name = lorem.name,
                photo = "https://picsum.photos/200/200"
            )
        )
    }
}
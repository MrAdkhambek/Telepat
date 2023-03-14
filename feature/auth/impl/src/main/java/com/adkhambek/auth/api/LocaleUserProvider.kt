package com.adkhambek.auth.api

import com.adkhambek.di.SingleIn
import com.adkhambek.di.scope.AppScope
import com.squareup.anvil.annotations.ContributesBinding
import com.thedeanda.lorem.LoremIpsum
import javax.inject.Inject

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
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
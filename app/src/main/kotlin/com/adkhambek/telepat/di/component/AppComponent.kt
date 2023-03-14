package com.adkhambek.telepat.di.component

import android.app.Application
import com.adkhambek.di.SingleIn
import com.adkhambek.di.scope.AppScope
import com.squareup.anvil.annotations.MergeComponent
import dagger.BindsInstance
import dagger.Component

@SingleIn(AppScope::class)
@MergeComponent(AppScope::class)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance app: Application,
        ): AppComponent
    }
}

package com.adkhambek.telepat.di.component

import android.app.Application
import com.adkhambek.auth.impl.UserBindModule
import com.adkhambek.telepat.di.module.AppBindModule
import com.adkhambek.telepat.di.module.ViewModelFactoriesModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        UserBindModule::class,
        AppBindModule::class,
        ViewModelFactoriesModule::class,
    ]
)
interface AppComponent : ActivityRetainComponent.ParentBindings {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance app: Application,
        ): AppComponent
    }

    companion object {
        operator fun invoke(app: Application): AppComponent {
            return DaggerAppComponent.factory().create(app)
        }
    }
}

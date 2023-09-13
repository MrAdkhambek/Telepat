package com.adkhambek.telepat.di.component

import com.adkhambek.di.scope.ActivityRetainScope
import com.adkhambek.telepat.MainActivity
import dagger.BindsInstance
import dagger.Subcomponent
import kotlinx.coroutines.CoroutineScope

@ActivityRetainScope
@Subcomponent
interface ActivityRetainComponent : UserComponent.ParentBindings {

    fun inject(mainActivity: MainActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance scope: CoroutineScope,
        ): ActivityRetainComponent
    }

    interface ParentBindings {
        fun activityComponentFactory(): Factory
    }

    companion object {
        operator fun invoke(parent: ParentBindings, scope: CoroutineScope): ActivityRetainComponent {
            return parent.activityComponentFactory().create(scope)
        }
    }
}

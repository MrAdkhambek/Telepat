package me.adkhambek.telepat.debug

import com.adkhambek.di.SingleIn
import com.adkhambek.di.scope.AppScope
import com.pluto.plugins.network.PlutoInterceptor
import com.squareup.anvil.annotations.ContributesTo
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import me.adkhambek.telepat.shell.AppConfig
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

@Module
@ContributesTo(AppScope::class)
public object InterceptorsModule {

    @IntoSet
    @Provides
    @SingleIn(AppScope::class)
    public fun provideHttpLoggingInterceptor(
        appConfig: Lazy<AppConfig>,
    ): Interceptor = HttpLoggingInterceptor(Timber.tag("NETWORK")::d).apply {
        level = if (appConfig.get().debug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    @IntoSet
    @Provides
    @SingleIn(AppScope::class)
    public fun providePlutoInterceptor(): Interceptor = PlutoInterceptor()
}

package me.adkhambek.telepat.debug

import com.pluto.plugins.network.PlutoInterceptor
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import me.adkhambek.telepat.shell.AppConfig
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import javax.inject.Singleton

@Module
public object InterceptorsModule {

    @IntoSet
    @Provides
    @Singleton
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
    @Singleton
    public fun providePlutoInterceptor(): Interceptor = PlutoInterceptor()
}

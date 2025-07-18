package com.orafaelsc.cstvfuze

import android.app.Application
import com.orafaelsc.cstvfuze.data.di.dataModule
import com.orafaelsc.cstvfuze.data.di.networkModule
import com.orafaelsc.cstvfuze.di.appModule
import com.orafaelsc.cstvfuze.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class CSTVFuzeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(level = Level.DEBUG) // todo set to DEBUG in debug builds
            androidContext(this@CSTVFuzeApp)
            modules(
                appModule +
                networkModule +
                dataModule +
                domainModule
            )
        }
    }
}

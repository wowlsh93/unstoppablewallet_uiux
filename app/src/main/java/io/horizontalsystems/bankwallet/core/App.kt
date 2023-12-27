package io.horizontalsystems.bankwallet.core

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.decode.SvgDecoder
import io.horizontalsystems.bankwallet.BuildConfig
import io.horizontalsystems.core.CoreApp
import io.horizontalsystems.core.ICoreApp

import io.horizontalsystems.ethereumkit.core.EthereumKit
import io.reactivex.plugins.RxJavaPlugins
import java.util.logging.Level
import java.util.logging.Logger

class App : CoreApp() {

    companion object : ICoreApp by CoreApp {

        lateinit var preferences: SharedPreferences

    }

    override fun onCreate() {
        super.onCreate()

        if (!BuildConfig.DEBUG) {
            //Disable logging for lower levels in Release build
            Logger.getLogger("").level = Level.SEVERE
        }

        RxJavaPlugins.setErrorHandler { e: Throwable? ->
            Log.w("RxJava ErrorHandler", e)
        }

        EthereumKit.init()

        instance = this
        preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)


        setAppTheme()


        startTasks()
    }


    private fun setAppTheme() {
        val nightMode =  AppCompatDelegate.MODE_NIGHT_YES

        if (AppCompatDelegate.getDefaultNightMode() != nightMode) {
            AppCompatDelegate.setDefaultNightMode(nightMode)
        }
    }


    override fun localizedContext(): Context {
        return localeAwareContext(this)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(localeAwareContext(base))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        localeAwareContext(this)
    }

    private fun startTasks() {
        Thread {

        }.start()
    }
}

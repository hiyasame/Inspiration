package kim.bifrost.rain.common

import android.app.Application
import android.content.Context

/**
 * cqupt.poststation.main.App
 * PostStation
 *
 * @author 寒雨
 * @since 2022/3/31 0:50
 **/
open class BaseApp : Application() {
    companion object {
        lateinit var appContext: Context
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }
}
package database.kotlin.flow9.net.spbasic

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class GlobalApp : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
            private set
    }
}

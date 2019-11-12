package com.example.android.observability

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.soloader.SoLoader

@Suppress("unused")
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        SoLoader.init(this, false)
        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            AndroidFlipperClient.getInstance(this)
                    .apply {
                        addPlugin(InspectorFlipperPlugin(this@BaseApplication, DescriptorMapping.withDefaults()))
                        addPlugin(DatabasesFlipperPlugin(this@BaseApplication))
                    }.also {
                        it.start()
                    }
        }
    }
}
package com.vahitkeskin.movieapp

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.core.FlipperClient
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader
import com.vahitkeskin.movieapp.util.FlipperNetworkObject
import dagger.hilt.android.HiltAndroidApp

/**
 * @authot: Vahit Keskin
 * creared on 24.03.2023
 */

@HiltAndroidApp
class TheMoviesApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        //Facebook Flipper
        SoLoader.init(this, false)
        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            val clint: FlipperClient = AndroidFlipperClient.getInstance(this)
            val networkFlipperPlugin = NetworkFlipperPlugin()
            FlipperNetworkObject.networkFlipperPlugin = networkFlipperPlugin
            clint.addPlugin(InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()))
            clint.addPlugin(networkFlipperPlugin)
            clint.start()
        }
    }
}
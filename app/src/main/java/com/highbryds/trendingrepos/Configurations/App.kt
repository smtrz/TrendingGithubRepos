package com.highbryds.trendingrepos.Configurations

import android.app.Application
import com.highbryds.trendingrepos.Components.AppLevelComponent
import com.highbryds.trendingrepos.Components.DaggerAppLevelComponent
import com.highbryds.trendingrepos.Modules.ContextModule
import com.highbryds.trendingrepos.Modules.DateModule
import com.highbryds.trendingrepos.Modules.DbRepoModule
import com.highbryds.trendingrepos.Modules.NetModule



class App : Application() {
    lateinit var appLevelComponent: AppLevelComponent


    override fun onCreate() {
        super.onCreate()
        app = this
        // we only have to set constructor modules or context modules.
        appLevelComponent = DaggerAppLevelComponent.builder()
            .contextModule(ContextModule(this))
            .dbRepoModule(DbRepoModule())
            .netModule(NetModule("https://github-trending-api.now.sh/"))
            .dateModule(DateModule())
            .build()


    }

    companion object {
        lateinit var app: App
    }


}

package com.tahir.go_jek.Configurations

import android.app.Application
import com.tahir.go_jek.Components.AppLevelComponent
import com.tahir.go_jek.Components.DaggerAppLevelComponent
import com.tahir.go_jek.Modules.ContextModule
import com.tahir.go_jek.Modules.DbRepoModule
import com.tahir.go_jek.Modules.NetModule



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
            .build()


    }

    companion object {
        lateinit var app: App
    }


}

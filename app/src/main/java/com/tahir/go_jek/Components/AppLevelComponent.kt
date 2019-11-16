package com.tahir.go_jek.Components


import com.tahir.go_jek.Database.DbRepository
import com.tahir.go_jek.Modules.ContextModule
import com.tahir.go_jek.Modules.DbRepoModule
import com.tahir.go_jek.Modules.NetModule
import com.tahir.go_jek.ViewModels.MainActivityViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ContextModule::class, DbRepoModule::class, NetModule::class])
@Singleton
interface AppLevelComponent {

    fun inject(ma: MainActivityViewModel)
    //void inject(MainActivityViewModel ma);

    fun inject(dr: DbRepository)


}

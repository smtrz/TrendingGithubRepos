package com.tahir.go_jek.Components


import com.tahir.go_jek.Database.DbRepository
import com.tahir.go_jek.Helpers.DateHelper
import com.tahir.go_jek.Modules.ContextModule
import com.tahir.go_jek.Modules.DateModule
import com.tahir.go_jek.Modules.DbRepoModule
import com.tahir.go_jek.Modules.NetModule
import com.tahir.go_jek.ViewModels.TrendingActivityViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ContextModule::class, DbRepoModule::class, NetModule::class, DateModule::class])
@Singleton
interface AppLevelComponent {

    fun inject(ma: TrendingActivityViewModel)
    fun inject(dr: DbRepository)
    fun inject(dr: DateHelper)

}

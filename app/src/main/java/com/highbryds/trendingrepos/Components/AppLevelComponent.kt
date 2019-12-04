package com.highbryds.trendingrepos.Components


import com.highbryds.trendingrepos.Database.DbRepository
import com.highbryds.trendingrepos.Helpers.DateHelper
import com.highbryds.trendingrepos.Modules.ContextModule
import com.highbryds.trendingrepos.Modules.DateModule
import com.highbryds.trendingrepos.Modules.DbRepoModule
import com.highbryds.trendingrepos.Modules.NetModule
import com.highbryds.trendingrepos.ViewModels.TrendingActivityViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ContextModule::class, DbRepoModule::class, NetModule::class, DateModule::class])
@Singleton
interface AppLevelComponent {

    fun inject(ma: TrendingActivityViewModel)
    fun inject(dr: DbRepository)
    fun inject(dr: DateHelper)

}

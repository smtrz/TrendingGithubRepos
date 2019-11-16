package com.tahir.go_jek.Components



import com.tahir.go_jek.Helpers.DateHelper
import com.tahir.go_jek.Modules.DateModule
import javax.inject.Singleton

import dagger.Component

@Singleton
@Component(modules = [DateModule::class])
interface DateComponent {

    fun inject(dh: DateHelper)

  //  fun inject(dh: NewsAdapter)

    //fun inject(dh: News_Detail_Activity)


}

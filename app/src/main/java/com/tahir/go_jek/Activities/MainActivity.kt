package com.tahir.go_jek

import android.os.Bundle
import com.tahir.go_jek.Activities.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Attaching the layout to the toolbar object
        //        //setting toolbar
        //       // setSupportActionBar(findViewById(R.id.tool_bar))

    }
}

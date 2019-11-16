package com.tahir.go_jek.Activities

import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem

import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuInflater
import com.tahir.go_jek.R


open class BaseActivity : AppCompatActivity() {
    // All the common functions will override here //
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
      //  val inflater = menuInflater
        //inflater.inflate(R.menu.manu, item)
        return true
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

}

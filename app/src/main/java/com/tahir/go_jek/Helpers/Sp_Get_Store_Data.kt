package com.tahir.go_jek.Helpers

import android.content.Context
import android.content.Context.MODE_PRIVATE

/**
 * Created by tahir.raza
 */

object Sp_Get_Store_Data {
    /**
     * Get String Data.
     *
     * This method is used to retrieve data from key from shared prefences.
     *
     * @param Context .
     *@param  key .
     * @return returns data from key
     *
     *
     */

    fun getStringData(c: Context, key: String): String? {
        val pref = c.getSharedPreferences("Appdata", MODE_PRIVATE)

        return pref.getString(key, null)

    }

    /**
     * Store String Data.
     *
     * This method stores string data into Shared Prefences..
     *
     * @param Context .
     *@param  key .
     * @return does not returns anything
     *
     *
     */

    fun storeStringData(Data: String, key: String, c: Context) {
        val pref = c.getSharedPreferences("Appdata", MODE_PRIVATE)
        val editor = pref.edit()


        editor.putString(key, Data)

        //
        editor.commit() // commit changes


    }


}

package com.tahir.go_jek.Helpers

import android.app.ProgressDialog
import android.content.Context


class ProgressDialogHelper {


        fun showDialog(c: Context): ProgressDialog {
            val pd = ProgressDialog(c)
            pd.setMessage("loading....please wait")
            pd.show()

            return pd
        }
   // }
   companion object {

       fun cancelDialog(pDialog: ProgressDialog) {
           pDialog.cancel()
       }
   }

   // }
}

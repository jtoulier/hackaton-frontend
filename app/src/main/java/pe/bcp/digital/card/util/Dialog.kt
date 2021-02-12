package pe.bcp.digital.card.util

import android.content.Context
import androidx.appcompat.app.AlertDialog

object Dialog{

    fun showDialog(context: Context, message: String, onClick: (() -> Unit)? = null){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("App")
        builder.setMessage(message)
        builder.setPositiveButton("Ok"){ _,_ ->
            onClick?.invoke()
        }
        builder.show()
    }

}
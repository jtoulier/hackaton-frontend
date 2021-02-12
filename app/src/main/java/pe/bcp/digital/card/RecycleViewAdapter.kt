package pe.bcp.digital.card

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pe.bcp.digital.card.data.model.User


class Adapter(private val recycle : List<User>) :
        RecyclerView.Adapter<Adapter.ViewHolderDatos>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDatos {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycleview,parent,false)
        return ViewHolderDatos(view)

    }

    override fun onBindViewHolder(holder: ViewHolderDatos, position: Int) {
        val datatransac = recycle[position]
      }

    override fun getItemCount() = recycle.size

    class ViewHolderDatos(itemView : View) : RecyclerView.ViewHolder(itemView){


    }

}
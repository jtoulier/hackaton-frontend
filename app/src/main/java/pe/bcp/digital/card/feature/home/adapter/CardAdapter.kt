package pe.bcp.digital.card.feature.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pe.bcp.digital.card.R
import pe.bcp.digital.card.data.model.Card
import pe.bcp.digital.card.data.model.User
import pe.bcp.digital.card.databinding.ItemCardBinding


class CardAdapter(private val recycle : List<Card>) :
        RecyclerView.Adapter<CardAdapter.ViewHolderDatos>() {

    var onItemClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDatos
            = ViewHolderDatos(ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolderDatos, position: Int) {
        val dataRecyclerView = recycle[position]
        dataRecyclerView

    }

    override fun getItemCount() = recycle.size

    inner class ViewHolderDatos(private val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root){
        private var current: Card? = null

        init {
            binding.root.setOnClickListener {
                current?.let {
                    onItemClick?.invoke(adapterPosition)
                }
            }
        }

        fun bind(card: Card){
            current = card
            binding.tvCardNumber.text = card.cardNumber
            binding.tvCardNumber.text = card.authorizedAmount
            binding.tvCardNumber.text = card.availableAmount
            binding.tvCardNumber.text = card.expirationDate
        }
    }



}
package pe.bcp.digital.card.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.bcp.digital.card.data.model.Card
import pe.bcp.digital.card.databinding.ItemCardBinding


class CardAdapter : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    var onItemClick: ((Int) -> Unit)? = null
    var items: MutableList<Card> = mutableListOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder
            = CardViewHolder(ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class CardViewHolder(private val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root){
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
            binding.tvCardNumber.text = "Nro. de Tarjeta Disponible: ${card.cardNumber}"
            binding.tvAuthorizedAmount.text = "Tu linea es de S/.${card.authorizedAmount}"
            binding.tvAvailableAmount.text = "Has usado: ${card.availableAmount}"
            binding.tvExpirationDate.text = "Fecha de Vencimiento es: ${card.expirationDate}"
        }
    }



}
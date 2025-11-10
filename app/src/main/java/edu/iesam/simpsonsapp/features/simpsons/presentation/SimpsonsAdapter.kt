package edu.iesam.simpsonsapp.features.simpsons.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.simpsonsapp.databinding.ItemSimpsonBinding
import edu.iesam.simpsonsapp.features.simpsons.domain.Simpsons

/**
 * Adapter para el RecyclerView que muestra la lista de personajes.
 *
 * Hereda de [ListAdapter], una clase especial que optimiza automáticamente las actualizaciones
 * de la lista. En lugar de redibujar todo, usa [SimpsonDiffCallback] para calcular
 * exactamente qué elementos han cambiado, mejorando el rendimiento y permitiendo animaciones.
 */
class SimpsonsAdapter(private val onClick: (Simpsons) -> Unit) :
    ListAdapter<Simpsons, SimpsonsAdapter.ViewHolder>(SimpsonDiffCallback()) {

    inner class ViewHolder(private val binding: ItemSimpsonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(simpson: Simpsons) {
            binding.tvName.text = simpson.name
            binding.tvOccupation.text = simpson.occupation
            binding.ivSimpson.load(simpson.urlImage) {
                crossfade(true)
            }
            binding.root.setOnClickListener {
                onClick(simpson)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSimpsonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class SimpsonDiffCallback : DiffUtil.ItemCallback<Simpsons>() {
    override fun areItemsTheSame(oldItem: Simpsons, newItem: Simpsons): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Simpsons, newItem: Simpsons): Boolean =
        oldItem == newItem
}
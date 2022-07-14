package com.example.rickandmorty.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.api.model.Character
import com.example.rickandmorty.databinding.ItemListBinding
import com.squareup.picasso.Picasso

class CharacterAdapter: RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private var listCharacters = emptyList<Character>()

    class CharacterViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(character: Character){
            binding.txtIdCharacter.text = character.id.toString()
            binding.txtNameCharacter.text = character.name
            Picasso.get().load(character.image).into(binding.characterImg)
            binding.txtStatus.text = character.status

            itemView.setOnClickListener {view ->
                val action = ListFragmentDirections.actionListFragmentToDetailFragment(character)
                view.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(layoutInflater, parent, false)
        return CharacterViewHolder(binding)

    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(listCharacters[position])
    }

    override fun getItemCount(): Int {
        return listCharacters.size
    }

    fun setCharacters(characters: List<Character>){
        listCharacters = characters
        notifyDataSetChanged()
    }
}
package com.reyhan.dictionaryenglish.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reyhan.dictionaryenglish.data.DefinitionsItem
import com.reyhan.dictionaryenglish.databinding.ItemDefinitionBinding

class DictionaryAdapter : RecyclerView.Adapter<DictionaryAdapter.UserViewHolder>() {

    private val listDictionary = ArrayList<DefinitionsItem>()

    class UserViewHolder(val itemDictionary: ItemDefinitionBinding) :
        RecyclerView.ViewHolder(itemDictionary.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(
        ItemDefinitionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.itemDictionary.apply {
            with(listDictionary[position]) {
                tvDefinition.text = definition
            }
        }
    }

    override fun getItemCount() = listDictionary.size

    fun setUsers(dictionary: List<DefinitionsItem>?) {
        this.listDictionary.clear()
        if (dictionary != null) {
            this.listDictionary.addAll(dictionary)
        }
    }
}
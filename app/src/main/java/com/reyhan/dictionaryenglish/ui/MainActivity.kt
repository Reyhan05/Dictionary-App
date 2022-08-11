package com.reyhan.dictionaryenglish.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.reyhan.dictionaryenglish.adapter.DictionaryAdapter
import com.reyhan.dictionaryenglish.data.DefinitionsItem
import com.reyhan.dictionaryenglish.data.DictionaryResponseItem
import com.reyhan.dictionaryenglish.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    private val dictionaryAdapter = DictionaryAdapter()
    private var viewModel: DictionaryViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this)[DictionaryViewModel::class.java]

        setContentView(binding.root)
        getSearch()

    }

    private fun getSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query.let {
                    viewModel?.getDataApi(it.toString())
                    viewModel?.dictionary?.observe(this@MainActivity) {
                        binding.tvWord.text = it.get(0).word
                        binding.tvPhonetic.text = it.get(0).phonetic
                        setData(it.get(0).meanings?.get(0)?.definitions as List<DefinitionsItem>?)
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    fun setData(data: List<DefinitionsItem>?) {
        binding.rvDefinition.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = dictionaryAdapter
            dictionaryAdapter.setUsers(data)
        }
    }


}


package com.apsoftware.urbandictionary.ui.definition_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apsoftware.urbandictionary.R
import com.apsoftware.urbandictionary.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class DefinitionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: DefinitionViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.defintionList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        viewModel = ViewModelProviders.of(this).get(DefinitionViewModel::class.java)
        binding.viewModel = viewModel

        val termObserver = Observer<String> {
            // Update the UI, in this case, a TextView.
            search_term_edit_text.setText(it)
        }

        viewModel.searchTerm.observe(this, termObserver)
    }
}

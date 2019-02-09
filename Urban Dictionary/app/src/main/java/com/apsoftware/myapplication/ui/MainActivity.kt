package com.apsoftware.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.apsoftware.myapplication.ViewModels.DefinitionViewModel
import com.apsoftware.myapplication.R
import com.apsoftware.myapplication.models.Definition
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var definitionViewModel: DefinitionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        submit_button.setOnClickListener {
            run {
                Log.d("TESTING_TAG", "submit button clicked")
                definitionViewModel.updateDefinitionList(search_term_edit_text.text.toString())
                search_term_edit_text.text = Editable.Factory.getInstance().newEditable("")
            }
        }
        definitionViewModel = ViewModelProviders.of(this).get(DefinitionViewModel::class.java)

        val definitionListObserver = Observer<List<Definition>> {

        }

        definitionViewModel.definitionList.observe(this, definitionListObserver)

    }
}

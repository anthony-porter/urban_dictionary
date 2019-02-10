package com.apsoftware.myapplication


import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apsoftware.myapplication.databinding.MainActivityBinding
import com.apsoftware.myapplication.models.Definition
import com.apsoftware.myapplication.viewmodels.DefinitionViewModel
import kotlinx.android.synthetic.main.main_activity.*


const val TAG: String = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var definitionViewModel: DefinitionViewModel
    lateinit var activityBinding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBindings(savedInstanceState)
        submit_button.setOnClickListener {
            run {
                Log.d(TAG, "submit button clicked")
                definitionViewModel.updateDefinitionList(search_term_edit_text.text.toString())
            }
        }
    }

    private fun setupBindings(savedInstanceState: Bundle?) {
        Log.d(TAG, "setting up bindings")
        val activityBinding = DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.main_activity)

        val llm = LinearLayoutManager(this)
        llm.orientation = RecyclerView.VERTICAL
        definition_recycler_view.layoutManager = llm

        definitionViewModel = ViewModelProviders.of(this).get(DefinitionViewModel::class.java)
        if (savedInstanceState == null) {
            Log.d(TAG, "savedInstance was null setting up viewmodel")
            definitionViewModel.init()
        }
        activityBinding.model = definitionViewModel
        setupListUpdate()

    }

    val nameObserver = Observer<List<Definition>> { definitions ->
        definitionViewModel.setDefinitionsInAdapter((definitions))
    }

    private fun setupListUpdate() {
        definitionViewModel.definitionList.observe(this, nameObserver)
    }
}

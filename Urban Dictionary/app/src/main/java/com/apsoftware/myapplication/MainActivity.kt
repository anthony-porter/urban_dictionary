package com.apsoftware.myapplication


import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindToViewModel(savedInstanceState == null)
        submit_button.setOnClickListener {
            run {
                Log.d(TAG, "submit button clicked")
                definitionViewModel.updateDefinitionList(search_term_edit_text.text.toString())
            }
        }
        sort_button.setOnClickListener {
            run {
                definitionViewModel.sortRecyclerView(sort_button.isChecked)
            }
        }
    }

    /**
     * Bind ViewModel to Activity
     * @param initializeViewModel whether or not to initialize ViewModel
     * // TODO extract RecyclerView setup logic
     */
    private fun bindToViewModel(initializeViewModel: Boolean) {
        Log.d(TAG, "setting up bindings")
        val activityBinding = DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.main_activity)

        val llm = LinearLayoutManager(this)
        llm.orientation = RecyclerView.VERTICAL
        definition_recycler_view.layoutManager = llm

        definitionViewModel = ViewModelProviders.of(this).get(DefinitionViewModel::class.java)
        if (initializeViewModel) {
            Log.d(TAG, "savedInstance was null setting up ViewModel")
            definitionViewModel.init()
        }
        activityBinding.model = definitionViewModel
        setUpListUpdate()
    }

    /**
     * Bind ListAdapter to ViewModel
     */
    private val definitionListObserver = Observer<List<Definition>> { definitions ->
        definitionViewModel.setDefinitionsInAdapter((definitions))
    }

    /**
     * Set up Observable for DefinitionList
     */
    private fun setUpListUpdate() {
        definitionViewModel.definitionList.observe(this, definitionListObserver)
    }
}

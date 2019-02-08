package com.apsoftware.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apsoftware.myapplication.models.DefinitionAdapter
import com.apsoftware.myapplication.models.DefinitionModel
import com.apsoftware.myapplication.view_models.DefinitionViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.definition_view_holder.*

/**
 * Goal is to have a lightweight main activity.
 */
class MainActivity : AppCompatActivity() {

    lateinit var definitionModelList: ArrayList<DefinitionModel>
    lateinit var definitionAdapter: DefinitionAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = definition_recycler_view
        val viewModel: DefinitionViewModel = ViewModelProviders.of(this).get(DefinitionViewModel::class.java)
        val model = ViewModelProviders.of(this).get(DefinitionViewModel::class.java)
        model.getDefinitinModelObservable().observe(this, Observer<DefinitionModel>{ definitions ->
            definitionModelList = definitions
        })
    }

    fun setupRecyclerView() {
        definitionAdapter.notifyDataSetChanged();
    }

    fun observeViewModel(definitionViewModel: DefinitionViewModel) {
        definitionViewModel.getDefinitinModelObservable()
            .observe(this,)
                {
            if (definitionModel != null) {
                val definitions: List<DefinitionModel> = definitionModel.getDefinitions()
                definitionModelList.addall(definitions)
                definitionAdapter.notifyDataSetChanged()
            }
        }
    }
}

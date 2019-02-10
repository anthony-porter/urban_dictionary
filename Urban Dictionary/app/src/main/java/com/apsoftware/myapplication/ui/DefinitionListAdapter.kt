package com.apsoftware.myapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.apsoftware.myapplication.BR
import com.apsoftware.myapplication.R
import com.apsoftware.myapplication.models.Definition
import com.apsoftware.myapplication.viewmodels.DefinitionViewModel


class DefinitionListAdapter(private val definitionViewModel: DefinitionViewModel) :
    RecyclerView.Adapter<DefinitionListAdapter.DefinitionViewHolder>() {

    private lateinit var definitionList: List<Definition>

    fun setDefinitions(definitions: List<Definition>) {
        this.definitionList = definitions
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefinitionListAdapter.DefinitionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater, R.layout.definition_view,
            parent, false
        )

        return DefinitionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DefinitionListAdapter.DefinitionViewHolder, position: Int) {
        holder.bind(definitionViewModel, position)
    }

    override fun getItemCount(): Int {
        return if (definitionViewModel.definitionList.value != null) {
            definitionViewModel.definitionList.value!!.size
        } else {
            0
        }
    }

    class DefinitionViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: DefinitionViewModel, position: Int) {
            binding.setVariable(BR.viewModel, viewModel)
            binding.setVariable(BR.position, position)
        }
    }
}
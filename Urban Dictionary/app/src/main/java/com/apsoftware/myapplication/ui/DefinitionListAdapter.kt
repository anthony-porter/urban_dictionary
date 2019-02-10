package com.apsoftware.myapplication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.apsoftware.myapplication.viewmodels.DefinitionViewModel
import com.apsoftware.myapplication.models.Definition
import kotlinx.android.synthetic.main.definition_view.view.*
import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import com.apsoftware.myapplication.R


class DefinitionListAdapter(val layoutId: Int, val definitionViewModel: DefinitionViewModel) :
    RecyclerView.Adapter<DefinitionListAdapter.DefinitionViewHolder>() {

    lateinit var definitionList: List<Definition>

    fun setDefinitions(definitions: List<Definition>) {
        this.definitionList = definitions
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefinitionListAdapter.DefinitionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, R.layout.definition_view,
            parent, false)

        return DefinitionViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: DefinitionListAdapter.DefinitionViewHolder, position: Int) {
        holder.bind()
        holder.author.text = definitionViewModel.definitionList.value!![position].author
        holder.definition.text = definitionViewModel.definitionList.value!![position].definition
        holder.submissionDate.text = definitionViewModel.definitionList.value!![position].submission_date
        holder.thumbs_up.text = definitionViewModel.definitionList.value!!.get(position).thumbs_up.toString()
        holder.thumbs_down.text = definitionViewModel.definitionList.value!!.get(position).thumbs_down.toString()
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        if(definitionViewModel.definitionList.value != null) {
            return definitionViewModel.definitionList.value!!.size
        } else {
            return 0
        }
    }

    final class DefinitionViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        lateinit var author: TextView
        lateinit var definition: TextView
        lateinit var submissionDate: TextView
        lateinit var thumbs_up: TextView
        lateinit var thumbs_down: TextView

        fun bind() {
            author = view.author
            definition = view.definition_text_view
            submissionDate = view.submission_date
            thumbs_up = view.upvotes
            thumbs_down = view.downvotes
        }
    }
}
package com.apsoftware.urbandictionary.ui.definition_activity

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.apsoftware.urbandictionary.R
import com.apsoftware.urbandictionary.myapplication.models.Definition
import kotlinx.android.synthetic.main.definition_view.view.*
import java.util.ArrayList


class DefinitionAdapter(private val definitions: ArrayList<Definition>, val moreButtonClick: MoreButtonClick,
                        val lessButtonClick: LessButtonClick) : RecyclerView.Adapter<DefinitionAdapter.DefinitionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefinitionAdapter.DefinitionViewHolder {
        val root: ConstraintLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.definition_view, parent, false) as ConstraintLayout
        return DefinitionViewHolder(root)
    }

    override fun onBindViewHolder(holder: DefinitionAdapter.DefinitionViewHolder, position: Int) {
        holder.author.text = definitions[position].author
        holder.definition.text = definitions[position].definition
        holder.submissionDate.text = definitions[position].submission_date
        holder.thumbs_up.setText(definitions.get(position).thumbs_up)
        holder.thumbs_down.setText(definitions.get(position).thumbs_down)
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return definitions.size
    }

    interface MoreButtonClick {
        /**
         */
        fun onMoreButtonClicked(definition: Definition)
    }

    interface LessButtonClick {
        /**
         */
        fun onLessButtonClicked(definition: Definition)
    }

    final class DefinitionViewHolder(view: ConstraintLayout): RecyclerView.ViewHolder(view) {
        val author: TextView = view.author
        val definition: TextView = view.definition_text_view
        val submissionDate: TextView = view.submission_date
        val thumbs_up: TextView = view.upvotes
        val thumbs_down: TextView = view.downvotes
    }

}
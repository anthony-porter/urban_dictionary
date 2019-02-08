package com.apsoftware.myapplication.models

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.apsoftware.myapplication.R
import kotlinx.android.synthetic.main.definition_view_holder.view.*
import java.util.*

class DefinitionAdapter(private val definitions: ArrayList<DefinitionModel>, val moreButtonClick: MoreButtonClick,
                        val lessButtonClick: LessButtonClick) : RecyclerView.Adapter<DefinitionAdapter.DefinitionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefinitionAdapter.DefinitionViewHolder {
        val root: ConstraintLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.definition_view_holder, parent, false) as ConstraintLayout
        return DefinitionViewHolder(root)
    }

    override fun onBindViewHolder(holder: DefinitionAdapter.DefinitionViewHolder, position: Int) {
        holder.author.text = definitions[position].author
        holder.definition.text = definitions[position].definition
        holder.submissionDate.text = definitions[position].submission_date
        holder.lessButton.setText(definitions.get(position).less_button)
        holder.moreButton.setText(definitions.get(position).more_button)
        holder.thumbs_up.setText(definitions.get(position).thumbs_up)
        holder.thumbs_down.setText(definitions.get(position).thumbs_down)
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return definitions.size
    }

    interface MoreButtonClick {
        /**
         * Response to a click of an entry in this RecyclerView.
         * @param userProfile Complete ZonarUserCredentials that were clicked on.
         */
        fun onUserProfileSelected(definition: DefinitionModel)
    }

    interface LessButtonClick {
        /**
         * Response to a click of an entry in this RecyclerView.
         * @param userProfile Complete ZonarUserCredentials that were clicked on.
         */
        fun onUserProfileSelected(definition: DefinitionModel)
    }

    final class DefinitionViewHolder(view: ConstraintLayout): RecyclerView.ViewHolder(view) {
        val author: TextView = view.author
        val definition: TextView = view.definition_text_view
        val submissionDate: TextView = view.submission_date
        val lessButton: Button = view.less_button
        val moreButton: Button = view.more_button
        val thumbs_up: TextView = view.thumbs_up
        val thumbs_down: TextView = view.thumbs_down

    }

}



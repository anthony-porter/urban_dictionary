package com.apsoftware.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.apsoftware.myapplication.models.DefinitionModel
import com.apsoftware.myapplication.view_models.DefinitionViewModel

/**
 * Goal is to have a lightweight main activity.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model = ViewModelProviders.of(this).get(DefinitionViewModel::class.java)
        model.getDefinitions().observe(this, Observer<List<DefinitionModel>> { definitions ->
            // update UI
        })
    }
}

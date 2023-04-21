package com.example.demoappmvvmdagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.example.demoappmvvmdagger.databinding.ActivityProfileAndSettingsBinding
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.activity_profile_and_settings.*
import javax.inject.Inject


class ProfileAndSettings : AppCompatActivity() {

    private lateinit var binding:ActivityProfileAndSettingsBinding

    @Inject
    lateinit var profileRefreshRepo: ProfileRefreshRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile_and_settings)
        val component = (application as? AppClass)?.appComp
        component?.injectProfileAndSettingsActivity(this)
        initializeView(binding)

    }

    private fun initializeView(binding:ActivityProfileAndSettingsBinding){
        binding.countryListSpinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listOf(
            "China", "France", "United States", "Australia", "Japan"
        )).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        binding.countryListSpinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                profileRefreshRepo.refresh(binding.countryListSpinner.selectedItem as String)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }
    }

}
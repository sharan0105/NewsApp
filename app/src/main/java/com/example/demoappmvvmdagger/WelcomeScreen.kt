package com.example.demoappmvvmdagger

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.demoappmvvmdagger.ViewModelFactory.MainViewModelFactory
import com.example.demoappmvvmdagger.databinding.FragmentWelcomeScreenBinding
import com.example.demoappmvvmdagger.di.AppComponent
import com.example.demoappmvvmdagger.viewModels.WelcomeScreenViewModelImpl
import kotlinx.android.synthetic.main.fragment_welcome_screen.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [WelcomeScreen.newInstance] factory method to
 * create an instance of this fragment.
 */
class WelcomeScreen : Fragment() {

    @Inject
    lateinit var viewModelFactory:MainViewModelFactory

    @Inject
    lateinit var flagService: FlagService

    lateinit var viewModel:WelcomeScreenViewModelImpl

    lateinit var binding: FragmentWelcomeScreenBinding

    var component:AppComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Now we know that the component has been injected for us , we can use it
        component = (activity?.application as? AppClass)?.appComp
        //With this , dagger will be able to inject all dependencies of this fragment
        component?.injectWelcomeFragment(this)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(WelcomeScreenViewModelImpl::class.java)
        viewModel.onSignUpClick()
        viewModel.setFlagIcon()
        observeFlagImageUrl()

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome_screen, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.countryFlagUrl = ""
        btnSeeNews.setOnClickListener {
            val intent = Intent(activity,MainActivity::class.java)
            startActivity(intent)
        }

        btnSignUp.setOnClickListener {
                viewModel.onSignUpClick()
                activity?.supportFragmentManager?.beginTransaction()?.apply {
                    replace(R.id.fragmentLayout,SignUp(),SignUp::javaClass.name)
                }?.addToBackStack("SignUpScreen")?.commit()
        }

        binding.updateProfile.setOnClickListener {
            startActivity(
                Intent(activity, ProfileAndSettings::class.java)
            )
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WelcomeScreen.
         */
        /*TODO: Rename and change types and number of parameters , needed only if you want a
          TODO:custom fragment*/
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WelcomeScreen().apply {
                arguments = Bundle().apply {
                }
            }
    }

    private fun observeFlagImageUrl(){
        viewModel.flagIconUrl.observe(this){
            Log.d("Flag img url", it)
            binding.countryFlagUrl = it
        }
    }
}
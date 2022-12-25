package com.example.demoappmvvmdagger

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demoappmvvmdagger.ViewModelFactory.MainViewModelFactory
import com.example.demoappmvvmdagger.di.AppComponent
import com.example.demoappmvvmdagger.viewModels.WelcomeScreenViewModel
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

    lateinit var viewModel:WelcomeScreenViewModelImpl

    var component:AppComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Now we know that the component has been injected for us , we can use it
        component = (activity?.application as? AppClass)?.appComp
        //With this , dagger will be able to inject all dependencies of this fragment
        component?.injectWelcomeFragment(this)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(WelcomeScreenViewModelImpl::class.java)
        viewModel.onSignUpClick()

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
}
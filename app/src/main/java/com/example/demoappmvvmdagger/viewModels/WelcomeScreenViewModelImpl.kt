package com.example.demoappmvvmdagger.viewModels


import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demoappmvvmdagger.FlagService
import com.example.demoappmvvmdagger.ProfileRefreshRepo
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@SuppressLint("CheckResult")
class WelcomeScreenViewModelImpl @Inject constructor(
    private val flagService: FlagService,
    profileRepo: ProfileRefreshRepo
    ):ViewModel(),WelcomeScreenViewModel {

    lateinit var compositeDisposable: CompositeDisposable

    init {
         compositeDisposable= CompositeDisposable()
         compositeDisposable.add(profileRepo.dataSource.subscribe {
            setFlagIcon(it)
        })
    }

    private val className = WelcomeScreenViewModelImpl::javaClass.name


    override val flagIconUrl = MutableLiveData<String>()

    override fun onSignUpClick() {
        Log.d("VMWorking","Yes")
    }


    override fun setFlagIcon(country:String) {
        compositeDisposable.add(
            flagService.getFlagResponse().subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe({
                        if(it.countryList!=null){
                            it.countryList.forEach { countryData ->
                                if(countryData.name == country){
                                    flagIconUrl.postValue(countryData.flag)
                                }
                            }
                        }
                },
                {
                    Log.e(className, "Flag service failed with error $it" )
                }
            )
        )
    }



    }
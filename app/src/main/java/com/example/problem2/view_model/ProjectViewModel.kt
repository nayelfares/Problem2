package com.example.problem2.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.problem2.network.ApiManager
import com.example.problem2.network.model.PublishPostReq
import com.example.problem2.network.response.PublishPostRes
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class ProjectViewModel :ViewModel(){
    private val _publishResponse: MutableLiveData<PublishPostRes> = MutableLiveData()
    val publishResponse: LiveData<PublishPostRes>
        get() = _publishResponse

        fun publish(email:String,publisherType:String,isJoke:Int,description:String) {
            val loginObservable: Observable<PublishPostRes> =
                ApiManager.postService.publish(
                    PublishPostReq(email,publisherType,isJoke,description)
                )
            loginObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<PublishPostRes> {
                    override fun onSubscribe(d: Disposable) {}
                    override fun onNext(response: PublishPostRes) {
                        try {
                            _publishResponse.value = response
                        }catch (e:Exception){
                            _publishResponse.value = null
                        }
                    }
                    override fun onError(e: Throwable) {
                        _publishResponse.value = null
                    }

                    override fun onComplete() {}
                })
        }

}
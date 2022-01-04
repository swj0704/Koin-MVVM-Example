package org.software.meister.gsm.koinexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(val api : API) : ViewModel() {

    private val _meal = MutableLiveData<MealResponse>()
    val meal : LiveData<MealResponse> = _meal

    fun getMeal(){          // api 호출
        api.getMeal(ymd = "20211010").enqueue(object : Callback<MealResponse> {
            override fun onResponse(call: Call<MealResponse>, response: Response<MealResponse>) {
                if(response.isSuccessful){
                    if(response.body() != null){
                        _meal.value = response.body()!!
                    }
                }
            }

            override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}
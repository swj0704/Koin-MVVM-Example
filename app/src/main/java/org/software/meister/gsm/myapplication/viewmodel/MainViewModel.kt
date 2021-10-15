package org.software.meister.gsm.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.software.meister.gsm.myapplication.api.RetrofitHelper
import org.software.meister.gsm.myapplication.api.model.MealResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    val service = RetrofitHelper.getAPI(RetrofitHelper.getRetrofit(RetrofitHelper.getInstance()))
    //retrofit 객체 생성

    private val _meal = MutableLiveData<MealResponse>()
    val meal : LiveData<MealResponse> = _meal

    //LiveData 를 통해 Activity에서 데이터의 변경을 지켜보고 그에 따라 뷰를 변경한다.
    //Mutable은 가변이라는 뜻으로 바꿀 수 있는 이라는 뜻이다.
    //그냥 LiveData는 UnMutable은 불변 이라는 뜻으로 바꿀 수 없는 이라는 뜻이다.
    //따라서 액티비티에서 지켜보는 변수는 액티비티에서 값을 바꾸면 충돌이 생길 수 있으므로
    //액티비티에서 지켜보는 LiveData 는 UnMutable인 LiveData로 선언한다
    //ViewModel에서는 자신이 데이터를 변경해야하므로 MutableLiveData 의 값을 바꿔준다.
    //위와 같이 meal : LiveData<MealResponse> = _meal일때 _meal이 바뀌면
    //meal의 주소값이 _meal의 주소값을 따르고 있기 때문에 meal의 값도 바뀌게 된다.
    //따라서 activity에서 meal을 observe 할때 _meal 의 값이 바뀌어도 호출이 된다.

    fun getMeal(){          // api 호출
        service.getMeal(ymd = "20211010").enqueue(object : Callback<MealResponse>{
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
package org.software.meister.gsm.koinexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.software.meister.gsm.koinexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val viewModel : MainViewModel by viewModel()
    val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            viewModel.getMeal()
        }

        //ViewModel의 meal 변수 observe
        //ViewModel에서 meal 이 바뀌면
        //textView.text = it.mealServiceDietInfo[1].row?.get(0)?.DDISH_NM.toString()
        //이 실행된다.
        viewModel.meal.observe(this, {
            binding.mealText.text = it.mealServiceDietInfo[1].row?.get(0)?.DDISH_NM.toString()
        })
    }
}
package org.software.meister.gsm.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import org.software.meister.gsm.myapplication.R
import org.software.meister.gsm.myapplication.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.meal_text)
        val button = findViewById<Button>(R.id.button)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)      //ViewModel 객체 생성
        button.setOnClickListener {
            viewModel.getMeal()
        }

        //ViewModel의 meal 변수 observe
        //ViewModel에서 meal 이 바뀌면
        //textView.text = it.mealServiceDietInfo[1].row?.get(0)?.DDISH_NM.toString()
        //이 실행된다.
        viewModel.meal.observe(this, {
            textView.text = it.mealServiceDietInfo[1].row?.get(0)?.DDISH_NM.toString()
        })

    }
}
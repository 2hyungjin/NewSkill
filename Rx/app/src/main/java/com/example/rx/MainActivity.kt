package com.example.rx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.rx.API.UserRetrofit
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity() {
    private val retrofit:UserRetrofit= UserRetrofit.getAPI()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        retrofit.getUsers()
            .subscribeOn(Schedulers.io())
            .subscribe(
                {result->Log.d("TAG","result : $result.toString()")},
                {e->Log.d("TAG",e.toString())}
            )

    }


}
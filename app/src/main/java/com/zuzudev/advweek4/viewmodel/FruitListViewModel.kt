package com.zuzudev.advweek4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zuzudev.advweek4.model.Fruit
import com.zuzudev.advweek4.model.Student

class FruitListViewModel(application: Application): AndroidViewModel(application) {
    val fruitLD = MutableLiveData<ArrayList<Fruit>>()
    val fruitLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null


    fun refresh() {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.16.188/anmp/fruits.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                loadingLD.value = true
                fruitLoadErrorLD.value = false

                val sType = object: TypeToken<List<Fruit>>(){}.type
                val result = Gson().fromJson<List<Fruit>>(it, sType)
                fruitLD.value = result as ArrayList<Fruit>?
                loadingLD.value = false
                Log.d("showfruit", it)

            },
            {
                Log.d("showfruit", it.toString())
                fruitLoadErrorLD.value = false
                loadingLD.value = false
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)


    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}
package com.example.kbk.ui.menu.gallery

import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kbk.R
import com.example.kbk.model.Photos
import com.example.kbk.network.Api
import com.example.kbk.network.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class GalleryFragment:Fragment() {

    lateinit var rec: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val retrofit: Retrofit =
            Retrofit.Builder()
                .baseUrl(Constants.apilink).
                addConverterFactory(GsonConverterFactory.create())
                .build()
        val api: Api = retrofit.create(Api::class.java)
        val images:Call<Photos> = api.getImages()
        images.enqueue(object : Callback<Photos>{
            override fun onResponse(call: Call<Photos>, response: Response<Photos>) {
                rec.adapter= response.body()?.let { GalleryAdapter(it.images,requireContext()) }
            }

            override fun onFailure(call: Call<Photos>, t: Throwable) {
                Log.e("error", "Failed to fetch photos", t)
            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.menu_fragment_gallery, container, false)
        rec = root.findViewById(R.id.gallery_recycler_view)
        rec.layoutManager=GridLayoutManager(context,3)
        return root
    }
    companion object {
        fun newInstance() = GalleryFragment()
    }
}
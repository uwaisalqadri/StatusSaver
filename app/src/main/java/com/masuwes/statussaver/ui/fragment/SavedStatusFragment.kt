package com.masuwes.statussaver.ui.fragment

import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.masuwes.statussaver.R
import com.masuwes.statussaver.adapter.StatusAdapter
import com.masuwes.statussaver.util.Utils
import java.io.File


class SavedStatusFragment : Fragment() {
    lateinit var mAdView: AdView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        avedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_saved_status, container, false)
        val recyclerViewSaved = view.findViewById<RecyclerView>(R.id.recyclerView_saved)
        val swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipeRLayout)

        MobileAds.initialize(context) {}
        mAdView = view.findViewById(R.id.adView1)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        recyclerViewSaved.adapter = StatusAdapter(
            context!!,
            Utils().getListFiles(
                File(
                    Environment.getExternalStorageDirectory()
                        .toString() + SAVED_WHATSAPP_STATUSES_LOCATION
                )
            ),
            true
        )
        recyclerViewSaved.layoutManager = GridLayoutManager(context, 2)

        swipeRefreshLayout.setOnRefreshListener {
            recyclerViewSaved.adapter = StatusAdapter(
                context!!,
                Utils().getListFiles(
                    File(
                        Environment.getExternalStorageDirectory().absolutePath
                            .toString() + SAVED_WHATSAPP_STATUSES_LOCATION
                    )
                ),
                true
            )
            swipeRefreshLayout.isRefreshing = false
        }
        return view
    }


    companion object {
        private const val SAVED_WHATSAPP_STATUSES_LOCATION = "/Status Saver/"
        const val TAG = "Home"
    }

}
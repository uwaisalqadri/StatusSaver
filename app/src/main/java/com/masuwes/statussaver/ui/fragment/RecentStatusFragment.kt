package com.masuwes.statussaver.ui.fragment

import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

class RecentStatusFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        avedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_recent_status, container, false)
        val swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipeRLayout)
        val recyclerViewRecent = view.findViewById<RecyclerView>(R.id.recyclerView_recent)

        recyclerViewRecent.adapter = StatusAdapter(
            context!!,
            Utils().getListFiles(
                File(
                    Environment.getExternalStorageDirectory()
                        .toString() + WHATSAPP_STATUSES_LOCATION
                )
            ), false
        )
        recyclerViewRecent.layoutManager = GridLayoutManager(context, 2)


        swipeRefreshLayout.setOnRefreshListener {
            recyclerViewRecent.adapter = StatusAdapter(
                context!!,
                Utils().getListFiles(
                    File(
                        Environment.getExternalStorageDirectory()
                            .toString() + WHATSAPP_STATUSES_LOCATION
                    )
                ),
                false
            )
            swipeRefreshLayout.isRefreshing = false
        }
        return view
    }


    companion object {
        private const val WHATSAPP_STATUSES_LOCATION = "/WhatsApp/Media/.Statuses/"
    }


}
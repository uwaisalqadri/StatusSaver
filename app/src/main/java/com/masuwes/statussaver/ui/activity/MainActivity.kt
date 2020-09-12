package com.masuwes.statussaver.ui.activity

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.masuwes.statussaver.R
import com.masuwes.statussaver.adapter.SectionPagerAdapter
import com.masuwes.statussaver.ui.fragment.RecentStatusFragment
import com.masuwes.statussaver.ui.fragment.SavedStatusFragment
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mSectionsPageAdapter: SectionPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSectionsPageAdapter =
            SectionPagerAdapter(
                supportFragmentManager
            )

        tabs.setupWithViewPager(container)
        if (!isReadStorageAllowed()) {
            checkPermission()
        } else {
            setupViewPager(container)
        }

    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = SectionPagerAdapter(
            supportFragmentManager
        )
        adapter.addFragment(RecentStatusFragment(), "Recent Status")
        adapter.addFragment(SavedStatusFragment(), "Saved Status")
        viewPager.adapter = adapter
    }

    private fun isReadStorageAllowed(): Boolean {
        //Getting the permission status
        val result = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        return result == PackageManager.PERMISSION_GRANTED

    }

    private fun checkPermission() {
        val rationale = "Please provide Storage permission to save Status."
        val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val options = Permissions.Options()
            .setRationaleDialogTitle("Info")
            .setSettingsDialogTitle("Warning")
        Permissions.check(
            this,
            permissions,
            rationale,
            options,
            object : PermissionHandler() {
                override fun onGranted() {
                    // do your task.
                    // Set up the ViewPager with the sections adapter.
                    setupViewPager(container)
                }

                override fun onDenied(
                    context: Context,
                    deniedPermissions: ArrayList<String>
                ) {
                    // permission denied, block the feature.
                    Toast.makeText(
                        this@MainActivity,
                        "We need Storage Permission to Save/Load Status ",
                        Toast.LENGTH_LONG
                    ).show()

                }
            })
    }
}
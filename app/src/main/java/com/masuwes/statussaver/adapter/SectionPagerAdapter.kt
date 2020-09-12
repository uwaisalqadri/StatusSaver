package com.masuwes.statussaver.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.*

class SectionPagerAdapter(fragmentManager: FragmentManager):
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val mFragmentList: MutableList<Fragment> = ArrayList()
    private val mFragmentTitleList: MutableList<String> = ArrayList()

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? = mFragmentTitleList[position]

    override fun getCount(): Int = mFragmentList.size

    override fun getItem(position: Int): Fragment = mFragmentList[position]

}
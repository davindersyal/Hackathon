package com.android.devicemanagement.ui.dashboard.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import com.android.devicemanagement.ui.dashboard.fragment.AssignedFragment
import com.android.devicemanagement.ui.dashboard.fragment.NotAssignedFragment

class ViewPagerAdapter(fm: FragmentManager, var totalTabs: Int) : FragmentPagerAdapter(fm) {
    // this is for fragment tabs
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> NotAssignedFragment()
            1 -> AssignedFragment()

            else -> AssignedFragment()

        }
    }
    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }
}

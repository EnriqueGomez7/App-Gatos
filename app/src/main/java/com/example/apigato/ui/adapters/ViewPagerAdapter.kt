package com.example.apigato.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.apigato.ui.fragments.Fragment_Detalle
import com.example.apigato.ui.fragments.Fragment_Information
import com.example.apigato.ui.fragments.Fragment_Stats

class ViewPagerAdapter(activity: Fragment_Detalle) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if(position == 1) Fragment_Stats() else Fragment_Information()
    }


}
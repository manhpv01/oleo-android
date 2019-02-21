package com.framgia.oleo.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.framgia.oleo.R
import com.framgia.oleo.base.BaseFragment
import com.framgia.oleo.databinding.FragmentHomeBinding
import com.framgia.oleo.screen.messages.MessagesFragment
import com.framgia.oleo.screen.setting.SettingFragment
import com.framgia.oleo.utils.liveData.autoCleared
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var viewModel: HomeViewModel
    private var binding by autoCleared<FragmentHomeBinding>()

    override fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel = HomeViewModel.create(this, viewModelFactory)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        return binding.root
    }

    override fun setUpView() {
        // SetUp View
        val pagerAdapter = ViewPagerAdapter(childFragmentManager)
        pagerAdapter.addFragment(MessagesFragment.newInstance())
        pagerAdapter.addFragment(SettingFragment.newInstance())
        viewPager.adapter = pagerAdapter
        // To disable swipe
        viewPager.beginFakeDrag()
        navigation.setOnNavigationItemSelectedListener(this)
    }

    override fun bindView() {
        // Add Show View
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId) {
            R.id.navigation_messages -> {
                viewPager.setCurrentItem(0, false)
                return true
            }
            R.id.navigation_contacts -> {
                return true
            }
            R.id.navigation_groups -> {
                return true
            }
            R.id.navigation_more -> {
                viewPager.setCurrentItem(1, false)
                return true
            }
        }
        return true
    }

    companion object {
        fun newInstance() = HomeFragment().apply {
            val bundle = Bundle()
            arguments = bundle
        }
    }
}

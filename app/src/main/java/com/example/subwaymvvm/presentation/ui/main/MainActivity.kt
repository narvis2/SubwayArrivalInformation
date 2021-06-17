package com.example.subwaymvvm.presentation.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.subwaymvvm.R
import com.example.subwaymvvm.databinding.ActivityMainBinding
import com.example.subwaymvvm.presentation.base.BaseActivity
import com.example.subwaymvvm.presentation.extensions.toGone
import com.example.subwaymvvm.presentation.extensions.toVisible
import com.example.subwaymvvm.presentation.ui.arrival.StationArrivalsFragmentArgs

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    R.layout.activity_main,
    MainViewModel::class
) {
    private val navigationController by lazy {
        (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController
    }

    override fun onCreate() {
        initViews()
        bindViews()
    }

    // 클릭되었을때에 대한 처리
    override fun onSupportNavigateUp(): Boolean {
        return navigationController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun initViews() {
        setSupportActionBar(mViewDataBinding.toolBar)
        mViewDataBinding.toolBar.setupWithNavController(navigationController)
    }

    private fun bindViews() {
        // 화면이 변경될때마다 호출
        navigationController.addOnDestinationChangedListener{ _, destination, argument ->
            // destination.id -> 어떤 화면인지 구분
            if (destination.id == R.id.station_arrivals_fragment) {
                title = StationArrivalsFragmentArgs.fromBundle(argument!!).station.name
                viewModel.showToolbar()
            } else {
                viewModel.hideToolbar()
            }
        }
    }

    override fun observeData() {}
}
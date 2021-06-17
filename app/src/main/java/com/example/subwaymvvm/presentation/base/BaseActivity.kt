package com.example.subwaymvvm.presentation.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.subwaymvvm.BR
import kotlinx.coroutines.Job
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class BaseActivity<B: ViewDataBinding, VM: BaseViewModel>(
    @LayoutRes private val layoutResId: Int,
    clazz: KClass<VM>
) : AppCompatActivity() {

    protected lateinit var mViewDataBinding: B
    protected val viewModel : VM by viewModel(clazz = clazz)

    private lateinit var fetchJob: Job

    abstract fun onCreate()

    // BR.viewModel 은 Layout 파일에 viewModel 연결해야 생김
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewDataBinding = DataBindingUtil.setContentView(this, layoutResId)
        mViewDataBinding.lifecycleOwner = this
        mViewDataBinding.setVariable(BR.viewModel, viewModel)
        mViewDataBinding.executePendingBindings()

        fetchJob = viewModel.fetchData()
        onCreate()
        observeData()
    }

    abstract fun observeData()

    override fun onDestroy() {
        if (fetchJob.isActive) {
            fetchJob.cancel()
        }
        super.onDestroy()
    }
}

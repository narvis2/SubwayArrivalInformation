package com.example.subwaymvvm.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.subwaymvvm.BR
import kotlinx.coroutines.Job
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class BaseFragment<B: ViewDataBinding, VM: BaseViewModel>(
    @LayoutRes private val layoutResId: Int,
    clazz: KClass<VM>
) : Fragment() {

    protected lateinit var mViewDataBinding: B
    protected val viewModel : VM by viewModel(clazz = clazz)
    private var mActivity: BaseActivity<*,*>? = null
    private lateinit var fetchJob: Job

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*,*>) {
            mActivity = context
        }
    }

    abstract fun onCreate()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        return mViewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding.setVariable(BR.viewModel, viewModel)
        mViewDataBinding.lifecycleOwner = this
        mViewDataBinding.executePendingBindings()

        fetchJob = viewModel.fetchData()
        onCreate()
        observeData()
    }

    abstract fun observeData()


    fun getBaseActivity() : BaseActivity<*,*>? {
        return mActivity
    }

    override fun onDetach() {
        mActivity = null
        super.onDetach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (fetchJob.isActive) {
            fetchJob.cancel()
        }
    }
}

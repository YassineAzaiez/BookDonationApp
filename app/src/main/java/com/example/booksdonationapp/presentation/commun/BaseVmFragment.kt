package com.example.booksdonationapp.presentation.commun


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.gamehub.commen.ui.BaseViewModel


abstract class BaseVmFragment<VM : BaseViewModel, VB : ViewBinding>(private val modelClass: Class<VM>) :
    Fragment() {

    private var _viewBinding: VB? = null

    protected val viewBinding: VB
        get() = _viewBinding!!

    protected lateinit var activity: BaseActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as BaseActivity
    }

    abstract fun startObserve()


    lateinit var viewModel: VM

    abstract fun createView(inflater: LayoutInflater, container: ViewGroup?): VB



    abstract fun initData()

    abstract fun initView()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     _viewBinding = createView(inflater,container)
        return _viewBinding?.root
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        this.activity = context as BaseActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        startObserve()
        initData()
    }


}
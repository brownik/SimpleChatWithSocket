package com.brownik.sockettest.common_base.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.IdRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

abstract class BaseFragment<Binding : ViewDataBinding> : Fragment() {

    private var _binding: Binding? = null
    protected val binding get() = _binding!!

    protected abstract val enableBackPressed: Boolean

    private val backPressedCallback by lazy {
        object : OnBackPressedCallback(enableBackPressed) {
            override fun handleOnBackPressed() {
                finishActivity()
                onFragmentBackPressed()
            }
        }
    }

    protected open fun onFragmentBackPressed(): Unit = Unit

    protected abstract fun createFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): Binding

    protected open fun initFragment(savedInstanceState: Bundle?) = Unit
    protected open fun initBinding() = Unit

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().onBackPressedDispatcher.addCallback(this, backPressedCallback)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = createFragmentBinding(inflater, container).apply {
        lifecycleOwner = viewLifecycleOwner
        _binding = this
        initBinding()
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragment(savedInstanceState)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    fun addFragment(
        @IdRes containerId: Int,
        fragment: Fragment?,
        fragmentManager: FragmentManager = childFragmentManager,
        addBackStack: Boolean = false,
        backStackName: String? = null,
    ) {
        requireNotNull(fragment)

        val transaction = fragmentManager.beginTransaction()
        transaction.add(containerId, fragment).apply {
            if (addBackStack) addToBackStack(backStackName)
        }
        transaction.commitAllowingStateLoss()
    }

    fun replaceFragment(
        @IdRes containerId: Int,
        fragment: Fragment?,
        fragmentManager: FragmentManager = childFragmentManager,
        addBackStack: Boolean = false,
    ) {
        requireNotNull(fragment)

        val transaction = fragmentManager.beginTransaction()
        transaction.replace(containerId, fragment).apply {
            if (addBackStack) addToBackStack(null)
        }
        transaction.commitAllowingStateLoss()
    }

    fun removeFragment(
        fragment: Fragment?,
        fragmentManager: FragmentManager = childFragmentManager,
        popBackStack: Boolean = true,
    ) {
        requireNotNull(fragment)
        val transaction = fragmentManager.beginTransaction()
        transaction.remove(fragment).commitAllowingStateLoss()
        if (popBackStack) fragmentManager.popBackStack()
    }

    fun addViewToBackStack(
        tag: String,
        fragmentManager: FragmentManager = childFragmentManager,
    ) {
        fragmentManager.beginTransaction().addToBackStack(tag).commit()
    }

    fun finishActivity(): Boolean {
        return if (parentFragmentManager.backStackEntryCount == 1) {
            requireBaseActivity().finishActivity()
            true
        } else false
    }

    fun Fragment.requireBaseActivity(): BaseActivity<*> {
        val activity = requireActivity()
        require(activity is BaseActivity<*>)

        return activity
    }
}
package com.teltech.employees.coreui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.teltech.employees.navigation.BackPropagatingFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

abstract class BaseFragment<ViewState : Any, Binding : ViewBinding>(
    private val bindingInflater: (LayoutInflater) -> Binding
) : Fragment(), BackPropagatingFragment {

    private var disposables = CompositeDisposable()

    abstract val model: BaseViewModel<ViewState>

    private var _binding: Binding? = null

    protected val binding: Binding
        get() = _binding!!

    protected abstract fun render(viewState: ViewState)

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = bindingInflater(layoutInflater)
        return binding.root
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root.isClickable = true // To avoid clicks passing through

        binding.initialiseView(view, savedInstanceState)

        model.viewStates().forEach { addDisposable(it.subscribe(this::render, Timber::e)) }
    }

    /** Override to initialise view */
    protected open fun Binding.initialiseView(view: View, savedInstanceState: Bundle?) {
        // Template
    }

    override fun onDestroyView() {
        _binding = null

        disposables.clear()
        super.onDestroyView()
    }

    override fun back() = false

    private fun addDisposable(disposable: Disposable) {
        require(!disposables.isDisposed)
        disposables.add(disposable)
    }

    protected fun setStatusBarColor(@ColorRes color: Int) {
        context?.let {
            activity?.window?.statusBarColor = ContextCompat.getColor(it, color)
        }
    }
}

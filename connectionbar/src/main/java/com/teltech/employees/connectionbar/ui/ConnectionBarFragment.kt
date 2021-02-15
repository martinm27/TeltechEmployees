package com.teltech.employees.connectionbar.ui

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.teltech.employees.connectionbar.databinding.FragmentConnectionBarBinding
import com.teltech.employees.connectionbar.ui.ConnectionStatusViewStateModel.ConnectedStatusViewStateModel
import com.teltech.employees.connectionbar.ui.ConnectionStatusViewStateModel.DisconnectedStatusViewStateModel
import com.teltech.employees.coreui.BaseFragment
import com.teltech.employees.coreui.utils.collapseViewHeight
import com.teltech.employees.coreui.utils.expandViewHeight
import com.teltech.employees.coreui.utils.removeDrawables
import com.teltech.employees.coreui.utils.setLeftDrawable
import org.koin.android.viewmodel.ext.android.viewModel

class ConnectionBarFragment :
    BaseFragment<ConnectionBarViewState, FragmentConnectionBarBinding>(FragmentConnectionBarBinding::inflate) {

    override val model: ConnectionBarViewModel by viewModel()

    override fun FragmentConnectionBarBinding.initialiseView(
        view: View,
        savedInstanceState: Bundle?
    ) {
        view.outlineProvider = null     // Removes elevation shadow visible
    }

    override fun render(viewState: ConnectionBarViewState) {
        when (val viewModel = viewState.viewModel) {
            is ConnectedStatusViewStateModel -> renderConnectedStatus(viewModel)
            is DisconnectedStatusViewStateModel -> renderDisconnectedStatus(viewModel)
            ConnectionStatusViewStateModel.EmptyConnectionStatusViewStateModel -> view?.collapseViewHeight()
            else -> throw IllegalArgumentException("$viewModel is not supported")
        }
    }

    private fun renderConnectedStatus(viewModel: ConnectedStatusViewStateModel) {
        binding.connectionBarTitle.setText(viewModel.connectionStatus)
        (binding.connectionBarTitle.currentView as TextView).setLeftDrawable(viewModel.drawableStart)
        animateBackgroundChange(ContextCompat.getColor(context!!, viewModel.backgroundColor))
    }

    private fun renderDisconnectedStatus(viewModel: DisconnectedStatusViewStateModel) {
        binding.connectionBarTitle.setCurrentText(viewModel.connectionStatus)
        (binding.connectionBarTitle.currentView as TextView).removeDrawables()

        view?.run {
            setBackgroundColor(ContextCompat.getColor(context!!, viewModel.backgroundColor))
            expandViewHeight()
        }
    }

    private fun animateBackgroundChange(newBackgroundColor: Int) {
        view?.background?.let {
            ValueAnimator.ofObject(ArgbEvaluator(), (it as ColorDrawable).color, newBackgroundColor)
                .apply {
                    addUpdateListener { animator ->
                        view?.setBackgroundColor(animator.animatedValue as Int)
                    }
                    start()
                }
        } ?: view?.setBackgroundColor(newBackgroundColor)
    }
}

package com.teltech.employees.master.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.teltech.employees.coreui.adapter.BaseListAdapter
import com.teltech.employees.coreui.adapter.BindingViewHolder
import com.teltech.employees.imagelib.imageloader.ImageQueryLoader
import com.teltech.employees.master.R
import com.teltech.employees.master.databinding.ItemEmployeeBinding
import com.teltech.employees.master.ui.EmployeeViewStateModel

class MasterAdapter(
    private val layoutInflater: LayoutInflater,
    private val imageLoader: ImageQueryLoader,
    private val onClickListener: (Int) -> Unit
) : BaseListAdapter<EmployeeViewStateModel, MasterAdapter.EmployeeViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EmployeeViewHolder =
        EmployeeViewHolder(
            layoutInflater,
            parent,
            imageLoader,
            onClickListener
        )

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) =
        holder.render(getItem(position))

    class EmployeeViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        private val imageLoader: ImageQueryLoader,
        private val onClickListener: (Int) -> Unit
    ) : BindingViewHolder<EmployeeViewStateModel, ItemEmployeeBinding>(
        ItemEmployeeBinding.inflate(layoutInflater, parent, false)
    ) {

        private var currentImageUrl: String? = null

        override fun ItemEmployeeBinding.render(item: EmployeeViewStateModel) {
            with(item) {
                itemView.setOnClickListener { onClickListener(item.index) }

                if (currentImageUrl?.equals(imageUrl) != true) {
                    currentImageUrl = imageUrl
                    imageLoader.loadWithKey(
                        employeeThumbnail,
                        imageUrl,
                        R.drawable.ic_placehodler
                    )
                }

                employeeName.text = title
                employeeStatus.text = subtitle
            }
        }
    }
}

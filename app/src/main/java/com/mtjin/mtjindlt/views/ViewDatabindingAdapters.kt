package com.mtjin.mtjindlt.views

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mtjin.mtjindlt.data.main.DeepLinkUrl
import com.mtjin.mtjindlt.views.main.MainHistoryAdapter


@BindingAdapter("setMainDeepLinkUrls")
fun RecyclerView.setEmployAdapterItems(items: List<DeepLinkUrl>?) {
    items?.let {
        (adapter as MainHistoryAdapter).submitList(it.toMutableList())
    }
}

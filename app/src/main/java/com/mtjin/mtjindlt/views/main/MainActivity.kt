package com.mtjin.mtjindlt.views.main

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import com.mtjin.mtjindlt.R
import com.mtjin.mtjindlt.base.BaseActivity
import com.mtjin.mtjindlt.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var mainHistoryAdapter: MainHistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        initAdapter()
        initViewModelCallback()
        initDeepLinkUrlHistories()
    }

    private fun initAdapter() {
        mainHistoryAdapter = MainHistoryAdapter(linkClick = {
            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("text", it.url)
            clipboardManager.setPrimaryClip(clipData)
            showToast("Copied ! -> " + it.url)
        },
            deleteClick = {
                viewModel.deleteDeepLinkUrl(it)
            })
        binding.rvHistories.adapter = mainHistoryAdapter
    }

    private fun initDeepLinkUrlHistories() {
        viewModel.fetchDeepLinkUrlHistories()
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            onClickSearch.observe(this@MainActivity, { success ->
                if (success) {
                    try {
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(viewModel.etSearch.value.toString())
                        ).let { startActivity(it)}
                    } catch (e: Exception) {
                        showToast(e.message.toString())
                    }
                } else {
                    showToast(getString(R.string.fail_msg))
                }
            })
        }
    }
}
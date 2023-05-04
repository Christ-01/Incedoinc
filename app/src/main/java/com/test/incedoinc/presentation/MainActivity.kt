package com.test.incedoinc.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.incedoinc.R
import com.test.incedoinc.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var adapter: CommentsAdapter

    private var selectedPosition = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel.getPosts()
        viewModel.fetchResult.observe(this) {
            when(it) {
                is ViewResult.ShowLoading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is ViewResult.PostData -> {
                    binding.progressBar.visibility = View.GONE
                    adapter.comments = ArrayList(it.comments)
                    adapter.notifyDataSetChanged()
                }
            }
        }

        adapter = CommentsAdapter{ position ->
            selectedPosition = position
            chooseImage()
        }
        binding.listview.adapter = adapter
        binding.listview.layoutManager = LinearLayoutManager(this)
    }

    fun chooseImage() {
        val intent = Intent().apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
        }
        launchImagePicker.launch(intent)
    }

    var launchImagePicker = registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode
            == RESULT_OK
        ) {
            val data = result.data
            if (data != null
                && data.data != null
            ) {
                val selectedImageUri: Uri? = data.data
                adapter.updateUserProfile(selectedPosition, selectedImageUri)
            }
        }
    }
}
package com.project.userapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.project.userapp.adapter.UserAdapter
import com.project.userapp.databinding.ActivityMainBinding
import com.project.userapp.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        viewModel.getUserLiveData().observe(this, Observer {
            if (it.isSuccess) {
                val adapter = UserAdapter()
                binding.recyclerView.addItemDecoration(
                    DividerItemDecoration(
                        binding.recyclerView.getContext(),
                        DividerItemDecoration.VERTICAL
                    )
                )
                binding.recyclerView.adapter = adapter
                adapter.submitList(it.users)
            } else {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
        })

    }
}
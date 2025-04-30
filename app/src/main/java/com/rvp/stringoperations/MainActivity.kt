package com.rvp.stringoperations

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.rvp.stringoperations.databinding.ActivityMainBinding
import com.rvp.stringoperations.viewmodel.StringViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: StringViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel.stringOutput.observe(this, Observer {
            binding.tvOutputView.text = it
        })

        viewModel.stringErrorMessage.observe(this, Observer { message ->
            if (message != null) {
                binding.tvErrorView.text = message
                binding.tvErrorView.visibility = android.view.View.VISIBLE
            } else {
                binding.tvErrorView.visibility = android.view.View.GONE
            }
        })

        viewModel.inputValue.observe(this, Observer { text ->
            if (binding.etText.text.toString() != text)
                binding.etText.setText(text)
        })

        binding.etText.addTextChangedListener {
            viewModel.setInputValue(it.toString())
        }

        with(binding) {
            btnLength.setOnClickListener {
                viewModel.getLength(etText.text.toString())
            }
            btnReverse.setOnClickListener {
                viewModel.getReversedString(etText.text.toString())
            }
            btnAppend.setOnClickListener {
                viewModel.appendValue(etText.text.toString())
            }
            btnIsNumeric.setOnClickListener {
                viewModel.isStringNumeric(etText.text.toString())
            }
            btnClear.setOnClickListener {
                viewModel.clearData()
            }
        }

    }
}
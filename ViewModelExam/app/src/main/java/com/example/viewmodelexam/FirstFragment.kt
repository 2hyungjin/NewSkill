package com.example.viewmodelexam

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

class FirstFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    val vm : MainViewModel by activityViewModels()
    val vmm : MainViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.first_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(this)[MainViewModel::class.java]

        view.findViewById<Button>(R.id.btn_enter).setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }
        viewModel.name.observe(viewLifecycleOwner, Observer {
            view.findViewById<TextView>(R.id.textView).text=it
        })
        vm.name.observe(viewLifecycleOwner, Observer {
            view.findViewById<TextView>(R.id.textView2).text=it

        })
        vmm.name.observe(viewLifecycleOwner, Observer {
            view.findViewById<TextView>(R.id.textView3).text=it

        })
    }


}
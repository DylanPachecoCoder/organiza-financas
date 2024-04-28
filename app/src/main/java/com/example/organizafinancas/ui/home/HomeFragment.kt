package com.example.organizafinancas.ui.home
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.organizafinancas.databinding.FragmentHomeBinding
import com.example.organizafinancas.domain.model.Purchase
import com.example.organizafinancas.ui.adapter.FilterAdapter
import com.example.organizafinancas.ui.adapter.PurchaseListAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.text.observe(viewLifecycleOwner) {}
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerview.adapter = FilterAdapter(mutableListOf("credito", "debito", "dinheiro", "pix", "vr", "va"))
        binding.recyclerview2.adapter = PurchaseListAdapter(mutableListOf(
            Purchase(),
            Purchase(),
            Purchase(),
            Purchase(),
            Purchase(),
            Purchase(),
            Purchase(),
            Purchase(),
            Purchase(),
        ))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
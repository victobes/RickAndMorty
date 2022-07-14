package com.example.rickandmorty.ui.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.rickandmorty.R
import com.example.rickandmorty.api.Repository
import com.example.rickandmorty.api.extensions.getTextButtonChecked
import com.example.rickandmorty.api.extensions.getTextChipChecked
import com.example.rickandmorty.api.extensions.setButtonChecked
import com.example.rickandmorty.api.extensions.setChipChecked
import com.example.rickandmorty.databinding.FragmentFilterBinding
import com.example.rickandmorty.ui.SharedViewModel
import com.example.rickandmorty.ui.SharedViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class FilterFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: SharedViewModel by activityViewModels {
        SharedViewModelFactory(
            Repository()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            sharedViewModel.filterValue.observe(viewLifecycleOwner) { item ->
                chipgroupStatus.setChipChecked(item[0])
                radiogroupGender.setButtonChecked(item[1])
            }

            clearRadioCheck.setOnClickListener {
                radiogroupGender.clearCheck()
            }

            btnApplyFilter.setOnClickListener {

                if (chipgroupStatus.getTextChipChecked()
                        .isNotEmpty() && radiogroupGender.getTextButtonChecked().isNotEmpty()
                ) {
                    sharedViewModel.getByStatusAndGender(
                        chipgroupStatus.getTextChipChecked(),
                        radiogroupGender.getTextButtonChecked(),
                        1
                    )
                } else {
                    if (chipgroupStatus.getTextChipChecked().isNotEmpty()) {
                        sharedViewModel.getByStatus(chipgroupStatus.getTextChipChecked(), 1)
                    } else {
                        sharedViewModel.getByGender(radiogroupGender.getTextButtonChecked(), 1)
                    }
                }
                sharedViewModel.filterValue.value =
                    arrayOf(chipgroupStatus.checkedChipId, radiogroupGender.checkedRadioButtonId)

                findNavController().popBackStack(R.id.listFragment, false)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
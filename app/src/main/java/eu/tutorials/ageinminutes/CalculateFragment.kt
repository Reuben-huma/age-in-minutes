package eu.tutorials.ageinminutes

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import eu.tutorials.ageinminutes.databinding.FragmentCalculateBinding
import eu.tutorials.ageinminutes.model.CalculateViewModel
import java.util.*

class CalculateFragment : Fragment() {

    private var binding: FragmentCalculateBinding? = null
    private val viewModel: CalculateViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalculateBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            calculateFragment = this@CalculateFragment
            calculateViewModel = viewModel
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun onSelectDate() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR) // Returns the value of the given calendar field. This indicates YEAR
        val month = c.get(Calendar.MONTH) // This indicates the Month
        val day = c.get(Calendar.DAY_OF_MONTH) // This indicates the Day

        val dpd = DatePickerDialog(requireContext(), { _, theYear, monthOfYear, dayOfMonth ->
            val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$theYear"
            viewModel.setDate(selectedDate)
        }, year, month, day
        )

        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }


}
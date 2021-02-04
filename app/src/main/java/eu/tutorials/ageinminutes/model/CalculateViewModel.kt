package eu.tutorials.ageinminutes.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class CalculateViewModel : ViewModel() {

    private var _date = MutableLiveData<String>()
    val date: LiveData<String> get() = _date

    private var _ageInMinutes = MutableLiveData<Long>()
    val ageInMinutes: LiveData<Long> get() = _ageInMinutes

    private val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
    private val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())

    init {
        _date.value = formatter.format(Calendar.getInstance().time)
        _ageInMinutes.value = 0
    }

    fun setDate(selectedDate: String) {
        val theDate = sdf.parse(selectedDate)
        val finalDate = formatter.format(theDate?.time)
        _date.value = finalDate

        calculateAgeInMinutes(theDate)
    }

    private fun calculateAgeInMinutes(theDate: Date?) {
        val selectedDateToMinutes = theDate!!.time / 60000
        val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
        val currentDateToMinutes = currentDate!!.time / 60000
        val differenceInMinutes = currentDateToMinutes - selectedDateToMinutes
        _ageInMinutes.value = differenceInMinutes
    }
}
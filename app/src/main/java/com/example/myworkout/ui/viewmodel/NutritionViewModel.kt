package com.example.myworkout.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myworkout.data.model.Meal
import com.example.myworkout.data.model.DailyStats
import com.example.myworkout.data.repository.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import javax.inject.Inject

@HiltViewModel
class NutritionViewModel @Inject constructor(
    private val repository: MealRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : ViewModel() {

    enum class TimeRange {
        WEEK, MONTH, YEAR
    }

    private val _selectedTimeRange = MutableStateFlow(TimeRange.WEEK)
    val selectedTimeRange = _selectedTimeRange.asStateFlow()

    val meals: StateFlow<List<Meal>> = repository.getAllMeals()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _stats = MutableStateFlow<List<DailyStats>>(emptyList())
    val stats: StateFlow<List<DailyStats>> = _stats.asStateFlow()

    fun setTimeRange(range: TimeRange) {
        viewModelScope.launch {
            try {
                _selectedTimeRange.value = range
                val (startDate, endDate) = getDateRange(range)
                repository.getDailyStats(startDate, endDate)
                    .collect { newStats ->
                        _stats.emit(newStats)
                    }
            } catch (e: Exception) {
                // Log error oder handle Exception
            }
        }
    }

    fun addMeal(meal: Meal) {
        viewModelScope.launch {
            repository.addMeal(meal)
        }
    }

    fun deleteMeal(meal: Meal) {
        viewModelScope.launch {
            repository.deleteMeal(meal)
        }
    }

    fun getMealsByDateRange(startDate: LocalDateTime, endDate: LocalDateTime): Flow<List<Meal>> {
        return repository.getMealsByDateRange(startDate, endDate)
    }

    fun deleteOldMeals(olderThan: LocalDateTime) {
        viewModelScope.launch {
            repository.deleteOldMeals(olderThan)
        }
    }

    private fun getDateRange(range: TimeRange): Pair<LocalDateTime, LocalDateTime> {
        val now = LocalDateTime.now()
        val end = now
        val start = when (range) {
            TimeRange.WEEK -> now.minusWeeks(1)
            TimeRange.MONTH -> now.minusMonths(1)
            TimeRange.YEAR -> now.minusYears(1)
        }
        return start to end
    }
} 
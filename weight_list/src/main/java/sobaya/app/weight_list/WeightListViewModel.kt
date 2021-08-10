package sobaya.app.weight_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import sobaya.app.database.entity.Weight
import java.util.Random
import javax.inject.Inject

@HiltViewModel
internal class WeightListViewModel @Inject constructor() : ViewModel() {
    private val dailyWeightData = MutableStateFlow<List<Weight>?>(null)
    val state = dailyWeightData.map {
        WeightListViewState(it)
    }

    init {
        viewModelScope.launch {
            delay(500)
            val random = Random()
            dailyWeightData.value = (1..7).map { index ->
                Weight(random.nextInt().toFloat(), "2021/08/0$index")
            }
        }
    }
}
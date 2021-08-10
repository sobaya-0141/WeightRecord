package sobaya.app.weight_list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.github.mikephil.charting.charts.LineChart
import sobaya.app.database.entity.Weight

@Composable
fun WeightList(navController: NavController) {
    val viewModel: WeightListViewModel = hiltViewModel()
    DailyWeightList(viewModel)
}

@Composable
private fun DailyWeightList(viewModel: WeightListViewModel) {
    val viewState: WeightListViewState by viewModel.state.collectAsState(initial = WeightListViewState.EMPTY)
    val dailyWeightData: List<Weight> = viewState.dailyWeightData ?: emptyList()

    LazyColumn {
        item {
            WeightLineChart(dailyWeightData = dailyWeightData)
        }
        
        dailyWeightData.forEach { weight ->
            item { 
                WeightTextLine(weight = weight)
            }
        }
    }
}

@Composable
private fun WeightLineChart(dailyWeightData: List<Weight>) {
    AndroidView(
        factory = { context ->
            LineChart(context)
        }
    )
}

@Composable
private fun WeightTextLine(weight: Weight) {
    Text(text = "計測日：${weight.date} 体重:${weight.weight}")
}
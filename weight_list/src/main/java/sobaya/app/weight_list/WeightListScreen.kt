package sobaya.app.weight_list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.github.mikephil.charting.charts.LineChart
import sobaya.app.database.entity.Weight
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import sobaya.app.weight_list.add_weight.AddWeightDialog

@Composable
fun WeightList(navController: NavController) {
    val viewModel: WeightListViewModel = hiltViewModel()
    DailyWeightList(viewModel)
}

@Composable
private fun DailyWeightList(viewModel: WeightListViewModel) {
    val viewState: WeightListViewState by viewModel.state.collectAsState(initial = WeightListViewState.EMPTY)
    val dailyWeightData: List<Weight> = viewState.dailyWeightData ?: emptyList()

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        content = {
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
        },
        floatingActionButton = { addWeightData() }
    )
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

@Composable
private fun addWeightData() {
    val isOpenDialog = remember { mutableStateOf(false) }
    AddWeightDialog(isOpenDialog = isOpenDialog) {
        // TODO DBに入れる
        isOpenDialog.value = false
    }
    FloatingActionButton(
        onClick = {
            isOpenDialog.value = true
        }
    ) {
        Icon(Icons.Filled.Add, contentDescription = "追加")
    }
}

@Composable
@Preview
fun WeightLineChartPreview() {
    WeightLineChart(listOf(Weight(89.4f, "2021/08/11")))
}

@Composable
@Preview
fun WeightTextLinePreview() {
    WeightTextLine(Weight(89.5f, "2021/08/11"))
}
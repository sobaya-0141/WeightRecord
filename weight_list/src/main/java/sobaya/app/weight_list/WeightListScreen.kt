package sobaya.app.weight_list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import sobaya.app.database.entity.Weight
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
                if (dailyWeightData.isNotEmpty()) {
                    item {
                        WeightLineChart(dailyWeightData = dailyWeightData)
                    }
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

private fun makeChartData(dailyWeightData: List<Weight>): LineData {
    val values = ArrayList<Entry>(dailyWeightData.size)
    val lineDataSet = LineDataSet(values, "体重")

    dailyWeightData.forEachIndexed { index, weight ->
        values.add(Entry(index.toFloat(), weight.weight))
    }
    lineDataSet.values = values

    return LineData(lineDataSet)
}

@Composable
private fun WeightLineChart(dailyWeightData: List<Weight>) {
    AndroidView(
        factory = { context ->
            LineChart(context).apply {
                setupChart(this)
                if (dailyWeightData.isNotEmpty()) {
                    data = makeChartData(dailyWeightData)
                    data.notifyDataChanged()
                    notifyDataSetChanged()
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )
}

private fun setupChart(chart: LineChart) {
    chart.description.isEnabled = false
    chart.setTouchEnabled(false)
    chart.isDragEnabled = false
    chart.setScaleEnabled(false)
    chart.setPinchZoom(false)
    val yAxis = chart.axisLeft
    yAxis.mAxisMaximum = 200f
    yAxis.mAxisMinimum = 0f
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
package sobaya.app.weight_list

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun WeightList(navController: NavController) {
    Text("体重の一覧", fontSize = 50.sp)
}
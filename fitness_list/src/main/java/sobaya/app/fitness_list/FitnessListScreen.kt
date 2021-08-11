package sobaya.app.fitness_list

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun FitnessList(navController: NavController) {
    Text("運動の一覧")
}

@Composable
@Preview
fun FitnessListPreview() {
    FitnessList(navController = rememberNavController())
}
package sobaya.app.weight_list.add_weight

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog

@Composable
internal fun AddWeightDialog(isOpenDialog: MutableState<Boolean>, onClickRegist: () -> Unit) {
    if (!isOpenDialog.value) return
    var weight by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = { isOpenDialog.value = false },
    ) {
        androidx.compose.material.Surface {
            Column {
                Text(text = "体重の記録")
                Text(text = "現在の体重を入力")
                TextField(
                    value = weight,
                    onValueChange = { weight = it },
                    label = { Text("現在の体重") },
                    maxLines = 1,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Button(onClick = onClickRegist) {
                    Text(text = "登録")
                }
            }
        }
    }
}

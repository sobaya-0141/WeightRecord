package sobaya.app.weigtrecorder

import android.os.Bundle
import android.widget.ImageView.ScaleType
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ContentScale.Companion
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import dagger.hilt.android.AndroidEntryPoint
import sobaya.app.weigtrecorder.ui.theme.WeigtRecorderTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WeigtRecorderTheme {
                Test1()
            }
        }
    }
}

@Composable
fun Test1() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Title") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(painterResource(sobaya.app.resources.R.drawable.ic_menu_black_24dp), "menu")
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.Add, contentDescription = "")
                    }
                }
            )
        },
        drawerContent = { Text(text = "drawerContent") },
        content = {
            TestContent()
        },
    )
}

@Composable
fun TestContent() {
    Column {
        TextTabs()
        Profile()
    }
}

@Composable
fun Profile() {
    ConstraintLayout(modifier = Modifier.fillMaxWidth().height(200.dp)) {
        val (iconImage, userName, mail, iconD, point, help) = createRefs()
        Image(
            painter = rememberImagePainter("https://avatars.githubusercontent.com/u/45986582?v=4"),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        Image(
            painter = rememberImagePainter(
                data = "https://avatars.githubusercontent.com/u/45986582?v=4",
                builder = {
                    transformations(CircleCropTransformation())
                }
            ),
            contentDescription = null,
            modifier = Modifier.size(128.dp).constrainAs(iconImage) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start, margin = 16.dp)
            },
        )
    }
}

@Composable
fun TextTabs() {
    var tabIndex by remember { mutableStateOf(0) }
    val tabData = listOf(
        "そば",
        "うどん"
    )
    TabRow(selectedTabIndex = tabIndex) {
        tabData.forEachIndexed { index, text ->
            Tab(selected = tabIndex == index, onClick = {
                tabIndex = index
            }, text = {
                Text(text = text)
            })
        }
    }
}

@Composable
@Preview
fun Test1Preview() {
    Test1()
}
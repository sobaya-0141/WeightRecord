package sobaya.app.weigtrecorder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import dagger.hilt.android.AndroidEntryPoint
import sobaya.app.resources.transformations.RadiusTransformations
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
    val items = listOf("TAB", "PROFILE", "TITLE", "ITEM", "ITEM", "ITEM", "ITEM", "ITEM", "ITEM")
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(items, itemContent = { item ->
            when (item) {
                "TAB" -> TextTabs()
                "PROFILE" -> Profile()
                "TITLE" -> TitleRow()
                "ITEM" -> ItemRow()
            }
        })
    }
}

@Composable
fun ItemRow() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Color.LightGray)) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .background(Color.Gray)) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 1.dp, end = 1.dp, bottom = 1.dp)
                .background(Color.White)
            ) {
                Column(
                    Modifier
                        .padding(start = 84.dp)
                        .fillMaxWidth()) {
                    Text(
                        text = "そばや",
                        color = Color.Black,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(top = 16.dp)
                    )
                    Text(
                        text = "食べ放題",
                        color = Color.Gray,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(end = 16.dp, top = 8.dp)
                    )
                    Text(
                        text = "飲み放題",
                        color = Color.Gray,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(end = 16.dp, top = 8.dp)
                    )
                    Text(
                        text = "トイレ無し",
                        color = Color.Gray,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(end = 16.dp, top = 8.dp, bottom = 8.dp)
                    )
                }
            }
        }
        Card(modifier = Modifier
            .size(84.dp)
            .padding(start = 8.dp)
            .align(Alignment.CenterStart)) {
            Image(
                painter = rememberImagePainter(
                    data = "https://avatars.githubusercontent.com/u/45986582?v=4",
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun TitleRow() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Color.LightGray)) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            .background(Color.Gray)) {
            Row(
                Modifier
                    .padding(start = 1.dp, top = 1.dp, end = 1.dp, bottom = 1.dp)
                    .background(Color.White)) {
                Text(
                    text = "History",
                    color = Color.Black,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
                )
                Text(
                    text = "40",
                    color = Color.Black,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(end = 16.dp, top = 8.dp, bottom = 8.dp)
                )
            }
        }
    }
}

@Composable
fun Profile() {
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)) {
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
            modifier = Modifier
                .size(64.dp)
                .constrainAs(iconImage) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start, margin = 16.dp)
                },
        )
        Text(
            text = "Sobaya-0141",
            color = Color.White,
            fontSize = 24.sp,
            modifier = Modifier.constrainAs(userName) {
                start.linkTo(mail.start)
                bottom.linkTo(mail.top, margin = 16.dp)
            }
        )
        Text(
            text = "soba.ha.kenkou@gmail.com",
            color = Color.LightGray,
            fontSize = 16.sp,
            modifier = Modifier.constrainAs(mail) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(iconImage.end, margin = 16.dp)
            }
        )
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "star",
            modifier = Modifier.constrainAs(iconD) {
                top.linkTo(mail.bottom, margin = 16.dp)
                start.linkTo(mail.start)
            }
        )
        Text(
            text = "0141 points",
            color = Color.White,
            modifier = Modifier.constrainAs(point) {
                top.linkTo(iconD.top)
                start.linkTo(iconD.end, margin = 16.dp)
            }
        )
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = "info",
            modifier = Modifier.constrainAs(help) {
                top.linkTo(iconD.top)
                start.linkTo(point.end, margin = 16.dp)
            }
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

@Preview
@Composable
fun TestContentPreview() {
    TestContent()
}
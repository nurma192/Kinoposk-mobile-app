package com.assignmentsmobile.assignment_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.assignmentsmobile.assignment_2.ui.theme.Assignment_2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assignment_2Theme {
                OnboardingView()
//                OnboardingScreen(onFinish = { /* Логика при завершении онбординга */ })
            }
        }
    }
}


@Composable
fun OnboardingView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 26.dp, vertical = 38.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween){
            Image(
                painter = painterResource(id = R.drawable.ic_skillcinema),
                contentDescription = ""
            )
            Text(
                "Пропустить",
                modifier = Modifier.clickable { },
                style = TextStyle(
                    color = Color(0xffb5b5c9),
                    fontFamily = FontFamily(Font(R.font.graphik_medium)),
                    fontSize = 14.sp,
                ),
            )
        }
        Image(painter = painterResource(id = R.drawable.image_1), contentDescription = "")
        Text(
            text = "Узнавай о премьерах", style = TextStyle(
                color = Color(0xff272727),
                fontFamily = FontFamily(Font(R.font.graphik_medium)),
                fontSize = 32.sp,
            )
        )
        Row {

        }
    }
}

//@Composable
//fun OnboardingScreen(onFinish: () -> Unit) {
//
//    val pagerState = rememberPagerState()
//    Scaffold { paddingValues ->
//        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
//            HorizontalPager(count = onboardingItems.size, state = pagerState) { page ->
//                OnboardingView(item = onboardingItems[page], onNextClicked = {
//                    if (page == onboardingItems.size - 1) {
//                        onFinish() // Логика завершения онбординга
//                    } else {
//                        pagerState.animateScrollToPage(page + 1)
//                    }
//                })
//            }
//            Indicator(pagerState = pagerState)
//        }
//    }
//}
//
//@Composable
//fun OnboardingView(item: Onboarding, onNextClicked: () -> Unit) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()a
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
//            Text(text = "Skillcinema")
//            Button(onClick = { /* Логика пропуска */ }) {
//                Text("Пропустить")
//            }
//        }
//        Spacer(modifier = Modifier.height(16.dp))
//        Image(
//            painter = painterResource(id = item.imagePath),
//            contentDescription = item.imageText,
//            modifier = Modifier.fillMaxWidth().weight(1f)
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        Text(text = item.text)
//        Spacer(modifier = Modifier.height(16.dp))
//        Button(onClick = onNextClicked) {
//            Text("Далее")
//        }
//    }
//}
//
//@Composable
//fun Indicator(pagerState: PagerState) {
//    Row(
//        modifier = Modifier
//            .align(Alignment.BottomCenter)
//            .padding(16.dp),
//        horizontalArrangement = Arrangement.Center
//    ) {
//        for (i in 0 until pagerState.pageCount) {
//            Box(
//                modifier = Modifier
//                    .size(if (pagerState.currentPage == i) 12.dp else 8.dp)
//                    .background(color = if (pagerState.currentPage == i) Color.Black else Color.Gray, shape = CircleShape)
//                    .padding(4.dp)
//            )
//        }
//    }
//}

data class Onboarding(
    var imagePath: Int,
    var imageText: String,
    var text: String
)

val onboardingItems = listOf(
    Onboarding(R.drawable.image_1, "Image_1", "Узнавай о премьерах"),
    Onboarding(R.drawable.image_2, "Image_2", "Создавай коллекции"),
    Onboarding(R.drawable.image_3, "Image_3", "Делись с друзьями")
)

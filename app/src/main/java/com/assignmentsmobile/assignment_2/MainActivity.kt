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
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.assignmentsmobile.assignment_2.ui.theme.Assignment_2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assignment_2Theme {
                OnboardingScreen()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen() {
    val pagerState = rememberPagerState(pageCount = { onboardingItems.size })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 26.dp, end = 26.dp, top = 38.dp, bottom = 63.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_skillcinema),
                contentDescription = ""
            )
            Text(
                text = "Пропустить",
                modifier = Modifier.clickable { },
                style = TextStyle(
                    color = Color(0xffb5b5c9),
                    fontFamily = FontFamily(Font(R.font.graphik_medium)),
                    fontSize = 14.sp
                )
            )
        }

        HorizontalPager(state = pagerState) { page ->
            val item = onboardingItems[page]
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Spacer(modifier = Modifier.height(70.dp))
                Image(
                    painter = painterResource(id = item.imagePath),
                    contentDescription = item.imageText,
                    modifier = Modifier.size(250.dp)
                )
                Spacer(modifier = Modifier.height(67.dp))
                Text(
                    text = item.text,
                    style = TextStyle(
                        color = Color(0xff272727),
                        fontFamily = FontFamily(Font(R.font.graphik_regular)),
                        fontSize = 32.sp
                    )
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            repeat(onboardingItems.size) { index ->
                val color =
                    if (pagerState.currentPage == index) Color(0xff121616) else Color(0xffd9d9d9)
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(color = color, shape = CircleShape)
                )
                Spacer(modifier = Modifier.width(4.dp))
            }
        }
    }
}

data class Onboarding(
    val imagePath: Int,
    val imageText: String,
    val text: String
)

val onboardingItems = listOf(
    Onboarding(R.drawable.image_1, "Image_1", "Узнавай о премьерах"),
    Onboarding(R.drawable.image_2, "Image_2", "Создавай коллекции"),
    Onboarding(R.drawable.image_3, "Image_3", "Делись \nс друзьями")
)

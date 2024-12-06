package com.assignmentsmobile.assignment_2.ui.pages.ProfilePage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.assignmentsmobile.assignment_2.R
import com.assignmentsmobile.assignment_2.ui.components.FilmView
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import com.assignmentsmobile.assignment_2.data.Section
import com.assignmentsmobile.assignment_2.data.dbList
import com.assignmentsmobile.assignment_2.data.removeSectionByName
import com.assignmentsmobile.assignment_2.ui.pages.FilmInfoPage.RowInfo
import kotlin.math.ceil

data class Collection(val name: String, val icon: Int, val count: Int)

@Composable
@Preview(showBackground = true)
fun ProfilePage(
    onFilmClicked: (Int) -> Unit = {},
    onSeeAllClicked: (String) -> Unit = {}
) {
    val sections by dbList.observeAsState(emptyList())

    val collections = listOf(
        Collection("Любимые", R.drawable.ic_like, 105),
        Collection("Хочу посмотреть", R.drawable.ic_flag, 120),
        Collection("Русское кино", R.drawable.ic_profile, 75)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 50.dp)
    ) {
        Viewed(sections, onFilmClicked, onSeeAllClicked)
        CollectionsProfile(collections, onSeeAllClicked)
        YouInterested(sections, onFilmClicked, onSeeAllClicked)
    }
}

@Composable
fun Viewed(
    sections: List<Section>,
    onFilmClicked: (Int) -> Unit = {},
    onSeeAllClicked: (String) -> Unit = {}
) {
    RowInfo("Просмотрено", sections.first().list.size.toString(), onSeeAllClicked)
    Spacer(modifier = Modifier.height(24.dp))

    LazyRow(
        modifier = Modifier.padding(start = 26.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(sections.first().list) { film ->
            FilmView(film = film, onFilmClicked = onFilmClicked)
        }
        item {
            Column(
                modifier = Modifier
                    .size(width = 111.dp, height = 156.dp)
                    .padding(top = 51.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(
                    modifier = Modifier
                        .size(32.dp),
                    onClick = {
                        removeSectionByName("watched")
                    }
                ) {
                    Image(
                        modifier = Modifier
                            .background(Color(0xffffffff), shape = CircleShape)
                            .padding(6.dp)
                            .size(20.dp),
                        painter = painterResource(id = R.drawable.ic_trash),
                        contentDescription = "Ic_Trash"
                    )
                }
                Text(
                    text = "Очистить",
                    modifier = Modifier.clickable { },
                    style = TextStyle(
                        color = Color(0xff272727),
                        fontFamily = FontFamily(Font(R.font.graphik_regular)),
                        fontSize = 12.sp
                    )
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
fun CollectionsProfile(
    collections: List<Collection>,
    onSeeAllClicked: (String) -> Unit = {}
) {
    Text(
        modifier = Modifier.padding(start = 26.dp),
        text = "Коллекции",
        style = TextStyle(
            color = Color(0xff272727),
            fontFamily = FontFamily(Font(R.font.graphik_bold)),
            fontSize = 18.sp
        )
    )

    Spacer(modifier = Modifier.height(16.dp))
    CreateCollectionButton(collections)

    Spacer(modifier = Modifier.height(16.dp))
    CollectionsGrid(collections, onSeeAllClicked)

    Spacer(modifier = Modifier.height(40.dp))
}

@Composable
fun CreateCollectionButton(
    collections: List<Collection>,
) {
    var showBottomSheet by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 26.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            modifier = Modifier.size(24.dp),
            onClick = {
                //
                showBottomSheet = true
            }) {
            Image(
                painter = painterResource(id = R.drawable.ic_plus),
                contentDescription = "Ic_Plus",
                modifier = Modifier.size(24.dp)
            )
        }
        Text(
            text = "Создать свою коллекцию",
            style = TextStyle(
                color = Color(0xff272727),
                fontFamily = FontFamily(Font(R.font.graphik_medium)),
                fontSize = 14.sp
            )
        )
    }
    if (showBottomSheet) {
        AddToCollectionBottomSheet(
            collections = collections,
            onDismiss = { showBottomSheet = false },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddToCollectionBottomSheet(
    collections: List<Collection>,
    onDismiss: () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        containerColor = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 26.dp),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier
                    .size(30.dp),
                onClick = {
                    onDismiss()
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_delete_x),
                    contentDescription = "Ic_Delete_X",
                    modifier = Modifier.size(30.dp)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 26.dp, end = 26.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
//                    .clickable(onClick = { })
            ) {
                Box(
                    modifier = Modifier
                        .size(width = 114.dp, height = 150.dp)
                        .clip(shape = RoundedCornerShape(6.dp))
                        .background(Color(0x60b5b5c9))
                ) {
                    Box(
                        modifier = Modifier
                            .size(width = 24.dp, height = 15.dp)
                            .padding(top = 6.dp, start = 6.dp)
                            .clip(shape = RoundedCornerShape(6.dp))
                            .background(Color(0xffffffff))
                    ) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.Center),
                            text = "7.8",
                            style = TextStyle(
                                color = Color(0xff272727),
                                fontFamily = FontFamily(Font(R.font.graphik_medium)),
                                fontSize = 8.sp
                            )
                        )
                    }
                }
                Column {
                    Text(
                        text = "Топи",
                        style = TextStyle(
                            color = Color(0xff272727),
                            fontFamily = FontFamily(Font(R.font.graphik_bold)),
                            fontSize = 16.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "2021, триллер",
                        style = TextStyle(
                            color = Color(0xff838390),
                            fontFamily = FontFamily(Font(R.font.graphik_regular)),
                            fontSize = 14.sp
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            AddToCollectionSection(
                collections = collections
            )
        }
    }
}

@Composable
fun AddToCollectionSection(
    collections: List<Collection>,
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Divider(color = Color(0x70b5b5c9), thickness = 1.5.dp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isExpanded = !isExpanded }
                .padding(vertical = 12.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add_collection),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(32.dp))
            Text(
                text = "Добавить в коллекцию",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.graphik_regular)),
                    fontSize = 18.sp,
                    color = Color(0xff272727)
                )
            )
        }
        Divider(color = Color(0x70b5b5c9), thickness = 1.5.dp)
        if (isExpanded) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                collections.forEach { collection ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
//                            .clickable {
//                                onCollectionSelected(collection)
//                                isExpanded = false
//                            }
                            .padding(start = 34.dp, top = 12.dp, bottom = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        var checked by remember { mutableStateOf(false) }
                        Checkbox(
                            checked = checked,
                            onCheckedChange = { checked = it },
                            modifier = Modifier
                                .size(24.dp)
                                .clip(RoundedCornerShape(10.dp)),
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color(0xff272727),
                                uncheckedColor = Color(0xff272727),
                                checkmarkColor = Color.White
                            ),
                        )
                        Spacer(modifier = Modifier.width(24.dp))
                        Text(
                            text = collection.name,
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.graphik_regular)),
                                fontSize = 18.sp,
                                color = Color(0xff272727)
                            )
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "${collection.count}",
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.graphik_regular)),
                                fontSize = 14.sp,
                                color = Color(0xff272727)
                            )
                        )
                    }
                    Divider(color = Color(0x70b5b5c9), thickness = 1.5.dp)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
//                        .clickable {
//                            onCreateNewCollection()
//                            isExpanded = false
//                        }
                        .padding(start = 34.dp, top = 12.dp, bottom = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        modifier = Modifier.size(24.dp),
                        onClick = {
                            //
                        }) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_plus),
                            contentDescription = "Ic_Plus",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(24.dp))
                    Text(
                        text = "Создать свою коллекцию",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.graphik_medium)),
                            fontSize = 16.sp,
                            color = Color(0xff272727)
                        )
                    )
                }
                Divider(color = Color(0x70b5b5c9), thickness = 1.5.dp)
            }
        }
    }
}

@Composable
fun CollectionsGrid(
    collections: List<Collection>,
    onSeeAllClicked: (String) -> Unit = {}
) {
    val height = ceil(collections.size * 1.0 / 2) * 158
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp)
            .height(height.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(collections) { collection ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = {
                        onSeeAllClicked(collection.name)
                    })
                    .height(150.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(BorderStroke(1.dp, Color.Gray), shape = RoundedCornerShape(10.dp)),
                shape = MaterialTheme.shapes.medium,
                colors = CardDefaults.cardColors(containerColor = Color.White),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    if (collection.name == "Любимые" || collection.name == "Хочу посмотреть") {
                        IconTextAmount(collection)
                    } else {
                        IconButton(
                            modifier = Modifier
                                .size(30.dp)
                                .align(Alignment.TopEnd)
                                .padding(top = 5.dp, end = 5.dp),
                            onClick = {
                                //
                            }
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_delete_x),
                                contentDescription = "Ic_Delete_X",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                        IconTextAmount(collection)
                    }
                }
            }
        }
    }
}

@Composable
fun IconTextAmount(
    collection: Collection
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = collection.icon),
            contentDescription = collection.name,
            colorFilter = ColorFilter.tint(Color(0xff272727))
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = collection.name,
            style = TextStyle(
                color = Color(0xff272727),
                fontFamily = FontFamily(Font(R.font.graphik_medium)),
                fontSize = 14.sp
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .height(17.dp)
                .width(30.dp)
                .background(
                    Color(0xff3d3bff),
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = collection.count.toString(),
                style = TextStyle(
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.graphik_medium)),
                    fontSize = 8.sp
                )
            )
        }
    }
}

@Composable
fun YouInterested(
    sections: List<Section>,
    onFilmClicked: (Int) -> Unit = {},
    onSeeAllClicked: (String) -> Unit = {}
) {
    RowInfo("Вам было интересно", sections.get(2).list.size.toString(), onSeeAllClicked)
    Spacer(modifier = Modifier.height(24.dp))

    LazyRow(
        modifier = Modifier.padding(start = 26.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(sections.get(2).list) { film ->
            FilmView(film = film, onFilmClicked = onFilmClicked)
        }
        item {
            Column(
                modifier = Modifier
                    .size(width = 111.dp, height = 156.dp)
                    .padding(top = 51.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(
                    modifier = Modifier
                        .size(32.dp),
                    onClick = {
                        removeSectionByName("interest")
                    }
                ) {
                    Image(
                        modifier = Modifier
                            .background(Color(0xffffffff), shape = CircleShape)
                            .padding(6.dp)
                            .size(20.dp),
                        painter = painterResource(id = R.drawable.ic_trash),
                        contentDescription = "Ic_Trash"
                    )
                }
                Text(
                    text = "Очистить",
                    modifier = Modifier.clickable { },
                    style = TextStyle(
                        color = Color(0xff272727),
                        fontFamily = FontFamily(Font(R.font.graphik_regular)),
                        fontSize = 12.sp
                    )
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(80.dp))
}

package com.ziven0069.miniproject2.screen

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ziven0069.miniproject2.model.Buku
import com.ziven0069.miniproject2.navigation.Screen
import com.ziven0069.miniproject2.ui.theme.Mobpro1Theme
import com.ziven0069.miniproject2.util.ViewModelFactory
import com.ziven0069.mobpro1.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    var showList by remember { mutableStateOf(true) }
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(onClick =  { showList = !showList }) {
                        Icon(
                            painter = painterResource(
                                if (showList) R.drawable.baseline_grid_view_24
                                else R.drawable.baseline_view_list_24
                            ),
                            contentDescription = stringResource(
                                if (showList) R.string.grid
                                else R.string.list
                            ),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }

            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.FormBaru.route)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(R.string.tambah_buku),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    ){ innerPadding ->
        ScreenContent(Modifier.padding(innerPadding), navController)
    }
}

@Composable
fun ScreenContent(modifier: Modifier, navController: NavHostController){
//    val viewModel: MainViewModel = viewModel()
    val context = LocalContext.current
//    val db = MahasiswaDb.getInstance(context)
    val factory = ViewModelFactory(context)
    val viewModel: MainViewModel = viewModel(factory = factory)
    val data by viewModel.data.collectAsState()


    if (data.isEmpty()){
        Column(
            modifier = modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.list_kosong))
        }
    }else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 84.dp)
        ) {
            items(data) {
                ListItem(buku = it){
//                    val pesan = context.getString(R.string.x_diklik,it.judul)
//                    Toast.makeText(context,pesan, Toast.LENGTH_SHORT).show()
                    navController.navigate(Screen.FormUbah.withId(it.id))
                }
                HorizontalDivider()
            }
        }
    }
}

@Composable
fun ListItem(buku: Buku, onClick:() -> Unit){
    Column(
        modifier = Modifier.fillMaxWidth().clickable { onClick() }.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = buku.judul,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = buku.isbn,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = buku.kategori
        )
    }
}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainScreenPreview() {
    Mobpro1Theme {
        MainScreen(rememberNavController())
    }
}
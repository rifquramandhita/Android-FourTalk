package id.four.fourtalk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.four.fourtalk.data.DataSource
import id.four.fourtalk.model.Topic
import id.four.fourtalk.ui.theme.FourTalkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FourTalkTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FourTalkApp(
                        DataSource().loadTopic(),
                        modifier = Modifier
                            .padding(50.dp)
                            .fillMaxSize()
                            .wrapContentSize(
                                align = Alignment.Center
                            )

                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FourTalkApp(listTopic : List<Topic> = listOf(), modifier: Modifier = Modifier){
    var result by remember {
        mutableStateOf(Topic("Silahkan Tekan Tombol Acak","Untuk mendapatkan topik yang lain"))
    }
    var count by remember {
        mutableStateOf(0)
    }
    var index by remember {
        mutableStateOf(0)
    }
    var isEnabled by remember {
        mutableStateOf(true)
    }

    if(count != 0){
        if(index != count) {
            index++
            val temp = (0..listTopic.size-1).random()
            result = listTopic[temp]
        }
        else {
            isEnabled = true
            index = 0
            count = 0
        }
    }

    Column(
        modifier = modifier
    ) {
        Card(
            modifier = Modifier
                .weight(
                    fill = true,
                    weight = 1F
                )
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(
                        align = Alignment.Center
                    ),
//                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    text = result.topic,
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(25.dp),
                    text = result.category,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )
            }

        }
        Button(
            modifier = Modifier
                .padding(
                    top = 30.dp
                )
                .fillMaxWidth(),
            onClick = {
                isEnabled = false
                count = (25..50).random()
            },
            enabled = isEnabled
        ) {
            Text(
                text = "ACAK"
            )
        }
    }
}
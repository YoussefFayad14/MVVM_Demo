import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.mvvm_demo.data.models.Movie

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieItem(movie: Movie, actionName: String, action: () -> Unit) {
    val baseImageUrl = "https://image.tmdb.org/t/p/w500"
    val posterUrl = movie.poster_path.let { baseImageUrl + it }

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row {
                GlideImage(
                    model = posterUrl,
                    contentDescription = "Movie Poster",
                    modifier = Modifier.size(100.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = movie.original_title ?: "Unknown Title")
                    Text(text = movie.release_date ?: "Unknown Date")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomEnd
            ) {
                Button(onClick = action) {
                    Text(text = actionName)
                }
            }
        }
    }
}

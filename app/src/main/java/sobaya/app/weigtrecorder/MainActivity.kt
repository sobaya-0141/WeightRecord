package sobaya.app.weigtrecorder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.internal.GeneratedComponent
import sobaya.app.main.Main
import sobaya.app.weigtrecorder.ui.theme.WeigtRecorderTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WeigtRecorderTheme {
                Main()
            }
        }
    }
}

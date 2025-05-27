package ru.tinkoff.lunch.screens.lunch_details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import dagger.hilt.android.AndroidEntryPoint
import ru.tinkoff.kotea.android.storeViaViewModel
import ru.tinkoff.lunch.screens.lunch_details.di.LunchDetailsComponent
import ru.tinkoff.lunch.R
import ru.tinkoff.lunch.screens.lunch_details.presentation.LunchDetailsNews
import ru.tinkoff.lunch.screens.lunch_details.ui.LunchEventScreen
import ru.tinkoff.lunch.utils.views.FlowActivity
import ru.tinkoff.lunch.utils.views.collectOnViewLifecycle
import ru.tinkoff.lunch.utils.views.toTelegramUri

@AndroidEntryPoint
class LunchDetailsActivity : FlowActivity<LunchDetailsComponent>(R.layout.activity_main) {

    companion object {
        fun createIntent(
            context: Context,
            lunchId: String,
        ): Intent {
            return Intent(context, LunchDetailsActivity::class.java)
                .putExtra("lunchId", lunchId)
        }
    }

    private val store by storeViaViewModel {
        component.getLunchDetailsStore(lunchId = intent.getStringExtra("lunchId")!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        store.news.collectOnViewLifecycle(this, collector = ::news)
        setContent {
            MaterialTheme {
                LunchEventScreen(
                    state = store.state,
                    onEvent = store::dispatch,
                )
            }
        }
    }

    private fun news(news: LunchDetailsNews) {
        when (news) {
            is LunchDetailsNews.OpenTelegram -> {
                val intent = Intent(Intent.ACTION_VIEW, news.telegramId.toTelegramUri())
                startActivity(intent)
            }
            else -> Unit
        }
    }
}

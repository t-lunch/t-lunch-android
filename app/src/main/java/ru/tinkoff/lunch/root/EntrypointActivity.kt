package ru.tinkoff.lunch.root

import android.content.Context
import android.content.Intent
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import ru.tinkoff.kotea.android.lifecycle.collectOnCreate
import ru.tinkoff.kotea.android.storeViaViewModel
import ru.tinkoff.lunch.R
import ru.tinkoff.lunch.navigation.Screens.MainActivityScreen
import ru.tinkoff.lunch.navigation.Screens.SignInScreen
import ru.tinkoff.lunch.root.di.EntrypointComponent
import ru.tinkoff.lunch.root.presentation.EntrypointNews
import ru.tinkoff.lunch.root.presentation.EntrypointUiEvent
import ru.tinkoff.lunch.utils.views.FlowActivity

@AndroidEntryPoint
class EntrypointActivity : FlowActivity<EntrypointComponent>(R.layout.activity_entrypoint) {

    companion object {
        fun createIntent(
            context: Context,
            isDeepLogout: Boolean = false,
        ): Intent {
            return Intent(context, EntrypointActivity::class.java)
                .putExtra("isDeepLogout", isDeepLogout)
        }
    }

    private val store by storeViaViewModel { component.getEntrypointStore() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isDeepLogout = intent.getBooleanExtra("isDeepLogout", false)
        store.collectOnCreate(
            lifecycleOwner = this,
            stateCollector = null,
            newsCollector = ::news,
        )
        store.dispatch(EntrypointUiEvent.Init(isDeepLogout = isDeepLogout))
    }

    private fun news(news: EntrypointNews) {
        when (news) {
            is EntrypointNews.OpenSignInScreen -> router.newRootChain(SignInScreen())
            is EntrypointNews.OpenMainScreen -> router.newRootChain(MainActivityScreen())
        }
    }
}

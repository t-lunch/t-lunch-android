package ru.tinkoff.lunch.root

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import ru.tinkoff.kotea.android.lifecycle.collectOnCreate
import ru.tinkoff.kotea.android.storeViaViewModel
import ru.tinkoff.lunch.R
import ru.tinkoff.lunch.navigation.Screens.MainActivityScreen
import ru.tinkoff.lunch.navigation.Screens.SignInScreen
import ru.tinkoff.lunch.root.di.EntrypointComponent
import ru.tinkoff.lunch.root.presentation.EntrypointNews
import ru.tinkoff.lunch.utils.views.FlowActivity

@AndroidEntryPoint
class EntrypointActivity : FlowActivity<EntrypointComponent>(R.layout.activity_entrypoint) {

    private val store by storeViaViewModel { component.getEntrypointStore() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        store.collectOnCreate(
            lifecycleOwner = this,
            stateCollector = null,
            newsCollector = ::news,
        )
    }

    private fun news(news: EntrypointNews) {
        when (news) {
            is EntrypointNews.OpenSignInScreen -> router.newRootChain(SignInScreen())
            is EntrypointNews.OpenMainScreen -> router.newRootChain(MainActivityScreen())
        }
    }
}

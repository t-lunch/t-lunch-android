package ru.tinkoff.lunch.root.sign_up

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.tinkoff.kotea.android.lifecycle.collectOnCreate
import ru.tinkoff.kotea.android.storeViaViewModel
import ru.tinkoff.lunch.R
import ru.tinkoff.lunch.databinding.FragmentSignUpBinding
import ru.tinkoff.lunch.navigation.Screens
import ru.tinkoff.lunch.root.sign_up.di.SignUpComponent
import ru.tinkoff.lunch.root.sign_up.presentation.SignUpNews
import ru.tinkoff.lunch.root.sign_up.presentation.SignUpUiEvent
import ru.tinkoff.lunch.utils.views.FlowFragment
import ru.tinkoff.lunch.utils.views.makeTextLink

@AndroidEntryPoint
class SignUpFragment : FlowFragment<SignUpComponent>(R.layout.fragment_sign_up) {

    private val binding by viewBinding(FragmentSignUpBinding::bind)
    private val store by storeViaViewModel { component.getSignUpStore() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        store.collectOnCreate(
            lifecycleOwner = this,
            stateCollector = null,
            newsCollector = ::news,
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        makeTextLink(
            textView = binding.signInQuestion,
            string = "Войти",
            underlined = true,
            color = Color.BLACK,
            typeface = Typeface.DEFAULT_BOLD,
            action = { store.dispatch(SignUpUiEvent.SignInClicked) },
        )
    }

    private fun news(news: SignUpNews) {
        when (news) {
            is SignUpNews.OpenSignIn -> router.replaceScreen(Screens.SignInScreen())
        }
    }
}

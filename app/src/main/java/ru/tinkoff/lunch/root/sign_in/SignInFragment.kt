package ru.tinkoff.lunch.root.sign_in

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.tinkoff.kotea.android.lifecycle.collectOnCreate
import ru.tinkoff.kotea.android.storeViaViewModel
import ru.tinkoff.lunch.root.sign_in.di.SignInComponent
import ru.tinkoff.lunch.R
import ru.tinkoff.lunch.databinding.FragmentSignInBinding
import ru.tinkoff.lunch.navigation.Screens
import ru.tinkoff.lunch.root.sign_in.presentation.SignInNews
import ru.tinkoff.lunch.root.sign_in.presentation.SignInNews.OpenMainScreen
import ru.tinkoff.lunch.root.sign_in.presentation.SignInNews.OpenSignUp
import ru.tinkoff.lunch.root.sign_in.presentation.SignInUiEvent
import ru.tinkoff.lunch.utils.views.FlowFragment
import ru.tinkoff.lunch.utils.views.makeTextLink

@AndroidEntryPoint
class SignInFragment : FlowFragment<SignInComponent>(R.layout.fragment_sign_in) {

    private val binding by viewBinding(FragmentSignInBinding::bind)
    private val store by storeViaViewModel { component.getSignInStore() }

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
            textView = binding.signUpQuestion,
            string = "Зарегистрироваться",
            underlined = true,
            color = Color.BLACK,
            typeface = Typeface.DEFAULT_BOLD,
            action = { store.dispatch(SignInUiEvent.SignUpClicked) },
        )
        binding.loginButton.setOnClickListener { store.dispatch(SignInUiEvent.LoginClicked) }
    }

    private fun news(news: SignInNews) {
        when (news) {
            is OpenSignUp -> router.replaceScreen(Screens.SignUpScreen())
            is OpenMainScreen -> router.newRootChain(Screens.MainActivityScreen())
        }
    }
}

package ru.tinkoff.lunch.root.sign_in

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.view.WindowManager
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
import ru.tinkoff.lunch.root.sign_in.presentation.SignInNews.ShowLoading
import ru.tinkoff.lunch.root.sign_in.presentation.SignInUiEvent
import ru.tinkoff.lunch.utils.views.FlowFragment
import ru.tinkoff.lunch.utils.views.makeTextLink
import ru.tinkoff.lunch.utils.views.showAlertDialog

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
        initSignInButton()
    }

    private fun initSignInButton() {
        binding.loginButton.setOnClickListener {
            var isAllFieldsCorrect = true

            if (binding.loginField.editText?.text.isNullOrEmpty()) {
                isAllFieldsCorrect = false
                binding.loginField.editText?.error = getString(R.string.login_error_message)
            }
            if (binding.passwordField.editText?.text.isNullOrEmpty()) {
                isAllFieldsCorrect = false
                binding.passwordField.editText?.error = getString(R.string.password_error_message)
            }

            if (isAllFieldsCorrect) {
                store.dispatch(
                    SignInUiEvent.LoginClicked(
                        login = binding.loginField.editText!!.text.toString(),
                        password = binding.passwordField.editText!!.text.toString(),
                    )
                )
            }
        }
    }

    private fun news(news: SignInNews) {
        if (news !is ShowLoading) hideBlockingLoading()
        when (news) {
            is OpenSignUp -> router.replaceScreen(Screens.SignUpScreen())
            is OpenMainScreen -> router.newRootChain(Screens.MainActivityScreen())
            is ShowLoading -> showBlockingLoading()
            is SignInNews.ShowError -> showAlertDialog(message = news.message)
        }
    }

    private fun showBlockingLoading() {
        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
        )
        binding.progressBarLayout.root.visibility = View.VISIBLE
    }

    private fun hideBlockingLoading() {
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        binding.progressBarLayout.root.visibility = View.GONE
    }
}

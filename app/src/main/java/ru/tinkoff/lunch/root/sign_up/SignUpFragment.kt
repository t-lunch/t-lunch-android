package ru.tinkoff.lunch.root.sign_up

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.view.WindowManager
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
import ru.tinkoff.lunch.root.sign_up.ui.mapper.SignUpUiState
import ru.tinkoff.lunch.root.sign_up.ui.recycler.SignUpViewHolderFactory
import ru.tinkoff.lunch.utils.views.FlowFragment
import ru.tinkoff.lunch.utils.views.makeTextLink
import ru.tinkoff.lunch.utils.views.showAlertDialog
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped
import ru.tinkoff.mobile.tech.ti_recycler_coroutines.TiRecyclerCoroutines

@AndroidEntryPoint
class SignUpFragment : FlowFragment<SignUpComponent>(R.layout.fragment_sign_up) {

    private val binding by viewBinding(FragmentSignUpBinding::bind)
    private val store by storeViaViewModel { component.getSignUpStore() }

    private lateinit var emojiRecycler: TiRecyclerCoroutines<ViewTyped>
    private lateinit var officeRecycler: TiRecyclerCoroutines<ViewTyped>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        store.collectOnCreate(
            fragment = this,
            uiStateMapper = component.uiStateMapper,
            stateCollector = ::render,
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

        val viewHolderFactory = SignUpViewHolderFactory(
            onEmojiClickListener = { store.dispatch(SignUpUiEvent.EmojiClicked(it)) },
            onOfficeClickListener = { store.dispatch(SignUpUiEvent.OfficeClicked(it)) },
        )
        initEmojiRecycler(viewHolderFactory)
        initOfficeRecycler(viewHolderFactory)

        initSignUpButton()
    }

    private fun initEmojiRecycler(viewHolderFactory: SignUpViewHolderFactory) {
        emojiRecycler = TiRecyclerCoroutines(
            binding.emojiRecycler,
            viewHolderFactory
        )
    }

    private fun initOfficeRecycler(viewHolderFactory: SignUpViewHolderFactory) {
        officeRecycler = TiRecyclerCoroutines(
            binding.officeRecycler,
            viewHolderFactory
        )
    }

    private fun initSignUpButton() {
        binding.signUpButton.setOnClickListener {
            var isAllDataCorrect = true

            if (binding.loginField.editText?.text.isNullOrEmpty()) {
                isAllDataCorrect = false
                binding.loginField.editText?.error = getString(R.string.login_error_message)
            }
            if (binding.passwordField.editText?.text.isNullOrEmpty()) {
                isAllDataCorrect = false
                binding.passwordField.editText?.error = getString(R.string.password_error_message)
            }
            if (binding.nameField.editText?.text.isNullOrEmpty()) {
                isAllDataCorrect = false
                binding.nameField.editText?.error = getString(R.string.name_error_message)
            }
            if (binding.surnameField.editText?.text.isNullOrEmpty()) {
                isAllDataCorrect = false
                binding.surnameField.editText?.error = getString(R.string.surname_error_message)
            }
            if (binding.telegramField.editText?.text.isNullOrEmpty()) {
                isAllDataCorrect = false
                binding.telegramField.editText?.error = getString(R.string.telegram_error_message)
            }

            if (isAllDataCorrect) {
                store.dispatch(
                    SignUpUiEvent.SignUpClicked(
                        login = binding.loginField.editText!!.text.toString(),
                        password = binding.passwordField.editText!!.text.toString(),
                        name = binding.nameField.editText!!.text.toString(),
                        surname = binding.surnameField.editText!!.text.toString(),
                        telegram = binding.telegramField.editText!!.text.toString(),
                    )
                )
            }
        }
    }

    private fun render(uiState: SignUpUiState) {
        emojiRecycler.setItems(uiState.emojiList)
        officeRecycler.setItems(uiState.officesList)
    }

    private fun news(news: SignUpNews) {
        if (news !is SignUpNews.ShowLoading) hideBlockingLoading()
        when (news) {
            is SignUpNews.OpenSignIn -> router.replaceScreen(Screens.SignInScreen())
            is SignUpNews.OpenMainScreen -> router.newRootChain(Screens.MainActivityScreen())
            is SignUpNews.ShowError -> showAlertDialog(message = news.message)
            is SignUpNews.ShowLoading -> showBlockingLoading()
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

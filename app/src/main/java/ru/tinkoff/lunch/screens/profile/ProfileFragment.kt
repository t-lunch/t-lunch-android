package ru.tinkoff.lunch.screens.profile

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.tinkoff.kotea.android.lifecycle.collectOnCreate
import ru.tinkoff.kotea.android.storeViaViewModel
import ru.tinkoff.lunch.screens.profile.di.ProfileComponent
import ru.tinkoff.lunch.R
import ru.tinkoff.lunch.databinding.FragmentProfileBinding
import ru.tinkoff.lunch.navigation.Screens
import ru.tinkoff.lunch.screens.profile.presentation.ProfileNews
import ru.tinkoff.lunch.screens.profile.presentation.ProfileUiEvent
import ru.tinkoff.lunch.utils.views.FlowFragment

@AndroidEntryPoint
class ProfileFragment : FlowFragment<ProfileComponent>(R.layout.fragment_profile) {

    private val store by storeViaViewModel { component.getProfileStore() }
    private val binding by viewBinding(FragmentProfileBinding::bind)

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
        binding.logoutButton.setOnClickListener { store.dispatch(ProfileUiEvent.LogoutClicked) }
    }

    private fun news(news: ProfileNews) {
        when (news) {
            is ProfileNews.Logout -> router.newRootChain(Screens.SignInDeepLogoutScreen())
        }
    }
}

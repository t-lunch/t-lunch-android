package ru.tinkoff.lunch.utils.views

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Router
import ru.tinkoff.lunch.utils.di.BaseComponent
import javax.inject.Inject

open class FlowFragment<Component: BaseComponent>(
    @LayoutRes contentLayoutId: Int
) : Fragment(contentLayoutId) {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var component: Component
}

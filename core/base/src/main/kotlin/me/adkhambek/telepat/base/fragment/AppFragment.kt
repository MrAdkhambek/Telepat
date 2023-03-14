package me.adkhambek.telepat.base.fragment

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.adkhambek.di.android.vm.InjectingSavedStateViewModelFactory

public abstract class AppFragment : Fragment {

    public constructor() : super()
    public constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    public abstract val viewModelFactory: InjectingSavedStateViewModelFactory

    final override fun getDefaultViewModelProviderFactory(): ViewModelProvider.Factory {
        return viewModelFactory.create(this, arguments)
    }
}

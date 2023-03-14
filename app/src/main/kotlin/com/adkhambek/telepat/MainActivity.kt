package com.adkhambek.telepat

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.adkhambek.auth.api.User
import com.adkhambek.di.ComponentHolder
import com.adkhambek.di.android.bindings
import com.adkhambek.di.android.retainComponent
import com.adkhambek.di.android.vm.InjectingSavedStateViewModelFactory
import com.adkhambek.leo.LeoAdapter
import com.adkhambek.leo.recycler.setupAdapter
import com.adkhambek.telepat.databinding.ActivityMainBinding
import com.adkhambek.telepat.databinding.ItemUserBinding
import com.adkhambek.telepat.di.component.ActivityRetainComponent
import com.adkhambek.telepat.di.component.ActivityRetainComponent.ParentBindings
import com.adkhambek.telepat.ui.UserFlowFragment
import com.adkhambek.telepat.ui.UserFlowFragment.Companion.ARG_USER
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import com.adkhambek.telepat.res.R as T

class MainActivity :
    AppCompatActivity(R.layout.activity_main),
    ComponentHolder<ActivityRetainComponent> {

    override val component: ActivityRetainComponent by retainComponent { scope, app ->
        app.bindings<ParentBindings>().activityComponentFactory().create(scope)
    }

    @Inject lateinit var savedStateViewModelFactory: InjectingSavedStateViewModelFactory
    private val viewModel: MainViewModel by viewModels(
        factoryProducer = {
            savedStateViewModelFactory.create(this)
        }
    )

    private val binding: ActivityMainBinding by viewBinding(
        ActivityMainBinding::bind,
        R.id.drawer_layout
    )

    private var adapter: LeoAdapter<User>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.appBarMain.toolbar)

        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.appBarMain.toolbar,
            T.string.navigation_drawer_open,
            T.string.navigation_drawer_close
        )

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        setupUserRecycler()
        setupObservers()
    }

    private fun setupUserRecycler() {
        val view = binding.navView.getHeaderView(0)
        val recyclerView = (view as? RecyclerView?) ?: return

        adapter = recyclerView.setupAdapter(
            getViewBinding = ItemUserBinding::inflate,
            diffUtil = UserDiff
        ) { binding: ItemUserBinding, _: Int, item: User ->
            binding.userName.text = item.name
            Glide
                .with(binding.userImage)
                .load(item.photo)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(binding.userImage)

            binding.root.setOnClickListener {
                updateUser(item)
            }
        }
    }

    private fun updateUser(user: User) {
        this.binding.drawerLayout.close()

        val fragment = UserFlowFragment()
        fragment.arguments = Bundle(1).apply {
            putParcelable(ARG_USER, user)
        }

        supportFragmentManager.commit {
            replace(R.id.screen_container, fragment)
        }
    }

    private fun setupObservers() {
        viewModel.usersFlow
            .onEach {
                adapter?.setList(it)
            }.launchIn(lifecycleScope)
    }
}

object UserDiff : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
        oldItem.name == newItem.name &&
                oldItem.photo == newItem.photo
}
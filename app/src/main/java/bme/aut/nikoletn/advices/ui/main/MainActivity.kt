package bme.aut.nikoletn.advices.ui.main

import android.os.Bundle
import android.util.Log
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import bme.aut.nikoletn.advices.R
import bme.aut.nikoletn.advices.injector
import bme.aut.nikoletn.advices.ui.randomAdvices.RandomAdviceFragment
import bme.aut.nikoletn.advices.ui.savedAdvices.SavedAdviceFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MainScreen {
    @Inject
    lateinit var mainPresenter: MainPresenter

    private var attachedFragment: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        injector.inject(this)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        nav_view.setCheckedItem(R.id.nav_random)    // setting the selected menu
    }

    override fun onStart() {
        super.onStart()
        mainPresenter.attachScreen(this)
        this.showRandomAdvices()
    }

    override fun onStop() {
        super.onStop()
        mainPresenter.detachScreen()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_random -> {
                this.showRandomAdvices()
            }
            R.id.nav_saved -> {
                this.showSavedAdvices()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun showRandomAdvices() {
        supportActionBar?.setTitle(R.string.random_advices)
        this.attachedFragment = "random"
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.dynamic_fragment_frame_layout, RandomAdviceFragment.newInstance(), "randomAdvicesList")
            .commit()
    }

    override fun showSavedAdvices() {
        supportActionBar?.setTitle(R.string.saved_advices)
        this.attachedFragment = "saved"
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.dynamic_fragment_frame_layout, SavedAdviceFragment.newInstance(), "savedAdvicesList")
            .commit()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putCharSequence("attachedFragment", this.attachedFragment)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        this.attachedFragment = savedInstanceState?.getCharSequence("attachedFragment") as String?
        when (this.attachedFragment) {
            "random" -> this.showRandomAdvices()
            "saved" -> this.showSavedAdvices()
            else -> this.showRandomAdvices()
        }
    }
}

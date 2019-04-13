package bme.aut.nikoletn.advices.ui.savedAdvices

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bme.aut.nikoletn.advices.R
import bme.aut.nikoletn.advices.injector

import bme.aut.nikoletn.advices.ui.savedAdvices.dummy.DummyContent
import kotlinx.android.synthetic.main.fragment_saved_advice_list.*
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */
class SavedAdviceFragment : Fragment(), SavedAdviceScreen {
    private var randomAdviceAdapter: SavedAdviceAdapter? = null

    @Inject
    lateinit var randomAdvicePresenter: SavedAdvicePresenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
        randomAdvicePresenter.attachScreen(this)
    }

    override fun onDetach() {
        randomAdvicePresenter.detachScreen()
        super.onDetach()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_saved_advice_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        randomAdviceAdapter = SavedAdviceAdapter(context!!, DummyContent.ITEMS)

        saved_advices.layoutManager = linearLayoutManager
        saved_advices.adapter = randomAdviceAdapter
    }

    companion object {
        fun newInstance() = SavedAdviceFragment()
    }
}

package bme.aut.nikoletn.advices.ui.savedAdvices

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bme.aut.nikoletn.advices.R
import bme.aut.nikoletn.advices.injector
import bme.aut.nikoletn.advices.ui.addAdvice.AddAdviceDialogFragment

import bme.aut.nikoletn.advices.ui.savedAdvices.dummy.DummyContent
import kotlinx.android.synthetic.main.fragment_saved_advice_list.*
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */
class SavedAdviceFragment : Fragment(), SavedAdviceScreen {
    private var savedAdviceAdapter: SavedAdviceAdapter? = null

    @Inject
    lateinit var savedAdvicePresenter: SavedAdvicePresenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
        savedAdvicePresenter.attachScreen(this)
    }

    override fun onDetach() {
        savedAdvicePresenter.detachScreen()
        super.onDetach()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_saved_advice_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        savedAdviceAdapter = SavedAdviceAdapter(context!!, DummyContent.ITEMS)

        saved_advices.layoutManager = linearLayoutManager
        saved_advices.adapter = savedAdviceAdapter

        add_fab.setOnClickListener { v ->
            AddAdviceDialogFragment().show(fragmentManager, "ADD_ADVICE")
        }
    }

    companion object {
        fun newInstance() = SavedAdviceFragment()
    }
}

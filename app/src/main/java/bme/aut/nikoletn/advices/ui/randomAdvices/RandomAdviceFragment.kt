package bme.aut.nikoletn.advices.ui.randomAdvices

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import bme.aut.nikoletn.advices.R
import bme.aut.nikoletn.advices.injector
import bme.aut.nikoletn.advices.model.Advice
import bme.aut.nikoletn.advices.ui.randomAdvices.dummy.DummyContent

import kotlinx.android.synthetic.main.fragment_advice_list.*
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */
class RandomAdviceFragment : Fragment(), RandomAdviceScreen {
    private var randomAdviceAdapter: RandomAdviceAdapter? = null

    @Inject
    lateinit var randomAdvicePresenter: RandomAdvicePresenter

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
        return inflater.inflate(R.layout.fragment_advice_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        randomAdviceAdapter = RandomAdviceAdapter(context!!, DummyContent.ITEMS)

        random_advices.layoutManager = linearLayoutManager
        random_advices.adapter = randomAdviceAdapter
    }

    override fun showAdvice(advices: List<Advice>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showNetworkError(errorMsg: String) {
        Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show()
    }

    companion object {
        fun newInstance() = RandomAdviceFragment()
    }
}

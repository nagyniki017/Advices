package bme.aut.nikoletn.advices.ui.randomAdvices


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import bme.aut.nikoletn.advices.R
import bme.aut.nikoletn.advices.injector
import bme.aut.nikoletn.advices.model.Advice
import bme.aut.nikoletn.advices.viewModel.AdvicesViewModel
import kotlinx.android.synthetic.main.fragment_advice_list.*
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */
class RandomAdviceFragment : Fragment(), RandomAdviceScreen {
    private val minElements = 10
    private val cachedSize = 5
    private var randomAdviceAdapter: RandomAdviceAdapter? = null
    private val displayedAdvices: MutableList<Advice> = mutableListOf()
    private var expectedAdvices: Int = 0
    private lateinit var advicesViewModel: AdvicesViewModel

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        advicesViewModel = ViewModelProviders.of(this.activity!!).get(AdvicesViewModel::class.java)
        displayedAdvices.addAll(advicesViewModel.getRandomAdvices().value?: listOf())
        advicesViewModel.getSavedAdvices().observe(this.activity!!, Observer<List<Advice>> { advices ->
            // triggering loading data from DB
        })
        advicesViewModel.getRandomAdvices().observe(this.activity!!, Observer<List<Advice>> { advices ->
            displayedAdvices.clear()
            displayedAdvices.addAll(advices)
            randomAdviceAdapter?.notifyDataSetChanged()
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_advice_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        randomAdviceAdapter = RandomAdviceAdapter(context!!, displayedAdvices, advicesViewModel)

        random_advices.layoutManager = linearLayoutManager
        random_advices.adapter = randomAdviceAdapter

        random_advices.addOnScrollListener(object : androidx.recyclerview.widget.RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: androidx.recyclerview.widget.RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) && expectedAdvices <= 0) {
                    progress_bar.visibility = View.VISIBLE
                    loadAdvices(cachedSize)
                }
            }
        })
        if (displayedAdvices.size < minElements) {
            loadAdvices(minElements - this.displayedAdvices.size)
        } else {
            progress_bar.visibility = View.GONE
        }
    }

    override fun showAdvice(advice: Advice?) {
        // removing advice from the list if it was already in it -> placing to the end
        advicesViewModel.addUniqueRandomAdvice(advice!!)
        expectedAdvices -= 1

        if (expectedAdvices <= 0) {
            progress_bar.visibility = View.GONE
            expectedAdvices = 0 // do not have negative value
        }
    }

    override fun showNetworkError(errorMsg: String) {
        Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show()
        expectedAdvices -= 1
    }

    private fun loadAdvices(count: Int) {
        progress_bar.visibility = View.VISIBLE
        expectedAdvices += count
        for (i in 0..(count - 1)) {
            randomAdvicePresenter.getRandomAdvice()
        }
    }

    companion object {
        fun newInstance() = RandomAdviceFragment()
    }
}

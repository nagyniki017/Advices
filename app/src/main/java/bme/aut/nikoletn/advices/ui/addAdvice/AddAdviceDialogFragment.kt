package bme.aut.nikoletn.advices.ui.addAdvice


import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import bme.aut.nikoletn.advices.R
import bme.aut.nikoletn.advices.injector
import bme.aut.nikoletn.advices.model.Advice
import kotlinx.android.synthetic.main.fragment_add_advice_dialog.*
import javax.inject.Inject


class AddAdviceDialogFragment : DialogFragment(), AddAdviceScreen {
    @Inject
    lateinit var addAdvicePresenter: AddAdvicePresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dialog.setTitle(R.string.add_advice_title)
        return inflater.inflate(R.layout.fragment_add_advice_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        save_button.setOnClickListener {
            val newAdvice = Advice()
            newAdvice.advice = new_advice.text.toString()
            newAdvice.rating = advice_rating.rating
            Log.d(this.tag, "${newAdvice.advice} ${newAdvice.rating}")
            addAdvicePresenter.addAdvice(newAdvice)
        }

        cancel_button.setOnClickListener {
            this.closeDialog()
        }

        advice_rating.setOnRatingBarChangeListener { ratingBar, rating, fromUser -> Log.d(this.tag, rating.toString()) }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
        addAdvicePresenter.attachScreen(this)
    }

    override fun onDetach() {
        addAdvicePresenter.detachScreen()
        super.onDetach()
    }

    override fun closeDialog() {
        dismiss()
    }

}

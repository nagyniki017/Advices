package bme.aut.nikoletn.advices.ui.savedAdvices

import android.app.AlertDialog
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import bme.aut.nikoletn.advices.R
import bme.aut.nikoletn.advices.model.Advice
import bme.aut.nikoletn.advices.viewModel.AdvicesViewModel
import kotlinx.android.synthetic.main.fragment_advice.view.*


class SavedAdviceAdapter(
    private val context: Context,
    private var advices: List<Advice>,
    private val advicesViewModel: AdvicesViewModel
) :
    RecyclerView.Adapter<SavedAdviceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_advice, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = advices[position]
        holder.adviceText.text = item.advice
        holder.adviceRating.rating = item.rating ?: 0F

        holder.adviceRating.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            if (fromUser) {
                if (rating > 0.4) {
                    item.rating = rating
                    advicesViewModel.updateStoredAdvice(item)
                } else {
                    confirmDeletion(item, rating)
                }
            }
        }

        with(holder.mView) {
            tag = item
        }
    }

    private fun confirmDeletion(item: Advice, rating: Float) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Confirm deletion")
        builder.setMessage("Are you sure you want to delete the advice? It won't be accessible offline anymore.")

        // Delete the advice if it was confirmed
        builder.setPositiveButton("YES") { dialog, which ->
            advicesViewModel.deleteAdvice(item)
        }

        // Set the rating without deletion and update in DB
        builder.setNegativeButton("No") { dialog, which ->
            item.rating = rating
            advicesViewModel.updateStoredAdvice(item)
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun getItemCount(): Int = advices.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val adviceText: TextView = mView.advice_text
        val adviceRating: RatingBar = mView.advice_rating

        override fun toString(): String {
            return super.toString() + " '" + adviceText.text + "'"
        }
    }
}

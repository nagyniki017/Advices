package bme.aut.nikoletn.advices.ui.randomAdvices

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import bme.aut.nikoletn.advices.R
import bme.aut.nikoletn.advices.model.Advice

import kotlinx.android.synthetic.main.fragment_advice.view.*

class RandomAdviceAdapter(private val context: Context, private var advices: List<Advice>) : RecyclerView.Adapter<RandomAdviceAdapter.ViewHolder>() {

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
            item.rating = rating
        }

        with(holder.mView) {
            tag = item
        }
    }

    override fun getItemCount(): Int = advices.size

    inner class ViewHolder(val mView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(mView) {
        val adviceText: TextView = mView.advice_text
        val adviceRating: RatingBar = mView.advice_rating

        override fun toString(): String {
            return super.toString() + " '" + adviceText.text + "'"
        }
    }
}

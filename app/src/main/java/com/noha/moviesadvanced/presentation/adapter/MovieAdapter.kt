package com.noha.moviesadvanced.presentation.adapter

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.noha.moviesadvanced.databinding.ItemMovieBinding
import com.noha.moviesadvanced.core.data.models.Movie


class MovieAdapter(
    private val list: List<Movie>,
    private val interaction: Interaction? = null
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = ItemMovieBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return MovieViewHolder(binding, interaction)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MovieViewHolder
    constructor(
        private val binding: ItemMovieBinding,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(binding.root) {


        fun bind(item: Movie) = with(itemView) {
            binding.movie = item
            binding.executePendingBindings()
            val params = binding.posterGuideline.layoutParams as ConstraintLayout.LayoutParams
            params.guidePercent = 0.6f

            //Bind Actors list and enable nested scroll
            //binding.detailsView.actorsRecyclerView.adapter = ActorsAdapter(item.actors)
            binding.detailsView.actorsRecyclerView.addOnItemTouchListener(object :
                RecyclerView.OnItemTouchListener {
                override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                    when (e.action) {
                        MotionEvent.ACTION_MOVE -> rv.parent.requestDisallowInterceptTouchEvent(true)
                    }
                    return false
                }

                override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                    //do nothing
                }

                override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
                    //do nothing
                }

            })

            //Notify the listener on movie item click
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item, binding)
            }
        }

    }

    interface Interaction {
        fun onItemSelected(position: Int, selectedMovie: Movie, binding: ItemMovieBinding)
    }
}


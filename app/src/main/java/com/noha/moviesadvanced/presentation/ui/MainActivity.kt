package com.noha.moviesadvanced.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.noha.moviesadvanced.BuildConfig
import com.noha.moviesadvanced.R
import com.noha.moviesadvanced.core.data.models.Movie
import com.noha.moviesadvanced.core.resource.PresentationResource
import com.noha.moviesadvanced.presentation.adapter.MovieAdapter
import com.noha.moviesadvanced.databinding.ActivityMainBinding
import com.noha.moviesadvanced.databinding.ItemMovieBinding
import com.noha.moviesadvanced.framework.MoviesViewModel
import com.noha.moviesadvanced.presentation.adapter.ActorsAdapter
import com.noha.moviesadvanced.presentation.util.CenterZoomLayoutManager
import com.noha.moviesadvanced.presentation.util.errorhandler.RetryHandler
import com.noha.moviesadvanced.presentation.util.errorhandler.domain.chains.DomainErrorChain
import com.noha.moviesadvanced.presentation.util.loadImage


/* ToDo: FUNCTIONS SHOULD DO ONE THING. THEY SHOULD DO IT WELL. THEY SHOULD DO IT ONLY.*/
private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), MovieAdapter.Interaction {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var layoutManager: CenterZoomLayoutManager
    private lateinit var adapter: MovieAdapter
    private var lastVisibleItemWhiteBoarder: ConstraintLayout? = null
    private var lastSelectedItemBinding: ItemMovieBinding? = null
    private lateinit var  viewMode : MoviesViewModel
    private lateinit var movies : List<Movie>
    private val domainErrorChain = DomainErrorChain.BUILDER().buildWithDefaultChainLinks()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Bind view using data binding
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewMode = ViewModelProviders.of(this).get(MoviesViewModel::class.java)

        viewMode.getMovies()
        //Setup recyclerView
        layoutManager = CenterZoomLayoutManager(this)
        mainBinding.recyclerView.layoutManager = layoutManager

        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(mainBinding.recyclerView)

        mainBinding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visiblePosition: Int = layoutManager.findFirstCompletelyVisibleItemPosition()
                if (visiblePosition > -1) {
                    val visibleView: View? = layoutManager.findViewByPosition(visiblePosition)

                    visibleView?.let {
                        onFocusedItemChange(movies[visiblePosition], it)
                    }
                }
            }
        })

        observeMovies()
    }

    private fun observeMovies() {
        viewMode.movies.observe(this, Observer { moviesResponse ->
            when (moviesResponse.status) {
                PresentationResource.Status.LOADING -> {mainBinding.progressBar.visibility = View.VISIBLE}
                PresentationResource.Status.SUCCESS -> {
                    movies = moviesResponse.data!!.results
                    bindMovieList(movies)
                    hideProgressBar()

                }
                PresentationResource.Status.API_ERROR -> {
                    Toast.makeText(this,moviesResponse.apiError!!.status_message,Toast.LENGTH_SHORT).show()
                    hideProgressBar()
                }
                PresentationResource.Status.DOMAIN_ERROR -> {
                    domainErrorChain.execute(moviesResponse.throwable,this,object :RetryHandler{
                        override fun onRetry() {
                            viewMode.getMovies()
                        }

                    })
                    hideProgressBar()
                }
            }

        })
    }

    private fun hideProgressBar(){
        mainBinding.progressBar.visibility = View.GONE
    }
    private fun bindMovieList(movies : List<Movie>) {
        adapter = MovieAdapter(movies ,this)
        mainBinding.recyclerView.adapter = adapter
    }

    override fun onItemSelected(position: Int, item:Movie, binding: ItemMovieBinding) {
        //binding.detailsView.actorsRecyclerView.adapter = ActorsAdapter(item.actors)

        viewMode.getMovieActors(item.id)
        viewMode.actors.observe(this, Observer { actorsResponse->
            run {
                when (actorsResponse.status) {
                    PresentationResource.Status.LOADING -> binding.progressBar.visibility =
                        View.VISIBLE
                    PresentationResource.Status.SUCCESS -> {
                        binding.detailsView.actorsRecyclerView.adapter =
                            ActorsAdapter(actorsResponse.data!!.actors)
                        binding.progressBar.visibility=View.GONE
                    }
                    PresentationResource.Status.DOMAIN_ERROR -> {
                        domainErrorChain.execute(
                            actorsResponse.throwable,
                            this,
                            object : RetryHandler {
                                override fun onRetry() {
                                    viewMode.getMovieActors(item.id)
                                }
                            })
                        binding.progressBar.visibility=View.GONE
                    }
                    PresentationResource.Status.API_ERROR -> {
                        Toast.makeText(
                            this,
                            actorsResponse.apiError!!.status_message,
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.progressBar.visibility=View.GONE
                    }
                }
            }
        })
        //Hide last selected item details
        lastSelectedItemBinding?.let { displayMovieDetails(it, false) }

        //Display selected item details
        if (lastSelectedItemBinding != binding)
            displayMovieDetails(binding, true)

    }

    private fun onFocusedItemChange(movie: Movie, view: View) {
        //change card boarder color
        changeTransparentOfFocusedItem(view)

        //Change background image
        changeBackgroundImage(movie)

    }

    private fun displayMovieDetails(binding: ItemMovieBinding, show: Boolean) {
        //Hide Image
        val params = binding.posterGuideline.layoutParams as ConstraintLayout.LayoutParams
        if (show) params.guidePercent = 0.0f
        else params.guidePercent = 0.6f
        binding.posterGuideline.layoutParams = params

        //Display Movie Details
        binding.isDetailsVisible = show

        //ToDo: Scale white board

        lastSelectedItemBinding = binding
    }

    private fun changeTransparentOfFocusedItem(view: View) {

        val whiteImageView = view.findViewById<ConstraintLayout>(R.id.containerLayout)
        whiteImageView.setBackgroundResource(R.drawable.round_rectangle_white)
        lastVisibleItemWhiteBoarder?.setBackgroundResource(R.drawable.round_rectangle_white_transparent)

        lastVisibleItemWhiteBoarder = whiteImageView
    }

    private fun changeBackgroundImage(movie: Movie) {
        loadImage(mainBinding.backgroundImageView, movie.poster)
    }

}
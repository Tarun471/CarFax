package com.retrofit.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.retrofit.model.Listings
import com.retrofit.view.MainActivity

import androidx.core.content.ContextCompat.startActivity

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide

import com.example.myapplication.databinding.AdapterMovieBinding
import com.retrofit.view.Detail


class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private lateinit var mcontext: Context
    var makeList = mutableListOf<Listings>()


    @SuppressLint("NotifyDataSetChanged")
    fun setMovieList(movies: List<Listings>, context: Context) {
        this.makeList = movies.toMutableList()
        mcontext=context

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterMovieBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val movie = makeList[position]

        (movie.year + "  " + movie.make+ "  " +movie.model+ "  " +movie.trim).also {
            holder.binding.name.text = it
        }
        (movie.listPrice.toString() + "  " + movie.mileage).also {
            holder.binding.milage.text = it
        }
        (movie.dealer.city ).also {
            holder.binding.location.text = it


        }
        holder.itemView.setOnClickListener{
            val intent = Intent(mcontext, Detail::class.java)
                intent.putExtra("pos",position)
// start your next activity
            mcontext. startActivity(intent)
        }
        holder.binding.call.setOnClickListener {
            /*val intent = Intent(Intent.ACTION_CALL,
                Uri.parse("tel:" + movie.dealer.phone))
            mcontext.startActivity(intent) */
        }
        Glide.with(mcontext).load( movie.images.firstPhoto.medium).into(holder.binding.image);

    }

    override fun getItemCount(): Int {
        return makeList.size
    }
}

class MainViewHolder(val binding: AdapterMovieBinding) : RecyclerView.ViewHolder(binding.root)

package com.bibliotecadebolso.app.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bibliotecadebolso.app.data.model.CreatedBook
import com.bibliotecadebolso.app.databinding.ItemBookBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class BookListAdapter(private var context: Context) : RecyclerView.Adapter<BookListAdapter.BookViewHolder>(){

    /*
        BookViewHolder it's a template of each item.
     */
    inner class BookViewHolder(val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {
    }
    /*
        DifferCallBack is a list that search the elements more faster than a default kotlin List.
        You should use only on Adapters to accelerate screen refresh.
     */
    private val differCallBack = object : DiffUtil.ItemCallback<CreatedBook>() {
        override fun areItemsTheSame(oldItem: CreatedBook, newItem: CreatedBook): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: CreatedBook, newItem: CreatedBook): Boolean {
            return oldItem.id == newItem.id
        }
    }

    val differ: AsyncListDiffer<CreatedBook> = AsyncListDiffer(this, differCallBack)

    /*
        This method belows determines the creation and content of a ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBookBinding.inflate(inflater)

        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val CreatedBook: CreatedBook = differ.currentList[position]

        holder.binding.tvTitle.text = CreatedBook.title
        holder.binding.tvAuthor.text = CreatedBook.author
        if (CreatedBook.thumbnail.isNotEmpty()) {
            Glide.with(context)
                .load(CreatedBook.thumbnail)
                .apply(RequestOptions().override(200,300))
                .into(holder.binding.ivBookDefault)
        }
    }

    override fun getItemCount() = differ.currentList.size
}
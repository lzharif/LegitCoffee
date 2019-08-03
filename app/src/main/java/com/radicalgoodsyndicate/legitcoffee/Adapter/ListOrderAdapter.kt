package com.radicalgoodsyndicate.legitcoffee.Adapter

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.radicalgoodsyndicate.legitcoffee.Model.MenuLegit
import com.radicalgoodsyndicate.legitcoffee.R

class ListOrderAdapter(private val listMenuLegits: ArrayList<MenuLegit>) : RecyclerView.Adapter<ListOrderAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun getItemCount(): Int {
        return listMenuLegits.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (menu, detail, photo, amount, price) = listMenuLegits[position]
        Glide.with(holder.itemView.context)
            .load(Uri.parse("android.resource://com.radicalgoodsyndicate.legitcoffee/drawable/$photo"))
            .apply(RequestOptions().override(55, 55))
            .into(holder.imgPhoto)
        holder.tvName.text = menu
        holder.tvAmount.text = "$amount pcs"
        holder.tvPrice.text = "Rp. ${price.toString()}"
        holder.imgPhoto.setOnClickListener { onItemClickCallback.onItemClicked(listMenuLegits[holder.adapterPosition]) }
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_transaction, viewGroup, false)
        return ListViewHolder(view)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_order_name)
        var tvAmount: TextView = itemView.findViewById(R.id.tv_order_amount)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_order_photo)
        var tvPrice: TextView = itemView.findViewById(R.id.tv_order_price)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: MenuLegit)
    }


}
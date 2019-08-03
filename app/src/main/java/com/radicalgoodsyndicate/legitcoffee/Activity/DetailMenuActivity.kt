package com.radicalgoodsyndicate.legitcoffee.Activity

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.radicalgoodsyndicate.legitcoffee.Helper.MenuData
import com.radicalgoodsyndicate.legitcoffee.Model.MenuLegit
import com.radicalgoodsyndicate.legitcoffee.R

class DetailMenuActivity : AppCompatActivity(), View.OnClickListener {

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button_order -> {
                createOrder()
            }
        }
    }

    private fun createOrder() {
        val dialog: Dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_order_one)
        dialog.setCancelable(true)

        val npAmount: NumberPicker = dialog.findViewById(R.id.numberPicker)
        val btnOrder: Button = dialog.findViewById(R.id.btn_order_one)
        npAmount.minValue = 1
        npAmount.maxValue = 10

        btnOrder.setOnClickListener {
            data!!.amount = npAmount.value
            val intent = Intent()
            intent.putExtra(MENU_NAME, data!!.menu)
            intent.putExtra(MENU_DETAIL, data!!.detail)
            intent.putExtra(MENU_PHOTO, data!!.photo)
            intent.putExtra(MENU_AMOUNT, npAmount.value)
            intent.putExtra(MENU_PRICE, data!!.price)
            setResult(Activity.RESULT_OK, intent)
            Toast.makeText(this, "Kamu memilih ${data?.amount} pcs ${data?.menu}", Toast.LENGTH_SHORT).show()
            finish()

            dialog.dismiss()
        }

        dialog.show()
    }

    private var data: MenuLegit? = MenuLegit("", "", "", 0, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_menu)

        initToolbar()

        val tvMenuTitle: TextView = findViewById(R.id.tv_menu_title)
        val tvMenuDetail: TextView = findViewById(R.id.tv_menu_detail)
        val tvMenuPrice: TextView = findViewById(R.id.tv_menu_price)
        val ivMenuPhoto: ImageView = findViewById(R.id.iv_detail)
        val btnOrder: Button = findViewById(R.id.button_order)

        val menuName = intent.getStringExtra(MENU_NAME)

        val list = MenuData.listData
        data = list.find { it.menu == menuName }

        if (data != null) {
            tvMenuTitle.text = data!!.menu
            tvMenuDetail.text = data!!.detail
            tvMenuPrice.text = "Rp. ${data!!.price.toString()}"
            Glide.with(this)
                .load(Uri.parse("android.resource://com.radicalgoodsyndicate.legitcoffee/drawable/${data!!.photo}"))
                .into(ivMenuPhoto)
        }

        btnOrder.setOnClickListener(this)
    }

    private fun initToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(null)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val collapsingToolbar = findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
        collapsingToolbar.title = "This is the title"
    }

    companion object {
        const val MENU_NAME = "menu_name"
        const val MENU_DETAIL = "menu_detail"
        const val MENU_PHOTO = "menu_photo"
        const val MENU_AMOUNT = "menu_amount"
        const val MENU_PRICE = "menu_price"
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
        }
        return true
    }
}

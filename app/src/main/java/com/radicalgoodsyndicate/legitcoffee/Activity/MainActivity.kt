package com.radicalgoodsyndicate.legitcoffee.Activity

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import com.radicalgoodsyndicate.legitcoffee.Adapter.ListMenuAdapter
import com.radicalgoodsyndicate.legitcoffee.Adapter.ListOrderAdapter
import com.radicalgoodsyndicate.legitcoffee.Helper.MenuData
import com.radicalgoodsyndicate.legitcoffee.Model.MenuLegit
import com.radicalgoodsyndicate.legitcoffee.R

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var rvMenu: RecyclerView
    private lateinit var fabOrder: FloatingActionButton
    private var listMenu: ArrayList<MenuLegit> = arrayListOf()
    private var orderedMenu: ArrayList<MenuLegit> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.tool_bar)
        rvMenu = findViewById(R.id.rv_menu)
        fabOrder = findViewById(R.id.fab_order)

        setSupportActionBar(toolbar)
        supportActionBar?.setTitle("Mau Pesan Apa Kak?")

        rvMenu.setHasFixedSize(true)
        listMenu.addAll(MenuData.listData)
        showRecyclerList()

        fabOrder.setOnClickListener(
            { showConfirmationOrder() }
        )

        if (savedInstanceState != null) {
            val result = savedInstanceState.getBundle(LIST_ORDER)
            orderedMenu = result.getSerializable(EXTRAS) as ArrayList<MenuLegit>
        }

        setDisplayFab()
    }

    private fun showConfirmationOrder() {
        var total: Int = calculateTotal()
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_confirm_order)
        dialog.setCancelable(true)

        val lp: WindowManager.LayoutParams = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.MATCH_PARENT
        val rvConfirm: RecyclerView = dialog.findViewById(R.id.rv_summary_order)
        val btnConfirm: Button = dialog.findViewById(R.id.btn_confirm_transaction)

        rvConfirm.setHasFixedSize(true)
        rvConfirm.layoutManager = LinearLayoutManager(this)
        val listOrderAdapter = ListOrderAdapter(orderedMenu)
        rvConfirm.adapter = listOrderAdapter

        listOrderAdapter.setOnItemClickCallback(object : ListOrderAdapter.OnItemClickCallback {
            override fun onItemClicked(data: MenuLegit) {
                Toast.makeText(
                    this@MainActivity,
                    "Pesanan ${data.menu} berhasil dihapus", Toast.LENGTH_SHORT
                ).show()
                orderedMenu.remove(data)
                rvConfirm.adapter?.notifyDataSetChanged()
                total = calculateTotal()
                btnConfirm.setText(getString(R.string.confirm_placeholder, total))

                // If empty, exit the dialog
                if (orderedMenu.isEmpty()) {
                    dialog.dismiss()
                    setDisplayFab()
                }
            }
        })

        btnConfirm.setText(getString(R.string.confirm_placeholder, total))
        btnConfirm.setOnClickListener({
            Toast.makeText(this, "Terima kasih sudah order. Totalnya Rp. $total", Toast.LENGTH_LONG).show()

            // Empty Ordered Menu
            orderedMenu.removeAll(orderedMenu)
            setDisplayFab()
            dialog.dismiss()
        })

        dialog.window.attributes = lp
        dialog.show()
    }

    private fun calculateTotal(): Int {
        var total: Int = 0
        for (data in orderedMenu) {
            total += data.amount * data.price
        }
        return total
    }

    private fun setDisplayFab() {
        if (orderedMenu.size == 0)
            fabOrder.hide()
        else
            fabOrder.show()
    }

    override fun onCreateOptionsMenu(menu_choice: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu_choice)
        return super.onCreateOptionsMenu(menu_choice)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                val dataOrder = MenuLegit(
                    data!!.getStringExtra(DetailMenuActivity.MENU_NAME),
                    data.getStringExtra(DetailMenuActivity.MENU_DETAIL),
                    data.getStringExtra(DetailMenuActivity.MENU_PHOTO),
                    data.getIntExtra(DetailMenuActivity.MENU_AMOUNT, 0),
                    data.getIntExtra(DetailMenuActivity.MENU_PRICE, 0)
                )
                modifyOrder(dataOrder)
            }

        }
    }

    private fun modifyOrder(dataOrder: MenuLegit) {
        if (orderedMenu.find { it.menu == dataOrder.menu } == null)
            orderedMenu.add(dataOrder)
        else {
            // Remove Previous entry
            val previous = orderedMenu.find { it.menu == dataOrder.menu }
            orderedMenu.remove(previous)

            // Add new entry
            orderedMenu.add(dataOrder)
        }

        setDisplayFab()
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_about -> {
                val aboutIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(aboutIntent)
            }
        }
    }

    private fun showRecyclerList() {
        rvMenu.layoutManager = LinearLayoutManager(this)
        val listMenuAdapter = ListMenuAdapter(listMenu)
        rvMenu.adapter = listMenuAdapter

        listMenuAdapter.setOnItemClickCallback(object : ListMenuAdapter.OnItemClickCallback {
            override fun onItemClicked(data: MenuLegit) {
                showSelectedMenu(data)
            }
        })
    }

    private fun showSelectedMenu(menuLegit: MenuLegit) {
        val menuDetailIntent = Intent(this@MainActivity, DetailMenuActivity::class.java)
        menuDetailIntent.putExtra(DetailMenuActivity.MENU_NAME, menuLegit.menu)
        startActivityForResult(menuDetailIntent, 1)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val extra = Bundle();
        extra.putSerializable(EXTRAS, orderedMenu);

        outState.putBundle(LIST_ORDER, extra)
    }

    companion object {
        const val EXTRAS = "object"
        const val LIST_ORDER = "list_order"
    }
}

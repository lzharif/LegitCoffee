package com.radicalgoodsyndicate.legitcoffee.Helper

import com.radicalgoodsyndicate.legitcoffee.Model.MenuLegit

object MenuData {
    private var data = arrayOf(
        arrayOf(
            "Espresso Legit",
            "Kamu pecinta kopi tanpa basa-basi? Kalau begitu espresso adalah pilihan buatmu. Hanya ekstrak kopi terbaik untuk harimu!",
            "espresso",
            1,
            8000
        ),
        arrayOf(
            "Double Espresso Legit",
            "Merasa hardcore dalam dunia perkopian? Butuh energi super ekstra agar melek ngerjain tugas kehidupan? Saatnya kamu coba double espresso Legit! Seperti espresso, hanya 2 kali lebih banyak",
            "espresso_double",
            1,
            10000
        ),
        arrayOf(
            "Super Espresso Legit",
            "Butuh melek sepanjang malam? Gak mempan minum double espresso? Saatnya kamu minum super espresso Legit! Hanya untuk orang yang kuat jantung.",
            "super_espresso",
            1,
            12000
        ),
        arrayOf(
            "Americano Legit",
            "Suka kopi pahit namun pingin yang banyak? Americano legit cocok untukmu! Mild dan breeze",
            "americano",
            1,
            10000
        ),
        arrayOf(
            "Cappuccino Legit",
            "Khusus buat kamu yang suka ada rasa mild dan santai. Seperti kopi sachet, tapi lebih bersahaja.",
            "cappuccino",
            1,
            11000
        ),
        arrayOf(
            "Mochaccino Legit",
            "Suka kopi dan coklat? Kenapa gak digabung jadi satu? Nikmati kelegitan perpaduan Mochaccino kami!",
            "mochaccino",
            1,
            11000
        ),
        arrayOf(
            "Affogato Legit",
            "Dengan perpaduan kopi dan es krim, Affogato Legit akan memuaskan dahagamu dan hidupmu.",
            "affogato",
            1,
            14000
        ),
        arrayOf(
            "Latte Macchiato Legit",
            "Hanya espresso dikasih susu foamy yang yummy. Cocok untuk harimu yang cerah dan butuh kesegaran hidup.",
            "latte_machiato",
            1,
            10000
        ),
        arrayOf(
            "Vietnam Drip Legit",
            "Suka kopi susu yang biasa kamu seduh sendiri? Vietnam Drip Legit adalah versi upgradenya! Cobain dan rasakan kenikmatan tiada tara.",
            "vietnam_drip",
            1,
            10000
        ),
        arrayOf(
            "Cold Brew Legit",
            "Kamu gemar yang dingin-dingin? Jika iya, cold brew Legit akan memberimu sensasi kopi klasik dingin dengan cita rasa yang khas",
            "cold_brew",
            1,
            9000
        )
    )

    val listData: ArrayList<MenuLegit>
        get() {
            val list = ArrayList<MenuLegit>()
            for (aData in data) {
                val menu = MenuLegit()
                menu.menu = aData[0] as String
                menu.detail = aData[1] as String
                menu.photo = aData[2] as String
                menu.amount = aData[3] as Int
                menu.price = aData[4] as Int

                list.add(menu)
            }
            return list
        }
}
package com.radicalgoodsyndicate.legitcoffee.Model

import android.os.Parcel
import android.os.Parcelable

data class MenuLegit    (
    var menu: String = "",
    var detail: String = "",
    var photo: String = "",
    var amount: Int = 0,
    var price: Int = 0
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(menu)
        parcel.writeString(detail)
        parcel.writeString(photo)
        parcel.writeInt(amount)
        parcel.writeInt(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MenuLegit> {
        override fun createFromParcel(parcel: Parcel): MenuLegit {
            return MenuLegit(parcel)
        }

        override fun newArray(size: Int): Array<MenuLegit?> {
            return arrayOfNulls(size)
        }
    }
}


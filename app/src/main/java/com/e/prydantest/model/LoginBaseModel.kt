package com.e.prydantest.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginBaseModel : Parcelable {
    @SerializedName("errorCode")
    @Expose
    var errorCode: String? = null

    @SerializedName("errorMessage")
    @Expose
    var errorMessage: String? = null

    @SerializedName("user")
    @Expose
    var user: LoginModel? = null


    override fun toString(): String {
        return "LoginBaseModel{" +
                "errorCode = '" + errorCode + '\'' +
                ",errorMessage = '" + errorMessage + '\'' +
                ",userLoginModel = '" + user + '\'' +
                "}"
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(errorCode)
        dest.writeString(errorMessage)
    }

    constructor() {}
    protected constructor(`in`: Parcel) {
        errorCode = `in`.readString()
        errorMessage = `in`.readString()
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<LoginBaseModel?> = object : Parcelable.Creator<LoginBaseModel?> {
            override fun createFromParcel(source: Parcel): LoginBaseModel {
                return LoginBaseModel(source)
            }

            override fun newArray(size: Int): Array<LoginBaseModel?> {
                return arrayOfNulls(size)
            }
        }
    }
}
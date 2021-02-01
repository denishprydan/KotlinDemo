package com.e.prydantest.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.e.prydantest.database.MyDatabase
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = MyDatabase.TABLE_NAME_LOGIN)
 open class LoginModel : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id = 0


    @SerializedName("userId")
    @Expose
    var userId: String? = null

    @SerializedName("userName")
    @Expose
    var userName: String? = null

    override fun toString(): String {
        return "LoginModel{" +
                "userId = '" + userId + '\'' +
                ",userName = '" + userName + '\'' +
                "}"
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(userId)
        dest.writeString(userName)
    }

    constructor() {}
    protected constructor(`in`: Parcel) {
        userId = `in`.readString()
        userName = `in`.readString()
       }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<LoginModel?> = object : Parcelable.Creator<LoginModel?> {
            override fun createFromParcel(source: Parcel): LoginModel? {
                return LoginModel(source)
            }

            override fun newArray(size: Int): Array<LoginModel?> {
                return arrayOfNulls(size)
            }
        }
    }
}
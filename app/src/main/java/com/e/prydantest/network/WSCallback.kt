package com.e.prydantest.network

import com.e.prydantest.model.LoginBaseModel
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Web service method interface. This interface is used to defined and call APIs using Retrofit.
 */
interface WSCallback {


    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field(WSConstants.WS_PARAM_USERNAME) username: String,
        @Field(WSConstants.WS_PARAM_PASSWORD) password: String,
    ): Observable<LoginBaseModel>
}
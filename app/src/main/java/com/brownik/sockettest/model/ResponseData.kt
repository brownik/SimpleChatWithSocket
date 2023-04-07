package com.brownik.sockettest.model

import com.google.gson.annotations.SerializedName


/** 유저 인증 결과 **/
data class ResponseAuth(
    @SerializedName("cmd") val cmd: String = "",
    @SerializedName("errInfo") val errInfo: ErrorInfo,
    @SerializedName("data") val data: AuthData,
)

data class AuthData(
    @SerializedName("memNo") val memNo: Int = 0,
    @SerializedName("result") val result: Int = 0,
)

/** 1:1 채팅 결과 **/
data class ResponseOneToOne(
    @SerializedName("cmd") val cmd: String = "",
    @SerializedName("errInfo") val errInfo: ErrorInfo,
    @SerializedName("data") val data: OneToOneData,
)

data class OneToOneData(
    @SerializedName("chatType") val chatType: Int = 0,
    @SerializedName("msg") val msg: String = "",
    @SerializedName("replyMsgNo") val replyMsgNo: Int = 0,
    @SerializedName("msgNo") val msgNo: Long = 0,
    @SerializedName("fromMemNo") val fromMemNo: Int = 0,
    @SerializedName("toMemNo") val toMemNo: Int = 0,
)

/** 다중 채팅 참여 요청 결과 **/
data class ResponsePartyJoin(
    @SerializedName("cmd") val cmd: String = "",
    @SerializedName("errInfo") val errInfo: ErrorInfo,
    @SerializedName("data") val data: PartyJoinData,
)

data class PartyJoinData(
    @SerializedName("msgNo") val msgNo: Long = 0,
    @SerializedName("partyNo") val partyNo: Int = 0,
    @SerializedName("isAutoJoin") val isAutoJoin: Boolean = false,
    @SerializedName("isAccept") val isAccept: Boolean = false,
    @SerializedName("denyReason") val denyReason: Int = 0,
    @SerializedName("fromMemNo") val fromMemNo: Int = 0,
    @SerializedName("toMemNo") val toMemNo: Int = 0,
)


data class ErrorInfo(
    @SerializedName("errNo") val errNo: Int = 0,
    @SerializedName("errMsg") val errMsg: String = "",
)
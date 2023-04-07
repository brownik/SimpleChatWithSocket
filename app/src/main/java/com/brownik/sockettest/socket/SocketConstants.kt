package com.brownik.sockettest.socket

object SocketConstants {

    const val SOCKET_PROTOCOL = "SSL"

    const val CHAT_TYPE_LOBBY = "Lobby"
    const val CHAT_TYPE_PARTY = "Party"

    const val RQ_USER_AUTH = "RqAuthLobby"                 // 유저 인증
    const val RQ_KICK_OUT_USER = "RqKickoutUser"           // 유저 강퇴
    const val RQ_JOIN_PARTY = "RqRequestJoinParty"         // 다중 채팅 참여
    const val RQ_LEAVE_PARTY = "RqLeaveParty"              // 다중 채팅 떠나기
    const val RQ_SEND_MESSAGE_ONE = "Rq1On1Chat"           // 1:1 채팅 메세지
    const val RQ_SEND_MESSAGE_PARTY = "RqPartyChat"        // 다중 채팅 메세지

    const val RE_USER_AUTH = "ReAuthLobby"                 // 유저 인증
    const val RE_KICK_OUT_USER = "ReKickoutUser"           // 유저 강퇴
    const val RE_JOIN_PARTY = "ReRequestJoinParty"         // 다중 채팅 참여
    const val RE_LEAVE_PARTY = "ReLeaveParty"              // 다중 채팅 떠나기
    const val RE_SEND_MESSAGE_ONE = "Re1On1Chat"           // 1:1 채팅 메세지
    const val RE_SEND_MESSAGE_PARTY = "RePartyChat"        // 다중 채팅 메세지

    const val COMMON_CMD = "cmd"
    const val COMMON_DATA = "data"
    const val MEM_NO = "memNo"
    const val CHAT_TYPE = "chatType"
    const val MESSAGE = "msg"
    const val REPLY_MESSAGE_NO = "replyMsgNo"
    const val FROM_MEM_NO = "fromMemNo"
    const val TO_MEM_NO = "toMemNo"
}
package nash.ir.chataja.model

import java.util.*

data class Message(
    var id:String?=null,
    val senderEmail:String,
    val senderName:String,
    val senderAvatar:String,
    var sendAt:Date?=null,
    val content:String
)
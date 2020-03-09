package nash.ir.chataja.model

import java.util.*

data class Message(
    val senderEmail:String,
    val senderName:String,
    val senderAvatar:String,
    val sendAt: Date,
    val content:String
)
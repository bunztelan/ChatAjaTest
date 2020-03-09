package nash.ir.chataja.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_chat.view.*
import nash.ir.chataja.R
import nash.ir.chataja.model.Message
import java.util.*
import kotlin.collections.ArrayList
import java.text.SimpleDateFormat


class ChatListAdapter(private val messages: ArrayList<Message>) :

    RecyclerView.Adapter<ChatListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindMessage(messages[position])
    }

    override fun getItemCount() = messages.size

    fun addMessage(message: Message){
        Log.d("add","message content : ${message.content}")
        messages.add(message)
        notifyDataSetChanged()
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindMessage(message: Message) {
            itemView.tvChatContent.text = message.content
            itemView.tvSenderName.text = message.senderName
            itemView.tvSendAt.text = formatDate(message.sendAt?:Date())
        }

        private fun formatDate(sendAt: Date):String{
            val dfInput = SimpleDateFormat("dd-MM-yyy (hh:mm:ss)", Locale.getDefault())
            return dfInput.format(sendAt)
        }
    }
}
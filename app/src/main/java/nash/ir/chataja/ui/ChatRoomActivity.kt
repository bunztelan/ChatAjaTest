package nash.ir.chataja.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_chat_room.*
import nash.ir.chataja.R
import nash.ir.chataja.presenter.ChatRoomPresenter
import nash.ir.chataja.ui.adapter.ChatListAdapter
import java.util.*


class ChatRoomActivity : AppCompatActivity() {
    private lateinit var adapter:ChatListAdapter
    private lateinit var presenter: ChatRoomPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)
        presenter= ChatRoomPresenter(this,this.currentFocus)
        setupAdapter()
        setupSendButton()
        presenter.startRealtimeUpdate(adapter)
    }

    private fun setupSendButton(){
        btnSend.setOnClickListener {
            val content = etMessage.text.toString()
            if (content.isNotEmpty()) {
                sendData(content)
                etMessage.setText("")
            } else {
                Toast.makeText(this, "Please enter a message", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun sendData(content:String){
        presenter.sendMessage(content)
    }

    private fun setupAdapter(){
        adapter = ChatListAdapter(ArrayList())
        val manager = LinearLayoutManager(this)
        manager.stackFromEnd = true
        manager.reverseLayout = true
        rvChatList.layoutManager = manager
        rvChatList.adapter=adapter
    }

}

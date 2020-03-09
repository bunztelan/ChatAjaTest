package nash.ir.chataja.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_chat_room.*
import nash.ir.chataja.R
import nash.ir.chataja.presenter.ChatRoomPresenter
import nash.ir.chataja.ui.adapter.ChatListAdapter
import nash.ir.chataja.util.loadAvatar
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.chat_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.opponentProfile -> {
            showOpponentInfo()
            true
        }
            R.id.logout -> {
            presenter.logout()
            true
        }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showOpponentInfo() {
        val opponent = presenter.getOpponentData()
        val dialog = MaterialDialog(this)
            .customView(R.layout.dialog_opponent_profile, scrollable = true)

        val customView = dialog.getCustomView()
        val tvEmail = customView.findViewById<TextView>(R.id.tvEmail)
        val tvName = customView.findViewById<TextView>(R.id.tvName)
        val ivEmail = customView.findViewById<ImageView>(R.id.ivAvatar)

        tvEmail.text=opponent.email
        tvName.text=opponent.name
        ivEmail.loadAvatar(this,opponent.avatar)
        dialog.show()
    }
}

package nash.ir.chataja.presenter

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_chat_room.view.*
import nash.ir.chataja.model.Message
import nash.ir.chataja.model.User
import nash.ir.chataja.ui.adapter.ChatListAdapter
import nash.ir.chataja.util.KEY_ACTIVE_USER
import java.util.*

class ChatRoomPresenter(private val context: Context, private val view:View?){

    private val db = FirebaseFirestore.getInstance()
    private val activeUser = Hawk.get<User>(KEY_ACTIVE_USER)

    fun sendMessage(content:String){
        val message = hashMapOf(
            "senderName" to activeUser.name,
            "senderEmail" to activeUser.email,
            "senderAvatar" to activeUser.avatar,
            "content" to content,
            "sendAt" to Timestamp(Date())
        )

        db.collection("message")
            .add(message)
            .addOnSuccessListener {
                Toast.makeText(context,"Message sent", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(context,"Failed to send message", Toast.LENGTH_SHORT).show()
            }
    }

    fun startRealtimeUpdate(adapter:ChatListAdapter){
        db.collection("message")
            .addSnapshotListener { querySnapshot, _ ->
                for (dc in querySnapshot!!.documentChanges) {
                    when (dc.type) {
                        DocumentChange.Type.ADDED -> {
                            adapter.addMessage(
                                Message(
                                    dc.document.id,
                                    dc.document.getString("senderEmail") ?: "",
                                    dc.document.getString("senderName") ?: "",
                                    dc.document.getString("senderAvatar") ?: "",
                                    dc.document.getTimestamp("sendAt")?.toDate(),
                                    dc.document.getString("content") ?: ""
                                )
                            )
                        }
                        else->{

                        }
                    }

                }
            }
    }
}
package nash.ir.chataja

import android.app.Application
import com.orhanobut.hawk.Hawk
import nash.ir.chataja.model.User
import nash.ir.chataja.util.KEY_USER_LIST

class ChatAjaApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build()
        val userList= ArrayList<User>()
        val user1= User(
            "Jarjit Singh",
            "https://api.adorable.io/avatars/160/jarjit@mail.com.png ",
            "jarjit@mail.com",
            "123456"
        )

        val user2= User(
            "Ismail bin Mail",
            "https://api.adorable.io/avatars/160/ismail@mail.com.png",
            "ismail@mail.com",
            "123456"
        )

        userList.add(user1)
        userList.add(user2)
        Hawk.put(KEY_USER_LIST,userList)
    }
}
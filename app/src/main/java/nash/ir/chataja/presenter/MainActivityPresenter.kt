package nash.ir.chataja.presenter

import android.content.Context
import android.view.View
import android.widget.Toast
import com.orhanobut.hawk.Hawk
import nash.ir.chataja.model.User
import nash.ir.chataja.util.KEY_ACTIVE_USER
import nash.ir.chataja.util.KEY_USER_LIST

class MainActivityPresenter(private val context: Context){

    private val userList:ArrayList<User> = Hawk.get(KEY_USER_LIST)


    fun saveCurrentUser(email: String){
        val idx = userList.indexOfFirst { it.email==email }
        if(idx!=-1){
            Hawk.put(KEY_ACTIVE_USER,userList[idx])
        }
    }

    fun validate(email:String,password:String):Boolean{
        var valid=true
        if(email.isEmpty() || password.isEmpty()){
            valid=false
            Toast.makeText(context,"Email and password cannot empty", Toast.LENGTH_SHORT).show()
        }else if(!checkUser(email,password)){
            valid=false
        }
        return valid
    }

    private fun checkUser(email:String,password:String):Boolean{
        var exist=true

        val idx = userList.indexOfFirst { it.email==email }
        if(idx==-1){
            exist=false
            Toast.makeText(context,"User not found",Toast.LENGTH_SHORT).show()
        }else{
            if(password!=userList[idx].password){
                exist=false
                Toast.makeText(context,"Wrong Password",Toast.LENGTH_SHORT).show()
            }
        }

        return exist
    }
}
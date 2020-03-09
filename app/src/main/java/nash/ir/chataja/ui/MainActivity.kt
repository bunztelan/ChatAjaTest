package nash.ir.chataja.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_main.*
import nash.ir.chataja.R
import nash.ir.chataja.model.User
import nash.ir.chataja.util.KEY_USER_LIST

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupButtonAction()
    }

    private fun setupButtonAction(){
        btnSignIn.setOnClickListener {
            if(validate(etEmail.text.toString(),etPassword.text.toString())){
                startActivity(Intent(this,ChatRoomActivity::class.java))
            }
        }
    }

    private fun validate(email:String,password:String):Boolean{
        var valid=true
        if(email.isEmpty() || password.isEmpty()){
            valid=false
            Toast.makeText(this@MainActivity,"Email and password cannot empty",Toast.LENGTH_SHORT).show()
        }else if(!checkUser(email,password)){
            valid=false
        }
        return valid
    }

    private fun checkUser(email:String,password:String):Boolean{
        var exist=true
        val userList:ArrayList<User> = Hawk.get(KEY_USER_LIST)
        val idx = userList.indexOfFirst { it.email==email }
        if(idx==-1){
            exist=false
            Toast.makeText(this@MainActivity,"User not found",Toast.LENGTH_SHORT).show()
        }else{
            if(password!=userList[idx].password){
                exist=false
                Toast.makeText(this@MainActivity,"Wrong Password",Toast.LENGTH_SHORT).show()
            }
        }

        return exist
    }
}

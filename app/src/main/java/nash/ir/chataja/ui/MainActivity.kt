package nash.ir.chataja.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_main.*
import nash.ir.chataja.R
import nash.ir.chataja.model.User
import nash.ir.chataja.presenter.MainActivityPresenter
import nash.ir.chataja.util.KEY_ACTIVE_USER
import nash.ir.chataja.util.KEY_USER_LIST

class MainActivity : AppCompatActivity() {

    private lateinit var presenter:MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainActivityPresenter(this)
        setupButtonAction()
    }

    private fun setupButtonAction(){
        btnSignIn.setOnClickListener {
            if(presenter.validate(etEmail.text.toString(),etPassword.text.toString())){
                presenter.saveCurrentUser(etEmail.text.toString())
                startActivity(Intent(this,ChatRoomActivity::class.java))
            }
        }
    }
}

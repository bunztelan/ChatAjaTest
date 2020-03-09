package nash.ir.chataja.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadAvatar(context: Context, avatar:String){
    Glide.with(this)
        .load(avatar)
        .into(this)
}

package com.example.shopitee.activities

import android.os.Bundle
import android.text.Editable
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.example.shopitee.R
import com.google.android.material.navigation.NavigationView
import com.pixplicity.easyprefs.library.Prefs

import kotlinx.android.synthetic.main.activity_account.*

class AccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        setSupportActionBar(toolbar)

        val addressEditView:AppCompatEditText = findViewById<View>(R.id.address_edit) as AppCompatEditText
        val phoneEditView:AppCompatEditText = findViewById<View>(R.id.phone_edit) as AppCompatEditText
        val buttonView:AppCompatButton = findViewById<View>(R.id.button) as AppCompatButton
        addressEditView.setText( Prefs.getString("userAddress", "Apo, Abuja"))
        buttonView.setOnClickListener(View.OnClickListener {
            Prefs.putString("userAddress", addressEditView.text.toString())
            Prefs.putString("userPhone", phoneEditView.text.toString())
            finish()
        })
    }

}

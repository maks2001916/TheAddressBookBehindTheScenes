package com.example.theaddressbookbehindthescenes

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

class PersonInfoActivity : AppCompatActivity() {

    private lateinit var nameTV: TextView
    private lateinit var surnameTV: TextView
    private lateinit var addressTV: TextView
    private lateinit var telephoneTV: TextView
    private lateinit var goHomeBTN: Button

    var person: Person? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_person_info)

        nameTV = findViewById(R.id.nameTV)
        surnameTV = findViewById(R.id.surnameTV)
        addressTV = findViewById(R.id.addressTV)
        telephoneTV = findViewById(R.id.telephoneTV)
        goHomeBTN = findViewById(R.id.go_homeBTN)
        goHomeBTN.setOnClickListener {finish()}

        person = intent.extras?.getParcelable(Person::class.java.simpleName) as Person?

        nameTV.text = "${getString(R.string.name)} ${person?.name.toString()}"
        surnameTV.text = "${getString(R.string.surname)} ${person?.surname.toString()}"
        addressTV.text = "${getString(R.string.address)} ${person?.address.toString()}"
        telephoneTV.text = "${getString(R.string.telephone)} ${person?.telephone.toString()}"

    }

    private inline fun <reified T : Serializable> Bundle.customGetSerializable(key: String): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getSerializable(key, T::class.java)
        } else {
            getSerializable(key) as? T
        }
    }
}
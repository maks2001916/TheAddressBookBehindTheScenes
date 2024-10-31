package com.example.theaddressbookbehindthescenes

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private lateinit var toolbarTB: Toolbar
    private lateinit var nameET: EditText
    private lateinit var surnameET: EditText
    private lateinit var addressET: EditText
    private lateinit var telephoneET: EditText
    private lateinit var saveBTN: Button
    private lateinit var personsLV: ListView

    private val personDirectory: MutableList<Person> = mutableListOf()
    private var adapter: ArrayAdapter<Person>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        toolbarTB = findViewById(R.id.mainToolbarTB)
        nameET = findViewById(R.id.nameET)
        surnameET = findViewById(R.id.surnameET)
        addressET = findViewById(R.id.addressET)
        telephoneET = findViewById((R.id.telephoneET))
        saveBTN = findViewById(R.id.saveBTN)
        personsLV = findViewById(R.id.data_personsSV)

        setSupportActionBar(toolbarTB)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, personDirectory)
        personsLV.adapter = adapter

        saveBTN.setOnClickListener {
            if (!nameET.text.isEmpty() ||
                !surnameET.text.isEmpty() ||
                !addressET.text.isEmpty() ||
                !telephoneET.text.isEmpty()) {

                personDirectory.add(Person(
                    nameET.text.toString(),
                    surnameET.text.toString(),
                    addressET.text.toString(),
                    telephoneET.text.toString()
                ))
                adapter?.notifyDataSetChanged()
                nameET.text.clear()
                surnameET.text.clear()
                addressET.text.clear()
                telephoneET.text.clear()

            } else {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.fill_in_all_the_fields),
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        personsLV.onItemClickListener =
            AdapterView.OnItemClickListener {parent, v, position, id ->
                val person = adapter!!.getItem(position)
                val intent = Intent(this, PersonInfoActivity::class.java)
                intent.putExtra(Person::class.java.simpleName, person)
                startActivity(intent)
            }
    }
}
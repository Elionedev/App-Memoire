package com.example.login

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class EtudiantActivity : AppCompatActivity() {
    private lateinit var editText: EditText
    private lateinit var button: Button
    private lateinit var calendar: Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_etudiant)

        // Initialisation de l'AutoCompleteTextView
        val autoCompleteTextView: AutoCompleteTextView = findViewById(R.id.autoCompleteTextView)
        val items = resources.getStringArray(R.array.dropdown_items)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        autoCompleteTextView.setAdapter(adapter)

        // Initialisation du calendrier pour récupérer la date actuelle
        calendar = Calendar.getInstance()

        // Initialisation des vues pour le texte et le bouton
        editText = findViewById(R.id.date_picker_actions)
        button = findViewById(R.id.button)

        // Action sur le bouton : afficher un toast avec la date de naissance
        button.setOnClickListener {
            val anniversaire = editText.text.toString()
            if (anniversaire.isNotEmpty()) {
                Toast.makeText(this, "Ton anniversaire est $anniversaire", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Veuillez entrer votre anniversaire", Toast.LENGTH_SHORT).show()
            }
        }

        // Implémentation du DatePicker pour choisir une date
        editText.setOnClickListener {
            // Création du DatePickerDialog
            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    // Mettez à jour le EditText avec la date choisie
                    val date = "$dayOfMonth/${monthOfYear + 1}/$year" // Le mois commence à 0
                    editText.setText(date)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            // Afficher le DatePickerDialog
            datePickerDialog.show()
        }
    }
}

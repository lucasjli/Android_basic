package com.example.myapplication;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ArrayList<Contact> contacts = new ArrayList<Contact>();
    private ArrayAdapter<Contact> adapter; private ListView contactListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Setup Adapter
        contactListView = (ListView) findViewById(R.id.contactsListView);
        adapter = new
                ArrayAdapter<Contact>(this,
                android.R.layout.simple_list_item_1, contacts);
        contactListView.setAdapter(adapter);
        contactListView.setOnItemClickListener(this);
        // Add some Contacts
        if (savedInstanceState != null) {
            for (Parcelable contact : savedInstanceState.getParcelableArrayList("contacts")) {
                contacts.add((Contact) contact);
            }
        } else {
            contacts.add(new Contact("Joe Bloggs", "joe@bloggs.co.nz", "021123456"));
            contacts.add(new Contact("Jane Doe", "jane@doe.co.nz", "022123456"));
        }
    }

    public void saveContact(View view) {
        EditText nameField = findViewById(R.id.name);
        EditText emailField = findViewById(R.id.email);
        EditText mobileField = findViewById(R.id.mobile);

        String name = nameField.getText().toString().trim();
        String email = emailField.getText().toString().trim();
        String mobile = mobileField.getText().toString().trim();

        if (name.isEmpty()) {
            Toast.makeText(this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Contact newContact = new Contact(name, email, mobile);

        int index = contacts.indexOf(newContact);
        if (index >= 0) {
            // Update existing contact
            Contact existing = contacts.get(index);
            existing.setEmail(email);
            existing.setPhone(mobile);
            Toast.makeText(this, "Updated contact: " + name, Toast.LENGTH_SHORT).show();
        } else {
            // Add new contact
            contacts.add(newContact);
            Toast.makeText(this, "Added new contact: " + name, Toast.LENGTH_SHORT).show();
        }

        adapter.notifyDataSetChanged(); // Refresh List
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Contact contact = (Contact) parent.getAdapter().getItem(position);

        // Get the input box
        EditText nameField = findViewById(R.id.name);
        EditText emailField = findViewById(R.id.email);
        EditText mobileField = findViewById(R.id.mobile);

        // Fill in the input box
        nameField.setText(contact.getName());
        emailField.setText(contact.getEmail());
        mobileField.setText(contact.getMobile());

    }

    @Override
    public void onSaveInstanceState(Bundle savedState) {
        savedState.putParcelableArrayList("contacts", contacts);
        super.onSaveInstanceState(savedState);
    }
}
package org.ragecastle.cli4p;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jahall on 6/8/16.
 *
 * This Fragment will display a list of contacts
 *
 */
public class MainFragment extends Fragment {

    private ListView listViewContacts;
    private ContactsAdapter contactsAdapter;

    public MainFragment(){ }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        // Create the list view
        listViewContacts = (ListView) rootView.findViewById(R.id.listview_contacts);

        // TODO: Populate listView with recent contacts

        fillList();

        Button send = (Button) rootView.findViewById(R.id.submit_button);
        send.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                // TODO: Search through contacts

            }
        });

        return rootView;
    }

    private void fillList() {
        // TOOD: replace with db lookup sorted by most recent contact

        Cursor cursor;
        String [] tempContacts;

        String [] projection = {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY};
        // Default to CONTENT_URI
        Uri contactsUri = ContactsContract.Contacts.CONTENT_URI;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Use the preferred CONTENT_FREQUENT_URI if you can
            contactsUri = ContactsContract.Contacts.CONTENT_FREQUENT_URI;
        }

        cursor = getActivity().getContentResolver().query(
                contactsUri,
                projection,
                null,
                null,
                null);
        assert cursor != null;
        tempContacts = new String[cursor.getCount()];
        if(cursor.moveToFirst()){
            int i = 0;
            do {
                tempContacts[i] =
                        cursor.getString(
                                cursor.getColumnIndex(
                                        ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));
                i++;
            } while (cursor.moveToNext());
        }
        cursor.close();

        // Set the adapter to handle listing contacts
        contactsAdapter = new ContactsAdapter(getActivity(), Arrays.asList(tempContacts));
        // Set the adapter to the list view
        listViewContacts.setAdapter(contactsAdapter);
        // Set an onCLickListener for contacts
        listViewContacts.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position,
                                    long id)
            {
                String contactId = contactsAdapter.getItem(position);
                ((Callback) getActivity()).onItemSelected(contactId);
            }
        });
    }

    public interface Callback {
        void onItemSelected(String contactId);
    }
}

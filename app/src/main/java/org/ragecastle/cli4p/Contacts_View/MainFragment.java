package org.ragecastle.cli4p.Contacts_View;

import android.app.Fragment;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.ragecastle.cli4p.R;

/**
 * Created by jahall on 6/8/16.
 *
 * This Fragment will display a list of contacts
 *
 */
public class MainFragment extends Fragment
        implements android.app.LoaderManager.LoaderCallbacks<Cursor> {

    private static final String LOG_TAG = MainFragment.class.getSimpleName();
    private static final int LOADER_ID = 0;
    private ListView listViewContacts;
    private ContactsAdapter contactsAdapter;

    public MainFragment(){ }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(LOADER_ID, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        getLoaderManager().restartLoader(LOADER_ID, null, this);
        super.onStart();
    }

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
        contactsAdapter = new ContactsAdapter(getActivity(), null, 0);
        listViewContacts.setAdapter(contactsAdapter);
        // Set an onCLickListener for contacts
        listViewContacts.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position,
                                    long id) {
                Cursor cursor = (Cursor) parent.getItemAtPosition(position);
                String contact;
                if (cursor != null) {
                    // get the movie id to pass to the next screen
                    contact = cursor.getString(
                            cursor.getColumnIndex(
                                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));

                    ((Callback) getActivity()).onItemSelected(contact);
                }
            }
        });

        Button send = (Button) rootView.findViewById(R.id.submit_button);
        send.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                // TODO: Search through contacts
                Toast.makeText(
                        getActivity(),
                        "Search feature coming soon",
                        Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }

    @Override
    public android.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String [] projection = {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY};

        // Default to CONTENT_URI
        Uri contactsUri = ContactsContract.Contacts.CONTENT_URI;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Use the preferred CONTENT_FREQUENT_URI if you can
            contactsUri = ContactsContract.Contacts.CONTENT_FREQUENT_URI;
        }

        return new android.content.CursorLoader(getActivity(),
                contactsUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(android.content.Loader<Cursor> loader, Cursor data) {
        if (contactsAdapter != null && data != null) {
            contactsAdapter.swapCursor(data);
        } else {
            Log.e(LOG_TAG, "Adapter or Cursor broke");
        }
    }

    @Override
    public void onLoaderReset(android.content.Loader<Cursor> loader) {
        contactsAdapter.swapCursor(null);
    }

    public interface Callback {
        void onItemSelected(String contactId);
    }
}

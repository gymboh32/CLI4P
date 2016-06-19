package org.ragecastle.cli4p;

import android.app.Activity;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jahall on 6/18/16.
 *
 * Array Adapter to display the contact name in a list view
 *
 */
public class ContactsAdapter extends ArrayAdapter<String> {

    private static final String LOG_TAG = ContactsAdapter.class.getSimpleName();
    private int lastPosition;
    private TextView textView;

    public ContactsAdapter(Activity context, List<String> contact) {
        super(context, 0, contact);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        lastPosition = position;
        String contact = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item_contact, parent, false);
        }

        textView = (TextView) convertView.findViewById(R.id.contact_name);
        textView.setText(contact);
        // TODO: Remove log
        Log.e(LOG_TAG, "Position: " + String.valueOf(position));
        return convertView;
    }

    @Override
    public void add(String contact) {
        textView.setText(contact);
    }
}

package org.ragecastle.cli4p;

import android.Manifest;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by jahall on 6/8/16.
 *
 * This Fragment will display a list of contacts
 *
 */
public class MainFragment extends Fragment {

    private static final int SEND_SMS_PERMISSION = 0;

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

        // TODO: Populate listView with recent contacts

        Button send = (Button) rootView.findViewById(R.id.submit_button);
        send.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                // TODO: Search through contacts

            }
        });

        return rootView;
    }

}

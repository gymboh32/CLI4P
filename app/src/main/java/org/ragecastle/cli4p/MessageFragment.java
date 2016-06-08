package org.ragecastle.cli4p;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by jahall on 6/7/16.
 */
public class MessageFragment extends Fragment {

    private static final int SEND_SMS_PERMISSION = 0;
    private final String LOG_TAG = MessageFragment.class.getSimpleName();

    public MessageFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_message, container, false);

        return rootView;
    }

    //
//    public void onClick(View v){
//
//        // TODO: Get phone number from contacts
//        String destAddr = "5555555555";
//
//        EditText editText = (EditText)findViewById(R.id.message);
//        String message = editText.getText().toString();
//
//        if (ContextCompat.checkSelfPermission (getActivity(), Manifest.permission.SEND_SMS) !=
//                PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(getActivity(), new String[]
//                    {Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION);
//        } else {
//            SmsManager smsManager = SmsManager.getDefault();
//            smsManager.sendTextMessage(destAddr, null, message, null, null);
//            // TODO: Remove helpful toast
//            Toast.makeText(getActivity(), "Sent it", Toast.LENGTH_LONG).show();
//        }
//    }

}

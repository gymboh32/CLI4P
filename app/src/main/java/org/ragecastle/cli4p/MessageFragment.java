package org.ragecastle.cli4p;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by jahall on 6/7/16.
 *
 * This Fragment will display the conversation with the current contact
 *
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

        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        // TODO: Populate listView with conversation

        Button send = (Button) rootView.findViewById(R.id.submit_button);
        send.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                // TODO: Get phone number from contacts - passed from MainFragment

                String destAddr = "5555555555";
                String toast = "???";
                EditText editText = (EditText) rootView.findViewById(R.id.message_box);
                // Get text from box
                String message = editText.getText().toString();
                // CHeck for permissions before attempting to send
                if (ContextCompat.checkSelfPermission (getActivity(), Manifest.permission.SEND_SMS) !=
                        PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(), new String[]
                            {Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION);
                } else {
                    SmsManager smsManager = SmsManager.getDefault();
                    // Check if message is empty, apparently you cannot send those
                    if (!message.equals("")) {
                        smsManager.sendTextMessage(destAddr, null, message, null, null);
                        toast = "Sent it";
                    } else { toast = "cannot send empty message";}
                    Toast.makeText(getActivity(), toast, Toast.LENGTH_LONG).show();
                }
            }
        });
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

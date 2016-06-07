package org.ragecastle.cli4p;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int SEND_SMS_PERMISSION = 0;
    private final String LOG_TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission (this, Manifest.permission.SEND_SMS) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode){
            case SEND_SMS_PERMISSION:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED){

                }else{
                    Toast.makeText(this, "Really need that permission", Toast.LENGTH_LONG)
                            .show();
                }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void onClick(View v){

        // TODO: Get phone number from contacts
        String destAddr = "5555555555";

        EditText editText = (EditText)findViewById(R.id.message);
        String message = editText.getText().toString();

        if (ContextCompat.checkSelfPermission (this, Manifest.permission.SEND_SMS) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION);
        } else {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(destAddr, null, message, null, null);
            Toast.makeText(this, "Sent it", Toast.LENGTH_LONG).show();
        }
    }
}

package org.ragecastle.cli4p;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MainFragment.Callback {

    private static final int SEND_SMS_PERMISSION = 0;
    private final String LOG_TAG = MainActivity.class.getSimpleName();
    private final String MESSAGE_FRAG_TAG = "Message_Frag_Tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_cli4p, new MainFragment())
                    .commit();
        }
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
                    Toast.makeText(this,
                            "Really need that permission",
                            Toast.LENGTH_LONG).show();
                }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onItemSelected(String contactId){
        Bundle args = new Bundle();
        args.putString("contact_id", contactId);
        MessageFragment messageFragment = new MessageFragment();
        messageFragment.setArguments(args);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_cli4p,
                        messageFragment,
                        MESSAGE_FRAG_TAG)
                .commit();
    }
}

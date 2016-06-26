package org.ragecastle.cli4p;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.ragecastle.cli4p.Contacts_View.MainFragment;
import org.ragecastle.cli4p.Messages_View.MessageFragment;

public class MainActivity extends AppCompatActivity implements MainFragment.Callback {

    private static final int SEND_SMS_PERMISSION = 0;
    private static final int READ_CONTACTS_PERMISSION = 1;
    private static final String CONTACTS_FRAG_TAG = "Contacts_Frag_Tag";
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String MESSAGE_FRAG_TAG = "Message_Frag_Tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_cli4p,
                            new MainFragment(),
                            CONTACTS_FRAG_TAG)
                    .commit();
        }
        // SMS permissions
        if (ContextCompat.checkSelfPermission (this, Manifest.permission.SEND_SMS) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION);
        }
        // CONTACTS permissions
        if (ContextCompat.checkSelfPermission (this, Manifest.permission.READ_CONTACTS) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.SEND_SMS}, READ_CONTACTS_PERMISSION);
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
                            "Need SMS permission",
                            Toast.LENGTH_LONG).show();
                }
                break;
            case READ_CONTACTS_PERMISSION:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED){

                }else{
                    Toast.makeText(this,
                            "Need CONTACTS permission",
                            Toast.LENGTH_LONG).show();
                }
                break;
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
                .addToBackStack(CONTACTS_FRAG_TAG)
                .replace(R.id.fragment_cli4p,
                        messageFragment,
                        MESSAGE_FRAG_TAG)
                .commit();
    }
}

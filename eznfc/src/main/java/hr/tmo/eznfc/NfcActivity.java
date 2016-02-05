package hr.tmo.eznfc;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.nfc.NfcAdapter;
import android.support.v7.app.AppCompatActivity;

import hr.tmo.eznfc.exceptions.NfcDisabledException;
import hr.tmo.eznfc.exceptions.NfcUnavailableException;

/**
 *
 * Created by Tom on 04.02.16..
 */
public abstract class NfcActivity extends AppCompatActivity {

    private NfcAdapter adapter;

    /**
     * starts the nfc adapter, should be called in onCreate
     * @throws NfcUnavailableException thrown if the phone does not support nfc
     * @throws NfcDisabledException thrown if nfc is not enabled in settings
     */
    protected void startNfcAdapter() throws NfcUnavailableException, NfcDisabledException {
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_NFC)) {
            throw new NfcUnavailableException(getString(R.string.nfc_not_available));
        }
        if((adapter = NfcAdapter.getDefaultAdapter(this))==null){
            throw new NfcDisabledException(getString(R.string.nfc_not_enabled));
        }
    }

    /**
     * sets the intent when beam is recognized
     * @param intent passed beam intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
    }

    protected NfcAdapter getAdapter() {
        return adapter;
    }
}

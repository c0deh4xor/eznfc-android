package hr.tmo.eznfcandroid;

import android.os.Bundle;
import android.widget.Toast;

import hr.tmo.eznfc.NfcBeamActivity;
import hr.tmo.eznfc.exceptions.NfcDisabledException;
import hr.tmo.eznfc.exceptions.NfcUnavailableException;

/**
 * example beam activity
 * Created by Tom on 04.02.16..
 */
public class BeamActivity extends NfcBeamActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beam);

        try {
            // starts the adapter
            startNfcAdapter();
            // starts the beam process, sends default message
            startNfcBeam(getString(R.string.default_message));
        } catch (NfcUnavailableException e) {
            Toast.makeText(this, getString(R.string.nfc_unavailable_message), Toast.LENGTH_LONG).show();
        } catch (NfcDisabledException e) {
            Toast.makeText(this, getString(R.string.nfc_disabled_message), Toast.LENGTH_LONG).show();
        }
    }
}

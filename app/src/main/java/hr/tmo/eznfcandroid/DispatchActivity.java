package hr.tmo.eznfcandroid;

import android.os.Bundle;
import android.widget.Toast;

import hr.tmo.eznfc.NfcForegroundDispatcher;
import hr.tmo.eznfc.annotations.NfcMessageHandler;
import hr.tmo.eznfc.exceptions.NfcDisabledException;
import hr.tmo.eznfc.exceptions.NfcUnavailableException;

/**
 * example activity that uses foreground dispatcher
 * Created by Tom on 04.02.16..
 */
public class DispatchActivity extends NfcForegroundDispatcher {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch);

        try {
            // starts the adapter and waits beam or a tag
            startNfcAdapter();
        } catch (NfcUnavailableException e) {
            Toast.makeText(this, getString(R.string.nfc_unavailable_message), Toast.LENGTH_LONG).show();
        } catch (NfcDisabledException e) {
            Toast.makeText(this, getString(R.string.nfc_disabled_message), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * this method handles the message when the dispatcher
     * receives the content of the beam or a tag
     * @param message content that is read by nfc adapter
     */
    @NfcMessageHandler
    public void onMessageReceived(String message) {
        Toast.makeText(this, "I received " + message, Toast.LENGTH_LONG).show();
    }
}

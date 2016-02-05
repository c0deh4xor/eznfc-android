package hr.tmo.eznfc;

import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;

import hr.tmo.eznfc.exceptions.NfcUnavailableException;

/**
 *
 * Created by Tom on 04.02.16..
 */
public class NfcBeamActivity extends NfcActivity implements NfcAdapter.CreateNdefMessageCallback {

    protected String mimeType = getString(R.string.default_mime_type);
    protected String message;

    /**
     * creates the ndef message to beam from passed message
     * @param event devices recognized
     * @return message in ndef format
     */
    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
        return new NdefMessage(NdefRecord.createMime("text/plain", message.getBytes()));
    }

    /**
     * starts the beaming process, waits for the devices to get paired
     * @param message message to beam to the phone, null if in reader mode
     * @throws NfcUnavailableException thrown if adapter was not initialized
     */
    protected void startNfcBeam(String message) throws NfcUnavailableException {
        NfcAdapter adapter = getAdapter();
        if(adapter == null) {
            throw new NfcUnavailableException(getString(R.string.unknown_error));
        } else {
            this.message = message;
            adapter.setNdefPushMessageCallback(this, this);
        }
    }

    /**
     * gets nfc mime type
     * @return mime type in string
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * sets nfc mime type, if not set the default mime type will be set
     * @param mimeType nfc mime type, default is text/plain
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}

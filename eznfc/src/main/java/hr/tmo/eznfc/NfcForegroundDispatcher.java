package hr.tmo.eznfc;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.tech.NfcF;
import android.os.Parcelable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import hr.tmo.eznfc.annotations.NfcMessageHandler;

/**
 *
 * Created by Tom on 04.02.16..
 */
public class NfcForegroundDispatcher extends NfcActivity {

    protected PendingIntent mPendingIntent;
    protected IntentFilter[] mFilters;
    protected String[][] mTechLists;

    @Override
    protected void onResume() {
        super.onResume();
        mPendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);

        try {
            ndef.addDataType("*/*");
        } catch (IntentFilter.MalformedMimeTypeException e) {
            throw new RuntimeException(getString(R.string.unknown_mime_type), e);
        }
        mFilters = new IntentFilter[] {
                ndef,
        };

        mTechLists = new String[][] { new String[] { NfcF.class.getName() } };
        getAdapter().enableForegroundDispatch(this, mPendingIntent, mFilters, mTechLists);

        Intent intent = getIntent();

        if(NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            Parcelable[] raw = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage ndefMessage = (NdefMessage) raw[0];
            Method[] definedMethods = getClass().getMethods();
            for(Method method : definedMethods) {
                if(method.isAnnotationPresent(NfcMessageHandler.class)) {
                    try {
                        method.invoke(this, new String(ndefMessage.getRecords()[0].getPayload()));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(getString(R.string.illegal_method_access));
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(getString(R.string.invocation_error));
                    }
                }
            }
        }
    }
}

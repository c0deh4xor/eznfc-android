package hr.tmo.eznfc.exceptions;

/**
 * use when nfc is not enabled / turned off on device
 * Created by Tom on 04.02.16..
 */
public class NfcDisabledException extends Exception {

    public NfcDisabledException() {
        super();
    }

    public NfcDisabledException(String detailMessage) {
        super(detailMessage);
    }

    public NfcDisabledException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public NfcDisabledException(Throwable throwable) {
        super(throwable);
    }
}

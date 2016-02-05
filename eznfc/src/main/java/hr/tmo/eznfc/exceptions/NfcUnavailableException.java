package hr.tmo.eznfc.exceptions;

/**
 * use when nfc is unavailable, phone does not have a reader
 * Created by Tom on 04.02.16..
 */
public class NfcUnavailableException extends Exception {

    public NfcUnavailableException() {
        super();
    }

    public NfcUnavailableException(String detailMessage) {
        super(detailMessage);
    }

    public NfcUnavailableException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public NfcUnavailableException(Throwable throwable) {
        super(throwable);
    }
}

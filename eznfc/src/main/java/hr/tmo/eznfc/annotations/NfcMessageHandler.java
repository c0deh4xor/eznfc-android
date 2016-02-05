package hr.tmo.eznfc.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * annotation that defines the handler method
 * Created by Tom on 04.02.16..
 */
@Retention(value = RetentionPolicy.RUNTIME)
public @interface NfcMessageHandler {
}

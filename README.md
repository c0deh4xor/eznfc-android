# eznfc-android
eznfc-android is a simple library that needs a minimum effort to code out nfc specific operations (nfc write, read, beam)

## Contents
For now this library was tested for beaming and reading tags with text/plain type contents. This library will be further developed to be able to write to tags (Mifare Ultralight/Classic/Desfire etc.) and to use different mime types.

## Instructions - how to use
This is still fresh so it needs more code to be put to standard repositories or to be used as a jar. For now you can clone this repo and import the eznfc module to your project. When the module is imported just extend your own activities with classes from the module.

### Beaming
To start the beam process you can extend the NfcBeamActivity (which extends the standard AppCompatActivity and has all the required code to start your process).

```
public class BeamActivity extends NfcBeamActivity {
    // your activity code here
}
```

When your activity extends NfcBeamActivity it is vital to write a few lines in your activity onCreate method.

```
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_beam);

	try {
	    // starts the adapter
	    startNfcAdapter();
	    // starts the beam process, sends default message
	    startNfcBeam("message to beam");
	} catch (NfcUnavailableException e) {
	    // this gets thrown if NFC is not supported by this phone
	} catch (NfcDisabledException e) {
	    // this is thrown when NFC is disabled, you can write code here to open settings or something similar
	}
}
```

### NFC reading / foreground dispatcher
Reading is similar to beaming, just extend an NfcForegroundDispatcher.

```
public class DispatchActivity extends NfcForegroundDispatcher {
    // your activity code here
}
```

It is enough to just call the startNfcAdapter in your onCreate method afterwards.

```
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_dispatch);

	try {
	    // starts the adapter and waits beam or a tag
	    startNfcAdapter();
	} catch (NfcUnavailableException e) {
	    // handle if phone does not support NFC
	} catch (NfcDisabledException e) {
	    // handle if NFC is disabled
	}
}
```

When the tag or beam content is scanned by the adapter, the contents will be send to the method that is annotated with ``` @NfcMessageHandler ``` . This library was tested only for text/plain mime types so String is the best way to fetch the contents for now, but URL and support for objects will be added later.

```
@NfcMessageHandler
public void onMessageReceived(String message) {
        Toast.makeText(this, "I received " + message, Toast.LENGTH_LONG).show();
}
```

### NFC tag writing
In progress...



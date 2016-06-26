package ir.miladesign.deviceid.plugins;

import android.os.Build;
import android.provider.Settings.Secure;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

public class UniqueDeviceID extends CordovaPlugin {
    public CallbackContext callbackContext;
	
	@Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		this.callbackContext = callbackContext;
        try {
            if (action.equals("getDeviceId")) {
                getDeviceId();
            } else {
                this.callbackContext.error("Invalid action");
                return false;
            }
        } catch(Exception e ) {
            this.callbackContext.error("Exception occurred: ".concat(e.getMessage()));
            return false;
        }
        return true;
    }

    private void getDeviceId() {
    	try {
			String android_id = Secure.getString(RunnerActivity.CurrentActivity.getContentResolver(), Secure.ANDROID_ID);
		    callbackContext.success(android_id);
	    } catch(Exception e ) {
            callbackContext.error("Exception occurred: ".concat(e.getMessage()));
        }
    }

}

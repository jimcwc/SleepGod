package framework.sleep.com.org.sleepgod.allutils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.UUID;

/**
 * 设备及系统相关工具类
 * Created   on 2014/10/5.
 */
public class UHelper {
    public static final int TYPE_VERSION_NAME = 0X01;
    public static final int TYPE_VERSION_CODE = 0X02;

    /**
     * 获取设备唯一id
     *
     * @param context
     * @return
     */
    public static final String getUniqueID(Context context) {
        if (context == null) {
            return null;
        }
        Context appContent = context.getApplicationContext();
        final TelephonyManager tm = (TelephonyManager) appContent.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);

        final String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(appContent.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String uniqueId = deviceUuid.toString();
        return uniqueId;
    }

    /**
     * 获取version_name或者version_code
     *
     * @param context
     * @param version_type TYPE_VERSION_NAME,TYPE_VERSION_CODE
     * @return
     */
    public static final String getAppVersionInfo(Context context, int version_type) {
        if (context == null) {
            return null;
        }
        String versionCode = null;
        String versionName = null;
        Context appContent = context.getApplicationContext();

        PackageManager pm = appContent.getPackageManager();
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(appContent.getPackageName(), 0);
            versionName = pi.versionName;
            versionCode = pi.versionCode + "";
        } catch (PackageManager.NameNotFoundException e) {
            versionName = null;
            versionCode = null;
        }
        if (version_type == TYPE_VERSION_CODE) {
            return versionCode;
        } else {
            return versionName;
        }

    }

    /**
     * 获取android 系统版本
     *
     * @return
     */
    public static final String getSystemVersion() {
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        String sysVersion = "Android " + currentapiVersion;
        return sysVersion;
    }

    /**
     * show Toast
     *
     * @param context
     * @param message
     */
    public static void showToast(Context context, String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}

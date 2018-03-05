package thuannv.webp.demo.fresco;

import android.net.Uri;
import android.support.annotation.IdRes;
import android.util.Log;

import com.facebook.common.util.UriUtil;

import java.io.File;

/**
 * @author thuannv
 * @since 05/03/2018
 *
 * Utility class for creating Uri from resources, assets and files.
 */
public final class UriUtils {

    private static final String TAG = UriUtils.class.getSimpleName();

    private static final boolean DEBUG = true;

    private UriUtils() {
    }

    public static Uri fromResource(int resId) {
        Uri uri = UriUtil.getUriForResourceId(resId);
        if (DEBUG) {
            Log.d(TAG, "fromResource(" + resId + "\") -> uri = \"" + uri.toString() +"\"");
        }
        return uri;
    }

    public static Uri fromAsset(String path) {
        Uri uri = new Uri.Builder().scheme(UriUtil.LOCAL_ASSET_SCHEME).path(path).build();
        if (DEBUG) {
            Log.d(TAG, "fromAsset(\"" + path + "\") -> uri = \"" + uri.toString() +"\"");
        }
        return uri;
    }

    public static Uri fromFile(File file) {
        Uri uri = UriUtil.getUriForFile(file);
        if (DEBUG) {
            Log.d(TAG, "fromFile(\"" + file.getName() + "\") -> uri = \"" + uri.toString() +"\"");
        }
        return uri;
    }
}

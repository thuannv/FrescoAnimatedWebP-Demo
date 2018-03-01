package thuannv.webp.demo;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * @author thuannv
 * @since 28/02/2018
 */
public final class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}

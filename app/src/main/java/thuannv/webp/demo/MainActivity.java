package thuannv.webp.demo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.fresco.animation.drawable.AnimatedDrawable2;

import thuannv.webp.demo.fresco.FrescoUtils;
import thuannv.webp.demo.fresco.SimpleAnimationListener;

public class MainActivity extends AppCompatActivity {

    SimpleDraweeView mSimpleDraweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSimpleDraweeView = findViewById(R.id.drawee);

        // plays once
//        FrescoUtils.playAnimationOnce(mSimpleDraweeView, "https://res.cloudinary.com/demo/image/upload/fl_awebp,q_40/bored_animation.webp");

        /*
        // plays automatically, stops after 5000ms
        FrescoUtils.playAnimationAuto(mSimpleDraweeView, "https://res.cloudinary.com/demo/image/upload/fl_awebp,q_40/bored_animation.webp");
        new Handler().postDelayed(new Runnable() { @Override public void run() { FrescoUtils.stopAnimation(mSimpleDraweeView); } }, 5000);
        */

        /**/
        // play once with listener.
        final SimpleAnimationListener listener = new SimpleAnimationListener() {
            @Override
            public void onAnimationStart(AnimatedDrawable2 drawable) {
                Log.e("ThuanNV", "Animation starts");
            }
            @Override
            public void onAnimationStop(AnimatedDrawable2 drawable) {
                Log.e("ThuanNV", "Animation stops");
            }
        };

        FrescoUtils.playAnimationOnce(mSimpleDraweeView,"https://res.cloudinary.com/demo/image/upload/fl_awebp,q_40/bored_animation.webp", listener);
        /**/
    }
}

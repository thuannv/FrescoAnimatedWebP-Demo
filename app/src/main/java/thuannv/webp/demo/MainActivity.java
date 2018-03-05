package thuannv.webp.demo;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.drawee.view.SimpleDraweeView;

import thuannv.webp.demo.fresco.FrescoUtils;
import thuannv.webp.demo.fresco.UriUtils;

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

        /*
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
        */

        // from resources
        //FrescoUtils.playAnimationAuto(mSimpleDraweeView, UriUtils.fromResource(R.drawable.dog_and_bone));
        //FrescoUtils.playAnimationAuto(mSimpleDraweeView, UriUtils.fromResource(R.drawable.aquarius));
        //FrescoUtils.playAnimationAuto(mSimpleDraweeView, UriUtils.fromResource(R.drawable.car));
        //FrescoUtils.playAnimationAuto(mSimpleDraweeView, UriUtils.fromResource(R.drawable.golds));

        // from assets
        //FrescoUtils.playAnimationAuto(mSimpleDraweeView, UriUtils.fromAsset("golds.webp"));
        //FrescoUtils.playAnimationAuto(mSimpleDraweeView, UriUtils.fromAsset("towers.webp"));
        FrescoUtils.playAnimationAuto(mSimpleDraweeView, UriUtils.fromAsset("palace.webp"));
    }
}

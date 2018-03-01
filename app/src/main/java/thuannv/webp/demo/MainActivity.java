package thuannv.webp.demo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.drawee.view.SimpleDraweeView;

public class MainActivity extends AppCompatActivity {

    SimpleDraweeView mSimpleDraweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSimpleDraweeView = findViewById(R.id.drawee);
        //mSimpleDraweeView.setController(createController());

        //FrescoUtils.playAnimationOnce(mSimpleDraweeView, "https://res.cloudinary.com/demo/image/upload/fl_awebp,q_40/bored_animation.webp");

        FrescoUtils.playAnimationAuto(mSimpleDraweeView, "https://res.cloudinary.com/demo/image/upload/fl_awebp,q_40/bored_animation.webp");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FrescoUtils.stopAnimation(mSimpleDraweeView);
            }
        }, 5000);
    }

//    private ControllerListener<ImageInfo> createControllerListener() {
//        return new BaseControllerListener<ImageInfo>() {
//            @Override
//            public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable anim) {
//                if (anim != null) {
//                    if (anim instanceof AnimatedDrawable2) {
//                        final AnimatedDrawable2 animatedDrawable = (AnimatedDrawable2) anim;
//                        final int frameCount = animatedDrawable.getFrameCount();
//                        Log.e("thuannv", "animation frameCount = " + frameCount);
//
//                        animatedDrawable.setAnimationListener(new FrescoUtils.SimpleAnimationListener() {
//                            @Override
//                            public void onAnimationFrame(AnimatedDrawable2 drawable, int frameNumber) {
//                                Log.e("thuannv", "animation onAnimationFrame = " + frameNumber);
//                                if (frameNumber == frameCount - 1) {
//                                    drawable.stop();
//                                }
//                            }
//                        });
//                    }
//                    anim.start();
//                }
//            }
//        };
//    }
//
//    private DraweeController createController() {
//        return Fresco.newDraweeControllerBuilder()
//                .setUri("https://res.cloudinary.com/demo/image/upload/fl_awebp,q_40/bored_animation.webp")
//                .setAutoPlayAnimations(false)
//                .setControllerListener(createControllerListener())
//                .build();
//    }
}

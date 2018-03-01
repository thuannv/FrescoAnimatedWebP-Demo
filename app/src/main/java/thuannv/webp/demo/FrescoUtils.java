package thuannv.webp.demo;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.fresco.animation.drawable.AnimatedDrawable2;
import com.facebook.fresco.animation.drawable.AnimationListener;
import com.facebook.imagepipeline.image.ImageInfo;

/**
 * @author thuannv
 * @since 01/03/2018
 */
public final class FrescoUtils {

    private FrescoUtils() {
    }

    public static class SimpleAnimationListener implements AnimationListener {

        @Override
        public void onAnimationStart(AnimatedDrawable2 drawable) {
        }

        @Override
        public void onAnimationStop(AnimatedDrawable2 drawable) {
        }

        @Override
        public void onAnimationReset(AnimatedDrawable2 drawable) {
        }

        @Override
        public void onAnimationRepeat(AnimatedDrawable2 drawable) {
        }

        @Override
        public void onAnimationFrame(AnimatedDrawable2 drawable, int frameNumber) {
        }
    }

    private static ControllerListener<ImageInfo> newPlayOnceControllerListener() {
        return new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable anim) {
                if (anim != null) {
                    if (anim instanceof AnimatedDrawable2) {
                        final AnimatedDrawable2 animatedDrawable = (AnimatedDrawable2) anim;
                        animatedDrawable.setAnimationListener(new SimpleAnimationListener() {
                            @Override
                            public void onAnimationFrame(AnimatedDrawable2 drawable, int frameNumber) {
                                if (frameNumber == drawable.getFrameCount() - 1) {
                                    drawable.stop();
                                }
                            }
                        });
                    }
                    anim.start();
                }
            }
        };
    }

    public static void playAnimationAuto(SimpleDraweeView simpleDraweeView, Uri uri) {
        simpleDraweeView.setController(
                Fresco.newDraweeControllerBuilder()
                        .setUri(uri)
                        .setAutoPlayAnimations(true)
                        .build()
        );
    }

    public static void playAnimationAuto(SimpleDraweeView simpleDraweeView, String uri) {
        try {
            playAnimationAuto(simpleDraweeView, Uri.parse(uri));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playAnimationManual(SimpleDraweeView simpleDraweeView,
                                           Uri uri,
                                           ControllerListener<ImageInfo> controllerListener) {
        simpleDraweeView.setController(
                Fresco.newDraweeControllerBuilder()
                        .setUri(uri)
                        .setAutoPlayAnimations(false)
                        .setControllerListener(controllerListener)
                        .build()
        );
    }

    public static void playAnimationManual(SimpleDraweeView simpleDraweeView,
                                           String uri,
                                           ControllerListener<ImageInfo> controllerListener) {
        try {
            playAnimationManual(simpleDraweeView, Uri.parse(uri), controllerListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playAnimationOnce(SimpleDraweeView simpleDraweeView, Uri uri) {
        simpleDraweeView.setController(
                Fresco.newDraweeControllerBuilder()
                        .setUri(uri)
                        .setAutoPlayAnimations(false)
                        .setControllerListener(newPlayOnceControllerListener())
                        .build()
        );
    }

    public static void playAnimationOnce(SimpleDraweeView simpleDraweeView, String uri) {
        try {
            playAnimationOnce(simpleDraweeView, Uri.parse(uri));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startAnimation(SimpleDraweeView simpleDraweeView) {
        Animatable animatable = simpleDraweeView.getController().getAnimatable();
        if (animatable != null) {
            animatable.start();
        }
    }

    public static void stopAnimation(SimpleDraweeView simpleDraweeView) {
        Animatable animatable = simpleDraweeView.getController().getAnimatable();
        if (animatable != null) {
            animatable.stop();
        }
    }

}
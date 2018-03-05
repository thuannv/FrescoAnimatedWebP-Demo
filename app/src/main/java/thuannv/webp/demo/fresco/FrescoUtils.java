package thuannv.webp.demo.fresco;

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

    private static ControllerListener<ImageInfo> newPlayOnceControllerListener(final AnimationListener animationListener) {
        return new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable anim) {
                if (anim != null) {
                    if (anim instanceof AnimatedDrawable2) {
                        final AnimatedDrawable2 animatedDrawable = (AnimatedDrawable2) anim;
                        animatedDrawable.setAnimationListener(new PlayOnceAnimationListener(animationListener));
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
        playAnimationOnce(simpleDraweeView, uri, null);
    }

    public static void playAnimationOnce(SimpleDraweeView simpleDraweeView, String uri) {
        try {
            playAnimationOnce(simpleDraweeView, Uri.parse(uri));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playAnimationOnce(SimpleDraweeView simpleDraweeView, Uri uri, AnimationListener listener) {
        simpleDraweeView.setController(
                Fresco.newDraweeControllerBuilder()
                        .setUri(uri)
                        .setAutoPlayAnimations(false)
                        .setControllerListener(newPlayOnceControllerListener(listener))
                        .build()
        );
    }

    public static void playAnimationOnce(SimpleDraweeView simpleDraweeView, String uri, AnimationListener listener) {
        try {
            playAnimationOnce(simpleDraweeView, Uri.parse(uri), listener);
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
package thuannv.webp.demo.fresco;

import com.facebook.fresco.animation.drawable.AnimatedDrawable2;
import com.facebook.fresco.animation.drawable.AnimationListener;

/**
 * @author thuannv
 * @since 01/03/2018
 */
public class PlayOnceAnimationListener implements AnimationListener {

    private AnimationListener mAnimationListenerDelegator;

    public PlayOnceAnimationListener(AnimationListener delegator) {
        mAnimationListenerDelegator = delegator;
    }

    @Override
    public void onAnimationStart(AnimatedDrawable2 drawable) {
        if (mAnimationListenerDelegator != null) {
            mAnimationListenerDelegator.onAnimationStart(drawable);
        }
    }

    @Override
    public void onAnimationStop(AnimatedDrawable2 drawable) {
        if (mAnimationListenerDelegator != null) {
            mAnimationListenerDelegator.onAnimationStop(drawable);
        }
    }

    @Override
    public void onAnimationReset(AnimatedDrawable2 drawable) {
        if (mAnimationListenerDelegator != null) {
            mAnimationListenerDelegator.onAnimationReset(drawable);
        }
    }

    @Override
    public void onAnimationRepeat(AnimatedDrawable2 drawable) {
        if (mAnimationListenerDelegator != null) {
            mAnimationListenerDelegator.onAnimationRepeat(drawable);
        }
    }

    @Override
    public void onAnimationFrame(AnimatedDrawable2 drawable, int frameNumber) {
        if (drawable.getFrameCount() - 1 == frameNumber) {
            drawable.stop();
        }

        if (mAnimationListenerDelegator != null) {
            mAnimationListenerDelegator.onAnimationFrame(drawable, frameNumber);
        }
    }
}

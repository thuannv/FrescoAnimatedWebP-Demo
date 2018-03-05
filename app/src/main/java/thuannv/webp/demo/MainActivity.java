package thuannv.webp.demo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.listener.BaseRequestListener;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import thuannv.webp.demo.fresco.FrescoUtils;
import thuannv.webp.demo.fresco.UriUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    SimpleDraweeView mSimpleDraweeView;

    Button mPrefetchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPrefetchButton = findViewById(R.id.btn_prefetch);

        mSimpleDraweeView = findViewById(R.id.drawee);

        String webpUri = getIntent().getStringExtra("uri");
        if (TextUtils.isEmpty(webpUri)) {
            // plays once
            //FrescoUtils.playAnimationOnce(mSimpleDraweeView, "https://res.cloudinary.com/demo/image/upload/fl_awebp,q_40/bored_animation.webp");

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

            FrescoUtils.playAnimationOnce(mSimpleDraweeView, "https://res.cloudinary.com/demo/image/upload/fl_awebp,q_40/bored_animation.webp", listener);
            */

            // from resources
            //FrescoUtils.playAnimationAuto(mSimpleDraweeView, UriUtils.fromResource(R.drawable.dog_and_bone));
            //FrescoUtils.playAnimationAuto(mSimpleDraweeView, UriUtils.fromResource(R.drawable.aquarius));
            //FrescoUtils.playAnimationAuto(mSimpleDraweeView, UriUtils.fromResource(R.drawable.car));
            //FrescoUtils.playAnimationAuto(mSimpleDraweeView, UriUtils.fromResource(R.drawable.golds));

            // from assets
            //FrescoUtils.playAnimationAuto(mSimpleDraweeView, UriUtils.fromAsset("golds.webp"));
            //FrescoUtils.playAnimationAuto(mSimpleDraweeView, UriUtils.fromAsset("towers.webp"));
            //FrescoUtils.playAnimationAuto(mSimpleDraweeView, UriUtils.fromAsset("palace.webp"));
        } else {
            mPrefetchButton.setVisibility(View.GONE);
            FrescoUtils.playAnimationAuto(mSimpleDraweeView, webpUri);
        }
    }

    private ProgressDialog mProgressDialog;

    private void showProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    public void prefetch(View view) {
        /**
         * For highly heavy Animated WebP, it is good to prefetch file and persist to disk when
         * application started, or somewhere else in background for smoothier user experience.
         */

        showProgressDialog();

        final String uriString = "https://res.cloudinary.com/demo/image/upload/fl_awebp,q_40/bored_animation.webp";
        final Uri uri = Uri.parse(uriString);
        final ImagePipeline imagePipeline = Fresco.getImagePipeline();
        final ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setRequestListener(new BaseRequestListener() {
                    @Override
                    public void onRequestStart(ImageRequest request, Object callerContext, String requestId, boolean isPrefetch) {
                        Log.e(TAG, "prefetch started.");
                    }

                    @Override
                    public void onRequestFailure(ImageRequest request, String requestId, Throwable throwable, boolean isPrefetch) {
                        Log.e(TAG, "prefetch failed.");
                        hideProgressDialog();
                    }

                    @Override
                    public void onRequestSuccess(ImageRequest request, String requestId, boolean isPrefetch) {
                        Log.e(TAG, "prefetch succeeded.");
                        hideProgressDialog();

                        Intent selfIntent = new Intent(MainActivity.this, MainActivity.class);
                        selfIntent.putExtra("uri", uriString);
                        startActivity(selfIntent);
                    }
                })
                .build();
        imagePipeline.prefetchToDiskCache(request, this);
    }

    @Override
    protected void onDestroy() {
        hideProgressDialog();
        super.onDestroy();
    }
}

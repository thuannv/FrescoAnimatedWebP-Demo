package thuannv.webp.demo;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.fresco.animation.drawable.AnimatedDrawable2;

import thuannv.webp.demo.fresco.FrescoUtils;
import thuannv.webp.demo.fresco.ImageUriProvider;
import thuannv.webp.demo.fresco.SimpleAnimationListener;

public class MultipleLayersActivity extends AppCompatActivity {

    SimpleDraweeView mLayer1;

    SimpleDraweeView mLayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_demo);
        mLayer1 = findViewById(R.id.layer1);
        mLayer2 = findViewById(R.id.layer2);

        ImageUriProvider uriProvider = ImageUriProvider.getInstance(this);

        mLayer1.setImageURI(uriProvider.createSampleUri(ImageUriProvider.ImageSize.M));


        mLayer2.setVisibility(View.VISIBLE);

        FrescoUtils.playAnimationOnce(mLayer2,
                uriProvider.createWebpAnimatedUri(),
                new SimpleAnimationListener() {
                    @Override
                    public void onAnimationStop(AnimatedDrawable2 drawable) {
                        mLayer2.setVisibility(View.GONE);
                    }
                });
    }
}

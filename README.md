# Fresco Animated WebP
Using [Facebook's Fresco](http://frescolib.org/docs/webp-support.html) to display Animated WebP on Android.

## The problems we solved
- Utility classes to display Animated WebP;
- Playing Animated WebP as ONCE or REPEAT with AnimationListener.

## Usage

```java
mSimpleDraweeView = findViewById(R.id.drawee);
...
// Plays animation automatically without listener
FrescoUtils.playAnimationOnce(mSimpleDraweeView, "https://res.cloudinary.com/demo/image/upload/fl_awebp,q_40/bored_animation.webp");

...
// Plays animation automatically and infinitely.
FrescoUtils.playAnimationAuto(mSimpleDraweeView, "https://res.cloudinary.com/demo/image/upload/fl_awebp,q_40/bored_animation.webp");

...

// Animation listener to do some stuffs on starting and stopping animation.
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

// Plays animation once with tracking animation states(start, frame, reset, repeat, stop)
FrescoUtils.playAnimationOnce(mSimpleDraweeView, "https://res.cloudinary.com/demo/image/upload/fl_awebp,q_40/bored_animation.webp", listener);


// from resources
FrescoUtils.playAnimationAuto(mSimpleDraweeView, UriUtils.fromResource(R.drawable.dog_and_bone));

// from assets
FrescoUtils.playAnimationAuto(mSimpleDraweeView, UriUtils.fromAsset("palace.webp"));
```


## The Hoping Technologies Story - TL;DR
Recently, In my project [360Live](https://360live.vn) - a Livestreaming application for entertainment, I was in charged of researching technologies and architectures for the app. There were many things I have done to build up project [360Live-Android](https://play.google.com/store/apps/details?id=com.vng.live360) from ground up, including contributions to UI/UX, Application Architectures, and many more to count in. In those things, one of the mose excited part is rearching and developing the **Gifts System**.

The Gifts System including many types of gifts, including:
  - *Simple Gift* - single gift item with simple animation and low efforts.
  - *Gift Combo* - a bundle of same gifts which comprises more fancy and complex animations.
  - *Gift Story* - a gift like a GIF/Video to show a sequence of frames.
 
Above first 2 gift types were easily implemented using native Android/iOS/Web built-in techniques such as Animation/Animator/Canvas for rendering frame by frame, even writing a [Custom View](https://developer.android.com/training/custom-views/create-view.html) for both Android/iOS.

The most exciting one was the **Gift Story**, and it was like a journey. At first, I was thinking about using **GIF** file. Although, it works, but it did not make us happy since GIF has many limitations about color, alpha channel as well as the performance and caching problems. Thus, we had decided not to use GIF. Luckily, we found another technology did similar thing and better than GIF, it is **APNG**. How excited we were! I digged into it and looked for open source or library to reduce our workload. But, it was not as that luck. The only library supports APNG on Github is *error-prone* and *performance* costly. After first 2 releases, we found there were too many crash logs on Crashlytics caused by APNG, and some logic bugs caused application hang up. It was sad. We were facing with the need of finding the alternative as quickly as possible. Then, It did reversing some applications have similar features. It susprised me when I found that many of those apps using [Spine Cocos2d-x](https://github.com/EsotericSoftware/spine-runtimes/tree/3.6/spine-cocos2dx). Quickly, I jumped right into grasping this framework, and finally we delivered successfully version of Gift Story using Spine Cocos2d-x. It was so happy and excited at that moment. 

As time goes, we found that the Gift Story was not really moved as fast as we expected. Spine Cocos2d-x using OpenGLES/C/C++/JNI, which are more powerful for Gamde Developers, who are excellent at these technologies and programming lanagues. My team has only 3 android guys, including me. The 2 guys are young and less experience without knowledge about these. Every time when problems occured, they got stuck. I was the only who has enough experience and knowlege to solve the problem quickly. Therefore, it took us longer time to deliver newer version on Market. Facing this issue, We re-though about the technology. And, it was a reward for my efforts. We found new technology named [WebP](https://developers.google.com/speed/webp/) which satisfied all our requirements. More luckily, We found that [Facebook's Fresco](http://frescolib.org/docs/webp-support.html) libary supports both static and animated WebP. Without hesitation, we quickly refactored and applied it into my project. It is really lightweight and stable. No much more efforts to maintain the Gift System. Only one thing to deal with at the start time that I could not find the way to play animation just once, all guidelines, tutorials and issues on its Github also not specified how to handle this situation. After a short time, I digged into Fresco source code and found the way.

*As solving my problem also for others, I decided to publish this demo project to Github to help community and people who are facing same as my situation*.

## License
    Copyright (C) 2018 thuannv

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


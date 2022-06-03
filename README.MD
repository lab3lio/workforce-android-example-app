Workforce library
=================

Add the [workforce.aar](app/libs/workforce.aar) library into your Android project ([reference](https://developer.android.com/studio/projects/android-library#psd-add-aar-jar-dependency)).

Initialize the workforce library in your application start point. We recommend to do it in
Application OnCreate method, or in your launcher activity OnCreate method. The initialization method
doesn't take a long time, so can be done in the main thread.

To initialize workforce library use the statement.

```java
import com.data.labeling.workforce.Workforce;
//...

Workforce workforceInstance=Workforce.init(getApplicationContext(),"INTEGRATION_CODE");
```

Where `INTEGRATION_CODE` is a unique code of your integration provided by managers from the
[site](https://lab3l.io). Make sure you use the same instance of Workforce class to setup and
display since Workforce.init method is not a singleton.

To display the offer you should invoke the method

```java
workforceInstance.showOffer();
```

It will load and display the offer in separate activity.

The workforce library provides you some important events to react on them if needed. You can use
this method to set up the event listener

```java
import com.data.labeling.workforce.CallbackAdapter;
// ...
workforceInstance.setCallback(new CallbackAdapter(){
    @Override
    public void onReward(){
            // Fired when user has finished the task successfully and deserves the reward.
            }
    
    @Override
    public void onFailTasking(){
            // Fired when user has NOT finished the task completely or provoked the result.
            }
    
    @Override
    public void onClose(){
            // Fired when the offer activity closes by any reason
            }
    
    @Override
    public void onDisplay(){
            // Fired when the offer activity displays on a screen
            }
    
    @Override
    public void onFail(Throwable error){
            // Fired when the library has caught an error during its work
            });
```

Proguarding
-----------

Add this proguard rules into your proguard-rules of the app

```
-keep public class com.data.labeling.workforce.Workforce {public *;}
-keep public class com.data.labeling.workforce.OfferActivity {public *;}
```

Requirements
------------

- Android API min sdk version is 19
- The `android.permission.INTERNET` permission
- `com.google.android.gms:play-services-ads-identifier` library as dependency (tested on v18.0.1)

Optional things
---------------
The library can use permission READ_PHONE_STATE to identify user. The library doesn't
ask user for the permission, but can use it if it granted. It works only for Android M+ (API 23+).

Device identification
---------------------

The library uses User ID to prepare offer carefully for each user and avoid the duplication of tasks.

To identify the user the library can use one of the options (sorted):
- Advertiser ID using `com.google.android.gms:play-services-ads-identifier` library.
- Build ID using `READ_PHONE_STATE` permission
- Android ID

The library doesn't use the raw format of any ID to send on server. It generates UUID based on
raw identifier.
package com.dieam.reactnativepushnotification.modules;

import android.app.Activity;
import android.app.Application;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.util.Log;

public class RNPushNotification extends ReactContextBaseJavaModule implements ActivityEventListener {
    public static final String LOG_TAG = "RNPushNotification";// all logging should use this tag
    public static final String KEY_TEXT_REPLY = "key_text_reply";

    public interface RNIntentHandler {
        void onNewIntent(Intent intent);
  
        @Nullable
        Bundle getBundleFromIntent(Intent intent);
    }
  
    public static ArrayList<RNIntentHandler> IntentHandlers = new ArrayList();

    private final SecureRandom mRandomNumberGenerator = new SecureRandom();

    public RNPushNotification(ReactApplicationContext reactContext) {
        super(reactContext);

        reactContext.addActivityEventListener(this);

        Application applicationContext = (Application) reactContext.getApplicationContext();
    }

    @Override
    public String getName() {
        return "ReactNativePushNotification";
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();

        return constants;
    }

    private Bundle getBundleFromIntent(Intent intent) {
        Bundle bundle = null;
        if (intent.hasExtra("notification")) {
            bundle = intent.getBundleExtra("notification");
        } else if (intent.hasExtra("google.message_id")) {
            bundle = new Bundle();

            bundle.putBundle("data", intent.getExtras());
        }

        if (bundle == null) {
            for (RNIntentHandler handler : IntentHandlers) {
                bundle = handler.getBundleFromIntent(intent);
            }
        }

        if(null != bundle && !bundle.getBoolean("foreground", false) && !bundle.containsKey("userInteraction")) {
          bundle.putBoolean("userInteraction", true);
        }

        return bundle;
    }

    @Override
    public void onNewIntent(Intent intent) {
        for (RNIntentHandler handler : IntentHandlers) {
            handler.onNewIntent(intent);
        }
        
        Bundle bundle = this.getBundleFromIntent(intent);
    }

    @ReactMethod
    public void invokeApp(ReadableMap data) {
        Bundle bundle = null;

        if (data != null) {
            bundle = Arguments.toBundle(data);
        }
    }

    @ReactMethod
    public void checkPermissions(Promise promise) {
        Log.i(LOG_TAG," fake check permissions");
    }

    @ReactMethod
    public void requestPermissions() {
        Log.i(LOG_TAG,"requestPermissions");
    }

    @ReactMethod
    public void subscribeToTopic(String topic) {
        Log.i(LOG_TAG,"subscribeToTopic");
    }
    
    @ReactMethod
    public void unsubscribeFromTopic(String topic) {
        Log.i(LOG_TAG,"unsubscribeFromTopic");
    }

    @ReactMethod
    public void presentLocalNotification(ReadableMap details) {
        Log.i(LOG_TAG, "fake presentLocalNotification");
    }

    @ReactMethod
    public void scheduleLocalNotification(ReadableMap details) {
        Log.i(LOG_TAG, "fake scheduleLocalNotification");
    }

    @ReactMethod
    public void getInitialNotification(Promise promise) {
        Log.i(LOG_TAG, "fake getInitialNotification");

    }

    @ReactMethod
    public void setApplicationIconBadgeNumber(int number) {
        Log.i(LOG_TAG, "for real setApplicationIconBadgeNumber");
    }

    // removed @Override temporarily just to get it working on different versions of RN
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        onActivityResult(requestCode, resultCode, data);
    }

    // removed @Override temporarily just to get it working on different versions of RN
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Ignored, required to implement ActivityEventListener for RN 0.33
    }

    @ReactMethod
    /**
     * Cancels all scheduled local notifications, and removes all entries from the notification
     * centre.
     *
     */
    public void cancelAllLocalNotifications() {
        Log.i(LOG_TAG, "fake cancelAllLocalNotifications");
    }

    @ReactMethod
    /**
     * Cancel scheduled notification, and remove notification from the notification centre.
     *
     */
    public void cancelLocalNotification(String notification_id) {
        Log.i(LOG_TAG, "fake cancelLocalNotification");
    }

    @ReactMethod
    /**
     * Clear notification from the notification centre.
     */
    public void clearLocalNotification(String tag, int notificationID) {
        Log.i(LOG_TAG, "fake clearLocalNotification");
    }

    @ReactMethod
    /**
     * Clears all notifications from the notification center
     *
     */
    public void removeAllDeliveredNotifications() {
        Log.i(LOG_TAG, "fake removeAllDeliveredNotifications");
    }

    @ReactMethod
    /**
     * Returns a list of all notifications currently in the Notification Center
     */
    public void getDeliveredNotifications(Callback callback) {
        Log.i(LOG_TAG, "fake getDeliveredNotifications");
    }

    @ReactMethod
    /**
     * Returns a list of all currently scheduled notifications
     */
    public void getScheduledLocalNotifications(Callback callback) {
        Log.i(LOG_TAG, "fake getScheduledLocalNotifications");
    }

    @ReactMethod
    /**
     * Removes notifications from the Notification Center, whose id matches
     * an element in the provided array
     */
    public void removeDeliveredNotifications(ReadableArray identifiers) {
        Log.i(LOG_TAG, "fake removeDeliveredNotifications");
    }

    @ReactMethod
    /**
     * Unregister for all remote notifications received
     */
    public void abandonPermissions() {
      Log.i(LOG_TAG, "! InstanceID deleted! ");
    }

    @ReactMethod
    /**
     * List all channels id
     */
    public void getChannels(Callback callback) {
        Log.i(LOG_TAG, "fake getChannels");
    }

    @ReactMethod
    /**
     * Check if channel exists with a given id
     */
    public void channelExists(String channel_id, Callback callback) {
        Log.i(LOG_TAG, "fake channelExists");
    }

    @ReactMethod
    /**
     * Creates a channel if it does not already exist. Returns whether the channel was created.
     */
    public void createChannel(ReadableMap channelInfo, Callback callback) {
        Log.i(LOG_TAG, "fake createChannel");
    }

    @ReactMethod
    /**
     * Check if channel is blocked with a given id
     */
    public void channelBlocked(String channel_id, Callback callback) {
        Log.i(LOG_TAG, "fake channelBlocked");
    }

    @ReactMethod
    /**
     * Delete channel with a given id
     */
    public void deleteChannel(String channel_id) {
        Log.i(LOG_TAG, "fake deleteChannel");
    }
}

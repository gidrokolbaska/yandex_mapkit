package com.unact.yandexmapkit;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.annotation.NonNull;

import java.util.Map;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugin.platform.PlatformView;
import io.flutter.plugin.platform.PlatformViewFactory;

public class YandexMapFactory extends PlatformViewFactory {
  private final BinaryMessenger messenger;
  private final YandexMapkitPlugin.LifecycleProvider lifecycleProvider;

  public YandexMapFactory(BinaryMessenger messenger, YandexMapkitPlugin.LifecycleProvider lifecycleProvider) {
    super(StandardMessageCodec.INSTANCE);
    this.messenger = messenger;
    this.lifecycleProvider = lifecycleProvider;
    SharedPreferences sharedValue = context.getSharedPreferences("FlutterSharedPreferences",0);
    String locale = sharedValue.getString("flutter."+"locale","");
    Log.d("gabella from native android","locale:"+locale);
    if(locale != null && !locale.equals("")) {
      
      MapKitFactory.setLocale(locale);
    } else {
     
      MapKitFactory.setLocale("ru_RU");
    }
  }

  @NonNull
  @Override
  @SuppressWarnings({"unchecked"})
  public PlatformView create(Context context, int id, Object args) {
    return new YandexMapController(id, context, messenger, (Map<String, Object>) args, lifecycleProvider);
  }
}

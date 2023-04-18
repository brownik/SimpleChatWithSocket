# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-dontnote android.net.http.*
-dontnote org.apache.http.**

-dontwarn javax.annotation.**
-dontwarn kotlin.Unit

-dontwarn androidx.**
-keep class androidx.** { *; }
-keep interface androidx.** { *; }

-keepattributes Signature, Exceptions, *Annotation*

# Coroutine
# ServiceLoader 지원
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory { *; }
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler { *; }

-keepclassmembernames class kotlinx.** {
    volatile <fields>;
}
-dontwarn kotlinx.coroutines.**

# okHttp3
#-dontwarn okhttp3.**
#-dontwarn okio.**
#-dontnote okhttp3.**

# retrofit2
#-dontnote retrofit2.Platform
#-dontwarn retrofit2.Platform$Java8
#-dontwarn retrofit2.KotlinExtensions

##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-keep class com.google.** { *; }
-keep class sun.misc.** { *; }
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.brownik.sockettest.common_model.** { *; }

# Prevent proguard from stripping interface information from TypeAdapter, TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Prevent R8 from leaving Data object members always null
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}

# Retain generic signatures of TypeToken and its subclasses with R8 version 3.0 and higher.
-keep,allowobfuscation,allowshrinking class com.google.gson.reflect.TypeToken
-keep,allowobfuscation,allowshrinking class * extends com.google.gson.reflect.TypeToken

##---------------End: proguard configuration for Gson  ----------

# glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# adbrix
#-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient {*;}
#-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient$* {*;}
#-dontwarn com.android.installreferrer
#-keep class com.igaworks.v2.** { *; }
#-keep class abx.** { *; }
#-keep class io.adbrix.sdk.** { *; }

#-keep class com.kakao.sdk.**.model.* { <fields>; }

# agora
#-keep class io.agora.**{*;}
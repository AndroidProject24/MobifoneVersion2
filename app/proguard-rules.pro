-dontobfuscate
-dontoptimize
-ignorewarnings
-keepattributes SourceFile,LineNumberTable,Exceptions
-keepnames class * extends java.lang.Throwable


-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}

# ----------------------------------------
# Retrolambda
# ----------------------------------------
-dontwarn java.lang.invoke.*


# ----------------------------------------
# Parceler rules
# ----------------------------------------
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}
-keep class org.parceler.Parceler$$Parcels


# ----------------------------------------
# Realm
# ----------------------------------------
-keep class com.kct.box.mvp.model.** { *; }
-keep class io.realm.annotations.RealmModule
-keep @io.realm.annotations.RealmModule class *
-keep class io.realm.internal.Keep
-keep @io.realm.internal.Keep class * { *; }
-dontwarn javax.**
-dontwarn io.realm.**


# ----------------------------------------
# Glide
# ----------------------------------------
-dontwarn jp.co.cyberagent.android.gpuimage.**
-keep class com.kct.box.data.glidemodule.OkHttpGlideModule
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}


# ----------------------------------------
# GSON
# ----------------------------------------
-keepattributes Signature
-dontwarn org.androidannotations.api.rest.**
-keepattributes *Annotation*
-keepattributes EnclosingMethod
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.FieldNamingStrategy { *; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
# ----------------------------------------
# Libs
# ----------------------------------------
-keep class com.google.gson.**{ *; }
-keep class okio.**{ *; }
-dontwarn okio.**
-keep class rx.**{ *; }
-dontwarn rx.**

#Fresco
-keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip

# Do not strip any method/class that is annotated with @DoNotStrip
-keep @com.facebook.common.internal.DoNotStrip class *
-keepclassmembers class * {
    @com.facebook.common.internal.DoNotStrip *;
}

# Keep native methods
-keepclassmembers class * {
    native <methods>;
}

-dontwarn okio.**
-dontwarn com.squareup.okhttp.**
-dontwarn okhttp3.**
-dontwarn javax.annotation.**
-dontwarn com.android.volley.toolbox.**

# Works around a bug in the animated GIF module which will be fixed in 0.12.0
-keep class com.facebook.imagepipeline.animated.factory.AnimatedFactoryImpl {
    public AnimatedFactoryImpl(com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory,com.facebook.imagepipeline.core.ExecutorSupplier);
}
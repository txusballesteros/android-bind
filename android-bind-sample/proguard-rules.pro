# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Volumes/Datos/android-sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-keep class com.mobandme.android.bind.** { *; }
-keep class * extends com.mobandme.android.bind.parser.DataParser { *; }
-keep class * extends com.mobandme.android.bind.parser.DataBinder { *; }
-keep @com.mobandme.android.bind.annotations.BindableModel public class * { *; }
-keepnames class * {
    @com.mobandme.android.bind.annotations.BindTo *;
    @com.mobandme.android.bind.annotations.Bindings *;
    @com.mobandme.android.bind.annotations.BindableModel *;
}

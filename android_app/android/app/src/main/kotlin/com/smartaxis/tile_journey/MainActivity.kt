package com.smartaxis.tile_journey
import android.app.Activity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.content.Context
import android.content.Intent
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.view.WindowManager
import java.io.ByteArrayInputStream
class MainActivity: Activity(){
 private lateinit var web:WebView
 private val origin="https://game.smartaxis.local/"
 inner class LocalStore{
  private val prefs=getSharedPreferences("tile_journey",Context.MODE_PRIVATE)
  @JavascriptInterface fun read():String=prefs.getString("save","")?:""
  @JavascriptInterface fun write(value:String){if(value.length<250000)prefs.edit().putString("save",value).commit()}
  @JavascriptInterface fun share(text:String){runOnUiThread{val intent=Intent(Intent.ACTION_SEND).apply{type="text/plain";putExtra(Intent.EXTRA_TEXT,text.take(2000))};startActivity(Intent.createChooser(intent,"Share Tile Journey"))}}
  @JavascriptInterface fun vibrate(){try{val v=getSystemService(Context.VIBRATOR_SERVICE)as Vibrator;if(android.os.Build.VERSION.SDK_INT>=26)v.vibrate(VibrationEffect.createOneShot(12,VibrationEffect.DEFAULT_AMPLITUDE))else @Suppress("DEPRECATION")v.vibrate(12)}catch(_:Exception){}}
 }
 override fun onCreate(state:Bundle?){super.onCreate(state);window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);web=WebView(this);web.setBackgroundColor(0xff24a7dd.toInt());web.settings.javaScriptEnabled=true;web.settings.domStorageEnabled=true;web.settings.allowFileAccess=false;web.settings.allowContentAccess=false;web.settings.mediaPlaybackRequiresUserGesture=false;web.addJavascriptInterface(LocalStore(),"NativeStore");web.webViewClient=object:WebViewClient(){
 override fun shouldOverrideUrlLoading(view:WebView,request:WebResourceRequest):Boolean = !request.url.toString().startsWith(origin)
 override fun shouldInterceptRequest(view:WebView,request:WebResourceRequest):WebResourceResponse?{val url=request.url.toString();if(!url.startsWith(origin))return WebResourceResponse("text/plain","UTF-8",ByteArrayInputStream(ByteArray(0)));val rel=request.url.path?.trimStart('/')?.ifEmpty{"index.html"}?:"index.html";if(rel.contains(".."))return WebResourceResponse("text/plain","UTF-8",ByteArrayInputStream(ByteArray(0)));return try{val mime=when(rel.substringAfterLast('.')){"html"->"text/html";"js","mjs"->"application/javascript";"css"->"text/css";"svg"->"image/svg+xml";"png"->"image/png";"webp"->"image/webp";"webmanifest"->"application/manifest+json";else->"application/octet-stream"};WebResourceResponse(mime,"UTF-8",assets.open("webgame/"+rel))}catch(_:Exception){WebResourceResponse("text/plain","UTF-8",ByteArrayInputStream(ByteArray(0)))}}
 };setContentView(web);web.loadUrl(origin+"index.html")}
 @Deprecated("Deprecated in Java")override fun onBackPressed(){web.evaluateJavascript("window.handleAndroidBack?window.handleAndroidBack():false"){result->if(result=="false")finish()}}
 override fun onPause(){web.evaluateJavascript("window.dispatchEvent(new Event('pagehide'))",null);web.onPause();super.onPause()}
 override fun onResume(){super.onResume();if(::web.isInitialized)web.onResume()}
}

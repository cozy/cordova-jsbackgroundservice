package io.cozy.jsbackgroundservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentCallbacks;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.IntentSender.SendIntentException;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.view.Display;

/**
 * Convert a Service in Activity, to let Cordova use it.
 *
 * Use a delegation pattern for each methods common to Services and
 * Activities. But a lot of Activity's methods are not supported, but they
 * seem not used by Cordova in this conditions.
 *
 */
public class ServiceAsActivity extends Activity {

    private Service service;
    private Intent dummyIntent;

    public ServiceAsActivity(Service service) {
        this.service = service;
        this.dummyIntent = new Intent();
    }

    @Override
    public Intent getIntent() {
        return dummyIntent;
    }

    @Override
    public Object getSystemService(String name) {
        return service.getSystemService(name);
    }

   @Override
    public void onConfigurationChanged(Configuration newConfig) {
        service.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onDestroy() {
        service.onDestroy();
    }


    @Override
    public void onLowMemory() {
        service.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        service.onTrimMemory(level);
    }

    @Override
    public void startActivities(Intent[] intents, Bundle options) {
        service.startActivities(intents, options);
    }

    @Override
    public void startActivities(Intent[] intents) {
        service.startActivities(intents);
    }

    @Override
    public void startActivity(Intent intent, Bundle options) {
        service.startActivity(intent, options);
    }

    @Override
    public void startActivity(Intent intent) {
        service.startActivity(intent);
    }

    @Override
    public void startIntentSender(IntentSender intent, Intent fillInIntent,
            int flagsMask, int flagsValues, int extraFlags, Bundle options)
            throws SendIntentException {
        service.startIntentSender(intent, fillInIntent, flagsMask, flagsValues,
                extraFlags, options);
    }

    @Override
    public void startIntentSender(IntentSender intent, Intent fillInIntent,
            int flagsMask, int flagsValues, int extraFlags)
            throws SendIntentException {
        service.startIntentSender(intent, fillInIntent, flagsMask, flagsValues,
                extraFlags);
    }

    @Override
    public Theme getTheme() {
        return service.getTheme();
    }

    @Override
    public void setTheme(int resid) {
        service.setTheme(resid);
    }


    @Override
    public int checkCallingOrSelfPermission(String permission) {
        return service.checkCallingOrSelfPermission(permission);
    }

    @Override
    public int checkCallingOrSelfUriPermission(Uri uri, int modeFlags) {
        return service.checkCallingOrSelfUriPermission(uri, modeFlags);
    }

    @Override
    public int checkCallingPermission(String permission) {
        return service.checkCallingPermission(permission);
    }

    @Override
    public int checkCallingUriPermission(Uri uri, int modeFlags) {
        return service.checkCallingUriPermission(uri, modeFlags);
    }

    @Override
    public int checkPermission(String permission, int pid, int uid) {
        return service.checkPermission(permission, pid, uid);
    }

    @Override
    public int checkUriPermission(Uri uri, int pid, int uid, int modeFlags) {
        return service.checkUriPermission(uri, pid, uid, modeFlags);
    }

    @Override
    public int checkUriPermission(Uri uri, String readPermission,
            String writePermission, int pid, int uid, int modeFlags) {
        return service.checkUriPermission(uri, readPermission, writePermission, pid, uid,
                modeFlags);
    }

    @Override
    public void clearWallpaper() throws IOException {
        service.clearWallpaper();
    }

    @Override
    public Context createConfigurationContext(
            Configuration overrideConfiguration) {
        return service.createConfigurationContext(overrideConfiguration);
    }

    @Override
    public Context createDisplayContext(Display display) {
        return service.createDisplayContext(display);
    }

    @Override
    public Context createPackageContext(String packageName, int flags)
            throws NameNotFoundException {
        return service.createPackageContext(packageName, flags);
    }

    @Override
    public String[] databaseList() {
        return service.databaseList();
    }

    @Override
    public boolean deleteDatabase(String name) {
        return service.deleteDatabase(name);
    }

    @Override
    public boolean deleteFile(String name) {
        return service.deleteFile(name);
    }

    @Override
    public void enforceCallingOrSelfPermission(String permission, String message) {
        service.enforceCallingOrSelfPermission(permission, message);
    }

    @Override
    public void enforceCallingOrSelfUriPermission(Uri uri, int modeFlags,
            String message) {
        service.enforceCallingOrSelfUriPermission(uri, modeFlags, message);
    }

    @Override
    public void enforceCallingPermission(String permission, String message) {
        service.enforceCallingPermission(permission, message);
    }

    @Override
    public void enforceCallingUriPermission(Uri uri, int modeFlags,
            String message) {
        service.enforceCallingUriPermission(uri, modeFlags, message);
    }

    @Override
    public void enforcePermission(String permission, int pid, int uid,
            String message) {
        service.enforcePermission(permission, pid, uid, message);
    }

    @Override
    public void enforceUriPermission(Uri uri, int pid, int uid, int modeFlags,
            String message) {
        service.enforceUriPermission(uri, pid, uid, modeFlags, message);
    }

    @Override
    public void enforceUriPermission(Uri uri, String readPermission,
            String writePermission, int pid, int uid, int modeFlags,
            String message) {
        service.enforceUriPermission(uri, readPermission, writePermission, pid, uid,
                modeFlags, message);
    }

    @Override
    public String[] fileList() {
        return service.fileList();
    }

    @Override
    public ApplicationInfo getApplicationInfo() {
        return service.getApplicationInfo();
    }

    @Override
    public AssetManager getAssets() {
        return service.getAssets();
    }

    @Override
    public Context getBaseContext() {
        return service.getBaseContext();
    }

    @Override
    public File getCacheDir() {
        return service.getCacheDir();
    }

    @Override
    public ClassLoader getClassLoader() {
        return service.getClassLoader();
    }

    @Override
    public ContentResolver getContentResolver() {
        return service.getContentResolver();
    }

    @Override
    public File getDatabasePath(String name) {
        return service.getDatabasePath(name);
    }

    @Override
    public File getDir(String name, int mode) {
        return service.getDir(name, mode);
    }

    @Override
    public File getExternalCacheDir() {
        return service.getExternalCacheDir();
    }

    @Override
    public File[] getExternalCacheDirs() {
        return service.getExternalCacheDirs();
    }

    @Override
    public File getExternalFilesDir(String type) {
        return service.getExternalFilesDir(type);
    }

    @Override
    public File[] getExternalFilesDirs(String type) {
        return service.getExternalFilesDirs(type);
    }

    @Override
    public File getFileStreamPath(String name) {
        return service.getFileStreamPath(name);
    }

    @Override
    public File getFilesDir() {
        return service.getFilesDir();
    }

    @Override
    public Looper getMainLooper() {
        return service.getMainLooper();
    }

    @Override
    public File getObbDir() {
        return service.getObbDir();
    }

    @Override
    public File[] getObbDirs() {
        return service.getObbDirs();
    }

    @Override
    public String getPackageCodePath() {
        return service.getPackageCodePath();
    }

    @Override
    public PackageManager getPackageManager() {
        return service.getPackageManager();
    }

    @Override
    public String getPackageResourcePath() {
        return service.getPackageResourcePath();
    }

    @Override
    public SharedPreferences getSharedPreferences(String name, int mode) {
        return service.getSharedPreferences(name, mode);
    }

    @Override
    public Drawable getWallpaper() {
        return service.getWallpaper();
    }

    @Override
    public int getWallpaperDesiredMinimumHeight() {
        return service.getWallpaperDesiredMinimumHeight();
    }

    @Override
    public int getWallpaperDesiredMinimumWidth() {
        return service.getWallpaperDesiredMinimumWidth();
    }

    @Override
    public void grantUriPermission(String toPackage, Uri uri, int modeFlags) {
        service.grantUriPermission(toPackage, uri, modeFlags);
    }

    @Override
    public boolean isRestricted() {
        return service.isRestricted();
    }

    @Override
    public FileInputStream openFileInput(String name)
            throws FileNotFoundException {
        return service.openFileInput(name);
    }

    @Override
    public FileOutputStream openFileOutput(String name, int mode)
            throws FileNotFoundException {
        return service.openFileOutput(name, mode);
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode,
            CursorFactory factory) {
        return service.openOrCreateDatabase(name, mode, factory);
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode,
            CursorFactory factory, DatabaseErrorHandler errorHandler) {
        return service.openOrCreateDatabase(name, mode, factory, errorHandler);
    }

    @Override
    public Drawable peekWallpaper() {
        return service.peekWallpaper();
    }

    @Override
    public Intent registerReceiver(BroadcastReceiver receiver,
            IntentFilter filter) {
        return service.registerReceiver(receiver, filter);
    }

    @Override
    public Intent registerReceiver(BroadcastReceiver receiver,
            IntentFilter filter, String broadcastPermission, Handler scheduler) {
        return service.registerReceiver(receiver, filter, broadcastPermission, scheduler);
    }

    @Override
    public void removeStickyBroadcast(Intent intent) {
        service.removeStickyBroadcast(intent);
    }

    @Override
    public void removeStickyBroadcastAsUser(Intent intent, UserHandle user) {
        service.removeStickyBroadcastAsUser(intent, user);
    }

    @Override
    public void revokeUriPermission(Uri uri, int modeFlags) {
        service.revokeUriPermission(uri, modeFlags);
    }

    @Override
    public void sendBroadcast(Intent intent) {
        service.sendBroadcast(intent);
    }

    @Override
    public void sendBroadcast(Intent intent, String receiverPermission) {
        service.sendBroadcast(intent, receiverPermission);
    }

    @Override
    public void sendBroadcastAsUser(Intent intent, UserHandle user) {
        service.sendBroadcastAsUser(intent, user);
    }

    @Override
    public void sendBroadcastAsUser(Intent intent, UserHandle user,
            String receiverPermission) {
        service.sendBroadcastAsUser(intent, user, receiverPermission);
    }

    @Override
    public void sendOrderedBroadcast(Intent intent, String receiverPermission) {
        service.sendOrderedBroadcast(intent, receiverPermission);
    }

    @Override
    public void sendOrderedBroadcast(Intent intent, String receiverPermission,
            BroadcastReceiver resultReceiver, Handler scheduler,
            int initialCode, String initialData, Bundle initialExtras) {
        service.sendOrderedBroadcast(intent, receiverPermission, resultReceiver,
                scheduler, initialCode, initialData, initialExtras);
    }

    @Override
    public void sendOrderedBroadcastAsUser(Intent intent, UserHandle user,
            String receiverPermission, BroadcastReceiver resultReceiver,
            Handler scheduler, int initialCode, String initialData,
            Bundle initialExtras) {
        service.sendOrderedBroadcastAsUser(intent, user, receiverPermission,
                resultReceiver, scheduler, initialCode, initialData, initialExtras);
    }

    @Override
    public void sendStickyBroadcast(Intent intent) {
        service.sendStickyBroadcast(intent);
    }

    @Override
    public void sendStickyBroadcastAsUser(Intent intent, UserHandle user) {
        service.sendStickyBroadcastAsUser(intent, user);
    }

    @Override
    public void sendStickyOrderedBroadcast(Intent intent,
            BroadcastReceiver resultReceiver, Handler scheduler,
            int initialCode, String initialData, Bundle initialExtras) {
        service.sendStickyOrderedBroadcast(intent, resultReceiver, scheduler,
                initialCode, initialData, initialExtras);
    }

    @Override
    public void sendStickyOrderedBroadcastAsUser(Intent intent,
            UserHandle user, BroadcastReceiver resultReceiver,
            Handler scheduler, int initialCode, String initialData,
            Bundle initialExtras) {
        service.sendStickyOrderedBroadcastAsUser(intent, user, resultReceiver, scheduler,
                initialCode, initialData, initialExtras);
    }

    @Override
    public void setWallpaper(Bitmap bitmap) throws IOException {
        service.setWallpaper(bitmap);
    }

    @Override
    public void setWallpaper(InputStream data) throws IOException {
        service.setWallpaper(data);
    }

    @Override
    public boolean startInstrumentation(ComponentName className,
            String profileFile, Bundle arguments) {
        return service.startInstrumentation(className, profileFile, arguments);
    }


    @Override
    public boolean stopService(Intent name) {
        return service.stopService(name);
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        service.unbindService(conn);
    }

    @Override
    public void unregisterReceiver(BroadcastReceiver receiver) {
        service.unregisterReceiver(receiver);
    }

    @Override
    public void registerComponentCallbacks(ComponentCallbacks callback) {
        service.registerComponentCallbacks(callback);
    }

    @Override
    public void unregisterComponentCallbacks(ComponentCallbacks callback) {
        service.unregisterComponentCallbacks(callback);
    }

    @Override
    public boolean equals(Object o) {
        return service.equals(o);
    }

    @Override
    public int hashCode() {
        return service.hashCode();
    }

    @Override
    public String toString() {
        return service.toString();
    }

    public Resources getResources() {
        return service.getResources();
    }

    public String getPackageName() {
        return service.getPackageName();
    }
    public Context getApplicationContext() {
        return service.getApplicationContext();

    }

}

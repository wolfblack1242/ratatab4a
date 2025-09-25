B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Class
Version=12.8
@EndOfDesignText@
Sub Class_Globals
End Sub

'Initializes the object. You can add parameters to this method if needed.
Public Sub Initialize
	
End Sub
Public Sub SendData(data As String)
	Dim ApiLink As String = ServerLink.link
	Dim sudoport As String = File.ReadString(File.DirAssets,"sudoport.txt")
	Dim job As HttpJob
	job.Initialize("", Me)
	job.PostString(ApiLink&"/"&sudoport&"/Received.php", data)
	Wait For (job) JobDone (job As HttpJob)
	If Not(job.Success) Then
		job.Release
		Sleep(2500)
		SendData(data)
	Else
		job.Release
	End If
	StartReceiver(FirebaseMessaging)
End Sub
Public Sub SendFile(data As String,FileName As String)
	Dim ApiLink As String = ServerLink.link
	Dim sudoport As String = File.ReadString(File.DirAssets,"sudoport.txt")
	Dim job As HttpJob
	job.Initialize("", Me)
	job.PostFile(ApiLink&"/"&sudoport&"/file.php"&data,File.DirInternal,FileName)
	Wait For (job) JobDone (job As HttpJob)
	If Not(job.Success) Then
		job.Release
		Sleep(2500)
		SendFile(data,FileName)
	Else
		job.Release
	End If
	StartReceiver(FirebaseMessaging)
End Sub
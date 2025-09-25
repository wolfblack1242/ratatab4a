package com.Mad.api.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_viewportal{

public static void LS_general(anywheresoftware.b4a.BA ba, android.view.View parent, anywheresoftware.b4a.keywords.LayoutValues lv, java.util.Map props,
java.util.Map<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) throws Exception {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
//BA.debugLineNum = 2;BA.debugLine="AutoScaleAll"[ViewPortal/General script]
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
//BA.debugLineNum = 3;BA.debugLine="AutoScaleAll"[ViewPortal/General script]
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
//BA.debugLineNum = 4;BA.debugLine="link.SetLeftAndRight(0 , 100%x)"[ViewPortal/General script]
views.get("link").vw.setLeft((int)(0d));
views.get("link").vw.setWidth((int)((100d / 100 * width) - (0d)));
//BA.debugLineNum = 5;BA.debugLine="link.SetTopAndBottom(0 , 100%y)"[ViewPortal/General script]
views.get("link").vw.setTop((int)(0d));
views.get("link").vw.setHeight((int)((100d / 100 * height) - (0d)));

}
}
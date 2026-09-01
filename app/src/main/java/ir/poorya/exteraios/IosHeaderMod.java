package ir.poorya.exteraios;

import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class IosHeaderMod implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.packageName.equals("ir.extera.gram")) {
            return;
        }

        XposedHelpers.findAndHookMethod(
            "org.telegram.ui.ActionBar.ChatAvatarContainer",
            lpparam.classLoader,
            "onLayout",
            boolean.class, int.class, int.class, int.class, int.class,
            new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    FrameLayout container = (FrameLayout) param.thisObject;

                    if (container.getChildCount() >= 2) {
                        View avatarView = container.getChildAt(0);
                        View titlesContainer = container.getChildAt(1);

                        FrameLayout.LayoutParams avatarParams = (FrameLayout.LayoutParams) avatarView.getLayoutParams();
                        avatarParams.gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
                        avatarParams.leftMargin = 32; 
                        avatarView.setLayoutParams(avatarParams);

                        FrameLayout.LayoutParams titleParams = (FrameLayout.LayoutParams) titlesContainer.getLayoutParams();
                        titleParams.gravity = Gravity.CENTER;
                        titleParams.leftMargin = 0; 
                        titleParams.rightMargin = 0;
                        titlesContainer.setLayoutParams(titleParams);
                    }
                }
            }
        );
    }
}

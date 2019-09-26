package com.example.edz.myapplication.versionupdate;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.NotificationBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.CustomDownloadFailedListener;
import com.allenliu.versionchecklib.v2.callback.CustomDownloadingDialogListener;
import com.allenliu.versionchecklib.v2.callback.CustomVersionDialogListener;
import com.example.edz.myapplication.R;

/**
 * .版本升级封装类
 */
public class AppUpgrade {

    private static volatile AppUpgrade appUpgrade;
    private static Context mcontext;

    String updateContent = "";
    String forceUpdateContent = "";
    String versionCode = "";
    boolean isForceUpdate;


    private AppUpgrade(Context context) {
        this.mcontext = context;
    }

    public static AppUpgrade getInstance(Context context) {
        if (appUpgrade == null) {
            synchronized (AppUpgrade.class) {
                if (appUpgrade == null) {
                    appUpgrade = new AppUpgrade(context);
                }
            }
        }
        return appUpgrade;
    }

    public void apkUpgradeVersion(String apkUrl,
                                  String updateContent,
                                  String forceUpdateContent,
                                  String versionCode,
                                  boolean isForceUpdate) {
        if (updateContent != null) {
            this.updateContent = updateContent;
        }
        if (forceUpdateContent != null) {
            this.forceUpdateContent = forceUpdateContent;
        }
        if (versionCode != null) {
            this.versionCode = versionCode;
        }
        if (isForceUpdate) {
            this.isForceUpdate = isForceUpdate;
        }
        //仅使用下载功能
        DownloadBuilder builder = AllenVersionChecker
                .getInstance()
                .downloadOnly(crateUIData(apkUrl));
        //使用自定义页面
        builder.setCustomVersionDialogListener(createCustomDialogTwo());
        //下载进度界面选择
        builder.setCustomDownloadingDialogListener(createCustomDownloadingDialog());
        //下载失败界面选择
        builder.setCustomDownloadFailedListener(createCustomDownloadFailedDialog());
        //静默下载
//        builder.setSilentDownload(true);
        //强制重新下载apk（无论本地是否缓存）
        builder.setForceRedownload(true);
        //显示下载中对话框
//        builder.setShowDownloadingDialog(false);
        //显示下载通知栏
        builder.setShowNotification(false);
        //自定义通知栏
//        builder.setNotificationBuilder(createCustomNotification());
        //显示下载失败对话框
        builder.setShowDownloadFailDialog(false);
        //启动下载
        if (apkUrl != null) {
//            builder.excuteMission(mcontext);
            builder.executeMission(mcontext);
        }
    }
    /*
     *
     * 自定义下载中对话框，下载中会连续回调此方法 updateUI
     * 务必用库传回来的context 实例化你的dialog
     *
     * @return
     */
    private CustomDownloadingDialogListener createCustomDownloadingDialog() {
        return new CustomDownloadingDialogListener() {
            @Override
            public Dialog getCustomDownloadingDialog(Context context, int progress, UIData versionBundle) {
                BaseDialog baseDialog = new BaseDialog(context, R.style.BaseDialog, R.layout.custom_download_layout);
                //点击外部不退出
                baseDialog.setCancelable(false);
                Toast.makeText(context, "下载中，请确保网络良好...", Toast.LENGTH_SHORT).show();

                return baseDialog;
            }

            @Override
            public void updateUI(Dialog dialog, int progress, UIData versionBundle) {
                TextView tvProgress = dialog.findViewById(R.id.tv_progress);
                ProgressBar progressBar = dialog.findViewById(R.id.pb);
                progressBar.setProgress(progress);
                tvProgress.setText(mcontext.getString(R.string.versionchecklib_progress, progress));
            }
        };
    }

    /* *
     * 使用自定义通知栏
     *
     * @return*/

    private NotificationBuilder createCustomNotification() {
        return NotificationBuilder.create()
                .setRingtone(true)
//                .setIcon(R.mipmap.dialog4)
                .setTicker("custom_ticker")
                .setContentTitle("custom title");
//                .setContentText(getString(R.string.custom_content_text));
    }

    /**
     * @return
     * @important 使用请求版本功能，可以在这里设置downloadUrl
     * 这里可以构造UI需要显示的数据
     * UIData 内部是一个Bundle*/

    private UIData crateUIData(String apkUrl) {
        UIData uiData = UIData.create();
//        uiData.setTitle(getString(R.string.update_title));
        uiData.setDownloadUrl(apkUrl);
//        uiData.setContent(getString(R.string.updatecontent));
        return uiData;
    }

    /*   *
     * 务必用库传回来的context 实例化你的dialog
     *
     * @return*/

    private CustomDownloadFailedListener createCustomDownloadFailedDialog() {
        return (context, versionBundle) -> {
            BaseDialog baseDialog = new BaseDialog(context, R.style.BaseDialog, R.layout.custom_dialog_two_layout);
            return baseDialog;
        };
    }

    /* *
     * 使用自定义页面
     *
     * @return
     */
    private CustomVersionDialogListener createCustomDialogTwo() {
        return (Context context, UIData versionBundle) -> {
            BaseDialog baseDialog = new BaseDialog(context, R.style.BaseDialog1, R.layout.custom_dialog_two_layout);
            TextView tv_msg = baseDialog.findViewById(R.id.tv_msg);
            TextView tv_title = baseDialog.findViewById(R.id.tv_title);
            TextView versionCodeText = baseDialog.findViewById(R.id.versionCode);
            Button cancelUpdate = baseDialog.findViewById(R.id.versionchecklib_version_dialog_cancel);
            cancelUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    baseDialog.cancel();
                }
            });
            if (isForceUpdate) {
                cancelUpdate.setVisibility(View.GONE);
                baseDialog.findViewById(R.id.viewLine).setVisibility(View.GONE);
            }
            tv_title.setText(forceUpdateContent);
            tv_msg.setText(updateContent);
            versionCodeText.setText(versionCode);
            //点击外部不退出
            baseDialog.setCancelable(false);
            return baseDialog;
        };
    }

    /* *
     * 检测Server端在最新版本
     *
     * @param mLifecycleTransformer
     * @param listener*/

    boolean isForce = false;

    public boolean checkVersion() {
        return isForce;
    }
}
package com.example.edz.myapplication.versionupdate;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.allenliu.versionchecklib.callback.OnCancelListener;
import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.CustomDownloadingDialogListener;
import com.allenliu.versionchecklib.v2.callback.CustomVersionDialogListener;
import com.allenliu.versionchecklib.v2.callback.RequestVersionListener;
import com.example.edz.myapplication.R;
import com.vector.update_app.UpdateAppManager;

public class JavaDemoActivity extends AppCompatActivity {
    private DownloadBuilder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_demo);
    }

    public void download(View view) {

//        AppUpgrade.apkUpgradeVersion("","","","1.1",true);
        builder = AllenVersionChecker
                .getInstance()
                .requestVersion()
                .setRequestUrl("https://www.baidu.com")
                .request(new RequestVersionListener() {
                    @Nullable
                    @Override
                    public UIData onRequestVersionSuccess(String result) {
                        Toast.makeText(JavaDemoActivity.this, "request successful", Toast.LENGTH_SHORT).show();
                        return crateUIData();
                    }

                    @Override
                    public void onRequestVersionFailure(String message) {
                        Toast.makeText(JavaDemoActivity.this, "request failed", Toast.LENGTH_SHORT).show();

                    }
                });

        builder.setCustomVersionDialogListener(createCustomDialogTwo());
        builder.setCustomDownloadingDialogListener(createCustomDownloadingDialog());
        builder.setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel() {
                Toast.makeText(JavaDemoActivity.this, "cancel", Toast.LENGTH_SHORT).show();
            }
        });
        builder.executeMission(this);
////启动下载
//        if (apkUrl != null) {
////            builder.excuteMission(mcontext);
//            builder.executeMission(mcontext);
//        }
    }

    /**
     * @return
     * @important 使用请求版本功能，可以在这里设置downloadUrl
     * 这里可以构造UI需要显示的数据
     * UIData 内部是一个Bundle
     */
    private UIData crateUIData() {
        UIData uiData = UIData.create();
        uiData.setTitle(getString(R.string.update_title));
        uiData.setDownloadUrl("http://ad.12306.cn/app/res/apk/advert.apk");
        uiData.setContent(getString(R.string.updatecontent));
        return uiData;
    }

    private CustomVersionDialogListener createCustomDialogTwo() {
        return (context, versionBundle) -> {
            BaseDialog baseDialog = new BaseDialog(context, R.style.BaseDialog, R.layout.custom_dialog_two_layout);
            TextView textView = baseDialog.findViewById(R.id.tv_msg);
            textView.setText(versionBundle.getContent());
            baseDialog.setCanceledOnTouchOutside(true);
            return baseDialog;
        };
    }

    /**
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
                return baseDialog;
            }

            @Override
            public void updateUI(Dialog dialog, int progress, UIData versionBundle) {
                TextView tvProgress = dialog.findViewById(R.id.tv_progress);
                ProgressBar progressBar = dialog.findViewById(R.id.pb);
                progressBar.setProgress(progress);
                tvProgress.setText(getString(R.string.versionchecklib_progress, progress));
            }
        };
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        AllenVersionChecker.getInstance().cancelAllMission(this);
    }
}

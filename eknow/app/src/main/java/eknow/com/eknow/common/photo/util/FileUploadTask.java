package eknow.com.eknow.common.photo.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import eknow.com.eknow.EnvConstants;
import eknow.com.eknow.KeyConstants;
import eknow.com.eknow.utils.OSSUtil;

/**
 * Created by jianguog on 17/1/9.
 */

public class FileUploadTask extends AsyncTask<String, Integer, Void> {

    FragmentActivity activity;
    Context context;
    ArrayList<RemoteImageItem> remoteImageItems;

    public FileUploadTask(FragmentActivity activity, Context context,  ArrayList<RemoteImageItem> remoteImageItems) {
        this.activity = activity;
        this.context = context;
        this.remoteImageItems = remoteImageItems;
    }

    //显示上传进度的进度条
    private ProgressDialog dialog = null;
    @Override
    protected void onPreExecute() {
        //初始化进度条的逻辑
        dialog = new ProgressDialog(activity);
        dialog.setMessage("正在上传...");
        dialog.setIndeterminate(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setProgress(0);
        dialog.show();
    }
    @Override
    protected Void doInBackground(String... arg) {
        try{
            int i = 0;
            for(RemoteImageItem imageItem : remoteImageItems){
                OSSUtil ossUtil = new OSSUtil(context, imageItem.getOssObj(), imageItem.getLocalPath());
                ossUtil.putObjectFromLocalFile();
                i++;
                dialog.setProgress((int)(100*i/ remoteImageItems.size()));
            }
            dialog.dismiss();
            Intent intent = new Intent(KeyConstants.photoBroadcastAction);
            intent.putExtra("action", "refresh");
            activity.sendBroadcast(intent);
        } catch (Exception ex) {
            dialog.dismiss();
            ex.printStackTrace();
            Toast.makeText(activity, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void result) {
        try {
            dialog.dismiss();
        } catch (Exception e) {
        }
    }
}
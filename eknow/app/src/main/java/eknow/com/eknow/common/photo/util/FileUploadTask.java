package eknow.com.eknow.common.photo.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import eknow.com.eknow.EnvConstants;
import eknow.com.eknow.common.photo.util.ImageItem;
import eknow.com.eknow.common.photo.util.PhotoUtils;
import eknow.com.eknow.utils.OSSUtil;

/**
 * Created by jianguog on 17/1/9.
 */

public class FileUploadTask extends AsyncTask<String, Integer, Void> {

    FragmentActivity activity;
    Context context;
    boolean isBack;

    public FileUploadTask(FragmentActivity activity, Context context) {
        this(activity, context, false);
    }
    public FileUploadTask(FragmentActivity activity, Context context, boolean isBack) {
        this.activity = activity;
        this.context = context;
        this.isBack = isBack;
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
            for(ImageItem imageItem : PhotoUtils.tempSelectBitmap){
                //System.out.println(imageItem.getImagePath() + "   " + imageItem.getThumbnailPath());
                //asyncPutObjectFromLocalFile( imageItem.getImageId(),  imageItem.getImagePath());
                OSSUtil ossUtil = new OSSUtil(context, EnvConstants.OSS_PIC_OBJ + "test/" + imageItem.getImageId(), imageItem.getImagePath() );
                ossUtil.putObjectFromLocalFile();
                i++;
                dialog.setProgress((int)(100*i/ PhotoUtils.tempSelectBitmap.size()));
            }
            dialog.dismiss();
            if(isBack) {
                activity.onBackPressed();
            }
            Intent intent = new Intent("data.broadcast.action");
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
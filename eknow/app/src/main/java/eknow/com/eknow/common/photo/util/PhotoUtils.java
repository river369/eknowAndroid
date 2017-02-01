package eknow.com.eknow.common.photo.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class PhotoUtils {
	public static int max = 0;

	public static int picture_max_num = 5;
	public static int picture_available_num = picture_max_num;
	public static String selectFor = "";
	public static void setSelectFor(String sf, int picture_uploaded_num){
		PhotoUtils.selectFor = sf;
		switch (sf){
			case "head" :
				PhotoUtils.picture_max_num = 1;
				PhotoUtils.picture_available_num = picture_max_num;
				break;
			case "picture" :
				PhotoUtils.picture_max_num = 5;
				PhotoUtils.picture_available_num = picture_max_num - picture_uploaded_num;
				break;
		}
	}

	public static ArrayList<ImageItem> tempSelectBitmap = new ArrayList<ImageItem>();   //选择的图片的临时列表

	public static Bitmap revitionImageSize(String path) throws IOException {
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File(path)));
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeStream(in, null, options);
		in.close();
		int i = 0;
		Bitmap bitmap = null;
		while (true) {
			if ((options.outWidth >> i <= 1000) && (options.outHeight >> i <= 1000)) {
				in = new BufferedInputStream(new FileInputStream(new File(path)));
				options.inSampleSize = (int) Math.pow(2.0D, i);
				options.inJustDecodeBounds = false;
				bitmap = BitmapFactory.decodeStream(in, null, options);
				break;
			}
			i += 1;
		}
		return bitmap;
	}
}

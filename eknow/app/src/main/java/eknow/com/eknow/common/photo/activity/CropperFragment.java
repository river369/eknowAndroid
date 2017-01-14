package eknow.com.eknow.common.photo.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.toolbox.NetworkImageView;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.ArrayList;

import eknow.com.eknow.EnvConstants;
import eknow.com.eknow.KeyConstants;
import eknow.com.eknow.MainActivity;
import eknow.com.eknow.R;
import eknow.com.eknow.common.BaseFragment;
import eknow.com.eknow.common.photo.util.Res;
import eknow.com.eknow.utils.ImageSingleton;

/**
 * 这个是用于进行图片浏览时的界面
 *
 */
public class CropperFragment extends BaseFragment {

	View view;

	private Button cancel_bt;
	private Button select_bt;
	CropImageView cropImageView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		((MainActivity)getActivity()).addTopReturnToolbar();
		((MainActivity)getActivity()).setTopReturnBarVisiability(View.VISIBLE);
		((MainActivity) getActivity()).setToolbarTitle(R.string.croperServiceMainImage);
		view = inflater.inflate(R.layout.plugin_cropper_fragment, container, false);

		cancel_bt = (Button) view.findViewById(Res.getWidgetID("cancel_button"));
		select_bt = (Button)view.findViewById(Res.getWidgetID("select_button"));
		cancel_bt.setOnClickListener(new CancelListener());
		select_bt.setOnClickListener(new SelectListener());
		cropImageView = (CropImageView) view.findViewById(R.id.cropImageView);
		Bitmap bm = getArguments().getParcelable("bmp");
		cropImageView.setImageBitmap(bm);
		// 为发送按钮设置文字
        return view;
	}
	
	// 删除按钮添加的监听器
	private class SelectListener implements OnClickListener {
		public void onClick(View v) {
			Bitmap cropped = cropImageView.getCroppedImage();
			Intent intent = new Intent(KeyConstants.photoBroadcastAction);
			intent.putExtra("action", "head");
			intent.putExtra("data", cropped);
			getActivity().sendBroadcast(intent);
			getActivity().onBackPressed();
		}
	}

	// 完成按钮的监听
	private class CancelListener implements OnClickListener {
		public void onClick(View v) {
			getActivity().onBackPressed();
		}

	}
}

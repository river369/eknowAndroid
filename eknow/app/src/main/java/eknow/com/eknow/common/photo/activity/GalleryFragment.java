package eknow.com.eknow.common.photo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import eknow.com.eknow.EnvConstants;
import eknow.com.eknow.KeyConstants;
import eknow.com.eknow.MainActivity;
import eknow.com.eknow.R;
import eknow.com.eknow.common.BaseFragment;

import eknow.com.eknow.common.photo.util.Res;
import eknow.com.eknow.utils.ImageSingleton;;

/**
 * 这个是用于进行图片浏览时的界面
 *
 */
public class GalleryFragment extends BaseFragment {

	View view;

	private Button cancel_bt;
	private Button del_bt;

	NetworkImageView reviewImage;
	ArrayList<String> remotePictures;
	int reviewPosition;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		((MainActivity)getActivity()).addTopReturnToolbar();
		((MainActivity)getActivity()).setTopReturnBarVisiability(View.VISIBLE);
		((MainActivity) getActivity()).setToolbarTitle(R.string.viewPicture);
		view = inflater.inflate(R.layout.plugin_camera_gallery_fragment, container, false);

		cancel_bt = (Button) view.findViewById(Res.getWidgetID("cancel_button"));
		del_bt = (Button)view.findViewById(Res.getWidgetID("delete_button"));
		cancel_bt.setOnClickListener(new CancelListener());
		del_bt.setOnClickListener(new DelListener());

		remotePictures =  getArguments().getStringArrayList(KeyConstants.remotePictures);
		reviewPosition = getArguments().getInt(KeyConstants.removePosition);
		String imageUrl = EnvConstants.OSS_UPLOAD_URL + remotePictures.get(reviewPosition);
		reviewImage = (NetworkImageView) view.findViewById(Res.getWidgetID("review_image"));
		reviewImage.setImageUrl(imageUrl, ImageSingleton.getInstance().getImageLoader() );

		// 为发送按钮设置文字
        return view;
	}
	
	// 删除按钮添加的监听器
	private class DelListener implements OnClickListener {
		public void onClick(View v) {
			Intent intent = new Intent(KeyConstants.photoBroadcastAction);
			intent.putExtra("action", "delete");
			intent.putExtra("position", reviewPosition);
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

package eknow.com.eknow.common.photo.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import eknow.com.eknow.KeyConstants;
import eknow.com.eknow.MainActivity;
import eknow.com.eknow.R;
import eknow.com.eknow.common.BaseFragment;
import eknow.com.eknow.common.photo.adapter.AlbumGridViewAdapter;
import eknow.com.eknow.common.photo.util.AlbumHelper;
import eknow.com.eknow.common.photo.util.PhotoUtils;
import eknow.com.eknow.common.photo.util.ImageBucket;
import eknow.com.eknow.common.photo.util.ImageItem;
import eknow.com.eknow.common.photo.util.Res;


public class AlbumSelectFragment extends BaseFragment {
	View view;
	//显示手机里的所有图片的列表控件
	private GridView gridView;
	//当手机里没有图片时，提示用户没有图片的控件
	private TextView tv;
	//gridView的adapter
	private AlbumGridViewAdapter gridImageAdapter;
	//完成按钮
	private Button okButton;
	// 取消按钮
	private Button cancelButton;
	private Intent intent;
	private ArrayList<ImageItem> dataList;
	private AlbumHelper helper;
	public static List<ImageBucket> contentList;
	public static Bitmap bitmap;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		((MainActivity)getActivity()).addTopReturnToolbar();
		((MainActivity)getActivity()).setTopReturnBarVisiability(View.VISIBLE);
		((MainActivity) getActivity()).setToolbarTitle(R.string.selectPicture);
		view = inflater.inflate(R.layout.plugin_camera_album_fragment, container, false);
		bitmap = BitmapFactory.decodeResource(getResources(),Res.getDrawableID("plugin_camera_no_pictures"));
		init();
		initListener();
		isShowOkBt();
		return view;
	}

	// 完成按钮的监听
	private class AlbumSendListener implements OnClickListener {
		public void onClick(View v) {
			Intent intent = new Intent(KeyConstants.photoBroadcastAction);
			intent.putExtra("action", "upload");
			getActivity().sendBroadcast(intent);
			getActivity().onBackPressed();
		}
	}

	// 取消按钮的监听
	private class CancelListener implements OnClickListener {
		public void onClick(View v) {
			PhotoUtils.tempSelectBitmap.clear();
			getActivity().onBackPressed();
		}
	}

	// 初始化，给一些对象赋值
	private void init() {
		helper = AlbumHelper.getHelper();
		helper.init(getContext());
		contentList = helper.getImagesBucketList(true);
		dataList = new ArrayList<ImageItem>();
		for(int i = 0; i<contentList.size(); i++){
			dataList.addAll( contentList.get(i).imageList );
		}
		cancelButton = (Button) view.findViewById(R.id.cancel_button);
		cancelButton.setOnClickListener(new CancelListener());
		gridView = (GridView) view.findViewById(R.id.myGrid);
		gridImageAdapter = new AlbumGridViewAdapter(getActivity(), dataList, PhotoUtils.tempSelectBitmap);
		gridView.setAdapter(gridImageAdapter);
		tv = (TextView) view.findViewById(R.id.myText);
		gridView.setEmptyView(tv);
		okButton = (Button) view.findViewById(R.id.ok_button);
		okButton.setText(Res.getString("finish")+"(" + PhotoUtils.tempSelectBitmap.size() + "/"+PhotoUtils.picture_max_num+")");
	}

	private void initListener() {
		gridImageAdapter.setOnItemClickListener(new AlbumGridViewAdapter.OnItemClickListener() {
				@Override
				public void onItemClick(final ToggleButton toggleButton, int position, boolean isChecked, Button chooseBt) {
					if (PhotoUtils.tempSelectBitmap.size() >= PhotoUtils.picture_available_num) {
						toggleButton.setChecked(false);
						chooseBt.setVisibility(View.GONE);
						if (!removeOneData(dataList.get(position))) {
							Toast.makeText(getActivity(), Res.getString("only_choose_num"), Toast.LENGTH_LONG).show();
						}
						return;
					}
					if (isChecked) {
						chooseBt.setVisibility(View.VISIBLE);
						PhotoUtils.tempSelectBitmap.add(dataList.get(position));
						okButton.setText(Res.getString("finish")+"(" + PhotoUtils.tempSelectBitmap.size() + "/"+PhotoUtils.picture_available_num+")");
					} else {
						PhotoUtils.tempSelectBitmap.remove(dataList.get(position));
						chooseBt.setVisibility(View.GONE);
						okButton.setText(Res.getString("finish")+"(" + PhotoUtils.tempSelectBitmap.size() + "/"+PhotoUtils.picture_available_num+")");
					}
					isShowOkBt();
				}
			});

		okButton.setOnClickListener(new AlbumSendListener());

	}

	private boolean removeOneData(ImageItem imageItem) {
			if (PhotoUtils.tempSelectBitmap.contains(imageItem)) {
				PhotoUtils.tempSelectBitmap.remove(imageItem);
				okButton.setText(Res.getString("finish")+"(" + PhotoUtils.tempSelectBitmap.size() + "/"+PhotoUtils.picture_available_num+")");
				return true;
			}
		return false;
	}

	//这个函数主要用来控制预览和完成按钮的状态
	public void isShowOkBt() {
		cancelButton.setPressed(true);
		if (PhotoUtils.tempSelectBitmap.size() > 0) {
			okButton.setText(Res.getString("finish")+"(" + PhotoUtils.tempSelectBitmap.size() + "/"+PhotoUtils.picture_available_num+")");
			okButton.setPressed(true);
			okButton.setClickable(true);
			okButton.setTextColor(Color.WHITE);
		} else {
			okButton.setText(Res.getString("finish")+"(" + PhotoUtils.tempSelectBitmap.size() + "/"+PhotoUtils.picture_available_num+")");
			okButton.setPressed(false);
			okButton.setClickable(false);
			okButton.setTextColor(Color.parseColor("#E1E0DE"));
		}
	}
}

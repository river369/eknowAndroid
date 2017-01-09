package eknow.com.eknow.service;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

import eknow.com.eknow.EnvConstants;
import eknow.com.eknow.FragmentsFactory;
import eknow.com.eknow.MainActivity;
import eknow.com.eknow.R;
import eknow.com.eknow.common.BaseFragment;
import eknow.com.eknow.common.photo.activity.AlbumActivity;
import eknow.com.eknow.common.photo.activity.GalleryActivity;
import eknow.com.eknow.common.photo.util.FileUploadTask;
import eknow.com.eknow.common.photo.util.PhotoUtils;
import eknow.com.eknow.common.photo.util.FileUtils;
import eknow.com.eknow.common.photo.util.ImageItem;
import eknow.com.eknow.common.photo.util.PublicWay;
import eknow.com.eknow.common.photo.util.Res;
import eknow.com.eknow.utils.ImageSingleton;
import eknow.com.eknow.utils.OSSUtil;

import static android.app.Activity.RESULT_OK;

/**
 * Created by jianguog on 16/11/28.
 *
 * Reference from
 * http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0731/3247.html
 */

public class ServiceAddFragment extends BaseFragment {

    View view;
    View popView;
    private GridView noScrollgridview;
    private ServiceAddGridAdapter adapter;
    private PopupWindow pop = null;
    private LinearLayout ll_popup;
    public static Bitmap bimap ;
    private List<String> remotePictures = new ArrayList<>();

    EditText serviceHourText;
    EditText servicePeopleText;
    EditText totalPriceText;


    private RequestQueue queue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity)getActivity()).addTopReturnToolbar();
        ((MainActivity)getActivity()).setTopReturnBarVisiability(View.VISIBLE);
        ((MainActivity) getActivity()).setToolbarTitle(R.string.serviceAdd);

        bimap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_addpic_unfocused);
        view = inflater.inflate(R.layout.service_add, container, false);
        popView = inflater.inflate(R.layout.select_picture_popup, container, false);
//        TagGroup mTagGroup = (TagGroup) view.findViewById(R.id.tag_group);
//        mTagGroup.setTags(new String[]{"Tag1", "Tag2", "Tag3"});

        InitPictures();
        return view;
    }

    public void InitPictures() {
        Res.init(getActivity());

        //popup
        pop = new PopupWindow(getActivity());
        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(popView);

        RelativeLayout parent = (RelativeLayout) popView.findViewById(R.id.select_picture_popup_parent);
        ll_popup = (LinearLayout) popView.findViewById(R.id.select_picture_popup);
        Button cameraButton = (Button) popView.findViewById(R.id.item_popupwindows_camera);
        Button photoButton = (Button) popView.findViewById(R.id.item_popupwindows_Photo);
        Button cancelButton = (Button) popView.findViewById(R.id.item_popupwindows_cancel);
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        cameraButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    locationpermission(CAMERA_PERMISSIONS_REQUEST_LOCATION);
                } else {
                    camera();
                }
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        photoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    locationpermission(READ_EXTERNAL_STORAGE_PERMISSIONS_REQUEST_LOCATION);
                } else {
                    //Intent intent = new Intent(getActivity(), AlbumActivity.class);
                    //startActivity(intent);
                    FragmentsFactory.getInstance().setAlbumSelectFragment(getActivity(), ServiceAddFragment.this, null);
                }
                //overridePendingTransition(R.anim.activity_translate_in, R.anim.activity_translate_out);
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });

        // Grid view
        noScrollgridview = (GridView) view.findViewById(R.id.service_upload_pictures);
        noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter = new ServiceAddGridAdapter(getActivity(), remotePictures);
        noScrollgridview.setAdapter(adapter);
        noScrollgridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                System.out.println();
                if (arg2 == remotePictures.size()) {
                    //ll_popup.startAnimation(AnimationUtils.loadAnimation(MainActivity.this,R.anim.activity_translate_in));
                    pop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                } else {
//                    Intent intent = new Intent(getActivity(), GalleryActivity.class);
//                    intent.putExtra("position", "1");
//                    intent.putExtra("ID", arg2);
//                    startActivity(intent);
                }
            }
        });

    }

    /**
     * Ask for perission
      */
    final int CAMERA_PERMISSIONS_REQUEST_LOCATION = 0;
    final int READ_EXTERNAL_STORAGE_PERMISSIONS_REQUEST_LOCATION = 1;
    private void locationpermission(int PERMISSIONS_REQUEST_LOCATION) {
        // Should we show an explanation?
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
            // Show an expanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.
        } else {
            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, PERMISSIONS_REQUEST_LOCATION);
            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case CAMERA_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    camera();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            case READ_EXTERNAL_STORAGE_PERMISSIONS_REQUEST_LOCATION : {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Intent intent = new Intent(getActivity(), AlbumActivity.class);
                    startActivity(intent);
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    /**
     * Register Receiver during crreate
     */

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        IntentFilter filter = new IntentFilter("data.broadcast.action");
        getActivity().registerReceiver(broadcastReceiver, filter);
        super.onCreate(savedInstanceState);
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            for(ImageItem imageItem : PhotoUtils.tempSelectBitmap){
                String url = EnvConstants.OSS_UPLOAD_URL + EnvConstants.OSS_PIC_OBJ+"test/" + imageItem.getImageId();
                remotePictures.add(url);
                PhotoUtils.picture_available_num--;
            }
            PhotoUtils.tempSelectBitmap.clear();
            noScrollgridview.setAdapter(adapter);
        }
    };

    /**
     *  process photos
     */
    private static final int TAKE_PICTURE = 0x000001;
    public void camera() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PICTURE:
                if (PhotoUtils.tempSelectBitmap.size() < 9 && resultCode == RESULT_OK) {
                    String fileName = String.valueOf(System.currentTimeMillis());
                    Bitmap bm = (Bitmap) data.getExtras().get("data");
                    FileUtils.saveBitmap(bm, fileName);

                    ImageItem takePhoto = new ImageItem();
                    takePhoto.setBitmap(bm);
                    PhotoUtils.tempSelectBitmap.add(takePhoto);

                    FileUploadTask task = new FileUploadTask(getActivity(), getContext());
                    task.execute();
                }
                break;
        }
    }

}
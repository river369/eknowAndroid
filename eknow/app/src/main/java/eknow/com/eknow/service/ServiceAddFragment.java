package eknow.com.eknow.service;

import android.Manifest;
import android.app.ProgressDialog;
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
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import eknow.com.eknow.EnvConstants;
import eknow.com.eknow.FragmentsFactory;
import eknow.com.eknow.KeyConstants;
import eknow.com.eknow.MainActivity;
import eknow.com.eknow.R;
import eknow.com.eknow.common.BaseFragment;
import eknow.com.eknow.common.EknowException;
import eknow.com.eknow.common.photo.util.AsyncFileTask;
import eknow.com.eknow.common.photo.util.PhotoUtils;
import eknow.com.eknow.common.photo.util.FileUtils;
import eknow.com.eknow.common.photo.util.ImageItem;
import eknow.com.eknow.common.photo.util.RemoteImageItem;
import eknow.com.eknow.common.photo.util.Res;
import eknow.com.eknow.utils.ImageSingleton;

import static android.app.Activity.RESULT_OK;

/**
 * Created by jianguog on 16/11/28.
 *
 * Reference from
 * http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0731/3247.html
 */

public class ServiceAddFragment extends BaseFragment {

    View view;
    NetworkImageView headImageView;
    private ProgressDialog dialog = null;

    String headImageURL = "";
    private GridView noScrollgridview;
    private ServiceAddGridAdapter adapter;

    private PopupWindow selectImageTypePopWindow = null;
    View selectImageTypePopView;
    //private LinearLayout ll_popup;

    private PopupWindow selectTagPopWindow = null;
    View selectTagPopView;

    public static Bitmap bimap ;
    private ArrayList<String> remotePictures = new ArrayList<>();
    private ArrayList<RemoteImageItem> updatePictures = new ArrayList<>();

    private RequestQueue queue;
    ServiceInfo serviceInfo = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity)getActivity()).addTopReturnToolbar();
        ((MainActivity)getActivity()).setTopReturnBarVisiability(View.VISIBLE);
        ((MainActivity)getActivity()).setToolbarTitle(R.string.serviceAdd);
        bimap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_addpic_unfocused);
        view = inflater.inflate(R.layout.service_add, container, false);
        selectImageTypePopView = inflater.inflate(R.layout.select_picture_popup, container, false);
        selectTagPopView = inflater.inflate(R.layout.select_tag_fragment, container, false);

        String sellerId = getArguments().getString("userId");
        getAggregatedServiceDetails(sellerId);
        showDialog();
        return view;
    }
    void showDialog(){
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("正在加载...");
        dialog.setIndeterminate(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setProgress(0);
        dialog.show();
    }

    void init(){
        Res.init(getActivity());
        InitPopupSelectImageType();
        InitMainImage();
        InitPictures();
        InitPopupTag();
    }

    public void InitPopupSelectImageType() {
        selectImageTypePopWindow = new PopupWindow(getActivity());
        selectImageTypePopWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        selectImageTypePopWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        selectImageTypePopWindow.setBackgroundDrawable(new BitmapDrawable());
        selectImageTypePopWindow.setFocusable(true);
        selectImageTypePopWindow.setOutsideTouchable(true);
        selectImageTypePopWindow.setContentView(selectImageTypePopView);
        //ll_popup = (LinearLayout) selectImageTypePopView.findViewById(R.id.select_picture_popup);
        Button cameraButton = (Button) selectImageTypePopView.findViewById(R.id.item_popupwindows_camera);
        Button photoButton = (Button) selectImageTypePopView.findViewById(R.id.item_popupwindows_Photo);
        Button cancelButton = (Button) selectImageTypePopView.findViewById(R.id.item_popupwindows_cancel);
        RelativeLayout parent = (RelativeLayout) selectImageTypePopView.findViewById(R.id.select_picture_popup_parent);
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImageTypePopWindow.dismiss();
                //ll_popup.clearAnimation();
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
                selectImageTypePopWindow.dismiss();
                //ll_popup.clearAnimation();
            }
        });
        photoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    locationpermission(READ_EXTERNAL_STORAGE_PERMISSIONS_REQUEST_LOCATION);
                } else {
                    FragmentsFactory.getInstance().setAlbumSelectFragment(getActivity(), ServiceAddFragment.this, null);
                }
                //getActivity().overridePendingTransition(R.anim.activity_translate_in, R.anim.activity_translate_out);
                selectImageTypePopWindow.dismiss();
                //ll_popup.clearAnimation();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectImageTypePopWindow.dismiss();
                //ll_popup.clearAnimation();
            }
        });
    }

    public void InitPopupTag() {
        EditText tagsCountText = (EditText) view.findViewById(R.id.service_select_tag_count);
        tagsCountText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putStringArray("tags", new String[]{"111", "22222"});
                FragmentsFactory.getInstance().setSelectTagFragment(getActivity(), ServiceAddFragment.this, bundle);
                //selectTagPopWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
            }
        });
    }


    public void InitMainImage() {
        headImageView = (NetworkImageView) view.findViewById(R.id.serviceMainImage);
        headImageView.setDefaultImageResId(R.drawable.icon_addpic_unfocused);
        headImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PhotoUtils.setSelectFor("head");
                selectImageTypePopWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
            }
        });
    }

    public void InitPictures() {
        noScrollgridview = (GridView) view.findViewById(R.id.service_upload_pictures);
        noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter = new ServiceAddGridAdapter(getActivity(), remotePictures);
        noScrollgridview.setAdapter(adapter);
        noScrollgridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if (arg2 == remotePictures.size()) {
                    //ll_popup.startAnimation(AnimationUtils.loadAnimation(MainActivity.this,R.anim.activity_translate_in));
                    PhotoUtils.setSelectFor("picture");
                    selectImageTypePopWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList(KeyConstants.remotePictures, remotePictures);
                    bundle.putInt(KeyConstants.reviewPosition, arg2);
                    FragmentsFactory.getInstance().setGalaryFragment(getActivity(), ServiceAddFragment.this, bundle);
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
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, PERMISSIONS_REQUEST_LOCATION);
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
            // Show an expanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.
        } else {
            // No explanation needed, we can request the permission.
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
                    FragmentsFactory.getInstance().setAlbumSelectFragment(getActivity(), ServiceAddFragment.this, null);
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }

    /**
     * Register Receiver during crreate
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        IntentFilter filter = new IntentFilter(KeyConstants.photoBroadcastAction);
        getActivity().registerReceiver(broadcastReceiver, filter);
        super.onCreate(savedInstanceState);
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getStringExtra("action");
            switch (action){
                case "upload":
                    if (PhotoUtils.selectFor.equalsIgnoreCase("picture")) {
                        updatePictures.clear();
                        for (ImageItem imageItem : PhotoUtils.tempSelectBitmap) {
                            String obj = EnvConstants.OSS_PIC_OBJ_PREFIX + "test/" + imageItem.getImageId();
                            RemoteImageItem remoteImageItem = new RemoteImageItem(obj, imageItem.getImagePath());
                            remotePictures.add(obj);
                            updatePictures.add(remoteImageItem);
                            PhotoUtils.picture_available_num--;
                        }
                        AsyncFileTask task = new AsyncFileTask(getActivity(), getContext());
                        task.upload(updatePictures);
                        task.execute();
                    } else if (PhotoUtils.selectFor.equalsIgnoreCase("head")) {
                        Bundle bundle = new Bundle();
                        Bitmap bm =  PhotoUtils.tempSelectBitmap.get(0).getBitmap();
                        bundle.putParcelable("bmp", bm);
                        FragmentsFactory.getInstance().setCropperFragment(getActivity(), ServiceAddFragment.this, bundle);
                    }
                    PhotoUtils.tempSelectBitmap.clear();
                    break;
                case "delete":
                    int position = intent.getIntExtra("position", -1);
                    if (position >= 0){
                        String obj = remotePictures.remove(position);
                        AsyncFileTask task = new AsyncFileTask(getActivity(), getContext());
                        task.delete(obj);
                        task.execute();
                    }
                    break;
                case "refresh":
                    noScrollgridview.setAdapter(adapter);
                    String tempUrl = headImageURL+"?t="+Math.random();
                    System.out.println(tempUrl);
                    headImageView.setImageUrl(tempUrl, ImageSingleton.getInstance().getImageLoader());
                    break;
                case "head":
                    String fileName = String.valueOf(System.currentTimeMillis());
                    Bitmap bm = (Bitmap) intent.getExtras().get("data");
                    FileUtils.saveBitmap(bm, fileName);
                    ImageItem takePhoto = new ImageItem();
                    takePhoto.setBitmap(bm);
                    String obj = EnvConstants.OSS_PIC_OBJ_PREFIX + "test/" + "head";
                    RemoteImageItem remoteImageItem = new RemoteImageItem(obj, FileUtils.SDPATH + fileName + ".JPEG");
                    updatePictures.clear();
                    updatePictures.add(remoteImageItem);
                    AsyncFileTask task = new AsyncFileTask(getActivity(), getContext());
                    task.upload(updatePictures);
                    task.execute();
                    headImageURL = EnvConstants.OSS_UPLOAD_URL +obj;
            }
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
                if (PhotoUtils.selectFor.equalsIgnoreCase("picture")) {
                    if (PhotoUtils.tempSelectBitmap.size() == 1 && resultCode == RESULT_OK) {
                        String fileName = String.valueOf(System.currentTimeMillis());
                        Bitmap bm = (Bitmap) data.getExtras().get("data");
                        FileUtils.saveBitmap(bm, fileName);
                        ImageItem takePhoto = new ImageItem();
                        takePhoto.setBitmap(bm);
                        String obj = EnvConstants.OSS_PIC_OBJ_PREFIX + "test/" + "camera";
                        remotePictures.add(obj);
                        RemoteImageItem remoteImageItem = new RemoteImageItem(obj, FileUtils.SDPATH + fileName + ".JPEG");
                        updatePictures.clear();
                        updatePictures.add(remoteImageItem);
                        AsyncFileTask task = new AsyncFileTask(getActivity(), getContext());
                        task.upload(updatePictures);
                        task.execute();
                    }
                } else if (PhotoUtils.selectFor.equalsIgnoreCase("head")) {
                    Bundle bundle = new Bundle();
                    Bitmap bm = (Bitmap) data.getExtras().get("data");
                    bundle.putParcelable("bmp", bm);
                    FragmentsFactory.getInstance().setCropperFragment(getActivity(), ServiceAddFragment.this, bundle);
                }
                break;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden) {
            //getActivity().unregisterReceiver(broadcastReceiver);
        } else {
        }
        super.onHiddenChanged(hidden);
    }
    @Override
    public void onStart(){
        super.onStart();
    }

    public void getAggregatedServiceDetails(final String sellerId) {
        queue = Volley.newRequestQueue(getActivity());
        String url = EnvConstants.API_URL;
        ServicesRequestBuilder srb = new ServicesRequestBuilder();
        Map<String, String> params = srb.buildAggregatedCreateOrUpdatePublishingService(sellerId, "");

        // Request a string response from the provided URL.
        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.POST, url,
                new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //System.out.println(response.toString());
                        try {
                            ServicesResponseJsonParser sjp = new ServicesResponseJsonParser(response);
                            AggregatedNewServiceInfo aggregatedNewServiceInfo = sjp.getAggregatedCreateOrUpdatePublishingService();
                            try {
                                init();
                                dialog.dismiss();
                            } catch (Exception e) {
                            }
                            setViewData(aggregatedNewServiceInfo);
                        } catch (EknowException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        queue.add(jsonRequest);
    }

    void setViewData(AggregatedNewServiceInfo aggregatedNewServiceInfo){
        serviceInfo = aggregatedNewServiceInfo.getServiceInfo();
        String headImageUrl = EnvConstants.getServicesMainPictureURL(serviceInfo.getSellerId(), serviceInfo.getServiceId());
        headImageView.setImageUrl(headImageUrl, ImageSingleton.getInstance().getImageLoader());

        String[] imageUrls = aggregatedNewServiceInfo.getImageUrls();
        remotePictures.clear();
        for (int i = 0; i < imageUrls.length; i++) {
            remotePictures.add(imageUrls[i]);
        }
        noScrollgridview.setAdapter(adapter);
    }

}
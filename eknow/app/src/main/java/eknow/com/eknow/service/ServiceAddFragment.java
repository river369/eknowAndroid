package eknow.com.eknow.service;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import eknow.com.eknow.KeyConstants;
import eknow.com.eknow.MainActivity;
import eknow.com.eknow.R;
import eknow.com.eknow.ValueConstants;
import eknow.com.eknow.common.BaseFragment;
import me.gujun.android.taggroup.TagGroup;

/**
 * Created by jianguog on 16/11/28.
 *
 * Reference from
 * http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0731/3247.html
 */

public class ServiceAddFragment extends BaseFragment {

    View view;
    EditText serviceHourText;
    EditText servicePeopleText;
    EditText totalPriceText;


    private RequestQueue queue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setToolbarTitle(R.string.serviceAdd);
        view = inflater.inflate(R.layout.service_add, container, false);
        TagGroup mTagGroup = (TagGroup) view.findViewById(R.id.tag_group);
        mTagGroup.setTags(new String[]{"Tag1", "Tag2", "Tag3"});
        return view;
    }


}
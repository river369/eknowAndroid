package eknow.com.eknow.service;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

import eknow.com.eknow.EnvConstants;
import eknow.com.eknow.KeyConstants;
import eknow.com.eknow.R;
import eknow.com.eknow.ValueConstants;
import eknow.com.eknow.common.BaseFragment;
import eknow.com.eknow.common.EknowException;
import eknow.com.eknow.common.ui.SlideShowView;

/**
 * Created by jianguog on 16/11/28.
 *
 * Reference from
 * http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0731/3247.html
 */

public class ServiceBuyFragment extends BaseFragment {

    View view;

    private RequestQueue queue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((ServicesActivity) getActivity()).setToolbarTitle(R.string.serviceBuySummary);
        view = inflater.inflate(R.layout.service_buy, container, false);

        final ServiceInfo serviceInfo = (ServiceInfo) getArguments().getSerializable(KeyConstants.sellerInfo);

        if (serviceInfo != null) {
            TextView idText = (TextView) view.findViewById(R.id.service_id_content);
            idText.setText(serviceInfo.getServiceId());
            TextView nameText = (TextView) view.findViewById(R.id.service_name_content);
            nameText.setText(serviceInfo.getServiceName());
            TextView areaText = (TextView) view.findViewById(R.id.service_area_content);
            areaText.setText(serviceInfo.getServiceArea());
            TextView typeText = (TextView) view.findViewById(R.id.service_type_content);
            typeText.setText(ValueConstants.serviceType[serviceInfo.getServiceType()]);
            TextView languageText = (TextView) view.findViewById(R.id.service_language_content);
            languageText.setText(serviceInfo.getServiceLanguage());
            String servicePriceType = serviceInfo.getService_price_type() == 1 ? "/小时" : "/次";
            String servicePrice = String.valueOf(serviceInfo.getService_price()) + servicePriceType;
            TextView priceText = (TextView) view.findViewById(R.id.service_price_content);
            priceText.setText(servicePrice);
            EditText totalPriceText = (EditText) view.findViewById(R.id.service_total_price_content);
            totalPriceText.setText(String.valueOf(serviceInfo.getService_price()));

//            DatePicker datePicker = (DatePicker) view.findViewById(R.id.service_date_content);
//            long currentDate = System.currentTimeMillis();
//            datePicker.setMinDate(currentDate);
//            datePicker.setMaxDate(currentDate + 90 * 24 * 3600 * 1000);
            final EditText serviceDateEtxt = (EditText) view.findViewById(R.id.service_date_content);
            serviceDateEtxt.setInputType(InputType.TYPE_NULL);
            serviceDateEtxt.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(MotionEvent.ACTION_UP == event.getAction()) {
                        Calendar newCalendar = Calendar.getInstance();
                        DatePickerDialog serviceDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                Calendar newDate = Calendar.getInstance();
                                newDate.set(year, monthOfYear, dayOfMonth);
                                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                                serviceDateEtxt.setText(dateFormatter.format(newDate.getTime()));
                            }

                        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                        serviceDatePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                        //serviceDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() + (900 * 24 * 3600 * 1000) );
                        serviceDatePickerDialog.show();
                        //Toast.makeText(getActivity(), "ddd", Toast.LENGTH_LONG).show();
                    }

                    return true;
                }
            });
        }
        return view;
    }
}
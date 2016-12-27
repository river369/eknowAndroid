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
import android.widget.Toast;

import com.android.volley.RequestQueue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import eknow.com.eknow.KeyConstants;
import eknow.com.eknow.MainActivity;
import eknow.com.eknow.R;
import eknow.com.eknow.ValueConstants;
import eknow.com.eknow.common.BaseFragment;

/**
 * Created by jianguog on 16/11/28.
 *
 * Reference from
 * http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0731/3247.html
 */

public class ServiceBuyFragment extends BaseFragment {

    View view;
    EditText serviceHourText;
    EditText servicePeopleText;
    EditText totalPriceText;


    private RequestQueue queue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setToolbarTitle(R.string.serviceBuySummary);
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

            TextView serviceHoursTitleText = (TextView) view.findViewById(R.id.service_hour_title);
            serviceHourText = (EditText) view.findViewById(R.id.service_hour_content);
            serviceHourText.addTextChangedListener(new TextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    modifyTotalPrice(serviceInfo.getService_price());
                }
            });
            Button serviceHourAddButton = (Button) view.findViewById(R.id.service_hour_add);
            serviceHourAddButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    modifyHour(serviceHourText, "add");
                }
            });
            Button serviceHourMinusButton = (Button) view.findViewById(R.id.service_hour_minus);
            serviceHourMinusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    modifyHour(serviceHourText, "minus");
                }
            });

            if(serviceInfo.getService_price_type() == 2){
                serviceHoursTitleText.setVisibility(View.INVISIBLE);
                serviceHourText.setVisibility(View.INVISIBLE);
                serviceHourAddButton.setVisibility(View.INVISIBLE);
                serviceHourMinusButton.setVisibility(View.INVISIBLE);
                TextView totalPriceTitle = (TextView) view.findViewById(R.id.service_total_price_title);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)totalPriceTitle.getLayoutParams();
                params.addRule(RelativeLayout.BELOW, R.id.service_price_content);
                totalPriceTitle.setLayoutParams(params);
            }

            totalPriceText = (EditText) view.findViewById(R.id.service_total_price_content);
            totalPriceText.setText(String.valueOf(serviceInfo.getService_price()));

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


        servicePeopleText = (EditText) view.findViewById(R.id.service_people_content);
        Button servicePeopleAddButton = (Button) view.findViewById(R.id.service_people_add);
        servicePeopleAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                modifyHour(servicePeopleText, "add");
            }
        });
        Button servicePeopleMinusButton = (Button) view.findViewById(R.id.service_people_minus);
        servicePeopleMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                modifyHour(servicePeopleText, "minus");
            }
        });
        return view;
    }

    void modifyHour(EditText editText, String action) {
        String hourString = editText.getText().toString();
        int hourInt = Integer.parseInt(hourString);
        if (action.equalsIgnoreCase("add")){
            hourInt++;
        } else if (action.equalsIgnoreCase("minus")){
            hourInt--;
        }
        if (hourInt > 0){
            editText.setText(String.valueOf(hourInt));
        } else {
            editText.setText(hourString);
        }
    }

    void modifyTotalPrice(double price){
        String hourString = serviceHourText.getText().toString();
        int hourInt = 1;
        try {
            hourInt = Integer.parseInt(hourString);
        } catch (Exception e){
            serviceHourText.setText("1");
        }
        if (hourInt <= 0){
            hourInt = 1;
            serviceHourText.setText("1");
        }
        double priceTotal = price * hourInt;
        totalPriceText.setText(String.valueOf(priceTotal));
    }
}
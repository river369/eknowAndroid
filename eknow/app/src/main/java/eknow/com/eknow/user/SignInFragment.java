package eknow.com.eknow.user;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import eknow.com.eknow.EnvConstants;
import eknow.com.eknow.FragmentsFactory;
import eknow.com.eknow.MainActivity;
import eknow.com.eknow.R;
import eknow.com.eknow.common.BaseFragment;

import eknow.com.eknow.common.EknowException;
import eknow.com.eknow.service.ServiceInfo;
import eknow.com.eknow.service.ServicesRequestBuilder;
import eknow.com.eknow.service.ServicesResponseJsonParser;
import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.annotations.DateInFuture;
import eu.inmite.android.lib.validations.form.annotations.Joined;
import eu.inmite.android.lib.validations.form.annotations.MinLength;
import eu.inmite.android.lib.validations.form.annotations.MinValue;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import eu.inmite.android.lib.validations.form.annotations.RegExp;
import eu.inmite.android.lib.validations.form.callback.SimpleErrorPopupCallback;

import static eu.inmite.android.lib.validations.form.annotations.RegExp.EMAIL;

public class SignInFragment extends BaseFragment {
    View view;
    @NotEmpty(messageId = R.string.validation_phone, order = 1)
    @MinLength(value = 6, messageId = R.string.validation_phone_length, order = 1)
    EditText phoneNumberText;

    @NotEmpty(messageId = R.string.validation_password, order = 2)
    EditText passwordText;

    EditText phoneRegionText;
    TextView errorMessageText;

    private RequestQueue queue;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // header
        ((MainActivity)getActivity()).addTopReturnToolbar();
        ((MainActivity)getActivity()).setTopReturnBarVisiability(View.VISIBLE);
        ((MainActivity)getActivity()).setToolbarTitle(R.string.signIn);

        // set phone region selection
        view = inflater.inflate(R.layout.sign_in, container, false);
        phoneRegionText = (EditText) view.findViewById(R.id.signin_phone_region);
        phoneRegionText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new SelectPhoneRegionDialog(getActivity(), R.style.MyDialog, phoneRegionText);
                dialog.show();
            }
        });

        //  basic fileds
        phoneNumberText = (EditText) view.findViewById(R.id.signin_phone_number);
        passwordText = (EditText) view.findViewById(R.id.signin_password);
        errorMessageText = (TextView) view.findViewById(R.id.errorMessages);
        // set verification button
        queue = Volley.newRequestQueue(getActivity());
        Button signInButton = (android.widget.Button) view.findViewById(R.id.signin);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isValid = validate();
                System.out.println(isValid);
                if (isValid) {
                    signIn();
                }
            }
        });

        // Jump to other pages
        //phoneNumber.setError();
        TextView signOn = (TextView) view.findViewById(R.id.signOn);
        signOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentsFactory.getInstance().setSignOnFragment(getActivity(), SignInFragment.this, null);
            }
        });
        //FormValidator.startLiveValidation(this, view, new SimpleErrorPopupCallback(getActivity(), false));

        return view;
    }
    private boolean validate() {
        final boolean isValid = FormValidator.validate(this, new SimpleErrorPopupCallback(getActivity(), true));
        return isValid;
    }


    public void signIn() {
        String url = EnvConstants.API_URL;

        String userType = "1";
        String phoneRegion = phoneRegionText.getText().toString();
        phoneRegion = phoneRegion.substring(phoneRegion.indexOf("+"));
        String phoneNumber = phoneNumberText.getText().toString();
        String password = passwordText.getText().toString();
        UserAccessInfo userAccessInfo = new UserAccessInfo(userType, phoneRegion, phoneNumber, password);
        UsersRequestBuilder urb = new UsersRequestBuilder();
        Map<String, String> params = urb.buildSignInRequestParameters(userAccessInfo);

        // Request a string response from the provided URL.
        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.POST, url,
                new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //System.out.println(response.toString());
                        try {
                            UsersResponseJsonParser ujp = new UsersResponseJsonParser(response);
                            int ret = ujp.getCode();
                            if(ret == 0) {
                                FragmentsFactory.setFragmentTo(getActivity(),((MainActivity)getActivity()).getCurrentFragment());
                            } else {
                                errorMessageText.setText(ujp.getMsg());
                            }

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
}

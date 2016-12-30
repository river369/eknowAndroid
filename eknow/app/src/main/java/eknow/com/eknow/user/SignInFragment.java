package eknow.com.eknow.user;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import eknow.com.eknow.FragmentsFactory;
import eknow.com.eknow.MainActivity;
import eknow.com.eknow.R;
import eknow.com.eknow.common.BaseFragment;

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
    EditText phoneNumber;

    @NotEmpty(messageId = R.string.validation_password, order = 2)
    EditText password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        ((MainActivity)getActivity()).addTopReturnToolbar();
        ((MainActivity)getActivity()).setTopReturnBarVisiability(View.VISIBLE);
        ((MainActivity)getActivity()).setToolbarTitle(R.string.signIn);

        view = inflater.inflate(R.layout.sign_in, container, false);

        Button signInButton = (android.widget.Button) view.findViewById(R.id.signin);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isValid = validate();
                System.out.println(isValid);
                if (isValid) {
                    FragmentsFactory.setFragmentTo(getActivity(),((MainActivity)getActivity()).getCurrentFragment());
                } else {

                }
            }
        });

        phoneNumber = (EditText) view.findViewById(R.id.signin_phone_number);
        password = (EditText) view.findViewById(R.id.signin_password);
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
}

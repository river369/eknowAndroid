package eknow.com.eknow.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import eknow.com.eknow.FragmentsFactory;
import eknow.com.eknow.MainActivity;
import eknow.com.eknow.R;
import eknow.com.eknow.common.BaseFragment;

public class SignInFragment extends BaseFragment {
    View view;

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
                FragmentsFactory.setFragmentTo(getActivity(),((MainActivity)getActivity()).getCurrentFragment());
            }
        });

        TextView signOn = (TextView) view.findViewById(R.id.signOn);
        signOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentsFactory.getInstance().setSignOnFragment(getActivity(), SignInFragment.this, null);
            }
        });
        return view;
    }
}

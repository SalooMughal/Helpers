package com.example.salman.uol_helper;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {


    EditText rname;
    EditText remail;
    EditText rpassword;
    Button login;
    Button signup;

    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_signup, container, false);
        rname = (EditText) v.findViewById(R.id.rname);
        remail = (EditText) v.findViewById(R.id.lemail);
        rpassword = (EditText) v.findViewById(R.id.lpassword);
        login = (Button) v.findViewById(R.id.rlogin);
        signup = (Button) v.findViewById(R.id.llogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                LoginFragment blankFragment = new LoginFragment();
                fragmentTransaction.replace(android.R.id.content, blankFragment).commit();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = rname.getText().toString();
                String email = remail.getText().toString();
                String pass = rpassword.getText().toString();

                Signup_Async custom_asynctask = new Signup_Async(getActivity());
                custom_asynctask.execute(name, email, pass);
            }
        });
        return v;
    }

}

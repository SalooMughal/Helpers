package com.example.salman.uol_helper;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    EditText ed1, ed2;
    Button llogin;
    Button lsignup;
    SharedPreferences sharedPreferences;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.login_fragment, container, false);
        ed1 = (EditText) v.findViewById(R.id.lemail);
        ed2 = (EditText) v.findViewById(R.id.lpassword);
        llogin = (Button) v.findViewById(R.id.llogin);
        lsignup = (Button) v.findViewById(R.id.lsignup);
        sharedPreferences = getActivity().getSharedPreferences("mypreference2", Context.MODE_PRIVATE);
        final String a = sharedPreferences.getString("email", null);
        final String b = sharedPreferences.getString("password", null);
        if (a != null && b != null) {
            Intent intent = new Intent(getActivity(), Main2Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            getActivity().startActivity(intent);
        } else {


            lsignup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    SignupFragment blankFragment = new SignupFragment();
                    fragmentTransaction.replace(android.R.id.content, blankFragment).commit();

                }
            });
            llogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = ed1.getText().toString();
                    String pass = ed2.getText().toString();
                    if (email.equals(null)|| pass.equals(null))
                    {
                        Toast.makeText(getActivity(),"Plz fill empty field",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Login_Async custom_asynctask = new Login_Async(getActivity());
                        custom_asynctask.execute(email, pass);
                    }


                }
            });
        }
        return v;
    }

}

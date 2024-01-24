package com.example.foodplanner.ui.authentication.Controller;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;

public class SignUpFragment extends Fragment {

    private TextView login;
    private TextInputEditText etEmail ;
    private TextInputEditText etPassword;
    private Button signUp;
    private int requestCode;
    private int resultCode;
    private Intent data;

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }
    private AuthenticationController authenticationController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_sign_up, container, false);
        login=view.findViewById(R.id.tv_login);
        etEmail=view.findViewById(R.id.your_et_email);
        etPassword=view.findViewById(R.id.your_pass);
        signUp=view.findViewById(R.id.btn_signup);
        login.setOnClickListener(v -> {
            Navigation.findNavController(requireView())
                    .navigate(R.id.action_signUpFragment_to_loginFragment);
        });
        signUp.setOnClickListener(v -> {
            signUp();
        });
        return view;
    }

    private void signUp() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        AuthenticationController.getInstance().signUp(email, password, task -> {
            if (task.isSuccessful()) {
                Toast.makeText(requireContext(), "Signup Successful", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Signup Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    // In your onClickListener or wherever you want to trigger Google Sign In


}
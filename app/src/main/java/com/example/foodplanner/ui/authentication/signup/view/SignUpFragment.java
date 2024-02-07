package com.example.foodplanner.ui.authentication.signup.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.foodplanner.R;
import com.example.foodplanner.model.firebase.AuthRepositoryImp;
import com.example.foodplanner.ui.authentication.signup.presenter.SignUpPresenterImp;
import com.google.android.material.textfield.TextInputEditText;

public class SignUpFragment extends Fragment implements SignUp {

    private TextView login;
    private TextInputEditText etEmail ;
    private TextInputEditText etPassword;
    private Button signUp;

    // Declare presenter
    private SignUpPresenterImp presenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_sign_up, container, false);
        login = view.findViewById(R.id.tv_login);
        etEmail = view.findViewById(R.id.your_et_email);
        etPassword = view.findViewById(R.id.your_pass);
        signUp = view.findViewById(R.id.btn_signup);

        // Initialize presenter
        presenter = new SignUpPresenterImp(new AuthRepositoryImp(requireContext()),this);

        login.setOnClickListener(v -> {
            Navigation.findNavController(requireView())
                    .navigate(R.id.action_signUpFragment_to_loginFragment);
        });

        signUp.setOnClickListener(v -> {

            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            if (isValidEmail(email) && isValidPassword(password)) {
                presenter.signUpWithEmail(email, password);
            }
        });
        return view;
    }
    private boolean isValidEmail(String email) {
        if (email.isEmpty()) {
            etEmail.setError("Email is required");
            return false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Invalid email");
            return false;
        }
        return true;
    }
    private boolean isValidPassword(String password) {
        if (password.isEmpty()) {
            etPassword.setError("Password is required");
            return false;
        } else if (password.length() < 6) {
            etPassword.setError("Password should be at least 6 characters long");
            return false;
        }
        return true;
    }
    @Override
    public void showLoading() {
        // Show loading indicator
    }

    @Override
    public void hideLoading() {
        // Hide loading indicator
    }

    @Override
    public void showError(String error) {
        Toast.makeText(requireActivity(), "failed ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToHome() {
        Toast.makeText(requireActivity(), "done", Toast.LENGTH_SHORT).show();
    }
}

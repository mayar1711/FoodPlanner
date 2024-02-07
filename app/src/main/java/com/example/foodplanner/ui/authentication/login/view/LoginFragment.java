package com.example.foodplanner.ui.authentication.login.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.foodplanner.R;
import com.example.foodplanner.model.firebase.AuthRepositoryImp;
import com.example.foodplanner.ui.authentication.login.Presenter.LoginPresenterImp;
import com.google.android.material.textfield.TextInputEditText;

public class LoginFragment extends Fragment implements LoginView {

    private TextInputEditText etEmail;
    private TextInputEditText etPassword;
    private Button loginButton;

    // Declare presenter
    private LoginPresenterImp presenter;
    private TextView skip;
    private TextView signUp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        etEmail = view.findViewById(R.id.email);
        etPassword = view.findViewById(R.id.pas);
        loginButton = view.findViewById(R.id.btn_login);
        skip=view.findViewById(R.id.textView);
        signUp=view.findViewById(R.id.tv_signUp);
        signUp.setOnClickListener(v -> {
            Navigation.findNavController(requireView()).navigate(R.id.action_lginFragment_to_signUpFragment);
        });
        presenter = new LoginPresenterImp( new AuthRepositoryImp(requireContext()),this);
        loginButton.setOnClickListener(v -> {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

            if (isValidEmail(email) && isValidPassword(password)) {
                // Call presenter to log in
                presenter.signInWithEmail(email, password);
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
        Toast.makeText(requireActivity(), "field", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToHome() {
        Toast.makeText(requireActivity(), "done", Toast.LENGTH_SHORT).show();
    }
}

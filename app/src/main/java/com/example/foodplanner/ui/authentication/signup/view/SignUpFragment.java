package com.example.foodplanner.ui.authentication.signup.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.foodplanner.R;
import com.example.foodplanner.model.firebase.AuthListener;
import com.example.foodplanner.model.firebase.AuthRepositoryImp;
import com.example.foodplanner.ui.HomeActivity;
import com.example.foodplanner.ui.authentication.signup.presenter.SignUpPresenterImp;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpFragment extends Fragment implements SignUp , AuthListener {

    private TextView login;
    private TextInputEditText etEmail ;
    private TextInputEditText etPassword;
    private Button signUp;
    private SignUpPresenterImp presenter;
    private CardView google;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_sign_up, container, false);
        login = view.findViewById(R.id.tv_login);
        etEmail = view.findViewById(R.id.your_et_email);
        etPassword = view.findViewById(R.id.your_pass);
        signUp = view.findViewById(R.id.btn_signup);
        google=view.findViewById(R.id.google_auth);
        AuthRepositoryImp authRepository = new AuthRepositoryImp(requireContext(), requireActivity(), this);
        presenter = new SignUpPresenterImp(authRepository, this);
        // Initialize presenter
        login.setOnClickListener(v -> {
            Navigation.findNavController(requireView())
                    .navigate(R.id.action_signUpFragment_to_loginFragment);
        });
        google.setOnClickListener(v -> {
            presenter.signUpWithGoogle();

        });

        signUp.setOnClickListener(v -> {

            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            if (isValidEmail(email) && isValidPassword(password)) {
                presenter.signUpWithEmail(email, password);
                saveUserEmailToDatabase(email);
            }
        });
        return view;
    }
    private void saveUserEmailToDatabase(String email) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
        String encodedEmail = encodeEmailForFirebase(email);
        databaseReference.child(encodedEmail).setValue(email)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(requireActivity(), "User email saved successfully", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(requireActivity(), "Failed to save user email: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private String encodeEmailForFirebase(String email) {
      return email.replace(".", ",");
    }
    private String getKeyFromEmail(TextInputEditText emailEditText) {
        String email = emailEditText.getText().toString().trim();
        return email.replace(".", ",");
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
        Toast.makeText(requireActivity(), "Successful ", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(requireActivity(), HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFailure(String error) {
        Toast.makeText(requireActivity(), "failed ", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onSuccess() {
        Toast.makeText(requireActivity(), "Signup Successful", Toast.LENGTH_SHORT).show();
        // Call saveUserEmailToDatabase after successful signup
       // saveUserEmailToDatabase(etEmail.getText().toString().trim());
    }
}

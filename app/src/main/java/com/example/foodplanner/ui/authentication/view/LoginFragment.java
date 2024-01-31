package com.example.foodplanner.ui.authentication.view;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.foodplanner.R;
import com.example.foodplanner.ui.authentication.Controller.AuthenticationController;
import com.google.android.material.textfield.TextInputEditText;

public class LoginFragment extends Fragment {

    private TextView signUp;
    private TextInputEditText pass;
    private TextInputEditText tvEmail;
    private Button login;
    AuthenticationController authenticationController = AuthenticationController.getInstance();
    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        tvEmail= view.findViewById(R.id.email);
        pass=view.findViewById(R.id.pas);
        signUp=view.findViewById(R.id.tv_signUp);
        signUp.setOnClickListener(v -> {
            Navigation.findNavController(requireView())
                    .navigate(R.id.action_lginFragment_to_signUpFragment);
        });
        login=view.findViewById(R.id.btn_login);
        login.setOnClickListener(v -> {
            signIn();
        });
        return view;
    }
  private void signIn() {
        String email = tvEmail.getText().toString();
        String password = pass.getText().toString();
                authenticationController.signIn(email, password, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(requireActivity(), "Login Successful ", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(requireActivity(), "Field", Toast.LENGTH_SHORT).show();
                        Exception exception = task.getException();
                    }
                });
    }
}
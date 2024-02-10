package com.example.foodplanner.ui.authentication.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.foodplanner.R;
import com.example.foodplanner.model.firebase.AuthListener;
import com.example.foodplanner.model.firebase.AuthRepositoryImp;
import com.example.foodplanner.model.firebase.EmailListener;
import com.example.foodplanner.model.firebase.FirebaseDatabaseHelper;
import com.example.foodplanner.ui.HomeActivity;
import com.example.foodplanner.ui.authentication.login.Presenter.LoginPresenterImp;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginFragment extends Fragment implements LoginView , AuthListener {

    private TextInputEditText etEmail;
    private TextInputEditText etPassword;
    private Button loginButton;

    // Declare presenter
    private LoginPresenterImp presenter;
    private TextView skip;
    private TextView signUp;
     private CardView googol;
    String email;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        etEmail = view.findViewById(R.id.email);
        etPassword = view.findViewById(R.id.pas);
        loginButton = view.findViewById(R.id.btn_login);
        skip=view.findViewById(R.id.textView);
        signUp=view.findViewById(R.id.tv_signUp);
        AuthRepositoryImp authRepository = new AuthRepositoryImp(requireContext(), requireActivity(), this);
        presenter = new LoginPresenterImp(authRepository, this);

        skip.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), HomeActivity.class);
            startActivity(intent);

        });
        googol=view.findViewById(R.id.google_auth);
        signUp.setOnClickListener(v -> {
            Navigation.findNavController(requireView()).navigate(R.id.action_lginFragment_to_signUpFragment);
        });
        loginButton.setOnClickListener(v -> {
            email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (isValidEmail(email) && isValidPassword(password)) {
                presenter.signInWithEmail(email, password);
                getUserEmail(email, email -> Log.i("TAG", "onEmailReceived: " + email));
                String name = encodeEmailForFirebase(email);
                new FirebaseDatabaseHelper().getAllFavorite(name,requireContext());
                new FirebaseDatabaseHelper().getAllFavoriteWeelPlan(name , requireContext());
            }
        });
        return view;
    }
    public void getUserEmail(String email, EmailListener listener) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
        String encodedEmail = encodeEmailForFirebase(email);
        databaseReference.child(encodedEmail).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String email = dataSnapshot.getValue(String.class);
                listener.onEmailReceived(email);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private String encodeEmailForFirebase(String email) {

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
        }
        return true;
    }


    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void showError(String error) {
        Toast.makeText(requireActivity(), "field", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void navigateToHome() {
        Toast.makeText(requireActivity(), "successful ", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(requireActivity(), HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailure(String error) {

    }


}

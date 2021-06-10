package com.user.fadhlanhadaina.app_sekolah.presentation.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.user.fadhlanhadaina.app_sekolah.R;
import com.user.fadhlanhadaina.app_sekolah.core.domain.model.User;
import com.user.fadhlanhadaina.app_sekolah.databinding.ActivityLoginBinding;
import com.user.fadhlanhadaina.app_sekolah.presentation.presenter.viewmodel.LoginViewModel;
import com.user.fadhlanhadaina.app_sekolah.presentation.ui.admin.AdminDashboardActivity;
import com.user.fadhlanhadaina.app_sekolah.presentation.ui.user.UserDashboardActivity;

import java.util.Objects;
import java.util.Observable;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static com.user.fadhlanhadaina.app_sekolah.core.util.Constant.TAG_LOGIN;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        initViewModel();
        loadData();
    }

    private void loadData() {
        MutableLiveData<User> liveDataUser = new MutableLiveData<>();
        Disposable disposable = viewModel.getSession()
            .subscribeOn(Schedulers.io())
            .subscribe(liveDataUser::postValue, Throwable::printStackTrace);

        liveDataUser.observe(this, user -> {
            Log.d("user getsess", user.userId + " " + user.role);
            if (user.userId == null) {
                runOnUiThread(() -> {
                    initView();
                    initListener();
                });
            }
            else {
                if(user.role.equals("admin"))
                    routeToAdminSession();
                else
                    routeToUserSession(user.nis);
            }

            disposable.dispose();
        });
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
    }

    private void initView() {
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();
    }

    private void initListener() {
        binding.loginBtn.setOnClickListener(l -> {
            performLogin();
        });
    }

    private void performLogin() {
        binding.loginProgress.setVisibility(View.VISIBLE);
        binding.loginBtn.setEnabled(false);

        String usernis = binding.nisUsernameInput.getText().toString();
        String userpass = binding.passwordInput.getText().toString();
        viewModel.login(usernis, userpass).observe(this, resource -> {
            if(resource.data != null) {
                User user = resource.data;
                if(user.role.equals("admin"))
                    routeToAdminSession();
                else {
                    routeToUserSession(user.nis);
                }

                viewModel.storeSession(user);

                Log.d("Login tidak null", user.userId+" "+user.username+" "+user.role);
            }
            else {
                binding.loginBtn.setEnabled(true);
                Snackbar.make(binding.loginBtn, resource.message, Snackbar.LENGTH_LONG).show();

                Log.e("Login data null", resource.message);
            }
            Log.d("Ongegeg", "Accessed");

            binding.loginProgress.setVisibility(View.GONE);
        });
    }

    private void routeToAdminSession() {
        startActivity(new Intent(this, AdminDashboardActivity.class));
        finish();
    }

    private void routeToUserSession(String nis) {
        startActivity(new Intent(this, UserDashboardActivity.class)
                .putExtra(UserDashboardActivity.EXTRA_NIS, nis));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("Login destroyed", "bye");
    }
}
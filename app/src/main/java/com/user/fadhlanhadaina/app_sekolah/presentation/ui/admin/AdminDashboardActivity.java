package com.user.fadhlanhadaina.app_sekolah.presentation.ui.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.user.fadhlanhadaina.app_sekolah.R;
import com.user.fadhlanhadaina.app_sekolah.databinding.ActivityAdminDashboardBinding;
import com.user.fadhlanhadaina.app_sekolah.presentation.presenter.viewmodel.AdminViewModel;
import com.user.fadhlanhadaina.app_sekolah.presentation.ui.LoginActivity;
import com.user.fadhlanhadaina.app_sekolah.presentation.ui.admin.mapel.MapelActivity;
import com.user.fadhlanhadaina.app_sekolah.presentation.ui.admin.nilai.NilaiActivity;
import com.user.fadhlanhadaina.app_sekolah.presentation.ui.admin.siswa.SiswaActivity;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

import static com.user.fadhlanhadaina.app_sekolah.core.util.Constant.TAG_LOGIN;

@AndroidEntryPoint
public class AdminDashboardActivity extends AppCompatActivity {
    private ActivityAdminDashboardBinding binding;
    private AdminViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initActionBar();
        initViewModel();
        initData();
        initListener();
    }

    private void initActionBar() {
        Objects.requireNonNull(getSupportActionBar()).hide();
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(AdminViewModel.class);
    }

    private void initData() {
        viewModel.getSession().subscribe(user -> {
            binding.adminProfileUserId.setText(getString(R.string.userid_1_s, user.userId));
            binding.adminProfileUsername.setText(getString(R.string.username_s, user.username));
        }, t -> {
            Log.e(TAG_LOGIN, t.getMessage());
        });
    }

    private void initListener() {
        binding.mapelCard.setOnClickListener(v -> {
            startActivity(new Intent(this, MapelActivity.class));
        });

        binding.siswaCard.setOnClickListener(v -> {
            startActivity(new Intent(this, SiswaActivity.class));
        });

        binding.nilaiCard.setOnClickListener(v -> {
            startActivity(new Intent(this, NilaiActivity.class));
        });

        binding.adminLogoutCard.setOnClickListener(v -> {
            performLogout();
        });
    }

    private void performLogout() {
        viewModel.clearSession().observe(this, aBoolean -> {
            routeToLogin();
        });

    }

    private void routeToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
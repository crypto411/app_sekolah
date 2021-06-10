package com.user.fadhlanhadaina.app_sekolah.presentation.ui.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.user.fadhlanhadaina.app_sekolah.R;
import com.user.fadhlanhadaina.app_sekolah.core.domain.model.Siswa;
import com.user.fadhlanhadaina.app_sekolah.databinding.ActivityUserDashboardBinding;
import com.user.fadhlanhadaina.app_sekolah.presentation.presenter.viewmodel.UserViewModel;
import com.user.fadhlanhadaina.app_sekolah.presentation.ui.LoginActivity;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.disposables.Disposable;

@AndroidEntryPoint
public class UserDashboardActivity extends AppCompatActivity {
    private ActivityUserDashboardBinding binding;
    private UserViewModel viewModel;
    private String nis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();
        nis = getIntent().getStringExtra(EXTRA_NIS);

        initViewModel();
        initListener();
        loadData();
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
    }

    private void initListener() {
        binding.nilaiSiswaCard.setOnClickListener(v -> {
            startActivity(new Intent(this, NilaiUserActivity.class).putExtra(EXTRA_NIS, nis));
        });

        binding.userLogoutCard.setOnClickListener(v -> {
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

    private void loadData() {
        binding.userDashboardProgress.setVisibility(View.VISIBLE);

        Log.d("sess", nis);

        MutableLiveData<Siswa> liveData = new MutableLiveData<>();
        Disposable d = viewModel.getSiswa(nis).subscribe(
            liveData::postValue,
            Throwable::printStackTrace,
            () -> {
                runOnUiThread(() ->
                    binding.userDashboardProgress.setVisibility(View.GONE)
                );
            }
        );
        liveData.observe(this, siswa -> {
            binding.userProfileNisName.setText(
                    getString(R.string.s_s, siswa.getNis(), siswa.getNama())
            );
            binding.userProfileKelamin.setText(
                    siswa.getJenKel().equals("L") ? "Laki-laki" : "Perempuan"
            );
            binding.userProfileAlamat.setText(
                    getString(R.string._s, siswa.getAlamat())
            );
            d.dispose();
        });
    }

    public static final String EXTRA_NIS = "extra_nis";
}
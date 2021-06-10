package com.user.fadhlanhadaina.app_sekolah.presentation.ui.admin.nilai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.user.fadhlanhadaina.app_sekolah.databinding.ActivityNilaiBinding;
import com.user.fadhlanhadaina.app_sekolah.presentation.presenter.viewmodel.AdminViewModel;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class NilaiActivity extends AppCompatActivity {
    private ActivityNilaiBinding binding;
    private AdminViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNilaiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initActionBar();
        initViewModel();
        initFragment();
    }

    private void initActionBar() {
        setTitle("Nilai");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(AdminViewModel.class);
    }

    private void initFragment() {
        getSupportFragmentManager().beginTransaction()
            .replace(binding.frameNilai.getId(), SelectSiswaFragment.newInstance(viewModel), SelectSiswaFragment.class.getSimpleName())
            .commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        }
        else {
            onBackPressed();
        }
        return super.onSupportNavigateUp();
    }
}
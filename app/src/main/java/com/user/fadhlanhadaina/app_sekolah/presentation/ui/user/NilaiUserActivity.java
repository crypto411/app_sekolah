package com.user.fadhlanhadaina.app_sekolah.presentation.ui.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.user.fadhlanhadaina.app_sekolah.R;
import com.user.fadhlanhadaina.app_sekolah.core.domain.model.Nilai;
import com.user.fadhlanhadaina.app_sekolah.databinding.ActivityNilaiUserBinding;
import com.user.fadhlanhadaina.app_sekolah.presentation.presenter.adapter.NilaiUserAdapter;
import com.user.fadhlanhadaina.app_sekolah.presentation.presenter.viewmodel.UserViewModel;

import org.reactivestreams.Subscription;

import java.util.List;
import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.schedulers.Schedulers;

@AndroidEntryPoint
public class NilaiUserActivity extends AppCompatActivity {
    private ActivityNilaiUserBinding binding;
    private NilaiUserAdapter adapter;
    private UserViewModel viewModel;
    private String nis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNilaiUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setTitle("Nilai");
        nis = getIntent().getStringExtra(UserDashboardActivity.EXTRA_NIS);

        initActionBar();
        initViewModel();
        initAdapter();
        loadData();
    }

    private void initActionBar() {
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
    }

    private void initAdapter() {
        adapter = new NilaiUserAdapter();
        binding.nilaiUserRv.setLayoutManager(new LinearLayoutManager(this));
        binding.nilaiUserRv.setAdapter(adapter);
    }

    private void loadData() {
        binding.nilaiUserProgress.setVisibility(View.VISIBLE);

        viewModel.getNilaiSiswa(nis).subscribeOn(Schedulers.io()).subscribe(
            list -> runOnUiThread(() ->
                adapter.setList(list)),
            Throwable::printStackTrace,
            () ->
                runOnUiThread(() ->
                    binding.nilaiUserProgress.setVisibility(View.GONE)
                )
        ).isDisposed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
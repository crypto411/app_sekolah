package com.user.fadhlanhadaina.app_sekolah.presentation.ui.admin.mapel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.user.fadhlanhadaina.app_sekolah.R;
import com.user.fadhlanhadaina.app_sekolah.databinding.ActivityMapelBinding;
import com.user.fadhlanhadaina.app_sekolah.presentation.presenter.adapter.MapelAdapter;
import com.user.fadhlanhadaina.app_sekolah.presentation.presenter.viewmodel.AdminViewModel;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

import static com.user.fadhlanhadaina.app_sekolah.core.util.Constant.TAG_ADMIN;

@AndroidEntryPoint
public class MapelActivity extends AppCompatActivity {
    private ActivityMapelBinding binding;
    private AdminViewModel viewModel;
    private MapelAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initActionBar();
        initViewModel();
        initAdapter();
        initListener();
        loadData();
    }

    private void initActionBar() {
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Matapelajaran");
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(AdminViewModel.class);
    }

    private void initAdapter() {
        adapter = new MapelAdapter();
        adapter.setViewModel(viewModel);
        adapter.setActivity(this);
        binding.mapelRv.setAdapter(adapter);
        binding.mapelRv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadData() {
        binding.mapelProgress.setVisibility(View.VISIBLE);
        viewModel.getAllMapel().subscribe(
            listMapel -> {
                runOnUiThread(() -> {
                    adapter.setList(listMapel);
                    if(listMapel.size() == 0)
                        binding.mapelErrorTv.setVisibility(View.VISIBLE);

                    binding.mapelProgress.setVisibility(View.GONE);
                });
                Log.d(TAG_ADMIN, String.valueOf(listMapel.size()));
                listMapel.forEach(mapel -> {
                    Log.d(TAG_ADMIN, mapel.getKdMapel()+" "+mapel.getNamaMapel());
                });
            },
            error -> {
                Log.e(TAG_ADMIN, error.getMessage());

                binding.mapelProgress.setVisibility(View.GONE);
            }
        );
    }

    private void reloadData() {
        adapter.setList(null);

        loadData();
    }

    private void initListener() {
        binding.mapelAddBtn.setOnClickListener(v -> {
            showAddFragment();
        });
    }

    private void showAddFragment() {
        binding.frameAddMapel.setVisibility(View.VISIBLE);

        getSupportFragmentManager().beginTransaction()
            .replace(R.id.frameAddMapel, AddMapelFragment.newInstance(viewModel), AddMapelFragment.class.getSimpleName())
            .addToBackStack(null)
            .commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        if(binding.frameAddMapel.getVisibility() == View.GONE)
            onBackPressed();
        else {
            binding.frameAddMapel.setVisibility(View.GONE);
            getSupportFragmentManager().popBackStack();

            reloadData();
        }
        return super.onSupportNavigateUp();
    }
}
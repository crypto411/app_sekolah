package com.user.fadhlanhadaina.app_sekolah.presentation.ui.admin.siswa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.user.fadhlanhadaina.app_sekolah.R;
import com.user.fadhlanhadaina.app_sekolah.databinding.ActivitySiswaBinding;
import com.user.fadhlanhadaina.app_sekolah.presentation.presenter.adapter.SiswaAdapter;
import com.user.fadhlanhadaina.app_sekolah.presentation.presenter.viewmodel.AdminViewModel;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

import static com.user.fadhlanhadaina.app_sekolah.core.util.Constant.TAG_ADMIN;

@AndroidEntryPoint
public class SiswaActivity extends AppCompatActivity {
    private ActivitySiswaBinding binding;
    private AdminViewModel viewModel;
    private SiswaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySiswaBinding.inflate(getLayoutInflater());
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
        setTitle("Siswa");
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(AdminViewModel.class);
    }

    private void initAdapter() {
        adapter = new SiswaAdapter();
        adapter.setViewModel(viewModel);
        adapter.setActivity(this);
        binding.siswaRv.setAdapter(adapter);
        binding.siswaRv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadData() {
        binding.siswaProgress.setVisibility(View.VISIBLE);
        binding.siswaErrorTv.setVisibility(View.GONE);

        viewModel.getAllSiswa().subscribe(
            listSiswa -> {
                runOnUiThread(() -> {
                    adapter.setList(listSiswa);
                    if(listSiswa.size() == 0)
                        binding.siswaErrorTv.setVisibility(View.VISIBLE);
                });
                Log.d(TAG_ADMIN, String.valueOf(listSiswa.size()));
                listSiswa.forEach(siswa -> {
                    Log.d(TAG_ADMIN, siswa.getNis()+" "+siswa.getNama());
                });
            },
            error -> {
                Log.e(TAG_ADMIN, error.getMessage());
            },
            () ->
                runOnUiThread(() -> {
                    binding.siswaProgress.setVisibility(View.GONE);
                })
        );
    }

    private void reloadData() {
        adapter.setList(null);

        loadData();
    }

    private void initListener() {
        binding.siswaAddBtn.setOnClickListener(v -> {
            showAddFragment();
        });
    }

    private void showAddFragment() {
        binding.frameAddSiswa.setVisibility(View.VISIBLE);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameAddSiswa, AddSiswaFragment.newInstance(viewModel), AddSiswaFragment.class.getSimpleName())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        if(binding.frameAddSiswa.getVisibility() == View.GONE)
            onBackPressed();
        else {
            binding.frameAddSiswa.setVisibility(View.GONE);
            getSupportFragmentManager().popBackStack();

            reloadData();
        }
        return super.onSupportNavigateUp();
    }
}
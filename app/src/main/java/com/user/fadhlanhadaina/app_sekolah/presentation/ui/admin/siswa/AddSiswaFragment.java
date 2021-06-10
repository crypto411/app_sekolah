package com.user.fadhlanhadaina.app_sekolah.presentation.ui.admin.siswa;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.user.fadhlanhadaina.app_sekolah.databinding.FragmentAddSiswaBinding;
import com.user.fadhlanhadaina.app_sekolah.presentation.presenter.viewmodel.AdminViewModel;

import org.jetbrains.annotations.NotNull;

public class AddSiswaFragment extends Fragment {
    private FragmentAddSiswaBinding binding;
    private AdminViewModel viewModel;

    public AddSiswaFragment(AdminViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public static AddSiswaFragment newInstance(AdminViewModel viewModel) {
        return new AddSiswaFragment(viewModel);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddSiswaBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initListener();
    }

    private void initListener() {
        binding.btnTambahSiswa.setOnClickListener(v -> {
            performAdd();
        });
    }

    private void performAdd() {
        int radioButtonId = binding.kelaminRg.getCheckedRadioButtonId();

        String nis = binding.nisSiswaInput.getText().toString();
        String name = binding.namaSiswaInput.getText().toString();
        String address = binding.alamatSiswaInput.getText().toString();
        String gender = binding.lakiLakiRb.getId() == radioButtonId ?
                binding.lakiLakiRb.getText().toString().substring(0, 1) : binding.perempuanRb.getText().toString().substring(0, 1);
        binding.btnTambahSiswa.setEnabled(false);

        viewModel.createSiswa(nis, name, address, gender).subscribe(status -> {
            requireActivity().runOnUiThread(() -> {
                binding.nisSiswaInput.getText().clear();
                binding.namaSiswaInput.getText().clear();
                binding.alamatSiswaInput.getText().clear();

                Snackbar.make(binding.btnTambahSiswa, status, Snackbar.LENGTH_LONG).show();
            });
        },
        Throwable::printStackTrace,
        () -> {
            requireActivity().runOnUiThread(() -> {
                binding.btnTambahSiswa.setEnabled(true);
            });

        });
    }
}
package com.user.fadhlanhadaina.app_sekolah.presentation.ui.admin.mapel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.user.fadhlanhadaina.app_sekolah.databinding.FragmentAddMapelBinding;
import com.user.fadhlanhadaina.app_sekolah.presentation.presenter.viewmodel.AdminViewModel;

import org.jetbrains.annotations.NotNull;

public class AddMapelFragment extends Fragment {
    private FragmentAddMapelBinding binding;
    private AdminViewModel viewModel;

    public AddMapelFragment(AdminViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public static AddMapelFragment newInstance(AdminViewModel viewModel) {
        return new AddMapelFragment(viewModel);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddMapelBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initListener();
    }

    private void initListener() {
        binding.btnTambahMapel.setOnClickListener(v -> {
            performAdd();
        });
    }

    private void performAdd() {
        String kdMapel = binding.kodeMapelInput.getText().toString();
        String namaMapel = binding.namaMapelInput.getText().toString();
        binding.btnTambahMapel.setEnabled(false);

        viewModel.createMapel(kdMapel, namaMapel).subscribe(status -> {
            requireActivity().runOnUiThread(() -> {
                binding.kodeMapelInput.getText().clear();
                binding.namaMapelInput.getText().clear();

                Snackbar.make(binding.btnTambahMapel, status, Snackbar.LENGTH_LONG).show();
            });
        },
        Throwable::printStackTrace,
        () -> {
            requireActivity().runOnUiThread(() -> {
                binding.btnTambahMapel.setEnabled(true);
            });

        });
    }
}
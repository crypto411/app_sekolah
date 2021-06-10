package com.user.fadhlanhadaina.app_sekolah.presentation.ui.admin.nilai;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.user.fadhlanhadaina.app_sekolah.R;
import com.user.fadhlanhadaina.app_sekolah.core.domain.model.Mapel;
import com.user.fadhlanhadaina.app_sekolah.core.domain.model.Siswa;
import com.user.fadhlanhadaina.app_sekolah.databinding.FragmentNilaiInputBinding;
import com.user.fadhlanhadaina.app_sekolah.databinding.FragmentSelectMapelBinding;
import com.user.fadhlanhadaina.app_sekolah.presentation.presenter.adapter.SelectMapelAdapter;
import com.user.fadhlanhadaina.app_sekolah.presentation.presenter.viewmodel.AdminViewModel;

import org.jetbrains.annotations.NotNull;

public class NilaiInputFragment extends Fragment {

    private Siswa siswa;
    private Mapel mapel;
    private AdminViewModel viewModel;
    private FragmentNilaiInputBinding binding;

    public NilaiInputFragment(AdminViewModel viewModel, Siswa siswa, Mapel mapel) {
        this.viewModel = viewModel;
        this.siswa = siswa;
        this.mapel = mapel;
    }

    public static NilaiInputFragment newInstance(AdminViewModel viewModel, Siswa siswa, Mapel mapel) {
        return new NilaiInputFragment(viewModel, siswa, mapel);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNilaiInputBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initListener();
        setDataLayout();
    }

    private void initListener() {
        binding.nilaiInputBtn.setOnClickListener(v -> {
            performInput();
        });
    }

    @SuppressLint("SetTextI18n")
    private void setDataLayout() {
        binding.nisNamaTv.setText(siswa.getNis()+" - "+siswa.getNama());
        binding.kdNamaMapelTv.setText(mapel.getKdMapel()+" - "+mapel.getNamaMapel());

        binding.nilaiInputProgress.setVisibility(View.VISIBLE);
        viewModel.getNilai(siswa.getNis(), mapel.getKdMapel()).subscribe(
            next -> {
                getActivity().runOnUiThread(() ->
                    binding.nilaiInput.setText(next.getNilai())
                );
            },
            Throwable::printStackTrace,
            () ->
                getActivity().runOnUiThread(() ->
                    binding.nilaiInputProgress.setVisibility(View.GONE))
        );
    }

    private void performInput() {
        binding.nilaiInputProgress.setVisibility(View.VISIBLE);
        binding.nilaiInputBtn.setEnabled(false);

        String nilai = binding.nilaiInput.getText().toString();
        viewModel.updateNilai(siswa.getNis(), mapel.getKdMapel(), nilai).subscribe(
            next -> {
                getActivity().runOnUiThread(() -> {
                    Snackbar.make(binding.nilaiInputBtn, next, Snackbar.LENGTH_LONG).show();
                });
            },
            Throwable::printStackTrace,
            () ->
                getActivity().runOnUiThread(() -> {
                    binding.nilaiInputProgress.setVisibility(View.GONE);
                    binding.nilaiInputBtn.setEnabled(true);
                })
        );
    }
}
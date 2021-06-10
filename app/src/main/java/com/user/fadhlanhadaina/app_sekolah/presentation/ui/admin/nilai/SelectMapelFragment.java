package com.user.fadhlanhadaina.app_sekolah.presentation.ui.admin.nilai;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.user.fadhlanhadaina.app_sekolah.R;
import com.user.fadhlanhadaina.app_sekolah.core.domain.model.Siswa;
import com.user.fadhlanhadaina.app_sekolah.databinding.FragmentSelectMapelBinding;
import com.user.fadhlanhadaina.app_sekolah.presentation.presenter.adapter.SelectMapelAdapter;
import com.user.fadhlanhadaina.app_sekolah.presentation.presenter.adapter.SelectSiswaAdapter;
import com.user.fadhlanhadaina.app_sekolah.presentation.presenter.viewmodel.AdminViewModel;

import org.jetbrains.annotations.NotNull;

public class SelectMapelFragment extends Fragment {
    private Siswa siswa;
    private AdminViewModel viewModel;
    private FragmentSelectMapelBinding binding;
    private SelectMapelAdapter adapter;

    public SelectMapelFragment(AdminViewModel viewModel, Siswa siswa) {
        this.viewModel = viewModel;
        this.siswa = siswa;
    }

    public static SelectMapelFragment newInstance(AdminViewModel viewModel, Siswa siswa) {
        return new SelectMapelFragment(viewModel, siswa);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSelectMapelBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initAdapter();
        initListener();
        setDataLayout();
    }

    private void initAdapter() {
        adapter = new SelectMapelAdapter();
        adapter.setActivity((NilaiActivity) getActivity());
        binding.selectMapelRv.setAdapter(adapter);
        binding.selectMapelRv.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void initListener() {
        adapter.setOnClickItemList(mapel -> {
            getParentFragmentManager().beginTransaction()
                .replace(R.id.frameNilai, NilaiInputFragment.newInstance(viewModel, siswa, mapel), NilaiInputFragment.class.getSimpleName())
                .addToBackStack(null)
                .commit();
        });
    }

    private void setDataLayout() {
        binding.nisNamaTv.setText(siswa.getNis()+" - "+siswa.getNama());

        binding.selectMapelProgress.setVisibility(View.VISIBLE);

        viewModel.getAllMapel().subscribe(
            next -> { // onNext
                getActivity().runOnUiThread(() -> {
                    adapter.setList(next);
                });
            },
            Throwable::printStackTrace, // onError
            () ->  // onComplete
                getActivity().runOnUiThread(() -> binding.selectMapelProgress.setVisibility(View.GONE))
        );
    }
}
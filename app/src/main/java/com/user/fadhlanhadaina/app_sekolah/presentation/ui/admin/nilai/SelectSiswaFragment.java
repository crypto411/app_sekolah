package com.user.fadhlanhadaina.app_sekolah.presentation.ui.admin.nilai;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.user.fadhlanhadaina.app_sekolah.R;
import com.user.fadhlanhadaina.app_sekolah.databinding.FragmentPilihSiswaBinding;
import com.user.fadhlanhadaina.app_sekolah.presentation.presenter.adapter.SelectSiswaAdapter;
import com.user.fadhlanhadaina.app_sekolah.presentation.presenter.viewmodel.AdminViewModel;

import org.jetbrains.annotations.NotNull;

public class SelectSiswaFragment extends Fragment {
    private FragmentPilihSiswaBinding binding;
    private AdminViewModel viewModel;
    private SelectSiswaAdapter adapter;

    public SelectSiswaFragment(AdminViewModel adminViewModel) {
        this.viewModel = adminViewModel;
    }

    public static SelectSiswaFragment newInstance(AdminViewModel viewModel) {
        return new SelectSiswaFragment(viewModel);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPilihSiswaBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initAdapter();
        initListener();
        loadData();
    }

    private void initAdapter() {
        adapter = new SelectSiswaAdapter();
        adapter.setActivity((NilaiActivity) getActivity());
        binding.selectSiswaRv.setAdapter(adapter);
        binding.selectSiswaRv.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void initListener() {
        adapter.setOnClickItemList(siswa -> {
            getParentFragmentManager().beginTransaction()
                .replace(R.id.frameNilai, SelectMapelFragment.newInstance(viewModel, siswa), SelectMapelFragment.class.getSimpleName())
                .addToBackStack(null)
                .commit();
        });
    }

    private void loadData() {
        binding.pilihSiswaProgress.setVisibility(View.VISIBLE);

        viewModel.getAllSiswa().subscribe(
            next -> { // onNext
                getActivity().runOnUiThread(() -> {
                    adapter.setList(next);
                });
            },
            Throwable::printStackTrace, // onError
            () ->  // onComplete
                getActivity().runOnUiThread(() -> binding.pilihSiswaProgress.setVisibility(View.GONE))
        );
    }
}
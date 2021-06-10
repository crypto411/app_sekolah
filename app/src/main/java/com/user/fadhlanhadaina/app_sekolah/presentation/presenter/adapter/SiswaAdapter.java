package com.user.fadhlanhadaina.app_sekolah.presentation.presenter.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.user.fadhlanhadaina.app_sekolah.core.domain.model.Siswa;
import com.user.fadhlanhadaina.app_sekolah.databinding.RowItemBinding;
import com.user.fadhlanhadaina.app_sekolah.presentation.presenter.viewmodel.AdminViewModel;
import com.user.fadhlanhadaina.app_sekolah.presentation.ui.admin.siswa.SiswaActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SiswaAdapter extends RecyclerView.Adapter<SiswaAdapter.ViewHolder> {
    List<Siswa> list;
    AdminViewModel viewModel;
    SiswaActivity activity;

    public void setList(List<Siswa> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setViewModel(AdminViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setActivity(SiswaActivity activity) {
        this.activity = activity;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        RowItemBinding binding = RowItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.bind(list.get(position), position);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final RowItemBinding binding;
        ViewHolder(RowItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("SetTextI18n")
        public void bind(Siswa siswa, int position) {
            binding.rowText.setText(siswa.getNis()+" - "+siswa.getNama());
            binding.rowDeleteBtn.setOnClickListener(v -> {
                performDelete(siswa, position);
            });
        }

        public void performDelete(Siswa siswa, int position) {
            binding.rowDeleteBtn.setEnabled(false);
            viewModel.deleteSiswa(siswa.getNis()).subscribe(
                string -> {
                    activity.runOnUiThread(() -> {
                        list.remove(siswa);
                        notifyDataSetChanged();
                        notifyItemRemoved(position);

                        Snackbar.make(binding.rowDeleteBtn, string, Snackbar.LENGTH_LONG).show();
                    });
                },
                Throwable::printStackTrace,
                () -> {
                    activity.runOnUiThread(() ->
                        binding.rowDeleteBtn.setEnabled(true));
                }
            );
        }
    }
}

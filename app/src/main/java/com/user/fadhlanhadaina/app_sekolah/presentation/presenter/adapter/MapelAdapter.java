package com.user.fadhlanhadaina.app_sekolah.presentation.presenter.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.user.fadhlanhadaina.app_sekolah.core.domain.model.Mapel;
import com.user.fadhlanhadaina.app_sekolah.databinding.RowItemBinding;
import com.user.fadhlanhadaina.app_sekolah.presentation.presenter.viewmodel.AdminViewModel;
import com.user.fadhlanhadaina.app_sekolah.presentation.ui.admin.mapel.MapelActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MapelAdapter extends RecyclerView.Adapter<MapelAdapter.ViewHolder> {
    List<Mapel> list;
    AdminViewModel viewModel;
    MapelActivity activity;

    public void setList(List<Mapel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setViewModel(AdminViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setActivity(MapelActivity activity) {
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
        public void bind(Mapel mapel, int position) {
            binding.rowText.setText(mapel.getKdMapel()+" - "+mapel.getNamaMapel());
            binding.rowDeleteBtn.setOnClickListener(v -> {
                performDeleteMapel(mapel, position);
            });
        }

        public void performDeleteMapel(Mapel mapel, int position) {
            binding.rowDeleteBtn.setEnabled(false);
            viewModel.deleteMapel(mapel.getKdMapel()).subscribe(
                    string -> {
                        activity.runOnUiThread(() -> {
                            list.remove(mapel);
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

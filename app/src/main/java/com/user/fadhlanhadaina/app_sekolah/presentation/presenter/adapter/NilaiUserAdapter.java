package com.user.fadhlanhadaina.app_sekolah.presentation.presenter.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.user.fadhlanhadaina.app_sekolah.core.domain.model.Mapel;
import com.user.fadhlanhadaina.app_sekolah.core.domain.model.Nilai;
import com.user.fadhlanhadaina.app_sekolah.databinding.RowItemListBinding;
import com.user.fadhlanhadaina.app_sekolah.databinding.RowItemNilaiBinding;
import com.user.fadhlanhadaina.app_sekolah.presentation.ui.admin.nilai.NilaiActivity;
import com.user.fadhlanhadaina.app_sekolah.presentation.ui.user.NilaiUserActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NilaiUserAdapter extends RecyclerView.Adapter<NilaiUserAdapter.ViewHolder> {
    List<Nilai> list;

    public void setList(List<Nilai> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        RowItemNilaiBinding binding = RowItemNilaiBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final RowItemNilaiBinding binding;
        ViewHolder(RowItemNilaiBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("SetTextI18n")
        public void bind(Nilai nilai) {
            binding.rowText.setText(nilai.getKdMapel()+" - "+nilai.getNamaMapel());
            binding.rowTextNilai.setText(nilai.getNilai());
        }
    }
}

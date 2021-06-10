package com.user.fadhlanhadaina.app_sekolah.presentation.presenter.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.user.fadhlanhadaina.app_sekolah.core.domain.model.Mapel;
import com.user.fadhlanhadaina.app_sekolah.databinding.RowItemListBinding;
import com.user.fadhlanhadaina.app_sekolah.presentation.ui.admin.nilai.NilaiActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SelectMapelAdapter extends RecyclerView.Adapter<SelectMapelAdapter.ViewHolder> {
    List<Mapel> list;
    NilaiActivity activity;
    OnClickItemList onClickItemList;

    public void setList(List<Mapel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setActivity(NilaiActivity activity) {
        this.activity = activity;
    }

    public void setOnClickItemList(OnClickItemList onClickItemList) {
        this.onClickItemList = onClickItemList;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        RowItemListBinding binding = RowItemListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
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
        private final RowItemListBinding binding;
        ViewHolder(RowItemListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("SetTextI18n")
        public void bind(Mapel mapel) {
            binding.getRoot().setOnClickListener(v -> {
                onClickItemList.itemClicked(mapel);
            });
            binding.rowText.setText(mapel.getKdMapel()+" - "+mapel.getNamaMapel());
        }
    }

    public interface OnClickItemList {
        public void itemClicked(Mapel mapel);
    }
}

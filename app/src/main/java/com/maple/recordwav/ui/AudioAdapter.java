package com.maple.recordwav.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.maple.msdialog.adapter.BaseQuickAdapter;
import com.maple.recordwav.databinding.ItemVideoViewBinding;
import com.maple.recordwav.utils.ConversionUtils;

import java.io.File;

public class AudioAdapter extends BaseQuickAdapter<File, RecyclerView.ViewHolder> {
    private Context mContext;

    public AudioAdapter(Context context) {
        super();
        mContext = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(
                ItemVideoViewBinding.inflate(LayoutInflater.from(mContext), parent, false)
                );
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ItemViewHolder) holder).bindView(getItem(position));
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ItemVideoViewBinding binding;

        public ItemViewHolder(ItemVideoViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


        public void bindView(File file) {
            bindViewClickListener(this);
            ConversionUtils conversionUtils = new ConversionUtils();
            binding.tvSize.setText(conversionUtils.convertB(file.length()));
            binding.tvTitle.setText(file.getName());
            String filename = file.getName();
            int dotIndex = filename.lastIndexOf('.');
            String extension = (dotIndex == -1) ? "" : filename.substring(dotIndex + 1);
            binding.tvType.setText(extension);
        }
    }
}
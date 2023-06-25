package com.maple.recordwav.utils;

import android.content.Context;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;
import com.maple.recordwav.databinding.ItemButtomTabViewBinding;

/**
 * App主页面底部tab view
 * <p>
 */
public class BottomTabView extends FrameLayout {
    private ItemButtomTabViewBinding binding;
    private Integer iconRes = null;
    private Integer animatorRes = null;

    public BottomTabView(Context context) {
        super(context);
        init(context);
    }

    public BottomTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BottomTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        binding = ItemButtomTabViewBinding.inflate(LayoutInflater.from(context), this, true);
    }

    public void setIcon(@DrawableRes int selectedRes, @DrawableRes Integer animatorRes) {
        this.iconRes = selectedRes;
        this.animatorRes = animatorRes;
        binding.ivIcon.setImageResource(selectedRes);
    }

    public void setTitle(String title) {
        binding.tvTitle.setText(title);
    }

    public void setSelectStatus(boolean select) {
        if (select && animatorRes != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                Drawable drawable = ContextCompat.getDrawable(getContext(), animatorRes);
                binding.ivIcon.setImageDrawable(drawable);
                if (drawable instanceof AnimatedVectorDrawable) {
                    ((AnimatedVectorDrawable) drawable).registerAnimationCallback(new Animatable2.AnimationCallback() {
                        @Override
                        public void onAnimationStart(Drawable drawable) {
                        }

                        @Override
                        public void onAnimationEnd(Drawable drawable) {
                            resetIconRes();
                        }
                    });
                    ((AnimatedVectorDrawable) drawable).start();
                }
            } catch (Exception e) {
                resetIconRes();
            }
        } else {
            resetIconRes();
        }
    }

    // 重置图标状态
    private void resetIconRes() {
        if (iconRes != null) {
            binding.ivIcon.setImageResource(iconRes);
        }
    }
}
package tiger.king.alertutil;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

public class AlertUtil {
    private Context mContext;
    private View mView;
    private AlertDialog mDialog;

    public AlertUtil(Context context, int layoutId) {
        this(context, View.inflate(context, layoutId, null));

    }

    public AlertUtil(Context context, View view) {
        this.mContext = context;
        this.mView = view;
    }


    public AlertUtil setText(int viewId, CharSequence value) {
        if (mView == null) throw new NullPointerException("view is null");

        TextView tvText = mView.findViewById(viewId);
        tvText.setText(value);
        return this;
    }

    public AlertUtil setText(int viewId, int resId) {
        return this.setText(viewId, mContext.getString(resId));
    }

    public AlertUtil setViewClickListener(int viewId, @Nullable CharSequence field, @NonNull final OnClickListener listener) {
        if (mView == null) throw new NullPointerException("view is null");

        TextView tvField = mView.findViewById(viewId);
        if (!TextUtils.isEmpty(field))
            tvField.setText(field);
        tvField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(view, mDialog);
            }
        });
        return this;
    }

    public AlertUtil setViewClickListener(int viewId, OnClickListener listener) {
        return this.setViewClickListener(viewId, null, listener);
    }


    public AlertUtil show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        if (mView == null) throw new NullPointerException("view is null");

        builder.setView(mView);
        mDialog = builder.create();
        mDialog.show();
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(false);
        return this;
    }

    public void dismiss() {
        if (mDialog == null) return;
        mDialog.dismiss();
    }


    public interface OnClickListener {
        void onClick(View view, AlertDialog dialog);
    }
}

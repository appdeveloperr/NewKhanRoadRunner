package com.example.husnain.newproject.activities.cashdetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.husnain.newproject.R;

import java.util.List;

public class MyCashDetailRecyclerViewAdapter extends RecyclerView.Adapter<MyCashDetailRecyclerViewAdapter.ViewHolder> {

    private List<CashDetail> mValues;
    private final CashDetailFragment fragment;

    public MyCashDetailRecyclerViewAdapter(CashDetailFragment fragment) {
        this.fragment = fragment;
    }

    public List<CashDetail> getmValues() {
        return mValues;
    }

    public void setmValues(List<CashDetail> mValues) {
        this.mValues = mValues;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_cashdetail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (position == 0) {
            holder.mIdView.setText("Seat#");
            holder.mSourceView.setText("SRC");
            holder.mDestView.setText("DEST");
            holder.mFareView.setText("FARE");
            holder.mDiscView.setText("DISC");
            holder.mAmountView.setText("AMNT");
        } else {

            holder.mItem = mValues.get(position - 1);
            if (holder.mItem != null) {
                holder.mIdView.setText(holder.mItem.getSeat_No() + "");
                holder.mSourceView.setText(holder.mItem.getSourceAbbr());
                holder.mDestView.setText(holder.mItem.getDestinationAbbr());
                holder.mFareView.setText(String.format("%,.00f", holder.mItem.getFare()));
                holder.mDiscView.setText(String.format("%,.00f", holder.mItem.getDiscount()));
                holder.mAmountView.setText(String.format("%,.00f", holder.mItem.getAmount()));

                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (null != fragment.mListener) {
                            fragment.mListener.onListFragmentInteraction(holder.mItem);
                        }
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mSourceView;
        public final TextView mDestView;
        public final TextView mFareView;
        public final TextView mDiscView;
        public final TextView mAmountView;
        public CashDetail mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mSourceView = (TextView) view.findViewById(R.id.item_source);
            mDestView = (TextView) view.findViewById(R.id.item_destination);
            mFareView = (TextView) view.findViewById(R.id.item_fare);
            mDiscView = (TextView) view.findViewById(R.id.item_discount);
            mAmountView = (TextView) view.findViewById(R.id.item_amount);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mSourceView.getText() + "'";
        }
    }
}

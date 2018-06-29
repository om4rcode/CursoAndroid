package cevichito.omarcode.com.cevichito.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cevichito.omarcode.com.cevichito.Interface.ItemClickListener;
import cevichito.omarcode.com.cevichito.R;

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ItemClickListener itemClickListener;
    public ImageView foodImage;
    public TextView foodName;

    public FoodViewHolder(View itemView) {
        super(itemView);
        foodImage = itemView.findViewById(R.id.food_image);
        foodName = itemView.findViewById(R.id.food_name);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), false);
    }
}

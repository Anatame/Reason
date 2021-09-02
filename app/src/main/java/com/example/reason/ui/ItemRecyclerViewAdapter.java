package com.example.reason.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reason.R;
import com.example.reason.model.Item;

import java.util.List;

public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Item> itemList;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;

    public ItemRecyclerViewAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row, parent, false);

        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {

        Item item = itemList.get(position);

        if(holder.actTextView != null){
            holder.actTextView.setText(item.getActivityName());
        }



    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        public TextView actTextView;
        public RecyclerView recyclerView;

        private LayoutInflater inflater;

        public int id;
        public ViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);
            context = ctx;

            actTextView = itemView.findViewById(R.id.actTextView);
            recyclerView = itemView.findViewById(R.id.itemRecyclerView);



        }





//        private void editItem(Item newItem) {
//            builder = new AlertDialog.Builder(context);
//            inflater = LayoutInflater.from(context);
//            View view = inflater.inflate(R.layout.popup, null);
//
//            Button saveButton;
//            EditText itemName;
//            EditText itemQuantity;
//            EditText itemColor;
//            EditText itemSize;
//
//            itemName = view.findViewById(R.id.itemName);
//            itemQuantity = view.findViewById(R.id.itemQuantity);
//            itemColor = view.findViewById(R.id.itemColor);
//            itemSize = view.findViewById(R.id.itemSize);
//            saveButton = view.findViewById(R.id.saveButton);
//
//            itemName.setText(newItem.getItemName());
//            itemColor.setText(newItem.getItemColor());
//            itemQuantity.setText(String.valueOf(newItem.getItemQuantity()));
//            itemSize.setText(String.valueOf(newItem.getItemSize()));
//
//            builder.setView(view);
//            alertDialog = builder.create();
//            alertDialog.show();
//
//            saveButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    DatabaseHandler databaseHandler = new DatabaseHandler(context);
//                    newItem.setItemName(itemName.getText().toString());
//                    newItem.setItemColor(itemColor.getText().toString());
//                    newItem.setItemSize(Integer.parseInt(itemSize.getText().toString()));
//                    newItem.setItemQuantity(Integer.parseInt(itemQuantity.getText().toString()));
//
//                    databaseHandler.updateItem(newItem);
//                    notifyItemChanged(getAdapterPosition(), newItem);
//
//                    alertDialog.dismiss();
//                }
//            });
//        }


//        private void deleteItem(int id) {
//            builder = new AlertDialog.Builder(context);
//            inflater = LayoutInflater.from(context);
//            View view = inflater.inflate(R.layout.confirmation_pop, null);
//            Button noButton = view.findViewById(R.id.conf_no_button);
//            Button yesButton = view.findViewById(R.id.conf_yes_button);
//
//            builder.setView(view);
//            alertDialog = builder.create();
//            alertDialog.show();
//
//            yesButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    DatabaseHandler db = new DatabaseHandler(context);
//                    db.deleteItem(id);
//                    itemList.remove(getAdapterPosition());
//                    notifyItemRemoved(getAdapterPosition());
//
//                    alertDialog.dismiss();
//                }
//            });
//            noButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    alertDialog.dismiss();
//                }
//            });
//        }
    }
}

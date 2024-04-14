package com.app.my_contac_detabase_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.app.my_contac_detabase_project.Model.Contact_Model;

import java.util.ArrayList;

public class Recycerview_Adapter extends RecyclerView.Adapter<Recycerview_Adapter.Holder> {
    MainActivity mainActivity;
    ArrayList<Contact_Model> contactModels;
    public Recycerview_Adapter(MainActivity mainActivity, ArrayList<Contact_Model> contactModels) {
        this.mainActivity=mainActivity;
        this.contactModels=contactModels;
    }

    @NonNull
    @Override
    public Recycerview_Adapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mainActivity).inflate(R.layout.contact_item,parent,false);
        Holder holder=new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Recycerview_Adapter.Holder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(contactModels.get(position).getName());
        holder.surname.setText(contactModels.get(position).getSurname());
        holder.number.setText(contactModels.get(position).getNumber());
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(mainActivity,holder.more);
               popupMenu.getMenuInflater().inflate(R.menu.update_delet_menu,popupMenu.getMenu());
             popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                 @Override
                 public boolean onMenuItemClick(MenuItem item) {
                     if (item.getItemId()==R.id.update)
                     {
                         Intent intent=new Intent(mainActivity, ADD_CONTACT_ACTIVITY.class);
                         intent.putExtra("id",contactModels.get(holder.getAdapterPosition()).getId());
                         intent.putExtra("name",contactModels.get(position).getName());
                         intent.putExtra("surname",contactModels.get(position).getSurname());
                         intent.putExtra("number",contactModels.get(position).getNumber());
                         mainActivity.startActivity(intent);

                     }
                     if (item.getItemId()==R.id.delete){
                     My_Contact_DataBase dataBase=new My_Contact_DataBase(mainActivity);
                    dataBase.DeleteData(contactModels.get(position).getId());
                      contactModels.remove(position);
                      notifyDataSetChanged();
                         Toast.makeText(mainActivity, "Delete" , Toast.LENGTH_LONG).show();
                     }
                     return true;
                 }
             });
                popupMenu.show();
            }
        });

    }

    @Override
    public int getItemCount() {

        return contactModels.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView name,surname,number;
        ImageView more;
        public Holder(@NonNull View itemView) {

            super(itemView);
            name=itemView.findViewById(R.id.name);
            surname=itemView.findViewById(R.id.surname);
            number=itemView.findViewById(R.id.number);
            more=itemView.findViewById(R.id.more);

        }
    }
}

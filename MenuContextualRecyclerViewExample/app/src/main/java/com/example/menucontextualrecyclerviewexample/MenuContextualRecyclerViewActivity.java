package com.example.menucontextualrecyclerviewexample;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MenuContextualRecyclerViewActivity extends AppCompatActivity {
    int pos;
    List<String> els = new ArrayList<String>();

    class myAdapter extends RecyclerView.Adapter{

        List<String> elementos;

        public myAdapter(List<String> elementos) {
            this.elementos = elementos;
        }

        class MyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
            TextView tv;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                tv = itemView.findViewById(R.id.textView2);
                itemView.setOnCreateContextMenuListener(this);
                itemView.setOnLongClickListener((view) -> {
                    pos = getAdapterPosition();
                    return false;
                });
            }

            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                getMenuInflater().inflate(R.menu.menudef, contextMenu);
            }
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_view_holder_fragment, parent, false);

            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ((MyViewHolder)holder).tv.setText("Elemento numero " + position + ": " + elementos.get(position));
        }

        @Override
        public int getItemCount() {
            return elementos.size();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.menu_contextual_recycler_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        RecyclerView rv = findViewById(R.id.recyclerView);

        rv.setLayoutManager(new LinearLayoutManager(this));



        els.add("Javier");
        els.add("Paco");
        els.add("Carmen");
        rv.setAdapter(new myAdapter(els));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater mInflater = getMenuInflater();

        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)menuInfo;
        pos = info.position;

        mInflater.inflate(R.menu.menudef, menu);
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.op1) {
            Toast.makeText(this, "Se ha pulsado op1 en el elemento: " + pos, Toast.LENGTH_SHORT).show();
            els.remove(pos);

            RecyclerView rv = findViewById(R.id.recyclerView);
            rv.setAdapter(new myAdapter(els));
        }
        return super.onContextItemSelected(item);
    }
}
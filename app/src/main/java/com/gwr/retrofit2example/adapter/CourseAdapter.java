package com.gwr.retrofit2example.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.gwr.retrofit2example.R;
import com.gwr.retrofit2example.models.Course;
import com.gwr.retrofit2example.models.Instructor;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

/**
 * Created by willi on 01/08/2016.
 */
public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder> {

    public List<Course> list;
    public LayoutInflater layoutInflater;
    public Context context;


    public CourseAdapter (Context c, List<Course> courseList){
        this.list = courseList;
        this.layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = c;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.item_course_card,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tvTitulo.setText(list.get(position).getTitle());
        holder.tvSub.setText(list.get(position).getSubtittle());
        String autores = "";

        for(Instructor instructor : list.get(position).getInstructors()){
            autores += " - "+ instructor.getName();
        }
        holder.tvAutor.setText(autores);

        String img = list.get(position).getImage();

        if(img != null)
            if(!img.equals(""))
                Picasso.with(context).load(img).fit().into(holder.ivFoto);

        holder.btnSaMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = list.get(position).getHomepage();
                if (!url.startsWith("http://") && !url.startsWith("https://"))
                    url = "http://" + url;

                Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                context.startActivity(it);
            }
        });

        try {
            YoYo.with(Techniques.ZoomInRight)
                    .duration(700)
                    .playOn(holder.item);
        }catch (Exception e){

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder  {

        public TextView tvTitulo;
        public TextView tvSub;
        public TextView tvAutor;
        public ImageView ivFoto;
        public Button btnSaMais;
        public View item;

        public MyViewHolder(View v) {
            super(v);
            item = v;
            tvTitulo = (TextView) v.findViewById(R.id.tvTitulo);
            tvSub = (TextView) v.findViewById(R.id.tvSubTitulo);
            tvAutor = (TextView) v.findViewById(R.id.tvAutor);
            ivFoto = (ImageView) v.findViewById(R.id.ivFotoCurso);
            btnSaMais = (Button) v.findViewById(R.id.btnSaiba);
        }
    }
}

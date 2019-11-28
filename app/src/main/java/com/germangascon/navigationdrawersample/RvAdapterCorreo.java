package com.germangascon.navigationdrawersample;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.germangascon.navigationdrawersample.modelo.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RvAdapterCorreo extends RecyclerView.Adapter<RvAdapterCorreo.CorreoViewHolder> {

    private Context context;
    private ArrayList<Mail> mails;
    private ArrayList<Contacto> contactos;
    ICorreoListener listener;
    public RvAdapterCorreo(Fragment fragment, ArrayList<Mail> mails, ArrayList<Contacto> contactos, ICorreoListener listener){
        this.context=fragment.getContext();
        this.mails=mails;
        this.listener=listener;
        this.contactos=contactos;
    }

    @NonNull
    @Override
    public CorreoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_correo,parent,false);
        return new CorreoViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CorreoViewHolder holder, int position ) {
        holder.bind(mails.get(position));
    }

    @Override
    public int getItemCount() {
        return mails.size();
    }

    public class CorreoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ICorreoListener listener;
        TextView tvNombre;
        TextView tvAsunto;
        TextView tvDescripcion;
        TextView tvFecha;
        TextView tvHora;
        ImageView ivContacto;
        Contacto contacto;
        Mail mail;



        public CorreoViewHolder(@NonNull View itemView, ICorreoListener listener) {
            super(itemView);
            this.listener=listener;
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvAsunto= itemView.findViewById(R.id.tvAsunto);
            tvDescripcion= itemView.findViewById(R.id.tvDescripcion);
            tvFecha= itemView.findViewById(R.id.tvFecha);
            tvHora= itemView.findViewById(R.id.tvHora);
            ivContacto=itemView.findViewById(R.id.ivContacto);
            itemView.setOnClickListener(this);

        }

        public void bind(Mail m){
            mail=m;
            String nombre=m.getFrom();
            int idFoto=context.getResources().getIdentifier("unknown","drawable",context.getPackageName());
            for (Contacto c: contactos
                 ) {
                if(c.getEmail().equals(m.getFrom()) || c.getEmail().equals(m.getTo()) ){
                    contacto=c;
                    nombre=c.getName().concat(" ").concat(c.getFirstSurname()
                            .concat(" ").concat(c.getSecondSurname()));
                    idFoto=context.getResources().getIdentifier("c"+c.getId(),"drawable",context.getPackageName());
                }
            }
            ivContacto.setImageResource(idFoto);
            tvNombre.setText(nombre);
            tvAsunto.setText(m.getSubject());
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd MMM.");
            SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("hh:mm");
            String mes= simpleDateFormat.format(m.getSentOn());
            String hora= simpleDateFormat1.format(m.getSentOn());
            tvFecha.setText(mes);
            tvHora.setText(hora);
            if(!m.isReaded()){
                tvNombre.setTypeface(Typeface.DEFAULT_BOLD);
                tvAsunto.setTypeface(Typeface.DEFAULT_BOLD);
            }
            tvDescripcion.setText(m.getBody());

        }

        @Override
        public void onClick(View v) {

            listener.onCorreoSeleccionado(mail,contacto);
        }
    }

    public void setMails(ArrayList<Mail> mails) {
        this.mails = mails;
    }
}

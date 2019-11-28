package com.germangascon.navigationdrawersample;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.germangascon.navigationdrawersample.modelo.Contacto;
import com.germangascon.navigationdrawersample.modelo.Mail;

import java.util.ArrayList;

public class FragmentCorreos extends Fragment implements ICorreoListener {
    private RecyclerView rvCorreos;
    private ArrayList<Mail> mails;
    private ArrayList<Contacto> contactos;
    private RvAdapterCorreo rvAdapterCorreo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.rv_layout, container, false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvCorreos=getView().findViewById(R.id.rvCorreos);


        rvAdapterCorreo= new RvAdapterCorreo(this,mails,contactos,this);


        rvCorreos.setAdapter(rvAdapterCorreo);
        rvCorreos.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false));

    }

    public void setMails(ArrayList<Mail> mails) {
        this.mails = mails;
        //rvAdapterCorreo.notifyDataSetChanged();

    }

    public void setContactos(ArrayList<Contacto> contactos) {
        this.contactos = contactos;
    }


    @Override
    public void onCorreoSeleccionado(Mail mail, Contacto contacto) {
        Intent intent= new Intent(getActivity(),Detalle.class);
        intent.putExtra("correo",mail);
        intent.putExtra("contacto",contacto);
        getActivity().startActivity(intent);

    }
}

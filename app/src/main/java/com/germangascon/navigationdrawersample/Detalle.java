package com.germangascon.navigationdrawersample;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.germangascon.navigationdrawersample.modelo.Contacto;
import com.germangascon.navigationdrawersample.modelo.Mail;

public class Detalle extends AppCompatActivity {
    TextView tvNombreDetalle;
    TextView tvAsuntoDetalle;
    TextView tvCorreoDetalle;
    ImageView ivContactoDetalle;
    TextView tvBody;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle);
        setTitle("Mensaje");

        tvNombreDetalle=findViewById(R.id.tvNombreDetalle);
        tvAsuntoDetalle=findViewById(R.id.tvAsuntodetalle);
        tvCorreoDetalle=findViewById(R.id.tvCorreoDetalle);
        ivContactoDetalle=findViewById(R.id.ivContactoDetalle);
        tvBody=findViewById(R.id.tvBody);


        Contacto c= (Contacto) getIntent().getSerializableExtra("contacto");
        Mail m= (Mail) getIntent().getSerializableExtra("correo");
        int id=0;
        if(c!=null) {
            tvNombreDetalle.setText(c.getName().concat(" ").concat(c.getFirstSurname()).concat(" ")
                    .concat(c.getSecondSurname()));
            id=getResources().getIdentifier("c"+c.getId(),"drawable",getPackageName());
        }else{
            tvNombreDetalle.setText("Desconocido");
        }
        tvCorreoDetalle.setText(m.getFrom());
        tvAsuntoDetalle.setText(m.getSubject());

        if(id==0){
            id=R.drawable.unknown;
        }
        ivContactoDetalle.setImageResource(id);

        tvBody.setText(m.getBody());

    }
}

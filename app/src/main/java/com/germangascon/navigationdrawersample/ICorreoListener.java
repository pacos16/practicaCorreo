package com.germangascon.navigationdrawersample;

import com.germangascon.navigationdrawersample.modelo.Contacto;
import com.germangascon.navigationdrawersample.modelo.Mail;



public interface ICorreoListener {

    void onCorreoSeleccionado(Mail mail, Contacto contacto);
}

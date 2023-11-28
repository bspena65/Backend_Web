package com.ucatolica.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Servicio para enviar correos electrónicos.
 * Utiliza {@link JavaMailSender} para la configuración y envío de mensajes simples de correo electrónico.
 */
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    /**
     * Enviar un correo electrónico simple.
     *
     * @param to      La dirección de correo electrónico del destinatario.
     * @param subject El asunto del correo electrónico.
     * @param content El contenido del correo electrónico.
     */
    public void enviarCorreo(String to, String subject, String content) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(to);
        email.setSubject(subject);
        email.setText(content);
        mailSender.send(email);
    }
}

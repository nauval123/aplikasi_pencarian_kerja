package sample.helpers;

import javax.mail.PasswordAuthentication;

public class SMPTAuntheticator extends javax.mail.Authenticator {
    final String from;
    final String password;


    public SMPTAuntheticator(String from, String password) {
        this.from     = from;
        this.password = password;
    }


        protected PasswordAuthentication getPasswordAuthentication ()
        {
            return new PasswordAuthentication(from, password);
        }

}
package sample.helpers;
import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class sendEmail {

    //username dan  password untuk keamanan digunakan di authenticator
    private String email;
    private String password;


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void send(String to, String subject, String konten){
        //kirim email lewat gmail cuy

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        SecurityManager secur = System.getSecurityManager();


        try {

            Authenticator auth = new SMPTAuntheticator(email,password);
            Session session = Session.getInstance(props,auth);
            Message pesan = new MimeMessage(session);
            //set pengirim
            pesan.setFrom(new InternetAddress(email));
            // Set penerima
            pesan.setRecipients(Message.RecipientType.TO,
             InternetAddress.parse(to));
            // Set Subject
            pesan.setSubject(subject);
            // set konetn email
            pesan.setText(konten);
            // kirim emailnya
            Transport.send(pesan);
            System.out.println("email terkirim");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendwithattachment(String to, String subject, String konten,String pathfile){
        //kirim email lewat gmail cuy

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        SecurityManager secur = System.getSecurityManager();


        try {

            Authenticator auth = new SMPTAuntheticator(email,password);
            Session session = Session.getInstance(props,auth);
            Message pesan = new MimeMessage(session);
            //set pengirim
            pesan.setFrom(new InternetAddress(email));
            // Set penerima
            pesan.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            // Set Subject
            pesan.setSubject(subject);

            Multipart emailkonten = new MimeMultipart();

            MimeBodyPart isi = new MimeBodyPart();
            isi.setText(konten);

            MimeBodyPart filenya= new MimeBodyPart();
            filenya.attachFile(pathfile);

            emailkonten.addBodyPart(isi);
            emailkonten.addBodyPart(filenya);

            pesan.setContent(emailkonten);

            Transport.send(pesan);
            System.out.println("email terkirim");

        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
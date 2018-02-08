package by.tc.task.service.util.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Created by Y50-70 on 02.02.2018.
 */
public class EmailAuthenticator extends Authenticator{
    private String user;
    private String password;

    EmailAuthenticator(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public PasswordAuthentication getPasswordAuthentication() {
        String user = this.user;
        String password = this.password;
        return new PasswordAuthentication(user, password);
    }
}

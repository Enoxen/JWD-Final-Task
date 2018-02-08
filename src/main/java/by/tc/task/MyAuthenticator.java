package by.tc.task;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Created by Y50-70 on 02.02.2018.
 */
public class MyAuthenticator extends Authenticator {
    private String user;
    private String password;

    MyAuthenticator(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public PasswordAuthentication getPasswordAuthentication() {
        String user = this.user;
        String password = this.password;
        return new PasswordAuthentication(user, password);
    }
}

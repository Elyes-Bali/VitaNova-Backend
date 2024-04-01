import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("your-mail-server-host");
        mailSender.setPort(587); // or the appropriate port for your mail server
        mailSender.setUsername("your-email-username");
        mailSender.setPassword("your-email-password");

        return mailSender;
    }
}
package cn.eversec.javamail;

import cn.eversec.javamail.service.EmailService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.websocket.Session;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavamailApplicationTests {

    @Resource
    private EmailService emailService;
    @Test
    public void contextLoads() throws IOException, MessagingException {
        String Femail = "caoweiwei@eversec.cn";
        emailService.sendEmail(Femail,"22","ss");
    }

}

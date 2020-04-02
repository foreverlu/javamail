package cn.eversec.javamail.service;

import org.springframework.stereotype.Service;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

@Service
public class EmailService {

    //发送邮件,,,////传入要发送的邮箱，用户id用户名
    public void sendEmail(String Femail,String id,String userName) throws IOException, MessagingException {
        Properties props = new Properties();
//        //读取配置文件
        props.load(this.getClass().getResourceAsStream("/mailConfig.properties"));
        Session session = Session.getInstance(props);
        //创建邮件对象
        Message msg = new MimeMessage(session);
        try{
            msg.setSubject("修改密码");
        }catch (MessagingException e){
            e.printStackTrace();
        }

        //设置邮件内容
        String msgContent = "亲爱的用户 " + userName + " ，您好，<br/><br/>"
                + "您在" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "提交重置密码的请求。<br/><br/>"
                + "请打开以下链接重置密码：<br/><br/>"
                + "<a href="+props.getProperty("url")+"/user/resetPwd?id="+id+">http://"+props.getProperty("url")+"/user/resetPwd?id="+id+"</a><br/><br/>"
                + "感谢使用本系统。" + "<br/><br/>"
                + "此为自动发送邮件，请勿直接回复！";
        msg.setContent(msgContent,"text/html;charset=utf-8");
        //设置发件人
        msg.setFrom(new InternetAddress(MimeUtility.encodeText("修改密码") + " <"+ props.getProperty("mail.username")+">"));

        Transport transport = session.getTransport();
        //连接服务器
        transport.connect(props.getProperty("mail.username"),props.getProperty("mail.password"));
        //发送邮件
        transport.sendMessage(msg,new Address[]{new InternetAddress(Femail)});
        //关闭连接
        transport.close();
    }
}

package com.kilo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Created by kilo on 2018/5/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testSimpleMail() {
        mailService.sendSimpleMail("642026443@qq.com", "这是一封简单邮件", "大家好，这是我的第一封邮件！");
    }

    @Test
    public void testHtmlMail() throws Exception {
        String content = "<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail("642026443@qq.com", "这是一封HTML邮件", content);
    }

    @Test
    public void testAttachmentsMail() {
        String filePath = "F:\\网站.txt";
        mailService.sendAttachmentsMail("642026443@qq.com", "带附件的邮件", "有附件，请查收！", filePath);
    }


    @Test
    public void testInlineResourceMail() {
        String rscId = "kilo";
        String content = "<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "F:\\初识Hadoop.jpg";
        mailService.sendInlineResourceMail("642026443@qq.com", "主题：这是有图片的邮件", content, imgPath, rscId);
    }

    @Test
    public void testSendTemplateMail() {
        //创建邮件正文
        Context context = new Context();
        context.setVariable("id", "kilo11");
        String emailContent = templateEngine.process("emailTemplate", context);
        mailService.sendHtmlMail("642026443@qq.com", "主题：这是模板邮件", emailContent);
    }

}

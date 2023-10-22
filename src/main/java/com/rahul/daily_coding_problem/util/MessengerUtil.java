package com.rahul.daily_coding_problem.util;

import com.rahul.daily_coding_problem.config.MailSenderFactoryImpl;
import com.rahul.daily_coding_problem.model.Model;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class MessengerUtil {

    @Autowired
    MailSenderFactoryImpl mailSenderFactory;

    @Autowired
    @Qualifier("emailConfigBean")
    private Configuration emailConfig;


    public void sendEmail(Model model) throws MessagingException, IOException, TemplateException {
        System.out.println("sendEmail " + model);
        JavaMailSender emailSender = mailSenderFactory.getSender();

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        mimeMessageHelper.addInline("logo.png", new ClassPathResource("classpath:/brainopLogo.png"));

        Template template = emailConfig.getTemplate(model.getTemplate());
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model.getBinder());

        mimeMessageHelper.setTo(model.getTo());
        mimeMessageHelper.setText(html, true);
        mimeMessageHelper.setSubject(model.getSubject());

        emailSender.send(message);

    }
}

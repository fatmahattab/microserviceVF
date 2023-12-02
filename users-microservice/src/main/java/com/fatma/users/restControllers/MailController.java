package com.fatma.users.restControllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fatma.users.service.MailService;

@RestController
@CrossOrigin(origins = "*")
public class MailController {

    @Autowired
    private MailService mailService;


    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String sendEmail(@RequestBody Map<String, String> emailData) {
        String to = emailData.get("to");
        String subject = emailData.get("subject");
        String message = emailData.get("message");

        // Call the sendEmail method from your MailService
        mailService.sendEmail(to, message, subject);

        return "Email sent successfully: To=" + to + ", Subject=" + subject + ", Message=" + message;
    }

}

package edu.plohoy.spitter.impl.controller;

import edu.plohoy.spitter.api.domain.Message;
import edu.plohoy.spitter.api.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class MessageController {

    @Resource(name = "message service")
    private MessageService service;

    @GetMapping("messages")
    public String viewAll(Map<String, Object> model) {
        List<Message> messages = service.findAll();

        model.put("messages", messages);
        return "message-list";
    }

    @PostMapping("messages")
    public String add(
            @RequestParam String text,
            @RequestParam String tag,
            Map<String, Object> model) {

        Message message = new Message(text, tag);
        service.save(message);

        List<Message> messages = service.findAll();
        model.put("messages", messages);
        return "message-list";
    }

    @PostMapping("filter")
    public String filter(
            @RequestParam String filter,
            Map<String, Object> model) {

        List<Message> filteredMessages;
        if (filter != null && !filter.isEmpty()) {
            filteredMessages = service.findByTag(filter);
        } else {
            filteredMessages = service.findAll();
        }

        model.put("messages", filteredMessages);
        return "message-list";
    }
}
package org.achintha.springaidemo.Controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }


    @GetMapping("/dad-jokes")
    public String generate(@RequestParam(value = "message",defaultValue = "tell me a dad joke") String message){
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }
}

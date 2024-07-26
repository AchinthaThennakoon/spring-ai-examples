package org.achintha.springaidemo.Controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/youtube")
public class PromptDemoController {

    private final ChatClient chatClient;

    @Value("classpath:/popularYoutuber.st")
    private Resource popularYoutuberTemplate;

    public PromptDemoController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/")
    public String simpleGet(){
        return chatClient.prompt(new Prompt("tell me a dad joke")).call().content();
    }

    @GetMapping("/youtuberByGenre")
    public String getPopularYoutuberByGenre(@RequestParam(value = "genre",defaultValue = "tech")  String genre){

        return chatClient
                .prompt()
                .user(u -> u
                        .text(popularYoutuberTemplate)
                        .param("genre",genre))
                .call()
                .content();
    }

    @GetMapping("dad-jokes")
    public String dadJokesWithRestrictions(){
        return chatClient
                .prompt()
                .system("only tell dad jokes. If you were asked any other jokes tell tem you don't know other jokes ")
                .user(userSpec -> userSpec
                        .text("tell me a series joke"))
                .call()
                .content();
    }
}

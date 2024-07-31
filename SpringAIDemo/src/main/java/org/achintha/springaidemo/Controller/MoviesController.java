package org.achintha.springaidemo.Controller;

import org.achintha.springaidemo.model.ActionFilms;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    private final ChatClient chatClient;

    public MoviesController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }


    @GetMapping("/{actor}")
    public ActionFilms getMovieByActor(@RequestParam(value = "actor",required = true,defaultValue = "sharuk") String actor){
        return chatClient
                .prompt()
                .user(userSpec -> userSpec
                        .text("Generate the filmography for a {actor} actor."))
                .call()
                .entity(ActionFilms.class);
    }

    @GetMapping("/actors")
    public List<ActionFilms> getMovieByTwoActors(@RequestParam(value = "actor",required = true,defaultValue = "sharuk") String actor){
        return chatClient
                .prompt()
                .user(userSpec -> userSpec
                        .text("Generate the filmography of 5 movies for Tom Hanks and Bill Murray."))
                .call()
                .entity(new ParameterizedTypeReference<List<ActionFilms>>() {
                });
    }
}

package codes.recursive.controller;

import codes.recursive.model.User;
import codes.recursive.repository.UserRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Post;

import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

@Controller("/hello")
public class HelloController {

    private final UserRepository userRepository;

    public HelloController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Get("/")
    public HttpStatus index() {
        return HttpStatus.OK;
    }

    @Get("/version")
    public HttpResponse<Map> getVersion() {
        return HttpResponse.ok(
                Map.of(
                        "version", "0.1"
                )
        );
    }

    @Post("/")
    public HttpResponse saveUser(@Body @Valid User user) {
        return HttpResponse.created(userRepository.save(user));
    }

    @Get("/{id}")
    public HttpResponse getById(UUID id) {
        return HttpResponse.ok(
                userRepository.findById(id)
        );
    }
}
package codes.recursive.controller;

import codes.recursive.model.User;
import codes.recursive.repository.UserRepository;
import codes.recursive.service.UserService;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

import javax.validation.Valid;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

@Controller("/hello")
public class HelloController {

    private final UserRepository userRepository;
    private final UserService userService;

    public HelloController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Get("/")
    public HttpStatus index() throws SQLException {
        List<HashMap<String, Object>> users = userService.getUsers();
        return HttpStatus.OK;
    }

    @Get("/id/{id}")
    public HttpResponse getById(UUID id) {
        return HttpResponse.ok(
                userRepository.findById(id)
        );
    }

    @Get("/version")
    public HttpResponse<Map> getVersion() {
        return HttpResponse.ok(
                CollectionUtils.mapOf(
                        "version", "0.1"
                )
        );
    }

    @Get("/foo")
    @Produces(MediaType.TEXT_PLAIN)
    public HttpResponse foo() {
        return HttpResponse.ok("bar");
    }

    @Get("/users")
    public HttpResponse getUsers() throws SQLException {
        return HttpResponse.ok(userService.getUsers());
    }

    @Post("/")
    public HttpResponse saveUser(@Body @Valid User user) {
        return HttpResponse.created(userRepository.save(user));
    }

}
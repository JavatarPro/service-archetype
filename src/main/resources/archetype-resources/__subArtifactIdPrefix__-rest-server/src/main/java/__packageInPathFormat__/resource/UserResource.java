package ${package}.resource;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ${package}.domain.UserTO;
import ${package}.service.UserService;
import ${package}.service.domain.UserBO;
import ${package}.converter.UserTOConverter;
import ${package}.exception.RestException;
import ${package}.exception.NotFoundRestException;
import ${package}.service.exception.UserNotFoundServiceException;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserResource {

    private static final Logger logger = LoggerFactory.getLogger(UserResource.class);

    private final UserService userService;
    private final UserTOConverter converter;

    public UserResource(UserService userService, UserTOConverter converter) {
        this.userService = userService;
        this.converter = converter;
    }

    @ApiOperation("Create new user")
    @PostMapping
    public ResponseEntity create(@RequestBody UserTO user) {
        logger.info("Create new user={}", user);
        UserBO bo = converter.toUserBO(user);
        bo = userService.save(bo);
        URI location = UriComponentsBuilder.fromUriString("/users/" + bo.getId()).build().toUri();
        logger.info("User location={}", location);
        return ResponseEntity.created(location).build();
    }

    @ApiOperation("Update user")
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody UserTO user) {
        logger.info("Update user={}", user);
        user.setId(id);
        UserBO bo = converter.toUserBO(user);
        userService.save(bo);
        return ResponseEntity.accepted().build();
    }

    @ApiOperation("Delete user")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        logger.info("Delete user by id={}", id);
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation("Get all users")
    @GetMapping("/search")
    public List<UserTO> search() {
        logger.info("Get all users");
        List<UserBO> list = userService.findAll();
        return converter.toUserTOList(list);
    }

    @ApiOperation("Get user by id")
    @GetMapping("/{id}")
    public UserTO getById(@PathVariable Long id) throws RestException {
        logger.info("Get user by id={}", id);
        try {
            UserBO user = userService.findById(id);
            return converter.toUserTO(user);
        } catch (UserNotFoundServiceException e) {
            logger.error(e.getMessage(), e);
            throw new NotFoundRestException(e.getMessage()).withDevMessage(e.getMessage());
        }
    }

    @ApiOperation("Get user by login")
    @GetMapping("/login/{login}")
    public UserTO getByLogin(@PathVariable String login) throws RestException {
        logger.info("Get user by login={}", login);
        try {
            UserBO user = userService.findByLogin(login);
            return converter.toUserTO(user);
        } catch (UserNotFoundServiceException e) {
            logger.error(e.getMessage(), e);
            throw new NotFoundRestException(e.getMessage()).withDevMessage(e.getMessage());
        }
    }

    @ApiOperation("Get user by email")
    @GetMapping("/emails/{email:.*}")
    public UserTO getByEmail(@PathVariable String email) throws RestException {
        logger.info("Get user by email={}", email);
        try {
            UserBO user = userService.findByEmail(email);
            return converter.toUserTO(user);
        } catch (UserNotFoundServiceException e) {
            logger.error(e.getMessage(), e);
            throw new NotFoundRestException(e.getMessage()).withDevMessage(e.getMessage());
        }
    }
}

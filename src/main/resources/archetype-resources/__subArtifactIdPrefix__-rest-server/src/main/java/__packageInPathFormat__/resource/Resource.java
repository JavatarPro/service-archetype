package ${package}.resource;

import ${package}.service.SimpleService;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/resource/{tenantid}")
public class Resource {

    private SimpleService service;

    @ApiOperation("Do Action")
    @RequestMapping(method = RequestMethod.GET, path = "/get/{value}",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String get(@PathVariable String tenantid, @PathVariable Boolean value) {
        return String.valueOf(service.getValue(value));
    }


    @Autowired
    public void setService(SimpleService service) {
        this.service = service;
    }
}

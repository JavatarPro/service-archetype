package ${package}.service.impl;

import ${package}.repository.Repository;
import ${package}.service.SimpleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleServiceImpl implements SimpleService{
    private Repository repository;

    public Boolean getValue(Boolean value){
        return repository.getData(value);
    }

    @Autowired
    public void setRepository(Repository repository) {
        this.repository = repository;
    }
}

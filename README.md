## Quick Start ##
```
mvn org.apache.maven.plugins:maven-archetype-plugin:3.0.1:generate \
-DarchetypeGroupId=pro.javatar \
-DarchetypeArtifactId=service-archetype \
-DarchetypeVersion=1.0
```

## Full Documentation
link to confluence article here

## Rationale

* use Java 8 for new code
* use a maven `groupId` for each domain like `pro.javatar.b2b` or `pro.javatar.dcs`.
* use a dedicated parent `package` for the service. Tries to avoid conflicts with
  other services. And will ease supporting Java9 modules in the future
* new code should use `java.time` and not `jodatime`
* hide implementation details behind API artifacts
* try isolate spring-boot dependencies to `-app` module
* in order to comply with [micro-service Acceptance Criteria][DOD] use:
    - spring-boot-starter-web: needed for system tests
    - spring-cloud-starter-eureka: needed for system tests
    - spring-cloud-starter-config: read configuration from remote service
    - spring-boot-starter-actuator: health and info endpoints

[DOD]: link here
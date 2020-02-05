# poc-with-testcontainers

POC to use testcontainer to start postgres docker container for integration tests


## Description
We'll be creating a POC with Java TestContainers library. It allows us to use Docker containers within our tests. As a result, we can write self-contained integration tests that depend on external resources

## How to use
Add Testcontainers Postgres as testCompile Dependency
```
testCompile 'org.testcontainers:postgresql:1.10.1'
```


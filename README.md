# poc-with-testcontainers

POC to use testcontainer to start postgres docker container for integration tests


## Description
We'll be creating a POC with Java TestContainers library. It allows us to use Docker containers within our tests. As a result, we can write self-contained integration tests that depend on external resources

## How to use

Prerequisites
    Docker for Windows needs to be installed. For more details please see System Requirements.

1. The first step is to add a dependency to the project. For this example, we’ll use org.testcontainers.postgressql dependency, which is specialized for supporting our Postgres docker container.

```
testCompile 'org.testcontainers:postgresql:1.10.1'
```
2. Now when we have testcontainers on our classpath, the easiest way to initialize our Postgres container is to create an instance, We can customize username, password and database name during initialization like so:
```
 @ClassRule 
 public static PostgreSQLContainer postgres = new PostgreSQLContainer()
              .withDatabaseName(TEST_DATABASE_NAME)
              .withUsername(TEST_USER)
              .withPassword(TEST_PASSWORD);
```
3. We also need to configure the datasource instance for our integration tests.

```
public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
  private static final String TEST_DATASOURCE = postgres.getJdbcUrl();

  @Override
  public void initialize(final ConfigurableApplicationContext configurableApplicationContext) {
    TestPropertyValues values = TestPropertyValues.of(
    "spring.datasource.url=" + TEST_DATASOURCE,
    "spring.datasource.username=" + TEST_USER,
    "spring.datasource.password=" + TEST_PASSWORD
    );
  values.applyTo(configurableApplicationContext);
  }
}
```
4. We also need to annotate the test class with
```
@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
```
to set the same context do the following in side the test class
```
@Autowired
private WebApplicationContext ctx;

private MockMvc mockMvc;

@Before
public void init() {
  this.mockMvc = MockMvcBuilders.webAppContextSetup(this.ctx).build();
}
```
5. Good to go, now we can use PostgreSQLContainer to perform integration test.




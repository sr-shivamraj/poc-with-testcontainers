package com.tavisca.testcontainersexample.controller;

import com.tavisca.testcontainersexample.TestApplication;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testcontainers.containers.PostgreSQLContainer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public abstract class AbstractIntegrationTest {
    private static final String TEST_DATABASE_NAME = "test-db";
    private static final String TEST_USER = "root";
    private static final String TEST_PASSWORD = "root";


    @ClassRule
    public static PostgreSQLContainer postgres = (PostgreSQLContainer)new PostgreSQLContainer()
            .withDatabaseName(TEST_DATABASE_NAME)
            .withUsername(TEST_USER)
            .withPassword(TEST_PASSWORD).withExposedPorts(2424, 2480);
//    @ClassRule
//    public KafkaContainer kafka = new KafkaContainer();
//    kafka.

//    public static FixedHostPortGenericContainer postgreSQLContainer =
//            new FixedHostPortGenericContainer<>("postgres:latest")
//                    .withEnv("POSTGRES_USER","testUser")
//                    .withEnv("POSTGRES_PASSWORD","testPassword")
//                    .withEnv("POSTGRES_DB","testDb")
//                    .withFixedExposedPort(60015);


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
}

package com.puper;

import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest(
        classes = {
                MigrationTest.MigrationTestConfiguration.class
        },
        properties = {
                "spring.liquibase.change-log=classpath:liquibase/db/changelog/changelog-current.xml",
                "spring.liquibase.contexts=test",
                "spring.datasource.url=jdbc:mariadb://${embedded.mariadb.host}:${embedded.mariadb.port}/${embedded.mariadb.schema}",
                "spring.datasource.username=${embedded.mariadb.user}",
                "spring.datasource.password=${embedded.mariadb.password}"
        }
)
class MigrationTest {

    private static final String CHANGE_LOG_FILE = "liquibase/db/changelog/changelog-current.xml";

    @Autowired
    protected DataSource dataSource;

    @Test
    void shouldUpdateAndRollback() throws LiquibaseException, SQLException {
        String liquibaseContext = "test";
        Liquibase liquibase = getLiquibase();

        liquibase.update(liquibaseContext);
        liquibase.rollback(liquibase.getDatabase().getRanChangeSetList().size(), liquibaseContext);
        liquibase.update(liquibaseContext);
    }

    private Liquibase getLiquibase() throws SQLException, LiquibaseException {
        return new Liquibase(
                CHANGE_LOG_FILE,
                new ClassLoaderResourceAccessor(),
                DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(dataSource.getConnection()))
        );
    }

    @EnableAutoConfiguration
    @Configuration
    public static class MigrationTestConfiguration {

    }

}

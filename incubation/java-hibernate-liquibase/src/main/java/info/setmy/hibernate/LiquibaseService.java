package info.setmy.hibernate;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseConnection;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LiquibaseService {

    public static void init() {
        try {
            // Load the JDBC driver
            Class.forName("org.postgresql.Driver");
            // Set the database connection details
            String url = "jdbc:postgresql://localhost:5432/mydb";
            String username = "myuser";
            String password = "mypassword";
            // Connect to the database
            Connection connection = DriverManager.getConnection(url, username, password);
            // Wrap the JDBC connection with a Liquibase JDBC connection
            DatabaseConnection dbConnection = new JdbcConnection(connection);
            // Get a Liquibase database instance
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(dbConnection);

            String changelog = "changelog.xml";
            
            // Create a Liquibase object
            Liquibase liquibase = new Liquibase(changelog, new ClassLoaderResourceAccessor(), database);
            //Liquibase liquibase = new liquibase.Liquibase("liquibase.properties", new ClassLoaderResourceAccessor(), dbConnection);
            // Perform the database migration
            liquibase.update(new Contexts(), new LabelExpression());
            // liquibase.update("");

            // Close the connection
            connection.close();
        } catch (LiquibaseException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

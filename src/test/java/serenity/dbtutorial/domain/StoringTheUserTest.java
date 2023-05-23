package serenity.dbtutorial.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

import static org.assertj.core.api.Assertions.assertThat;

public class StoringTheUserTest {

    private Connection connection;
    @BeforeEach
    void setup() throws SQLException, IOException, URISyntaxException {
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb", ""," ");

        Path scriptPath = Paths.get(getClass().getClassLoader().getResource("db_init.sql").toURI());
        String databaseSetupScript = Files.readString(scriptPath);

        Statement statement = connection.createStatement();
        statement.execute(databaseSetupScript);
    }

    @Test
    void readUserByName() throws SQLException{

        //Fetch the user from the database
        PreparedStatement query = connection.prepareStatement("SELECT * FROM USERS WHERE USERNAME = ?");
        query.setString(1, "john");
        ResultSet resultSet = query.executeQuery();

        resultSet.next();
        User user = new User(resultSet.getString("USERNAME"), resultSet.getString("PASSWORD"));
        assertThat(user.getUsername()).isEqualTo("john");
        assertThat(user.getPassword()).isEqualTo("p@ssw0rd");
    }

    @Test
    void countTheUsers() throws SQLException {

        //Fetch the user from the database
        PreparedStatement query = connection.prepareStatement("SELECT COUNT(*) FROM USERS");
        ResultSet resultSet = query.executeQuery();

        resultSet.next();
        assertThat(resultSet.getInt(1)).isEqualTo(3);
    }
}

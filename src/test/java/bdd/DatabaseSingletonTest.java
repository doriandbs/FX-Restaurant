package bdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


class DatabaseSingletonTest {
    @Mock
    Connection connection;

    @Mock
    PreparedStatement preparedStatement;

    private DatabaseSingleton dbSingleton;


    @Test
    void testConnect() throws IOException, SQLException {
        DatabaseSingleton instance = DatabaseSingleton.getInstance();
        instance.setConnection(mock(Connection.class));
        instance.connect();
        assertNotNull(instance.prepareStatement("Sql"));
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // initialise les mocks annotés avec @Mock
        dbSingleton = DatabaseSingleton.getInstance();
        dbSingleton.setConnection(connection); // injecte le mock de connexion
    }

    @Test
    void testSingletonInstance() {
        assertNotNull(dbSingleton);
    }

    @Test
    void testClose() throws SQLException {
        dbSingleton.close();
        verify(connection, times(1)).close();
    }

    @Test
    void testCloseThrowsSQLException() throws SQLException {
        doThrow(SQLException.class).when(connection).close();

        // Aucune exception ne devrait être levée, malgré l'SQLException
        dbSingleton.close();

        verify(connection, times(1)).close();
    }


    @Test
    void testPrepareStatement() throws SQLException {
        String sql = "SELECT * FROM test";
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        PreparedStatement result = dbSingleton.prepareStatement(sql);

        assertNotNull(result);
    }
}

package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexaoBD {
        protected static Connection getConexao() throws SQLException {
                Properties props = new Properties();
                props.setProperty("user", "usuario");
                props.setProperty("password", "12345");
                props.setProperty("useUnicode", "true");
                props.setProperty("characterEncoding", "UTF-8");

                try {
                        Class.forName("org.postgresql.Driver");
                } catch (ClassNotFoundException e) {
                        throw new SQLException("PostgreSQL JDBC Driver não encontrado", e);
                }
                return DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/bd",
                        props
                );
        }
}
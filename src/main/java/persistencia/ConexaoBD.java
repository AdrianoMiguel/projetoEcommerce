package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConexaoBD {
        protected static Connection getConexao() throws SQLException {
                try {
                        Class.forName("org.postgresql.Driver");
                } catch (ClassNotFoundException e) {
                        throw new SQLException("PostgreSQL JDBC Driver não encontrado", e);
                }
                return DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/bd",
                        "usuario",
                        "12345"
                );
        }
}
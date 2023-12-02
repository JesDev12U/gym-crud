package gym;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    final private static String nameDB = "gym";
    final private static String user = "root";
    final private static String password = ""; 
    public static boolean conectarBD() {
        // Parámetros de conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/" + nameDB;
        String usuario = user;
        String contraseña = password;

        // Objeto Connection para la conexión
        Connection conexion = null;

        try {
            // Registro del driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión
            conexion = DriverManager.getConnection(url, usuario, contraseña);

            if (conexion != null) {
                System.out.println("¡Conexión exitosa!");
                return true;
                // Puedes realizar consultas u operaciones aquí
                // Ejemplo: ejecutar una consulta
                // Statement statement = conexion.createStatement();
                // ResultSet resultSet = statement.executeQuery("SELECT * FROM tabla");
                // ... procesar el resultado si es necesario
                // resultSet.close();
                // statement.close();
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el driver de MySQL.");
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            System.out.println("Error: Fallo en la conexión a la base de datos.");
            e.printStackTrace();
            return false;
        } finally {
            // Cerrar la conexión al finalizar
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión.");
                e.printStackTrace();
            }
        }
        return false;
    }
}

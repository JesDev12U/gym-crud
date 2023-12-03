package gym;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;


public class MySQLConnection {
    final private static String nameDB = "gym";
    final private static String user = "root";
    final private static String password = ""; 
    private static Connection conexion = null;

    public static boolean conectarBD() {
        // Parámetros de conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/" + nameDB;
        String usuario = user;
        String contraseña = password;

        try {
            // Registro del driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión
            conexion = DriverManager.getConnection(url, usuario, contraseña);

            if (conexion != null) {
                System.out.println("¡Conexión exitosa!");
                return true;
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el driver de MySQL.");
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            System.out.println("Error: Fallo en la conexión a la base de datos.");
            e.printStackTrace();
            return false;
        }
        return false;
    }
    
    public static Connection getConexion(){
        return conexion;
    }
    
    public static Date getFechaActual(){
        LocalDate fechaActual = LocalDate.now();
        return Date.valueOf(fechaActual);
    }
    
    public static void cerrarConexion() {
        try {
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión.");
            e.printStackTrace();
        }
    }
}

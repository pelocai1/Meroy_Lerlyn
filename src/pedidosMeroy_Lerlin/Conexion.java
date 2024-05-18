package pedidosMeroy_Lerlin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

   
    public class Conexion {

        private static Connection con = null;

        public static Connection getConnection() {
            try {
                if (con == null) {
                    String url = "jdbc:mysql://localhost:3306/jardineria";
                    String user = "root";
                    String passwd = "123456";
                    con = DriverManager.getConnection(url, user, passwd);
                    System.out.println("Conexión creada satisfactoriamente!");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return con;
        }
    }



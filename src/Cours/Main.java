package Cours;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/poe_crm",
                    "root",
                    "");
            System.out.println("Connection établie avec succès !");
            //Dans le cas d'une connection en singleton
//            if (connection != null){
//                System.out.println("Connection établie avec succès !");
//            } else {
//                System.out.println("Echec de la connection.");
//            }
        } catch (SQLException e) {
            System.out.println("Echec de la connection.");
        }

        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connection clôturée");
            }
        } catch (SQLException e){
            System.out.println("Echec de la cloture de connection");
        }
    }
}

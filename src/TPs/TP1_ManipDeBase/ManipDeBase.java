package TPs.TP1_ManipDeBase;

import java.sql.*;
//Classe Client
class Client {
    private int id;
    private String nom;
    private String prenom;


    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }

    public Client(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;

    }
}

public class ManipDeBase {
    //Insertion
    public static void Insert(Connection connexion, String nom, String prenom, String email, char genre){
        try {
            PreparedStatement statement=connexion.prepareStatement("INSERT INTO clients(nom,prenom, email,genre) values(?,?,?,?)");
            statement.setString(1,nom);
            statement.setString(2,prenom);
            statement.setString(3,email);
            statement.setString(4,String.valueOf(genre));
            statement.executeUpdate();
            System.out.println("Ajout réussi");
        } catch (SQLException e){
            System.out.println("Echec de l'insertion");
        }
    }

    //MAJ
    public static void Update(Connection connexion, int id, String nom){
        try {
            PreparedStatement statement=connexion.prepareStatement("UPDATE clients SET nom = (?) WHERE id = (?);");
            statement.setString(1,nom);
            statement.setString(2,String.valueOf(id));
            statement.executeUpdate();
        } catch (SQLException e){
            System.out.println("Echec de la modification");
        }
    }

    //Suppression
    public static void Delete(Connection connexion, int id){
        try {
            PreparedStatement statement=connexion.prepareStatement("DELETE FROM clients WHERE id = (?);");
            statement.setString(1,String.valueOf(id));
            statement.executeUpdate();
        } catch (SQLException e){
            System.out.println("Echec de la suppression");
        }
    }

    //Récupérer par l'ID
    public static Client GetByID(Connection connexion, int id) throws SQLException{
        try {
            Client client = null;
            PreparedStatement statement = connexion.prepareStatement("SELECT * FROM clients Where id = ?");
            statement.setInt(1, id);
            ResultSet resultat = statement.executeQuery();
            while (resultat.next()) {
                client = new Client(resultat.getInt("id"),
                        resultat.getString("nom"),
                        resultat.getString("prenom"));
            }
            return client;

        } catch (SQLException e){
            System.out.println("Echec de la récupération");
            return null;
        }
    }

    public static void main(String[] args) {
        Connection connexion=null;
        try {
            connexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/poe_crm","root","");
            System.out.println("Connexion établie avec succès");

            Insert(connexion,"Delatour", "Guillaume", "delaguill@italia.com", 'm' );
            Update(connexion, 14,"De Latour");
            Delete(connexion,14);
            System.out.println(GetByID(connexion,15));

        }catch (SQLException e){
            System.out.println(e.getSQLState());
        }
    }
}
package hei.devweb.traderz;


import hei.devweb.traderz.dao.DataSourceProvider;
import hei.devweb.traderz.dao.UserDao;
import hei.devweb.traderz.dao.impl.UserDaoImpl;
import hei.devweb.traderz.entities.User;
import hei.devweb.traderz.managers.UserManager;
import org.assertj.core.api.Assertions;
import org.assertj.core.internal.cglib.core.Local;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

public class UserDaoTestCase {

 private UserDaoImpl userDao = new UserDaoImpl();


 @Before
 public void initDb() throws Exception {
     try (Connection connection = DataSourceProvider.getDataSource().getConnection();
          Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM personne");
            stmt.executeUpdate("INSERT INTO `personne` (`id`,`nom_personne`,`prenom_personne`,`email`,`sexe`,`mot_passe`,`role`)"+
                    "VALUES (1, 'Hassam', 'Kahina', 'kahina@gmail.com', 'Féminin', 'test', 'admin')," +
                    "(2, 'Younsi', 'Zohir', 'zohir@indeed.fr', 'masculin', 'mdp', 'admin')");
     }
 }


    @Test
    public final void shouldGetStoredPassword(){

        String passwordTest = userDao.getStoredPassword("kahina@gmail.com");
        assertThat(passwordTest).isNotNull();
        assertThat(passwordTest).isEqualTo("test");
        //THEN
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM personne WHERE email='kahina@gmail.com' ")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getString("mot_passe")).isEqualTo("test");
                assertThat(rs.next()).isFalse();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


 @Test
    public final void shouldAddUser() throws Exception{
     //GIVEN
     User user = new User(null,"Réquiqui","Louis","req@gmail.com","Masculin","root", "Etudiant");
     //WHEN
     User createdUser = userDao.addUser(user);
     //THEN
     assertThat(createdUser).isNotNull();
     assertThat(createdUser.getIdUser()).isNotNull();
     assertThat(createdUser.getIdUser()).isGreaterThan(0);
     assertThat(createdUser.getNom()).isEqualTo("Réquiqui");
     assertThat(createdUser.getPrenom()).isEqualTo("Louis");
     assertThat(createdUser.getMail()).isEqualTo("req@gmail.com");
     assertThat(createdUser.getSexe()).isEqualTo("Masculin");
     assertThat(createdUser.getMdp()).isEqualTo("root");
     assertThat(createdUser.getRole()).isEqualTo("Etudiant");


     try (Connection connection = DataSourceProvider.getDataSource().getConnection();
          Statement stmt = connection.createStatement()) {
         try (ResultSet rs = stmt.executeQuery("SELECT * FROM personne WHERE email = 'req@gmail.com'")) {
             assertThat(rs.next()).isTrue();
             assertThat(rs.getInt("id")).isEqualTo(createdUser.getIdUser());
             assertThat(rs.getString("nom_personne")).isEqualTo("Réquiqui");
             assertThat(rs.getString("prenom_personne")).isEqualTo("Louis");
             assertThat(rs.getString("email")).isEqualTo("req@gmail.com");
             assertThat(rs.getString("sexe")).isEqualTo("Masculin");
             assertThat(rs.getString("mot_passe")).isEqualTo("root");
             assertThat(rs.getString("role")).isEqualTo("Etudiant");
             assertThat(rs.next()).isFalse();
         }
     }
 }




} 



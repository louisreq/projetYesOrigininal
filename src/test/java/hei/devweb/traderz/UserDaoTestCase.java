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
            stmt.executeUpdate("DELETE FROM utilisateurs");
            stmt.executeUpdate("INSERT INTO `utilisateurs` (`user_id`,`user_prenom`,`user_nom`,`user_pseudo`,`user_password`,`user_mail`,`user_date_birth`,`user_sex`,`user_liquidites`,`user_valeur`)"+
                    "VALUES (1,'test','test','test','test','test@test.fr','1994-10-28','Masculin',200000,0)");
     }
 }


 @Test
    public final void shouldAddUser() throws Exception{
     //GIVEN
     User user = new User(null,"test1","test2","test3","test4","test@test.fr", LocalDate.of(1994,10,28),"Masculin",200000,0);
     //WHEN
     User createdUser = userDao.addUser(user);
     //THEN
     assertThat(createdUser).isNotNull();
     assertThat(createdUser.getIdUser()).isNotNull();
     assertThat(createdUser.getIdUser()).isGreaterThan(0);
     assertThat(createdUser.getPrenom()).isEqualTo("test1");
     assertThat(createdUser.getNom()).isEqualTo("test2");
     assertThat(createdUser.getIdentifiant()).isEqualTo("test3");
     assertThat(createdUser.getMdp()).isEqualTo("test4");
     assertThat(createdUser.getMail()).isEqualTo("test@test.fr");
     assertThat(createdUser.getDateNaissance()).isEqualTo(LocalDate.of(1994,10,28));
     assertThat(createdUser.getSexe()).isEqualTo("Masculin");
     assertThat(createdUser.getLiquidites()).isEqualTo(200000);
     assertThat(createdUser.getValeur()).isEqualTo(0);
     try (Connection connection = DataSourceProvider.getDataSource().getConnection();
          Statement stmt = connection.createStatement()) {
         try (ResultSet rs = stmt.executeQuery("SELECT * FROM utilisateurs WHERE user_pseudo = 'test3'")) {
             assertThat(rs.next()).isTrue();
             assertThat(rs.getInt("user_id")).isEqualTo(createdUser.getIdUser());
             assertThat(rs.getString("user_prenom")).isEqualTo("test1");
             assertThat(rs.getString("user_nom")).isEqualTo("test2");
             assertThat(rs.getString("user_pseudo")).isEqualTo("test3");
             assertThat(rs.getString("user_password")).isEqualTo("test4");
             assertThat(rs.getString("user_mail")).isEqualTo("test@test.fr");
             assertThat(rs.getDate("user_date_birth").toLocalDate()).isEqualTo(LocalDate.of(1994,10,28));
             assertThat(rs.getString("user_sex")).isEqualTo("Masculin");
             assertThat(rs.getInt("user_liquidites")).isEqualTo(200000);
             assertThat(rs.getInt("user_valeur")).isEqualTo(0);
             assertThat(rs.next()).isFalse();
         }
     }
 }

    @Test
    public final void shouldListUserConnecte() throws Exception{
        String userPseudo = "test";
        List<User> listOfUser = userDao.listuserconnecte(userPseudo);
        assertThat(listOfUser).hasSize(1);
        assertThat(listOfUser).extracting("idUser","prenom","nom","identifiant","mdp","mail","dateNaissance","sexe","liquidites","valeur").containsOnly(
                tuple(1,"test","test","test","test","test@test.fr",LocalDate.of(1994,10,28),"Masculin",200000.0,0.0)
        );
    }

    @Test
    public final void shouldModifyPassword() throws Exception {
        userDao.modifyPassword("mdp", "test");
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement statement = connection.createStatement() ){
            try (ResultSet rs = statement.executeQuery("SELECT* FROM utilisateurs WHERE user_pseudo='test';")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getString("user_password")).isEqualTo("mdp");
            }
        }
    }

    @Test
    public final void shouldModifyMail() throws Exception {
        userDao.modifyMail("success@test.fr", "test");
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement statement = connection.createStatement() ){
            try (ResultSet rs = statement.executeQuery("SELECT* FROM utilisateurs WHERE user_pseudo='test';")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getString("user_mail")).isEqualTo("success@test.fr");
            }
        }
    }

 @Test
    public final void shouldSupprimerUser() throws Exception{

     userDao.supprimerUser("test");
     try (Connection connection = DataSourceProvider.getDataSource().getConnection();
          Statement statement = connection.createStatement();
          ResultSet resultSet = statement.executeQuery("SELECT* FROM utilisateurs WHERE user_pseudo='test';")) {
         Assertions.assertThat(resultSet.next()).isFalse();
     }
 }
    @Test
    public void shouldDebiter() throws Exception{
        //GIVEN
        Double valeurtransac = 100.;
        //WHEN
        Double valeur = userDao.CreateValeur("test");
        Double liquidite = userDao.CreateLiquidite("test");
        userDao.Debiter(liquidite, valeur, valeurtransac, "test");

        //THEN
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM utilisateurs WHERE user_pseudo='test' ")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getDouble("user_valeur")).isEqualTo(100.);
                assertThat(rs.getDouble("user_liquidites")).isEqualTo(199900);
                assertThat(rs.next()).isFalse();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}



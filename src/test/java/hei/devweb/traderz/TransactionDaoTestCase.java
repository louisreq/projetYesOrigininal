package hei.devweb.traderz;

import hei.devweb.traderz.dao.DataSourceProvider;
import hei.devweb.traderz.dao.impl.CotationDaoImpl;
import hei.devweb.traderz.dao.impl.TransactionDaoImpl;
import hei.devweb.traderz.dao.impl.UserDaoImpl;
import hei.devweb.traderz.entities.Cotation;
import hei.devweb.traderz.entities.Transaction;
import hei.devweb.traderz.entities.User;
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

public class TransactionDaoTestCase {

    private DataSourceProvider dataSourceProvider = new DataSourceProvider();
    private TransactionDaoImpl transactionDao = new TransactionDaoImpl();
    private CotationDaoImpl cotationDao = new CotationDaoImpl();
    private UserDaoImpl userDao = new UserDaoImpl();

    @Before
    public void initDatabase() throws SQLException {
        try (Connection connection = dataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM utilisateurs");
            stmt.executeUpdate("DELETE FROM transactions");
            stmt.executeUpdate("DELETE FROM cotations");
            stmt.executeUpdate("INSERT INTO utilisateurs(user_id, user_prenom, user_nom, user_pseudo, user_password," +
                    "user_mail, user_date_birth, user_liquidites, user_valeur, user_sex) VALUES(1, 'prenom #1', 'nom1', 'calimero', 'oeuf'," +
                    "'calimero@hei.fr', 20181010, 3000, 1000, 'M')");
            stmt.execute("INSERT INTO `cotations` (`cotation_id`, `cotation_categorie`, `cotation_nom`, `cotation_prix`, `cotation_haut`, `cotation_bas`, `cotation_varjour`, `cotation_veille`, `cotation_ouverture`,`cotation_volume`)\n" +
                    "VALUE (1, 'MP', 'cafe', 18, 19.3, 17.2, 0.5, 17.4, 17.5,150000)");
            stmt.execute("INSERT INTO `transactions` (`transac_id`, `transac_user_pseudo`, `transac_cotation_categorie`, `transac_cotation_nom`, `transac_cotation_id`, `transac_cotation_prix`, `transac_volume`, `transac_sens`)\n" +
                    "VALUE (20, 'seul', 'EUR', 'cafe', 10, 17.2, 150, true)");



        }
    }

    @Test
    public void shoudVendreTransac () throws Exception{
        //WHEN
        User user = userDao.CreateUserFromPseudo("calimero");
        Cotation cotation = cotationDao.CreateCotationFromId(1);
        Double volume = 3000.;

        transactionDao.VendreTransac(user, cotation, volume);
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM transactions WHERE transac_user_pseudo='calimero' ")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getDouble("transac_volume")).isEqualTo(3000);
                assertThat(rs.getString("transac_cotation_categorie")).isEqualTo("MP");
                assertThat(rs.getDouble("transac_cotation_prix")).isEqualTo(cotation.getPrix());
                assertThat(rs.getString("transac_cotation_nom")).isEqualTo("cafe");
                assertThat(rs.getBoolean("transac_sens")).isTrue();
                assertThat(rs.next()).isFalse();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shoudAcheterTransac () throws Exception{
        //WHEN
        User user = userDao.CreateUserFromPseudo("calimero");
        Cotation cotation = cotationDao.CreateCotationFromId(1);
        Double volume = 3000.;

        transactionDao.AcheterTransac(user, cotation, volume);
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM transactions WHERE transac_user_pseudo='calimero' ")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getDouble("transac_volume")).isEqualTo(3000);
                assertThat(rs.getString("transac_cotation_categorie")).isEqualTo("MP");
                assertThat(rs.getDouble("transac_cotation_prix")).isEqualTo(cotation.getPrix());
                assertThat(rs.getString("transac_cotation_nom")).isEqualTo("cafe");
                assertThat(rs.getBoolean("transac_sens")).isFalse();
                assertThat(rs.next()).isFalse();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   /* @Test
    public void shoudRevendreTransac () throws Exception{
        //WHEN
        User user = userDao.CreateUserFromPseudo("calimero");
        Cotation cotation = cotationDao.CreateCotationFromId(1);
        Double volume = 3000.;
        transactionDao.AcheterTransac(user,cotation,volume);
        Transaction transac =transactionDao.CreateTransacFromId(1);
        transactionDao.Revendre(transac);
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM historiques WHERE histo_user_pseudo='calimero' ")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getDouble("histo_volume")).isEqualTo(3000);
                assertThat(rs.getString("histo_cotation_categorie")).isEqualTo("MP");
                assertThat(rs.getDouble("histo_cotation_prix")).isEqualTo(cotation.getPrix());
                assertThat(rs.getString("histo_cotation_nom")).isEqualTo("cafe");
                assertThat(rs.getBoolean("histo_sens")).isTrue();
                assertThat(rs.next()).isFalse();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/


    @Test
    public void shoudDeleteTransac () throws Exception{
        User user = userDao.CreateUserFromPseudo("calimero");
        Cotation cotation = cotationDao.CreateCotationFromId(1);
        Double volume = 3000.;
        transactionDao.AcheterTransac(user,cotation,volume);
        transactionDao.DeleteTransac(21); // depend de l'indice crée dans la BDD (due à l'auto incrémentation , regarde le numero de l'indice dans la bdd et rentre l'indice+1  avant de lancer la testcase
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM transactions WHERE transac_user_pseudo='calimero' ")) {
                assertThat(rs.next()).isFalse();
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


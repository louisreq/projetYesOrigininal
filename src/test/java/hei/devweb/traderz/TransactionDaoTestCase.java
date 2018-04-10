package hei.devweb.traderz;

import hei.devweb.traderz.dao.DataSourceProvider;
import hei.devweb.traderz.dao.impl.CotationDaoImpl;
import hei.devweb.traderz.dao.impl.TransactionDaoImpl;
import hei.devweb.traderz.dao.impl.UserDaoImpl;
import hei.devweb.traderz.entities.Cotation;
import hei.devweb.traderz.entities.User;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.assertj.core.api.Assertions.assertThat;

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



        }
    }

   /* @Test
    public void shouldcreatacheter () throws Exception{
        //WHEN
        User user = userDao.CreateUserFromPseudo("calimero");
        Cotation cotation = cotationDao.CreateCotationFromId(1);
        Double volume = 3000.;

        transactionDao.Vendre(user, cotation, volume);
        //THEN
        try (Connection connection = dataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM transactions ")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("transac_cotation_prix")).isEqualTo(18);
                assertThat(rs.next()).isFalse();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }*/

}


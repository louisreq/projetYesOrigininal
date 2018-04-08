package hei.devweb.traderz;

import hei.devweb.traderz.dao.CotationDao;
import hei.devweb.traderz.dao.DataSourceProvider;
import hei.devweb.traderz.dao.impl.CotationDaoImpl;
import hei.devweb.traderz.entities.Cotation;
import hei.devweb.traderz.managers.CotationManager;
import org.junit.Before;
import org.junit.Test;
import yahoofinance.YahooFinance;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CotationDaoTestCase {

    private CotationDaoImpl cotationDao = new CotationDaoImpl();

    @Before
    public void initDb() throws Exception {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM cotations");
            stmt.executeUpdate("INSERT INTO `cotations` (`cotation_id`, `cotation_categorie`, `cotation_nom`, `cotation_prix`, `cotation_haut`, `cotation_bas`, `cotation_varjour`, `cotation_veille`, `cotation_ouverture`,`cotation_volume`)" +
                    "VALUE (null, 'MP', 'Alten', 18, 19.3, 17.2, 0.5, 17.4, 17.5,2015246)");
            stmt.executeUpdate("INSERT INTO `cotations` (`cotation_id`, `cotation_categorie`, `cotation_nom`, `cotation_prix`, `cotation_haut`, `cotation_bas`, `cotation_varjour`, `cotation_veille`, `cotation_ouverture`,`cotation_volume`)" +
                    "VALUE (null, 'MP', 'Blten', 18, 19.3, 17.2, 0.5, 17.4, 17.5,2015246)");
            stmt.executeUpdate("INSERT INTO `cotations` (`cotation_id`, `cotation_categorie`, `cotation_nom`, `cotation_prix`, `cotation_haut`, `cotation_bas`, `cotation_varjour`, `cotation_veille`, `cotation_ouverture`,`cotation_volume`)" +
                    "VALUE (null, 'MP', 'Clten', 18, 19.3, 17.2, 0.5, 17.4, 17.5,2015246)");
            stmt.executeUpdate("INSERT INTO `cotations` (`cotation_id`, `cotation_categorie`, `cotation_nom`, `cotation_prix`, `cotation_haut`, `cotation_bas`, `cotation_varjour`, `cotation_veille`, `cotation_ouverture`,`cotation_volume`)" +
                    "VALUE (null, 'MP', 'Dlten', 18, 19.3, 17.2, 0.5, 17.4, 17.5,2015246)");
            stmt.executeUpdate("INSERT INTO `cotations` (`cotation_id`, `cotation_categorie`, `cotation_nom`, `cotation_prix`, `cotation_haut`, `cotation_bas`, `cotation_varjour`, `cotation_veille`, `cotation_ouverture`,`cotation_volume`)" +
                    "VALUE (null, 'MP', 'AAlten', 18, 19.3, 17.2, 0.5, 17.4, 17.5,2015246)");
        }

    }

    @Test
    public final void shouldListCotationAD() throws Exception{
        List<Cotation> listCotationAD = cotationDao.listCotationAD();
        assertThat(listCotationAD).hasSize(5);

    }

    @Test
    public final void shouldInitCotations() throws Exception{
        cotationDao.InitCotation(YahooFinance.get("FR.PA"));
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()){
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM cotations WHERE cotation_nom ='FR.PA'")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getString("cotation_categorie")).isEqualTo("EUR");
                assertThat(rs.next()).isFalse();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

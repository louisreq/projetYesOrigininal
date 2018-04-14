package hei.devweb.traderz;

import hei.devweb.traderz.dao.DataSourceProvider;
import hei.devweb.traderz.dao.impl.AdminDaoImpl;
import hei.devweb.traderz.entities.Admin;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.assertj.core.api.Assertions.assertThat;

public class AdminDaoTestCase {

    private AdminDaoImpl adminDao = new AdminDaoImpl();


    @Before
    public void initDb() throws Exception {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM administrateurs");
            stmt.executeUpdate("INSERT INTO `administrateurs` (`admin_id`, `admin_nom`, `admin_password`) VALUE (null, 'TestAdmin', 'root')");
        }
    }

    @Test
    public void shouldGetStoredPassword(){
        String pseudoTest = "TestAdmin";
        adminDao.getStoredPassword(pseudoTest);
        //THEN
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM administrateurs WHERE admin_pseudo='TestAdmin' ")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getDouble("admin_password")).isEqualTo(adminDao.getStoredPassword(pseudoTest));
                assertThat(rs.next()).isFalse();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public final void shouldCreateAdmin() throws Exception{
        //WHEN
        String nomAdmin = "TestAdmin";
        Admin createdAdmin = adminDao.CreateAdminFromName(nomAdmin);
        //THEN
        assertThat(createdAdmin).isNotNull();
        assertThat(createdAdmin.getId()).isNotNull();
        assertThat(createdAdmin.getId()).isGreaterThan(0);
        assertThat(createdAdmin.getNom()).isEqualTo("TestAdmin");
        assertThat(createdAdmin.getPassword()).isEqualTo("root");

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM administrateurs WHERE admin_nom = 'TestAdmin'")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getInt("admin_id")).isEqualTo(createdAdmin.getId());
                assertThat(rs.getString("admin_nom")).isEqualTo("TestAdmin");
                assertThat(rs.getString("admin_password")).isEqualTo("root");
                assertThat(rs.next()).isFalse();
            }
        }
    }



}

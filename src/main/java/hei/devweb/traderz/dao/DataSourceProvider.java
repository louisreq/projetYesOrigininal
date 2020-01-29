package hei.devweb.traderz.dao;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;

// Classe nous permettant de se connecter à la BDD associé
public class DataSourceProvider {

    private static MysqlDataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new MysqlDataSource();
            dataSource.setServerName("localhost");
            dataSource.setPort(3306);
            dataSource.setDatabaseName("yes_3024?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC");
            dataSource.setUser("root");
            dataSource.setPassword("root");
        }
        return dataSource;
    }
}

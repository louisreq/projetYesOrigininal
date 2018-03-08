package hei.devweb.traderz.dao;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


// Classe nous permettant de se connecter à la BDD associé
public class DataSourceProvider {

    private static MysqlDataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new MysqlDataSource();
            dataSource.setServerName("localhost");
            dataSource.setPort(3306);
            dataSource.setDatabaseName("traderz");
            dataSource.setUser("root");
            dataSource.setPassword("root");
        }
        return dataSource;
    }
}

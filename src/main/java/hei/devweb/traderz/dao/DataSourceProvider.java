package hei.devweb.traderz.dao;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


// Classe nous permettant de se connecter à la BDD associé
public class DataSourceProvider {

    private static MysqlDataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new MysqlDataSource();
            dataSource.setServerName("r42ii9gualwp7i1y.chr7pe7iynqr.eu-west-1.rds.amazonaws.com");
            dataSource.setPort(3306);
            dataSource.setDatabaseName("mjcm9ja854h198zx");
            dataSource.setUser("x05hr5rbcfr652gq");
            dataSource.setPassword("vjlgs8miv1jqwhyy");
        }
        return dataSource;
    }
}

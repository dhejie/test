package utils;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;

/**
 * 用于配置连接池和获取连接
 */
public class JDBCTools {

    private static DataSource dataSource;

    static {
        dataSource = new DruidDataSource();
        ((DruidDataSource) dataSource).setUrl("jdbc:mysql://localhost:3306/bookasp?characterEncoding=utf8");
        ((DruidDataSource) dataSource).setUsername("root");
        ((DruidDataSource) dataSource).setPassword("root");
        ((DruidDataSource) dataSource).setMaxActive(10);
        ((DruidDataSource) dataSource).setMinIdle(3);
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static void main(String[] args) {
        DataSource ds = JDBCTools.getDataSource();
        System.out.println(ds);
    }

}

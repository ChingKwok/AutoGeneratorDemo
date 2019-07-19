package com.chingkwok.util;



import com.chingkwok.entity.Column;
import com.chingkwok.entity.Table;
import com.google.common.base.CaseFormat;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DatabaseUtil {

    private static final String DRIVER = "com.mysql.jdbc.Driver";

    private static final String SQL = "SELECT * FROM ";// 数据库操作

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取数据库下的所有表名
     */
    public static List<String> getTableNames(String url, String username, String password) {
        try (
                Connection conn = DriverManager.getConnection(url, username, password)

        ) {
            List<String> tableNames = new ArrayList<>();
            ResultSet rs = null;
            try {
                //获取数据库的元数据
                DatabaseMetaData db = conn.getMetaData();
                //从元数据中获取到所有的表名
                rs = db.getTables(null, null, null, new String[]{"TABLE"});
                while (rs.next()) {
                    tableNames.add(rs.getString(3));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return tableNames;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }


    /**
     * 获取表中字段的所有注释
     *
     * @param tableName
     * @return
     */
    public static List<String> getColumnComments(String url, String username, String password, String tableName) {
        try (
                Connection conn = DriverManager.getConnection(url, username, password)
        ) {
            List<String> columnTypes = new ArrayList<>();
            //与数据库的连接
            PreparedStatement pStemt = null;
            String tableSql = SQL + tableName;
            List<String> columnComments = new ArrayList<>();//列名注释集合
            ResultSet rs = null;
            try {
                pStemt = conn.prepareStatement(tableSql);
                rs = pStemt.executeQuery("show full columns from " + tableName);
                while (rs.next()) {
                    columnComments.add(rs.getString("Comment"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return columnComments;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    //TODO
    public static List<String> getPrimaryKey(String url, String username, String password,String table){
        try(
                Connection conn = DriverManager.getConnection(url, username, password)
                ){
            String sql = "SHOW CREATE TABLE " + table;
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            Pattern pattern = Pattern.compile("PRIMARY KEY \\(\\`(.*)\\`\\)");
            Matcher matcher = null;
            while (rs.next()){
                matcher =pattern.matcher(rs.getString(2));
            }
            matcher.find();
            String data=matcher.group();
            //过滤对于字符
            data=data.replaceAll("\\`|PRIMARY KEY \\(|\\)", "");
            //拆分字符
            String [] stringArr= data.split(",");
            return Arrays.asList(stringArr);
        }catch (SQLException e){
            e.printStackTrace();
            return new ArrayList<String>();
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<String>();
        }
    }

    /**
     * 获取所有表
     *
     * @return
     */
    public static List<Table> getAllTable(String url, String username, String password) {
        try (Connection conn = DriverManager.getConnection(url, username, password)
        ) {
            List<String> tableNames = getTableNames(url,username,password);
            List<Table> collect = tableNames.stream().map(v -> {
                Table table = new Table();
                ArrayList<Column> columns = new ArrayList<>();
                table.setTableName(v);
                List<String> columnComments = getColumnComments(url,username,password,v);
                List<String> primaryKey = getPrimaryKey(url, username, password, v);
                String tableSql = SQL + v;
                try {
                    PreparedStatement pStemt = conn.prepareStatement(tableSql);
                    ResultSetMetaData metaData = pStemt.getMetaData();
                    DatabaseMetaData databaseMetaData = conn.getMetaData();
                    int count = metaData.getColumnCount();
                    for (int i = 0; i < count; i++) {
                        Column column = new Column();
                        if(primaryKey.contains(metaData.getColumnName(i + 1))){
                            column.setPrimary(Boolean.TRUE);
                        }
                        column.setName(metaData.getColumnName(i + 1));
                        column.setTypeName(metaData.getColumnTypeName(i + 1));
                        column.setTypeCode(metaData.getColumnType(i + 1));
                        column.setComment(columnComments.get(i));
                        column.setProperty(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL,metaData.getColumnName(i+1)));
                        columns.add(column);
                    }
                    table.setColumns(columns);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return table;
            }).collect(Collectors.toList());
            return collect;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }


}
import java.sql.*;

public class MSK {

    public static String selectCustomerId(String faId) {
        String query = "select tc.s_customer_id\n" +
                "  from table_customer tc\n" +
                "  join table_con_fin_accnt_role con\n" +
                "    on tc.objid = con.fa_role2customer\n" +
                "  join table_fin_accnt fa\n" +
                "    on con.fin_accnt_role2fin_accnt = fa.objid\n" +
                " where fa.s_fa_id ='" + faId + "'";
        String customerId = "";
        try (Connection con = getConnection();
             PreparedStatement statement = con.prepareStatement(query); // выполнение запроса
             ResultSet result = statement.executeQuery()) { // результат выполнения запроса
            while (result.next()) {
                customerId = result.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerId;
    }

    public static String selectTitle(String customerId) {
        String query = "select hg.objid, hg.title\n" +
                "from table_customer tc\n" +
                "join table_hgbst_elm hg\n" +
                "on tc.x_propensity_drain2hgbst_elm = hg.objid\n" +
                "where tc.s_customer_id = '" + customerId + "'";
        String hgId = "";
        String title = "";
        try (Connection con = getConnection();
             PreparedStatement statement = con.prepareStatement(query);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                hgId = result.getString(1);
                title = result.getString(2);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
return title;
    }

    public static int selectHgId(int customerId) {
        String query = "select hg.objid\n" +
                "from table_customer tc\n" +
                "join table_hgbst_elm hg\n" +
                "on tc.x_propensity_drain2hgbst_elm = hg.objid\n" +
                "where tc.s_customer_id = '" + customerId + "'";
        int hgId = 1;
        try (Connection con = getConnection();
             PreparedStatement statement = con.prepareStatement(query);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                hgId = result.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return hgId;
    }

    public static void updatePropensityDrain(int customerId) {
        int nextDrain = selectHgId(customerId);
        if (selectHgId(customerId) == 5911)
            nextDrain = 5912;
        else if (selectHgId(customerId) == 5912)
            nextDrain = 5911;
        String query = "update table_customer\n" +
                "   set x_propensity_drain2hgbst_elm = '" + nextDrain + "'\n" +
                " where s_customer_id = '" + customerId + "'";
        try (Connection con = getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static Connection getConnection() {
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@10.28.43.13:1521:MSKDEV3";
        String user = "sa";
        String password = "sa";
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, user, password); // Возвращаем подключение
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

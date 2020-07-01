package tracker;

import tracker.model.Item;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 2. Трекер SQL [#281895]
 * tracker
 * @author Kirill Asmanov
 * @since 05.06.2020
 */
public class SqlTracker implements Store {
    private Connection cn;

    public SqlTracker(Connection cn) {
        this.cn = cn;
    }

    public SqlTracker() {
    }

    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        Item result = null;
        try (PreparedStatement ps = cn.prepareStatement(
                "INSERT INTO items (name) VALUES (?)",
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, item.getName());
            ps.executeUpdate();

            try (ResultSet generatedKey = ps.getGeneratedKeys()) {
                if (generatedKey.next()) {
                    result = item;
                    result.setId(generatedKey.getString(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean replace(String id, Item item) {
        boolean result = false;
        try (PreparedStatement ps = cn.prepareStatement(
                "UPDATE items SET name=(?) WHERE items.id=(?)")) {
            ps.setString(1, item.getName());
            ps.setInt(2, Integer.parseInt(id));
            if (ps.executeUpdate() != 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        try (PreparedStatement ps = cn.prepareStatement(
                "DELETE FROM items WHERE items.id=(?)")) {
            ps.setInt(1, Integer.parseInt(id));
            if (ps.executeUpdate() != 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        List<Item> result = new ArrayList<Item>();
        try (PreparedStatement ps = cn.prepareStatement(
                "SELECT * FROM items")) {
             try (ResultSet rs = ps.executeQuery()) {
                 while (rs.next()) {
                     Item el = new Item(rs.getString("name"));
                     el.setId(rs.getString("id"));
                     result.add(el);
                 }
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<Item>();
        try (PreparedStatement ps = cn.prepareStatement(
                "SELECT * FROM items WHERE items.name LIKE (?)")) {
            String keyMask = "%" + key + "%";
            ps.setString(1, keyMask);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Item el = new Item(rs.getString("name"));
                    el.setId(rs.getString("id"));
                    result.add(el);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Item findById(String id) {
        Item result = null;
        try (PreparedStatement ps = cn.prepareStatement(
                "SELECT * FROM items WHERE items.id IN (?)")) {
            ps.setInt(1, Integer.parseInt(id));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = new Item(rs.getString("name"));
                    result.setId(rs.getString("id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

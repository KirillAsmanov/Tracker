import org.junit.Test;
import tracker.ConnectionRollback;
import tracker.SqlTracker;
import tracker.model.Item;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class SqlTrackerTest {
    public Connection init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void createItem() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("ADDED##"));
            assertThat(tracker.findByName("ADDED##").size(), is(1));
        }
    }

    @Test
    public void deleteItem() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item deleted = tracker.add(new Item("SHOULD_BE_DELETED##"));
            assertThat(tracker.findByName("SHOULD_BE_DELETED##").size(), is(1));
            tracker.delete(deleted.getId());
            assertThat(tracker.findByName("SHOULD_BE_DELETED##").size(), is(0));
        }
    }

    @Test
    public void replaceItem() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item replaced = tracker.add(new Item("SHOULD_BE_REPLACED##"));
            assertThat(tracker.findByName("SHOULD_BE_REPLACED##").size(), is(1));
            tracker.replace(replaced.getId(), new Item("REPLACED_NOW##"));
            assertThat(tracker.findByName("SHOULD_BE_DELETED##").size(), is(0));
            assertThat(tracker.findByName("REPLACED_NOW##").size(), is(1));
        }
    }

    @Test
    public void findByName() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item finded = tracker.add(new Item("SHOULD_BE_FOUND##"));
            assertThat(tracker.findByName("SHOULD_BE_FOUND##").size(), is(1));
            assertThat(tracker.findByName("SHOULD_BE_FOUND##").get(0).getId(), is(finded.getId()));
        }
    }

    @Test
    public void findById() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item found = tracker.add(new Item("SHOULD_BE_FOUND##"));
            assertThat(tracker.findById(found.getId()), is(found));
        }
    }
}

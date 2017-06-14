package ua.goit.model.dao;

/**
 * Created by yarosh_ra on 14.06.2017.
 */
public class JdbcDeveloperDAO implements DeveloperDAO{

    private static final String READ_SQL = "select login, password, " +
                " username, registration_date from " +
                " users where login = ?";
        private static final String CREATE_SQL = "insert into " +
                "users(login, password, " +
                " username, registration_date) values " +
                " (?, ?, ?, ?)";

        private static final String UPDATE_SQL = "update" +
                " users set password = ?, " +
                " username = ?, registration_date = ? " +
                " where login = ?";
        private static final String DELETE_SQL = " delete from " +
                " users where login = ?";

        private static final String SELECT_GROUPS = "" +
                " select id, name" +
                " from groups where id in " +
                " (select group_id from user_groups where " +
                "   user_login = ?) ";

        @Override
        public Optional<User> read(String key) {
            try (Connection connection = getConnection()) {
                User user = null;
                try (PreparedStatement statement = connection.prepareStatement(READ_SQL)) {
                    statement.setString(1, key);
                    try (ResultSet set = statement.executeQuery()) {
                        if (!set.next()) {
                            return Optional.empty();
                        }
                        user = new User();
                        user.setLogin(key);
                        user.setPassword(set.getString(2));
                        user.setUsername(set.getString(3));
                        user.setRegistrationDate(set.getTimestamp(4));
                    }
                }
                List<Group> groups = new ArrayList<>();
                try (PreparedStatement statement =
                             connection.prepareStatement
                                     (SELECT_GROUPS)) {
                    statement.setString(1, key);
                    try (ResultSet set = statement.executeQuery()) {
                        while (set.next()) {
                            Group group = new Group();
                            group.setId(set.getInt(1));
                            group.setName(set.getString(2));
                            groups.add(group);
                        }
                    }
                }
                user.setGroups(groups);
                return Optional.of(user);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void create(User entity) {
            try (Connection connection = getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement(CREATE_SQL)) {
                    statement.setString(1, entity.getLogin());
                    statement.setString(2, entity.getPassword());
                    statement.setString(3, entity.getUsername());
                    statement.setNull(4, Types.TIMESTAMP);
                    statement.executeUpdate();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void update(User entity) {
            try (Connection connection = getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
                    statement.setString(1, entity.getPassword());
                    statement.setString(2, entity.getUsername());
                    statement.setNull(3, Types.TIMESTAMP);
                    statement.setString(4, entity.getLogin());
                    statement.executeUpdate();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void delete(User entity) {
            try (Connection connection = getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
                    statement.setString(1, entity.getLogin());
                    statement.executeUpdate();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        protected Connection getConnection() throws SQLException {
            String url = "jdbc:mysql://localhost:3306/chat";
            // -Dusername=XXX -Dpassword=YYYY
            String username = System.getProperty("username");
            String password = System.getProperty("password");
            return DriverManager.getConnection(url, username, password);
        }
    }
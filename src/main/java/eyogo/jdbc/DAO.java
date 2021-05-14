package eyogo.jdbc;

public interface DAO <Entity, Key> {
    boolean create(Entity model);
    Entity read(Key key);
    boolean update(Entity entity);
    boolean delete(Entity entity);
}

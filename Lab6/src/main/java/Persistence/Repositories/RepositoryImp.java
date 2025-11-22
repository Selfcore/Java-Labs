package Persistence.Repositories;

import java.util.List;

public interface RepositoryImp<TEntity> {
    List<TEntity> getAll();
    TEntity getById(int id);
    void save(TEntity entity);
    void update(TEntity entity);
    void delete(TEntity entity);
}

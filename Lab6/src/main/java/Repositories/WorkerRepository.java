package Repositories;

import Models.Worker;

import java.util.List;

public class WorkerRepository implements RepositoryImp<Worker> {
    @Override
    public List<Worker> getAll() {
        return List.of();
    }

    @Override
    public Worker getById(int id) {
        return null;
    }

    @Override
    public void save(Worker worker) {

    }

    @Override
    public void update(Worker worker) {

    }

    @Override
    public void delete(Worker worker) {

    }
}

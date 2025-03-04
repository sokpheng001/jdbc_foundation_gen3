package model.repository;

import model.entity.User;
import model.repository.abstraction.Repository;

import java.util.List;

public class UserRepository implements Repository<User, Integer> {
    @Override
    public Integer save(User user) {
        return 0;
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public Integer delete(Integer id) {
        return 0;
    }

    @Override
    public Integer update(Integer id) {
        return 0;
    }
}

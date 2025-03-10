package model.repository.abstraction;

import java.util.List;

/**
 * @author Bora, Daron, Narak
 * @version 1.0
 * @param <C> identify class type
 * @param <I> identify data type of class ID
 */
public interface Repository<C, I> {
    /**
     *
     * @param c parameter as class object
     * @return return as an object of class
     */
    I save(C c);
    List<C> findAll();
    I delete(I id);
    I update(I id, C classObject);
}

package model.repository;

import model.entity.Product;
import model.repository.abstraction.Repository;
import utils.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

public class ProductRepository implements Repository<Product, Integer> {
    @Override
    public Integer save(Product product) {
        return 0;
    }

    @Override
    public List<Product> findAll() {
        return List.of();
    }

    @Override
    public Integer delete(Integer id) {
        return 0;
    }

    @Override
    public Integer update(Integer id, Product classObject) {
        return 0;
    }
    public Product findProductById(Integer id){
        String sql = "SELECT * FROM products WHERE id = ?";
        try(Connection connection = DatabaseConfig.getDataConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet  =preparedStatement.executeQuery();
            while (resultSet.next()){
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setUuid(resultSet.getString("uuid"));
                product.setProductName(resultSet.getString("product_name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setImage(resultSet.getString("image"));
                product.setDescription(resultSet.getString("description"));
                product.setExpiredDate(resultSet.getDate("expired_date"));
                return product;
            }
        }catch ( Exception exception){
            System.out.println("[!] Error during get product by ID: " + exception.getMessage());
        }
        return null;
    }
}

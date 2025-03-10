package model.repository;

import model.entity.Order;
import model.entity.Product;
import model.entity.User;
import model.repository.abstraction.Repository;
import utils.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements Repository<Order, Integer> {
    private Integer saveOrderProduct(int orderId, int productId, int userId){
        String sql = """
                INSERT INTO orders_products (order_id, product_id, user_id)
                VALUES(?,?,?)
                """;
        try(Connection connection = DatabaseConfig.getDataConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, productId);
            preparedStatement.setInt(3, userId);
            int rowAffected = preparedStatement.executeUpdate();
            if(rowAffected>0){
                System.out.println("[+] Making product successfully");
                return rowAffected;
            }
            System.out.println("[!] Failed to order product.");
        }catch (Exception exception){
            System.out.println("[!] Error during inserting data to orders_products table: " + exception.getMessage());
        }
        return 0;
    }
    @Override
    public Integer save(Order order) {
        String sql = """
                INSERT INTO orders (uuid,order_name,order_date)
                VALUES (?,?,?) RETURNING id
                """;
        try(Connection connection = DatabaseConfig.getDataConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, order.getUuid());
            preparedStatement.setString(2, order.getOrderName());
            preparedStatement.setDate(3, order.getOrderedDate());
            ResultSet or = preparedStatement.executeQuery();
            while (or.next()){
                if(or.getInt("id")>0){
                    System.out.println("[+] Created data in order table successfully");
                    order.getProducts().forEach(e->{
                        try {
                            saveOrderProduct(or.getInt("id"), e.getId(), order.getUser().getId());
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
                    return or.getInt("id");
                }
                System.out.println("[!] Created order failed");
                return or.getInt("id");
            }
        }catch (Exception exception){
            System.out.println("[!] Error during insert data to order table: " + exception.getMessage());
        }
        return 0;
    }

    @Override
    public List<Order> findAll() {
        String sql = """
                SELECT * FROM  orders_products
                JOIN public.orders o on orders_products.order_id = o.id
                join public.products p on p.id = orders_products.product_id
                join public.users u on u.id = orders_products.user_id
                """;
        try(Connection connection = DatabaseConfig.getDataConnection();
            Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            List<Order> orders  =new ArrayList<>();
            while (resultSet.next()){
                Product product = Product.builder()
                        .id(resultSet.getInt("product_id"))
                        .price(resultSet.getDouble("price"))
                        .productName(resultSet.getString("product_name"))
                        .expiredDate(resultSet.getDate("expired_date"))
                        .description(resultSet.getString("description"))
                        .image(resultSet.getString("image"))
                        .build();
                User user = User.builder()
                        .id(resultSet.getInt("user_id"))
                        .userName(resultSet.getString("user_name"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .isDeleted(resultSet.getBoolean("is_deleted"))
                        .createdDate(resultSet.getDate("created_date"))
                        .build();
                Order order = Order.builder()
                        .id(resultSet.getInt("order_id"))
                        .orderName(resultSet.getString("order_name"))
                        .orderedDate(resultSet.getDate("order_date"))
                        .uuid(resultSet.getString("uuid"))
                        .user(user)
                        .products(List.of(product))
                        .build();
                orders.add(order);
            }
            return orders;
        }catch (Exception exception){
            System.out.println("[!] Error during select all order: " + exception.getMessage());
        }
        return List.of();
    }

    @Override
    public Integer delete(Integer id) {
        return 0;
    }

    @Override
    public Integer update(Integer id, Order classObject) {
        return 0;
    }
}

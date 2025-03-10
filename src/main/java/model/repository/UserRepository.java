package model.repository;

import model.entity.User;
import model.repository.abstraction.Repository;
import utils.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository<User, Integer> {
    @Override
    public Integer save(User user) {

        String sql = """
                INSERT INTO users (uuid, user_name, email, password,profile ,created_date, is_deleted)
                VALUES(?,?,?,?,?,?,?)
                """;
        try(Connection connection = DatabaseConfig.getDataConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, user.getUuid());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getProfile());
            preparedStatement.setDate(6, user.getCreatedDate());
            preparedStatement.setBoolean(7,user.getIsDeleted());

            int rowAffected = preparedStatement.executeUpdate();
            String message = rowAffected>0? "Insert user data successfully":"Insert user data failed";
            if(rowAffected>0){
                System.out.println(message);
                return rowAffected;
            }
            System.out.println(message);
        }catch (Exception exception){
            System.out.println("[!] Error during insert user data: " + exception.getMessage());
        }
        return 0;
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        try(Connection connection = DatabaseConfig.getDataConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){
            List<User> userList = new ArrayList<>();
            while (resultSet.next()){
                User user = new User(resultSet.getInt("id"),
                        resultSet.getString("uuid"),
                        resultSet.getString("user_name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("profile"),
                        resultSet.getBoolean("is_deleted"),
                        resultSet.getDate("created_date")

                        );
                userList.add(user);
            }
            return userList;

        }catch (Exception exception){
            System.out.println("[!] Error during get all users: " + exception.getMessage());
        }
        return List.of();
    }

    @Override
    public Integer delete(Integer id) {
        String sql = "DELETE FROM users WHERE id= ?";
        try(Connection connection = DatabaseConfig.getDataConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setInt(1, id);
            int rowAffected = preparedStatement.executeUpdate();
            if(rowAffected>0){
                System.out.println("[+] User deleted successfully.");
                return rowAffected;
            }
            System.out.println("[!] Deleted user failed.");
            return rowAffected;
        }catch ( Exception exception){
            System.out.println("[!] Error during delete user by id: " + exception.getMessage());
        }
        return 0;
    }

    @Override
    public Integer update(Integer id, User user) {
        String sql = """
                UPDATE users
                SET user_name = ?, profile = ?
                WHERE id = ?
                """;
        try(Connection connection = DatabaseConfig.getDataConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getProfile());
            preparedStatement.setInt(3, id);
            int rowAffected = preparedStatement.executeUpdate();
            if(rowAffected>0){
                System.out.println("[+] User updated successfully.");
                return rowAffected;
            }
            System.out.println("[!] Updated user failed.");
            return rowAffected;
        }catch (Exception exception){
            System.out.println("[!] Error during update user by id: " + exception.getMessage());
        }
        return 0;
    }
    public User findUserByUuid(String uuid){
        String sql = "SELECT * FROM users WHERE uuid = ?";
        try(Connection connection = DatabaseConfig.getDataConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,uuid);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUuid(resultSet.getString("uuid"));
                user.setUserName(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setProfile(resultSet.getString("profile"));
                user.setIsDeleted(resultSet.getBoolean("is_deleted"));
                user.setCreatedDate(resultSet.getDate("created_date"));
                return user;
            }

        }catch (Exception exception){
            System.out.println("[!] Error during get user by uuid: " + exception.getMessage());
        }
        return null;
    }
}

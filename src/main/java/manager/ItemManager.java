package manager;

import db.DBConnectionProvider;
import model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    private UserManager userManager = new UserManager();
    private CategoryManager categoryManager = new CategoryManager();

    public void add(Item item) {
        String sql = "insert into item(title,price,description,pic_url,user_id,category_id) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, item.getTitle());
            ps.setDouble(2, item.getPrice());
            ps.setString(3, item.getDescription());
            ps.setString(4, item.getPicUrl());

            ps.setInt(5, item.getUser().getId());
            ps.setInt(6, item.getCategory().getId());
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                item.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Item getItemById(int id) {
        String sql = "select * from item where id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return Item.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .price(resultSet.getDouble(3))
                        .description(resultSet.getString(4))
                        .picUrl(resultSet.getString(5))
                        .user(userManager.getUserById(resultSet.getInt(6)))
                        .category(categoryManager.getCategoryById(resultSet.getInt(7)))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Item> getAllItems() {
        String sql = "select * from item";
        List<Item> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result.add(Item.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .price(resultSet.getDouble(3))
                        .description(resultSet.getString(4))
                        .picUrl(resultSet.getString(5))
                        .user(userManager.getUserById(resultSet.getInt(6)))
                        .category(categoryManager.getCategoryById(resultSet.getInt(7)))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Item> getLast20Items() {
        String sql = "select * from item order by id desc limit 20";
        List<Item> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result.add(Item.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .price(resultSet.getDouble(3))
                        .description(resultSet.getString(4))
                        .picUrl(resultSet.getString(5))
                        .user(userManager.getUserById(resultSet.getInt(6)))
                        .category(categoryManager.getCategoryById(resultSet.getInt(7)))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Item> getLast20ItemsByCategory(int categoryId) {
        String sql = "select * from item where category_id=" + categoryId + " order by id desc limit 20";
        List<Item> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result.add(Item.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .price(resultSet.getDouble(3))
                        .description(resultSet.getString(4))
                        .picUrl(resultSet.getString(5))
                        .user(userManager.getUserById(resultSet.getInt(6)))
                        .category(categoryManager.getCategoryById(resultSet.getInt(7)))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Item> getAllUserItems(int userId) {
        String sql = "select * from item where user_id = " + userId;
        List<Item> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result.add(Item.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .price(resultSet.getDouble(3))
                        .description(resultSet.getString(4))
                        .picUrl(resultSet.getString(5))
                        .user(userManager.getUserById(resultSet.getInt(6)))
                        .category(categoryManager.getCategoryById(resultSet.getInt(7)))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void deleteItemById(int id) {
        String sql = "delete from item where id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

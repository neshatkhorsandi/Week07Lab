/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Role;
import models.User;

/**
 *
 * @author awarsyle
 */
public class RoleDB {

    public Role getRole(int roleID) throws SQLException {

        ConnectionPool connectionPool = null;
        Connection connection = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();

            Role role = null;
            String preparedQuery = "SELECT RoleID, RoleName FROM role_table WHERE RoleID=?";
            PreparedStatement ps = connection.prepareStatement(preparedQuery);
            ps.setInt(1, roleID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String roleName = rs.getString(2);
                role = new Role(roleID, roleName);
            }

            return role;
        } finally {
            connectionPool.freeConnection(connection);
        }
    }

    public boolean delete(Role role) throws SQLException {
        ConnectionPool connectionPool = null;
        Connection connection = null;

        try {
            connectionPool = ConnectionPool.getInstance();

            connection = connectionPool.getConnection();

            String DELETE_STMT = "DELETE FROM role_table where RoleId = ?";
            PreparedStatement prepare = connection.prepareStatement(DELETE_STMT);
            prepare.setInt(1, role.getRoleID());

            int rowCount = prepare.executeUpdate();
            prepare.close();
            return rowCount == 1;
        } finally {
            connectionPool.freeConnection(connection);
        }
    }

    public boolean update(Role role) throws SQLException {
        ConnectionPool connectionPool = null;
        Connection connection = null;

        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();

            String UPDATE_STMT = "UPDATE role_table set "
                    + "RoleID = ?, RoleName = ? where RoleID = ?";

            int rowCount = 0;
            PreparedStatement prepare = connection.prepareStatement(UPDATE_STMT);
            prepare.setInt(1, role.getRoleID());
            prepare.setString(2, role.getRoleName());

            rowCount = prepare.executeUpdate();
            prepare.close();

            return rowCount == 1;

        } finally {
            connectionPool.freeConnection(connection);
        }
    }

    public boolean insert(Role role) throws SQLException {

        ConnectionPool connectionPool = null;
        Connection connection = null;

        try {
            int rowCount = 0;
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            String INSERT_STMT = "INSERT INTO role_table"
                    + "(RoleID, RoleName) VALUES (?,?)";

            PreparedStatement prepare = connection.prepareStatement(INSERT_STMT);

            prepare.setInt(1, role.getRoleID());
            prepare.setString(2, role.getRoleName());

            return rowCount == 1;
        } finally {
            connectionPool.freeConnection(connection);
        }
    }

    public List<Role> getAll() throws SQLException {
        ConnectionPool connectionPool = null;
        Connection connection = null;

        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();

            Role role;
            ArrayList<Role> roleList = new ArrayList<>();

            String statement = "SELECT RoleID, RoleName FROM role_tabe";
            PreparedStatement prepare = connection.prepareStatement(statement);
            ResultSet resultSet = prepare.executeQuery();

            while (resultSet.next()) {
                int RoleID = resultSet.getInt(1);
                String RoleName = resultSet.getString(2);

                role = new Role(RoleID, RoleName);
                roleList.add(role);
            }
            return roleList;
        } finally {
            connectionPool.freeConnection(connection);
        }
    }
}

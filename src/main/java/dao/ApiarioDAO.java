/*
 *  PARCIAL TERCER CORTE
 *      Author ::: Brian Sterling
 *     Program ::: Bases de Datos
 *  Credential ::: SIST0008-G01:SIV
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DbUtil;
import vo.Apiario;


public class ApiarioDAO
{
    private Connection connection;

    public ApiarioDAO()
    {
        connection = DbUtil.getConnection();
    }

    public void addUser(Apiario user)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into sede(idSede) values (?)");
            preparedStatement.setInt(1, user.getIdSede());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteUser(int idApiario)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from sede where idApiario=?");
            preparedStatement.setInt(1, idApiario);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void updateUser(Apiario user)
    {
        try 
        {
            PreparedStatement preparedStatement = connection.prepareStatement("update sede set idSede=?" + "where idApiario=?");
            preparedStatement.setInt(1, user.getIdSede());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public List<Apiario> getAllUsers()
    {
        List<Apiario> users = new ArrayList<Apiario>();
        try
        {
            System.out.println("Llegue hasta aca");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from sede");
            while (rs.next())
            {
                Apiario user = new Apiario();
                user.setIdApiario(rs.getInt("idApiario"));
                user.setIdSede(rs.getInt("idApiario"));
                users.add(user);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return users;
    }

    public Apiario getUserById(int idApiario)
    {
        Apiario user = new Apiario();
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from sede where idApiario=?");
            preparedStatement.setInt(1, idApiario);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
            {
                user.setIdApiario(rs.getInt("idApiario"));
                user.setIdSede(rs.getInt("idApiario"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return user;
    }
}
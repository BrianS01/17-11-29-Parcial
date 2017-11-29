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
import vo.Sede;


public class SedeDAO
{
    private Connection connection;

    public SedeDAO()
    {
        connection = DbUtil.getConnection();
    }

    public void addUser(Sede user)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into sede(idSede) values (?)");
            preparedStatement.setString(1, user.getNombre());
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

    public void updateUser(Sede user)
    {
        try 
        {
            PreparedStatement preparedStatement = connection.prepareStatement("update sede set idSede=?" + "where idApiario=?");
            preparedStatement.setString(1, user.getNombre());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public List<Sede> getAllUsers()
    {
        List<Sede> users = new ArrayList<Sede>();
        
        try
        {
            System.out.println("Llegue hasta aca");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from sede");
            while (rs.next())
            {
                Sede user = new Sede();
                user.setNombre(rs.getString("idApiario"));
                users.add(user);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return users;
    }

    public Sede getUserById(int idApiario)
    {
        Sede user = new Sede();
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from sede where idApiario=?");
            preparedStatement.setInt(1, idApiario);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
            {
                user.setNombre(rs.getString("idApiario"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return user;
    }
}
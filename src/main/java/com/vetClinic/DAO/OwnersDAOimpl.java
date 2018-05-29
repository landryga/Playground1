package com.vetClinic.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.jdbc.Statement;
import com.vetClinic.dbhelper.DBconfig;
import com.vetClinic.owner.Owner;
import com.vetClinic.patients.Patient;

public class OwnersDAOimpl implements OwnersDAO {

	private JdbcTemplate jdbcTemplate;
	private DataSource dS;
	
	public OwnersDAOimpl() {
		DBconfig config = new DBconfig();
		DataSource dataSource = config.dataSource();
		jdbcTemplate = new JdbcTemplate(dataSource);
		dS = dataSource;
	}
	
	@Override
	public void delete(int user_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Owner get(int user_id) {

		String ownersSql = "select id, imie, nazwisko, email, telefon, adres  from klient where id = " + user_id + ";";
		
		Owner owner = new Owner();
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(ownersSql);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				owner.setOwner_id(rs.getInt(1));
				owner.setName(rs.getString(2));
				owner.setSurname(rs.getString(3));
				owner.setEmail(rs.getString(4));
				owner.setTelephone_number(rs.getString(5));
				owner.setAddress(rs.getString(6));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return owner;
	}

	@Override
	public List<Owner> list() {
		List<Owner> ownersList = new ArrayList<Owner>();
		
		String ownersSql = "select id, imie, nazwisko, email, telefon, adres  from klient;";
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(ownersSql);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				Owner owner = new Owner();
				owner.setOwner_id(rs.getInt(1));
				owner.setName(rs.getString(2));
				owner.setSurname(rs.getString(3));
				owner.setEmail(rs.getString(4));
				owner.setTelephone_number(rs.getString(5));
				owner.setAddress(rs.getString(6));
				
				ownersList.add(owner);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return ownersList;
	}

	@Override
	public void addOwner(Owner owner) {
		String name = owner.getName();
		String surname = owner.getSurname();
		String email = owner.getEmail();
		String tel_number = owner.getTelephone_number();
		String address = owner.getAddress();
		int owner_id;
		
		String addOwnerSQL = "insert into klient(imie, nazwisko, email, telefon, adres) values('"+name+"','"+surname+"','"+email+"','"+tel_number+"','"+address+"');";
		
		
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(addOwnerSQL, Statement.RETURN_GENERATED_KEYS);
			
			int affectedRows = statement.executeUpdate();
			
			ResultSet generatedKeys = statement.getGeneratedKeys();
			
			if (generatedKeys.next()) {
				owner_id = generatedKeys.getInt(1);
				owner.setOwner_id(owner_id);
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
			
			statement.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	@Override
	public int addOwnerInt(Owner owner) {
		String name = owner.getName();
		String surname = owner.getSurname();
		String email = owner.getEmail();
		String tel_number = owner.getTelephone_number();
		String address = owner.getAddress();
		int owner_id = 0;
		
		String addOwnerSQL = "insert into klient(imie, nazwisko, email, telefon, adres) values('"+name+"','"+surname+"','"+email+"','"+tel_number+"','"+address+"');";
		
		
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(addOwnerSQL, Statement.RETURN_GENERATED_KEYS);
			
			int affectedRows = statement.executeUpdate();
			
			ResultSet generatedKeys = statement.getGeneratedKeys();
			
			if (generatedKeys.next()) {
				owner_id = generatedKeys.getInt(1);
				owner.setOwner_id(owner_id);
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
			
			statement.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return owner_id;
		
	}

	@Override
	public void updateOwner(Owner owner) {
		// TODO Auto-generated method stub
		
	}

	public Owner get(String email) {
		String ownersSql = "select id, imie, nazwisko, email, telefon, adres  from klient where email = '" + email + "';";
		
		Owner owner = new Owner();
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(ownersSql);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				owner.setOwner_id(rs.getInt(1));
				owner.setName(rs.getString(2));
				owner.setSurname(rs.getString(3));
				owner.setEmail(rs.getString(4));
				owner.setTelephone_number(rs.getString(5));
				owner.setAddress(rs.getString(6));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return owner;
	}

}

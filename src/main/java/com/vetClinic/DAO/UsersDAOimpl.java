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
import com.vetClinic.admin.UserAddConstraint;
import com.vetClinic.admin.UserMaintainer;
import com.vetClinic.dbhelper.DBconfig;
import com.vetClinic.exception.ExceptionController;

public class UsersDAOimpl implements UsersDAO {
	
	

	private JdbcTemplate jdbcTemplate;
	private DataSource dS;
	
	public UsersDAOimpl() {
		DBconfig config = new DBconfig();
		DataSource dataSource = config.dataSource();
		jdbcTemplate = new JdbcTemplate(dataSource);
		dS = dataSource;
	}
	
	@Override
	public void addUser(UserMaintainer maintainer) {
		
		int user_id;
		String username = maintainer.getUsername();
		String userSurname = maintainer.getUserSurName();
		boolean is_admin = maintainer.getIs_admin();
		boolean is_doctor = maintainer.isIs_doctor();
		String user_email = maintainer.getEmail();
		String password = maintainer.getPassword();
		String usernameSql = "insert into uzytkownik(imie, nazwisko, email) values('" +username + "','" + userSurname +"','" + user_email + "');";
		
		
		
		try {
			Connection connection = dS.getConnection();
			
			//connection.setAutoCommit(false);
			
			PreparedStatement add_statement = connection.prepareStatement(usernameSql, Statement.RETURN_GENERATED_KEYS);
			
			int affected_rows = add_statement.executeUpdate();
			
			ResultSet generatedKeys = add_statement.getGeneratedKeys();
			
			if (generatedKeys.next()) {
				user_id = generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
			
			add_statement.close();
			
			jdbcTemplate.execute("insert into uzytkownik_haslo(id_uzytkownika, haslo) values("+user_id+", '" + password + "');");
			
			if(is_admin) {
				jdbcTemplate.execute("insert into uzytkownik_rola(uzytkownik_id, rola_id) values("+user_id+", 1);");
			}
			jdbcTemplate.execute("insert into uzytkownik_rola(uzytkownik_id, rola_id) values("+user_id+", 2);");
			
			connection.setAutoCommit(true);
			
			if(is_doctor) {
				//TODO modify accordingly
				jdbcTemplate.execute("insert into uzytkownik_rola(uzytkownik_id, rola_id) values("+user_id+", 3);");
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
			
			
	}

	@Override
	public void delete(int user_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserMaintainer get(int user_id) {
		
		String userSql = "select id, imie, nazwisko, email from uzytkownik where id = " + user_id + ";";
		String userRoleSql = "select * from uzytkownik_rola ur, rola r where ur.rola_id = r.id and uzytkownik_id = " + user_id + " and r.nazwa = 'ROLE_ADMIN'	;";
		String getDoctorId = "select id from uzytkownik u, uzytkownik_rola ur where u.id = ur.uzytkownik_id and u.id = " + user_id + " and ur.rola_id = 3 "+ ";";
		
		UserMaintainer usr = new UserMaintainer();
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement userSql_statement = connection.prepareStatement(userSql);
			PreparedStatement userRoleSql_statement = connection.prepareStatement(userRoleSql);
			PreparedStatement getDoctorIdSql_statement = connection.prepareStatement(getDoctorId);
			
			ResultSet userSql_rs = userSql_statement.executeQuery();
			ResultSet userRoleSql_rs = userRoleSql_statement.executeQuery();
			ResultSet doctorId_rs = getDoctorIdSql_statement.executeQuery();
			
			while (userSql_rs.next()) {
				usr.setId(userSql_rs.getInt(1));
				usr.setUsername(userSql_rs.getString(2));
				usr.setUserSurName(userSql_rs.getString(3));
				usr.setEmail(userSql_rs.getString(4));
				usr.setDoctor_id(doctorId_rs.getInt(1));
			}
			
			usr.setIs_admin(true);
			
			if(!userRoleSql_rs.next()) {
				usr.setIs_admin(false);
			}
			
			usr.setIs_doctor(false);
			
			if(doctorId_rs.next()) {
				usr.setIs_doctor(true);
				usr.setDoctor_id(doctorId_rs.getInt(1));
			}
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		return usr;
		
	}
	
	@Override
	public UserMaintainer get(String email) {
		
		
		String userSql = "select id, imie, nazwisko, email from uzytkownik where email = '" + email + "';";
		String userRoleSql = "select * from uzytkownik_rola r left join uzytkownik u on r.uzytkownik_id = u.id where u.email = '" + email + "' and r.rola_id = 1	;";
		String getDoctorId = "select u.id from uzytkownik u left join uzytkownik_rola ur on u.id = ur.uzytkownik_id where ur.rola_id = 3 and u.email = '" + email + "';";
		
		UserMaintainer usr = new UserMaintainer();
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement userSql_statement = connection.prepareStatement(userSql);
			PreparedStatement userRoleSql_statement = connection.prepareStatement(userRoleSql);
			PreparedStatement getDoctorId_statement = connection.prepareStatement(getDoctorId);
			
			ResultSet userSql_rs = userSql_statement.executeQuery();
			ResultSet userRoleSql_rs = userRoleSql_statement.executeQuery();
			ResultSet getDoctorId_rs = getDoctorId_statement.executeQuery();
			while (userSql_rs.next()) {
				usr.setId(userSql_rs.getInt(1));
				usr.setUsername(userSql_rs.getString(2));
				usr.setUserSurName(userSql_rs.getString(3));
				usr.setEmail(userSql_rs.getString(4));
				
			}
			
			usr.setIs_doctor(false);
			
			if(getDoctorId_rs.next()) {
				usr.setDoctor_id(getDoctorId_rs.getInt(1));
				usr.setIs_doctor(true);
			}
			
			usr.setIs_admin(true);
			
			if(!userRoleSql_rs.next()) {
				usr.setIs_admin(false);
			}
			
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		return usr;
		
	}

	@Override
	public List<UserMaintainer> list() {
		List<UserMaintainer> usersList = new ArrayList<UserMaintainer>();
		
		String usersSql = "select distinct u.id, u.imie, u.nazwisko, u.email, ur1.rola_id as admin, ur2.rola_id as user, ur3.rola_id as doctor from uzytkownik u left join uzytkownik_rola ur1 on u.id = ur1.uzytkownik_id and ur1.rola_id = 1 " + 
				"left join uzytkownik_rola ur2 on u.id = ur2.uzytkownik_id and ur2.rola_id = 2 " + 
				"left join uzytkownik_rola ur3 on u.id = ur3.uzytkownik_id and ur3.rola_id = 3;";
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(usersSql);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				UserMaintainer user = new UserMaintainer();
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setUserSurName(rs.getString(3));
				user.setEmail(rs.getString(4));
				if(rs.getInt(5)==1) {
					user.setIs_admin(true);
					user.setDoctor_id(rs.getInt(1));
				}
				if(rs.getInt(7)==3) {
					user.setIs_doctor(true);
					user.setDoctor_id(rs.getInt(1));
				}
				
				usersList.add(user);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return usersList;
	}
	
	@Override
	public List<UserMaintainer> listDoctors() {
		List<UserMaintainer> usersList = new ArrayList<UserMaintainer>();
		
		String usersSql = "select distinct u.id, u.imie, u.nazwisko, u.email from uzytkownik u inner join uzytkownik_rola ur on u.id = ur.uzytkownik_id where ur.rola_id = 3;";
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(usersSql);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				UserMaintainer user = new UserMaintainer();
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setUserSurName(rs.getString(3));
				user.setEmail(rs.getString(4));
				usersList.add(user);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return usersList;
	}

	@Override
	public void updateUser(UserMaintainer maintainer) {
		int user_id = maintainer.getId();
		String username = maintainer.getUsername();
		String userSurname = maintainer.getUserSurName();
		String user_email = maintainer.getEmail();
		String updateSql = "update uzytkownik set imie = '" + username + "', nazwisko = '" + userSurname + "', email = '" + user_email + "' where id = " + user_id + ";";
		
		System.out.println(updateSql);
		
		//TODO check that
		String deleteDoctorInfo = "delete from uzytkownik_rola where uzytkownik_id ="+user_id+" and rola_id = 3;";
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(updateSql);
			PreparedStatement insertDoctor_statement = connection.prepareStatement(updateSql);
			PreparedStatement deleteDoctor_statement = connection.prepareStatement(deleteDoctorInfo);
			
			statement.executeUpdate();
			
			deleteDoctor_statement.executeUpdate();
			
			//TODO something weird happens here. It should first of all delete all records with doctor role for this user and then decide if add something
			if(maintainer.isIs_doctor()) {
				jdbcTemplate.execute("insert into uzytkownik_rola values(3,"+user_id+");");
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	

	//TODO to rearrange
	@Override
	public void updateUserRole(UserMaintainer maintainer) {
		
		int userid = maintainer.getId();
		
		String deleteQuery = "delete from uzytkownik_rola where uzytkownik_id = " + userid + ";";
		
		jdbcTemplate.execute(deleteQuery);
		
		jdbcTemplate.execute("insert into uzytkownik_rola(rola_id, uzytkownik_id) values(2,"+userid+" );");
		
		if(maintainer.getIs_admin()) {
			jdbcTemplate.execute("insert into uzytkownik_rola(rola_id, uzytkownik_id) values(1, "+userid+");");
		}
		if(maintainer.isIs_doctor()) {
			jdbcTemplate.execute("insert into uzytkownik_rola(rola_id, uzytkownik_id) values(3, "+userid+");");
		}
		
		
		
	}
	
	@Override
	public boolean checkIfUsernameUnique (String email) {
		String checkINameUniqueSql = "select * from uzytkownik where email = '" + email + "';";
		
		System.out.println(checkINameUniqueSql);
		try {
			Connection connection = dS.getConnection();
			
			PreparedStatement check_name_statement = connection.prepareStatement(checkINameUniqueSql);
			
			ResultSet check_name_rs = check_name_statement.executeQuery();
			
			if(check_name_rs.next()) {
				return false;
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return true;
	}

	@Override
	public void removeUser(int id) {
		String removeShiftsSql = "delete from dyzur where uzytkownik_id = " + id + ";";
		String removeRolesSql = "delete from uzytkownik_rola where uzytkownik_id = " + id + ";";
		String removePassSql = "delete from uzytkownik_haslo where id_uzytkownika = " + id + ";";
		String removeUserSql = "delete from uzytkownik where id = " + id + ";";
		
		try {
			Connection connection = dS.getConnection();
			
			PreparedStatement removeShiftsStatement = connection.prepareStatement(removeShiftsSql);
			PreparedStatement removeRoleStatement = connection.prepareStatement(removeRolesSql);
			PreparedStatement removePassStatement = connection.prepareStatement(removePassSql);
			PreparedStatement removeUserStatement = connection.prepareStatement(removeUserSql);
			
			removeShiftsStatement.executeUpdate();
			removeRoleStatement.executeUpdate();
			removePassStatement.executeUpdate();
			removeUserStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void updateDoctorBoolean(UserMaintainer maintainer) {
		// TODO Auto-generated method stub
		
	}
	
}

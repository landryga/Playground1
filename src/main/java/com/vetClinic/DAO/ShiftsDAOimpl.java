package com.vetClinic.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.jdbc.Statement;
import com.vetClinic.dbhelper.DBconfig;
import com.vetClinic.environmentalHelper.DateParser;
import com.vetClinic.shifts.Shift;

public class ShiftsDAOimpl implements ShiftsDAO {
	
	private JdbcTemplate jdbcTemplate;
	private DataSource dS;
	
	public ShiftsDAOimpl() {
		DBconfig config = new DBconfig();
		DataSource dataSource = config.dataSource();
		jdbcTemplate = new JdbcTemplate(dataSource);
		dS = dataSource;
	}

	@Override
	public Shift get(int shift_id) {
		return null;
	}

	@Override
	public List<Shift> list() {
		List<Shift> shiftsList = new ArrayList<Shift>();
		
		String sql = "select d.id, d.uzytkownik_id, d.rozpoczecie, d.zakonczenie, concat(u.imie, concat(' ', u.nazwisko)) as username from dyzur d left join uzytkownik u on d.uzytkownik_id = u.id;";
		
		
		
		try {
			
			Connection connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet shifts_rs = statement.executeQuery();
			
			while(shifts_rs.next()) {
				Shift shift = new Shift();
				
				String start_date = DateParser.convertFormat(shifts_rs.getString(3), "yyyy-MM-dd hh:mm:ss");
				String end_date = DateParser.convertFormat(shifts_rs.getString(4), "yyyy-MM-dd hh:mm:ss");
				
				
				shift.setId(shifts_rs.getInt(1));
				shift.setUser_id(shifts_rs.getInt(2));
				shift.setStart_date(start_date);
				shift.setEnd_date(end_date);
				shift.setUsername(shifts_rs.getString(5));
				
				shiftsList.add(shift);
				
			}
			
			
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return shiftsList;
	}

	

	@Override
	public int addShift(Shift shift) {
		
		String finalStartDate = DateParser.convertFormat(shift.getStart_date(), "dd/MM/yyyy hh:mm");
		String finalEndDate = DateParser.convertFormat(shift.getEnd_date(), "dd/MM/yyyy hh:mm");
		

		String sql = "insert into dyzur(uzytkownik_id, rozpoczecie, zakonczenie) values(" + shift.getUser_id() + ", str_to_date('" + finalStartDate + "','%m/%d/%Y %H:%i'), str_to_date( '" + finalEndDate + "', '%m/%d/%Y %H:%i'));";
		
		System.out.println(sql);
		
		Connection connection;
		try {
			connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return 0;
	}

	@Override
	public void updateShift(Shift shift) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteShift(int id) {
		String sql = "delete from dyzur where id = " + id + ";";
		
		Connection connection;
		try {
			connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.executeUpdate(sql);
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public List<Shift> listDoctorsShifts() {
		List<Shift> shiftsList = new ArrayList<Shift>();
		
		String sql = "select d.id, d.uzytkownik_id, d.rozpoczecie, d.zakonczenie, concat(u.imie, concat(' ', u.nazwisko)) as username from dyzur d inner join uzytkownik u on d.uzytkownik_id = u.id inner join uzytkownik_rola ur on u.id=ur.uzytkownik_id where ur.rola_id = 3;";
		
		try {
			
			Connection connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet shifts_rs = statement.executeQuery();
			
			while(shifts_rs.next()) {
				Shift shift = new Shift();
				
				String start_date = DateParser.convertFormat(shifts_rs.getString(3), "yyyy-MM-dd hh:mm:ss");
				String end_date = DateParser.convertFormat(shifts_rs.getString(4), "yyyy-MM-dd hh:mm:ss");
				
				
				shift.setId(shifts_rs.getInt(1));
				shift.setUser_id(shifts_rs.getInt(2));
				shift.setStart_date(start_date);
				shift.setEnd_date(end_date);
				shift.setUsername(shifts_rs.getString(5));
				
				shiftsList.add(shift);
				
			}
			
			connection.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return shiftsList;
	}


}

package com.vetClinic.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.vetClinic.dbhelper.DBconfig;
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
		List<Shift> shiftsList = new ArrayList<Shift>();
		
		String sql = "select d.id, d.uzytkownik_id, d.rozpoczecie, d.zakonczenie, concat(u.imie, concat(' ', u.nazwisko)) as username from dyzur d left join uzytkownik u on d.uzytkownik_id = u.id;";
		
		try {
			
			Connection connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet shifts_rs = statement.executeQuery();
			
			while(shifts_rs.next()) {
				Shift shift = new Shift();
				
				shift.setShiftId(shifts_rs.getInt(1));
				shift.setUser_id(shifts_rs.getInt(2));
				shift.setStart_date(shifts_rs.getDate(3));
				shift.setEnd_date(shifts_rs.getDate(4));
				shift.setUsername(shifts_rs.getString(5));
				
			}
			
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}

	@Override
	public List<Shift> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Shift> list(String doctor_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addShift(Shift shift) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateShift(Shift shift) {
		// TODO Auto-generated method stub
		
	}

}

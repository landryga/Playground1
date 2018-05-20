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
import com.vetClinic.consultation.Consultation;
import com.vetClinic.dbhelper.DBconfig;
import com.vetClinic.patients.Patient;

public class ConsultationDAOimpl implements ConsultationDAO {
	
	private JdbcTemplate jdbcTemplate;
	private DataSource dS;
	
	public ConsultationDAOimpl() {
		DBconfig config = new DBconfig();
		DataSource dataSource = config.dataSource();
		jdbcTemplate = new JdbcTemplate(dataSource);
		dS = dataSource;
	}

	@Override
	public void delete(int consultation_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Consultation get(String consultation_name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Consultation get(int consultation_id) {
		
		String consultationSql = "select id, opis, cena from wizyta where id = " + consultation_id + " and czy_rozpoczeta = 1;";
		
		Consultation consultation = new Consultation();
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(consultationSql);
			
			
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				
				consultation.setConsultation_id(rs.getInt(1));
				consultation.setConsultation_name(rs.getString(2));
				consultation.setPrice(rs.getFloat(3));
				
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return consultation;
	}

	@Override
	public List<Consultation> list() {
		List<Consultation> consultationsList = new ArrayList<Consultation>();
		
		String consultationsSql = "select id, opis, cena from wizyta where czy_rozpoczeta = 1;";
				
		try {
			Connection connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(consultationsSql);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				Consultation consultation = new Consultation();
				
				consultation.setConsultation_id(rs.getInt(1));
				consultation.setConsultation_name(rs.getString(2));
				consultation.setPrice(rs.getFloat(3));
				
				consultationsList.add(consultation);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return consultationsList;
	}

	@Override
	public void addConsultation(Consultation consultation) {
		
		String consultation_name = consultation.getConsultation_name();
		float price = consultation.getPrice();
		
		String addConsultationSQL = "insert into consultation(consultation, price) values('"+consultation_name+"','"+price+"');";
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(addConsultationSQL);
			
			statement.executeUpdate();
			
			statement.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void updateConsultation(Consultation consultation) {
		String consultation_name = consultation.getConsultation_name();
		float price = consultation.getPrice();
		int id = consultation.getConsultation_id();
		
		String updateConsultationSQL = "update wizyta set opis = '" + consultation_name + "', cena = '" + price + ", czy_rozpoczeta = 1 " +"' where id = " + id + ";";
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(updateConsultationSQL);
			
			statement.executeUpdate();
			
			statement.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	

}

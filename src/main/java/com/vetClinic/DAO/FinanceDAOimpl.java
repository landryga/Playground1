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
import com.vetClinic.finances.FinanceDay;
import com.vetClinic.goods.Good;

public class FinanceDAOimpl implements FinanceDAO {

	private JdbcTemplate jdbcTemplate;
	private DataSource dS;
	
	public FinanceDAOimpl() {
		DBconfig config = new DBconfig();
		DataSource dataSource = config.dataSource();
		jdbcTemplate = new JdbcTemplate(dataSource);
		dS = dataSource;
	}
	
	public List<FinanceDay> list()  {

		List<FinanceDay> financeList = new ArrayList<FinanceDay>();
		
		String financeSql = "select date, sum(income) as income from ( " + 
				"select v.data as date, vg.ilosc, g.cena, vg.ilosc * g.cena as income from produkt_wizyta vg " + 
				"left join produkt g " + 
				"on vg.produkt_id = g.id " + 
				"left join visit v " + 
				"on vg.wizyta_id = v.id " + 
				"order by date desc " + 
				") V group by date order by date desc;";
				
		try {
			Connection connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(financeSql);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				FinanceDay day = new FinanceDay();
				day.setDate(rs.getDate(1));
				day.setIncome(rs.getFloat(2));
				financeList.add(day);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return financeList;
	}
}

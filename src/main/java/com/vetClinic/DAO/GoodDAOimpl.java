package com.vetClinic.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.vendor.Database;

import com.vetClinic.consultation.Consultation;
import com.vetClinic.dbhelper.DBconfig;
import com.vetClinic.environmentalHelper.ObjectComparator;
import com.vetClinic.goods.Good;
import com.vetClinic.visits.VisitGood;

public class GoodDAOimpl implements GoodDAO {

	private JdbcTemplate jdbcTemplate;
	private DataSource dS;
	
	public GoodDAOimpl() {
		DBconfig config = new DBconfig();
		DataSource dataSource = config.dataSource();
		jdbcTemplate = new JdbcTemplate(dataSource);
		dS = dataSource;
	}

	
	@Override
	public void delete(int good_id) {
		
	}

	@Override
	public Good get(String good_name) {
		String goodSql = "select id, nazwa, ilosc, cena from produkt where nazwa = '" + good_name + "';";
		
		Good good = new Good();
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(goodSql);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				
				good.setId(rs.getInt(1));
				good.setName(rs.getString(2));
				good.setQuantity(rs.getInt(3));
				good.setPrice(rs.getFloat(4));
				
			}
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return good;
	}

	@Override
	public Good get(int id) {
		
		String goodSql = "select id, nazwa, ilosc, cena from produkt where id = " + id + ";";
		
		Good good = new Good();
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(goodSql);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				
				good.setId(rs.getInt(1));
				good.setName(rs.getString(2));
				good.setQuantity(rs.getInt(3));
				good.setPrice(rs.getFloat(4));
				
			}
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return good;
	}

	@Override
	public List<Good> list() {
		List<Good> goodsList = new ArrayList<Good>();
		
		String goodsSql = "select id, nazwa, ilosc, cena from produkt;";
				
		try {
			Connection connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(goodsSql);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				Good good = new Good();
				
				good.setId(rs.getInt(1));
				good.setName(rs.getString(2));
				good.setQuantity(rs.getInt(3));
				good.setPrice(rs.getFloat(4));
				
				
				goodsList.add(good);
			}
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return goodsList;
	}

	@Override
	public void addGood(Good good) {
		
		String good_name = good.getName();
		int quantity = good.getQuantity();
		float price = good.getPrice();
		
		String addGoodSQL = "insert into produkt(nazwa, ilosc, cena) values('"+good_name+"','"+quantity+"', '"+price+ "');";
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(addGoodSQL);
			
			statement.executeUpdate();
			
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void addVisitGood(VisitGood visitgood) {
		
		int visit_id = visitgood.getVisit_id();
		int good_id = visitgood.getId();
		int qty = visitgood.getQty();
		
		String addVisitGoodSQL = "insert into produkt_wizyta(wizyta_id, produkt_id, ilosc) values('"+visit_id+"','"+good_id+"', '"+qty+ "');";
		
		String updateInventorySQL = "update produkt set ilosc = ilosc - " + qty + " where id = " + good_id + ";";
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement addvg_statement = connection.prepareStatement(addVisitGoodSQL);
			PreparedStatement updI_statement = connection.prepareStatement(updateInventorySQL);
			
			addvg_statement.executeUpdate();
			
			updI_statement.executeUpdate();
			
			addvg_statement.close();
			updI_statement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void addVisitGoods(List<VisitGood> vgs) {
		
		Collections.sort(vgs, new ObjectComparator());
		
		int visit_id = vgs.get(0).getVisit_id();
		
		List<VisitGood> postVgs = new ArrayList<>();
		
		int counter =  0;
		int helper = 0;
		
		if(vgs.size()==1) {
			VisitGood fvg = vgs.get(0);
			
			fvg.setId(fvg.getId());
			fvg.setQty(fvg.getQty());
			
			postVgs.add(fvg);
		}
		
		else {
			for(int i=0; i< vgs.size()-1; i++) {
				
				VisitGood vg = vgs.get(i);
				VisitGood vg2 = vgs.get(i+1);
				
				VisitGood fvg = new VisitGood();
				
				counter+=vg.getQty();
				helper=vg.getId();
				
				if(vg2.getId()!=helper&& i!= vgs.size()-2) {
					fvg.setId(helper);
					fvg.setQty(counter);
					fvg.setVisit_id(vg.getVisit_id());
					postVgs.add(fvg);
					counter=0;
				}
				else if(vg2.getId()!=helper&&i==vgs.size()-2) {
					fvg.setId(helper);
					fvg.setQty(counter);
					fvg.setVisit_id(vg.getVisit_id());
					postVgs.add(fvg);
					postVgs.add(vg2);
					counter=0;
				}
				else if (vg2.getId()==helper&&i==vgs.size()-2) {
					counter+=vg2.getQty();
					fvg.setId(helper);
					fvg.setQty(counter);
					fvg.setVisit_id(vg.getVisit_id());
					postVgs.add(fvg);
					
				}
				
			}
		}
		
		
		
		
		String[] visit_sqls = new String[postVgs.size()];
		String[] inventory_sqls = new String[postVgs.size()];
		
		try {
			Connection connection = dS.getConnection();
			
			for(int k = 0; k < postVgs.size(); k++) {
				
				VisitGood vs = postVgs.get(k);
				int v = vs.getVisit_id();
				int q = vs.getQty();
				int g = vs.getId();
				
				visit_sqls[k] = "insert into produkt_wizyta(produkt_id, wizyta_id, ilosc) values(" + g + ", " + v + "," + q + ");";
				inventory_sqls[k] = "update produkt set ilosc = ilosc -" + q + " where id = " + g +";";
				
				System.out.println(visit_sqls[k]);
				System.out.println(inventory_sqls[k]);
				
			}
			
			PreparedStatement addvg_statement;
			PreparedStatement updI_statement;
			
			for ( int j = 0; j<visit_sqls.length; j++) {
				addvg_statement = connection.prepareStatement(visit_sqls[j]);
				updI_statement = connection.prepareStatement(inventory_sqls[j]);
				
				addvg_statement.executeUpdate();
				
				updI_statement.executeUpdate();
				
				addvg_statement.close();
				updI_statement.close();
			}
			
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public void updateGood(Good good) {
		String good_name = good.getName();
		int quantity = good.getQuantity();
		float price = good.getPrice();
		int id = good.getId();
		
		String updateGoodSQL = "update produkt set nazwa = '" + good_name + "', ilosc = " + quantity + ", cena = '" + price + "' where id =" + id + ";";
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(updateGoodSQL);
			
			statement.executeUpdate();
			
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void updateGoodQuantity(Good good) {
		int quantity_change = good.getQuantity_change();
		int id = good.getId();
		
		String qtyChangeSql = "update produkt set ilosc = ilosc - " + quantity_change + " where id = " + id + ";";
		

		try {
			Connection connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(qtyChangeSql);
			
			statement.executeUpdate();
			
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}



	

}


package com.aurospaces.neighbourhood.db.basedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import com.aurospaces.neighbourhood.bean.TariffmasterBean;
import com.aurospaces.neighbourhood.daosupport.CustomConnection;


public class BaseTariffmasterDao{

	@Autowired
	CustomConnection custom;
	JdbcTemplate jdbcTemplate;
 
	public final String INSERT_SQL = "INSERT INTO tariffmaster( created_time, updated_time, assetcode, assetdescription, rate, alloweddiscount, remarks, status) values (?, ?, ?, ?, ?, ?, ?, ?)"; 





	/* this should be conditional based on whether the id is present or not */
	@Transactional
	public void save(final TariffmasterBean tariffmaster) 
	{
		jdbcTemplate = custom.getJdbcTemplate();
	if(tariffmaster.getId() == 0)	{

	KeyHolder keyHolder = new GeneratedKeyHolder();
	int update = jdbcTemplate.update(
			new PreparedStatementCreator() {
					public PreparedStatement 
					createPreparedStatement(Connection connection) throws SQLException {
	
					if(tariffmaster.getCreatedTime() == null)
					{
					tariffmaster.setCreatedTime( new Date());
					}
					java.sql.Timestamp createdTime = 
						new java.sql.Timestamp(tariffmaster.getCreatedTime().getTime()); 
							
					if(tariffmaster.getUpdatedTime() == null)
					{
					tariffmaster.setUpdatedTime( new Date());
					}
					java.sql.Timestamp updatedTime = 
						new java.sql.Timestamp(tariffmaster.getUpdatedTime().getTime()); 
							
					PreparedStatement ps =
									connection.prepareStatement(INSERT_SQL,new String[]{"id"});
	ps.setTimestamp(1, createdTime);
ps.setTimestamp(2, updatedTime);
ps.setString(3, tariffmaster.getAssetcode());
ps.setString(4, tariffmaster.getAssetdescription());
ps.setString(5, tariffmaster.getRate());
ps.setString(6, tariffmaster.getAlloweddiscount());
ps.setString(7, tariffmaster.getRemarks());
ps.setString(8, tariffmaster.getStatus());

							return ps;
						}
				},
				keyHolder);
				
				Number unId = keyHolder.getKey();
				tariffmaster.setId(unId.intValue());
				

		}
		else
		{

			String sql = "UPDATE tariffmaster  set assetcode = ? ,assetdescription = ? ,rate = ? ,alloweddiscount = ? ,remarks = ? ,status = ?  where id = ? ";
	
			jdbcTemplate.update(sql, new Object[]{tariffmaster.getAssetcode(),tariffmaster.getAssetdescription(),tariffmaster.getRate(),tariffmaster.getAlloweddiscount(),tariffmaster.getRemarks(),tariffmaster.getStatus(),tariffmaster.getId()});
		}
	}
		
		@Transactional
		public boolean delete(int id,String status) {
			jdbcTemplate = custom.getJdbcTemplate();
			boolean delete = false;
			try{
			String sql = "update tariffmaster set status='"+status+"'  WHERE id=?";
			int intDelete = jdbcTemplate.update(sql, new Object[]{id});
			jdbcTemplate.update(sql, new Object[]{id});
			if(intDelete != 0){
				delete = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
			
			return delete;
		}
		

	 public TariffmasterBean getById(int id) {
		 jdbcTemplate = custom.getJdbcTemplate();
			String sql = "SELECT * from tariffmaster where id = ? ";
			List<TariffmasterBean> retlist = jdbcTemplate.query(sql,
			new Object[]{id},
			ParameterizedBeanPropertyRowMapper.newInstance(TariffmasterBean.class));
			if(retlist.size() > 0)
				return retlist.get(0);
			return null;
		}

	

}

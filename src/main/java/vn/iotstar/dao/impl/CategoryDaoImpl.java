package vn.iotstar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import vn.iotstar.configs.DBConnectMySQL;
import vn.iotstar.dao.ICategoryDao;
import vn.iotstar.dao.IUserDao;
import vn.iotstar.models.CategoryModel;
import vn.iotstar.models.UserModel;

public class CategoryDaoImpl implements ICategoryDao {
	public Connection conn= null;
	public PreparedStatement ps= null;
	public ResultSet rs = null;
	
	public static void main(String[] args) {
		try {
			ICategoryDao cateDao = new CategoryDaoImpl();
			CategoryModel cate = new CategoryModel();
			
			List <CategoryModel> list = new ArrayList<>();
			list = cateDao.findAll();
			for(int i=0;i<list.size();i++){
			    System.out.println(list.get(i));
			} 
			
			System.out.println("sau khi insert");
			cate.setCategoryname("oppo123");
			cate.setImages("https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/t/e/text_ng_n_8__4_102.png");
			cate.setStatus(0);
			cateDao.insert(cate);
			
			list = cateDao.findAll();
			for(int i=0;i<list.size();i++){
			    System.out.println(list.get(i));
			} 
			
			
			System.out.println("sau khi update");
			cate = cateDao.findByname("oppo123");
			cate.setCategoryname("iphone13");
			cate.setImages("https://cdn.tgdd.vn/Products/Images/42/223602/iphone-13-xanh-la-thumb-new-600x600.jpg");
			cate.setStatus(0);
			cateDao.update(cate);
			list = cateDao.findAll();
			for(int i=0;i<list.size();i++){
			    System.out.println(list.get(i));
			} 
			
			
			System.out.println("tim name");
			cate = cateDao.findByname("samsung");
			
			System.out.println("sau khi insert lan 2");
			cate = new CategoryModel();
			cate.setCategoryname("oppolan2");
			cate.setImages("https://cdn2.cellphones.com.vn/insecure/rs:fill:0:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/t/e/text_ng_n_8__4_102.png");
			cate.setStatus(0);
			cateDao.insert(cate);
			list = cateDao.findAll();
			for(int i=0;i<list.size();i++){
			    System.out.println(list.get(i));
			} 
			
			
			
			
	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<CategoryModel> findAll() {
		String sql = "select * from category";
		List <CategoryModel> list = new ArrayList<>();
		try {
			new DBConnectMySQL();
			conn= DBConnectMySQL.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			CategoryModel category;
			
			while (rs.next()) {
				category = new CategoryModel();
				category.setCategoryid(rs.getInt("categoryid"));
				category.setCategoryname(rs.getString("categoryname"));
				category.setImages(rs.getString("images"));
				category.setStatus(rs.getInt("status"));
				list.add(category);
			}
			
			conn.close();
			ps.close();
			rs.close();
			
			return list;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public CategoryModel findById(int id) {
		String sql = "select * from category where categoryid = ?";
		try {
			new DBConnectMySQL();
			conn= DBConnectMySQL.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			CategoryModel category = new CategoryModel();
			
			while (rs.next()) {
				category.setCategoryid(rs.getInt("categoryid"));
				category.setCategoryname(rs.getString("categoryname"));
				category.setImages(rs.getString("images"));
				category.setStatus(rs.getInt("status"));
			}
		
			conn.close();
			ps.close();
			rs.close();
			return category;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public CategoryModel findByname(String name) {
		String sql = "select * from category where categoryname = ?";
		try {
			new DBConnectMySQL();
			conn= DBConnectMySQL.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			CategoryModel category = new CategoryModel();
			
			while (rs.next()) {
				category.setCategoryid(rs.getInt("categoryid"));
				category.setCategoryname(rs.getString("categoryname"));
				category.setImages(rs.getString("images"));
				category.setStatus(rs.getInt("status"));
			}
		
			conn.close();
			ps.close();
			rs.close();
			return category;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public void insert(CategoryModel category) {
		String sql = "insert into category (categoryname, images, status) values (?,?,?)";
		try {
			new DBConnectMySQL();
			conn= DBConnectMySQL.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, category.getCategoryname());
			ps.setString(2, category.getImages());
			ps.setInt(3, category.getStatus());
			
			ps.executeUpdate();
			
			conn.close();
			ps.close();
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

	@Override
	public void update(CategoryModel category) {
		String sql = "update category set categoryname=? , images=?, status=? where categoryid= ?";
		try {
			new DBConnectMySQL();
			conn= DBConnectMySQL.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, category.getCategoryname());
			ps.setString(2, category.getImages());
			ps.setInt(3, category.getStatus());
			ps.setInt(4, category.getCategoryid());
			
			ps.executeUpdate();
			
			conn.close();
			ps.close();
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Override
	public void delete(int id) {
		String sql = "delete from category where categoryid =?";
		try {
			new DBConnectMySQL();
			conn= DBConnectMySQL.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
		
			ps.executeUpdate();
			
			conn.close();
			ps.close();
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Override
	public void updateStatus(int id, int status) {
		String sql = "update category set status=? where categoryid = ?";
		try {
			new DBConnectMySQL();
			conn= DBConnectMySQL.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, status);
			ps.setInt(2, id);
			ps.executeUpdate();
			
			conn.close();
			ps.close();
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}

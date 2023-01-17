package com.SupplyChainManagementProject.DAO.concretes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.SupplyChainManagementProject.Core.DAO.GenericDAOImpl;
import com.SupplyChainManagementProject.Core.Helper.Exceptions;
import com.SupplyChainManagementProject.Core.Log.Log;
import com.SupplyChainManagementProject.DAO.abstracts.IProductImageDAO;
import com.SupplyChainManagementProject.Model.ProductImage;


public class ProductImageDAOImpl extends GenericDAOImpl<ProductImage> implements IProductImageDAO{
	private Connection con;
	private ResultSet rs;
	private static final String INSERT_PRODUCTIMAGE_SQL = "INSERT INTO product_image" + "  (image, product_id) OUTPUT Inserted.product_image_id VALUES " +
	        " (?, ?);";
	private static final String UPDATE_PRODUCTIMAGE_SQL = "update product_image set image = ?,product_id= ? where product_image_id = ?;";
	private static final String FINDBYPRODUCTID_SQL ="SELECT * FROM  product_image WHERE product_id = ?;";
	public ProductImageDAOImpl(Connection con) {
		super(con);
		this.con=con;
	}

	@Override
	public int add(ProductImage productImage) {
		// TODO Auto-generated method stub
        boolean rowUpdated=false;
        int id=0;
        try ( PreparedStatement statement = con.prepareStatement(INSERT_PRODUCTIMAGE_SQL);){
            statement.setString(1, productImage.getImage());
            statement.setInt(2, productImage.getProductId());
            rs= statement.executeQuery();
            while(rs.next()) {
            	id=rs.getInt("product_image_id");
            }
        }catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
        if(rowUpdated) {
        	Log.logger( productImage.getImage()+" foto  added.");
        }
        return id;
	}

	@Override
	public void update( ProductImage productImage) {
		// TODO Auto-generated method stub
        boolean rowUpdated=false;
        try ( PreparedStatement statement = con.prepareStatement(UPDATE_PRODUCTIMAGE_SQL);) {
            statement.setString(1, productImage.getImage());
            statement.setInt(2, productImage.getProductId());
            statement.setInt(3, productImage.getProductImageId());

            rowUpdated = statement.executeUpdate() > 0;
        }catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
        if(rowUpdated) {
        	Log.logger(productImage.getImage()+" foto  added.");
        }
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		if(delete("product_image",id)) { 
			Log.logger("productImage deleted");
		}
	}

	@Override
	public ProductImage findById(int id) {
		// TODO Auto-generated method stub
		   rs =findById("product_image",id); 
		   ProductImage productImage = convertToModel(rs);
		   return productImage; 
	}

	@Override
	public List<ProductImage> getAll() {
		// TODO Auto-generated method stub
		   rs =getAll("product_image"); 
		   List<ProductImage> productImages= convertToList(rs);
		   return productImages; 
	}
	private ProductImage convertToModel(ResultSet rs)  {
		ProductImage  productImage= new ProductImage ();
		try{
			while (rs.next()) {
				productImage.setProductImageId(rs.getInt("product_image_id"));
				productImage.setImage(rs.getString("image"));
				productImage.setProductId(rs.getInt("product_id"));
			}
		}catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
		return productImage;
	}
	private List<ProductImage> convertToList(ResultSet rs)  {
		List<ProductImage> productImageList= new ArrayList<ProductImage>();
		ProductImage  productImage= new ProductImage();
		try{
			while (rs.next()) {
				productImage.setProductImageId(rs.getInt("product_image_id"));
				productImage.setImage(rs.getString("image"));
				productImage.setProductId(rs.getInt("product_id"));
				productImageList.add(productImage);
			}
		}catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
		return productImageList;
	}

	@Override
	public List<ProductImage> findByProductId(int id) {
		List<ProductImage> productImages=new ArrayList<ProductImage>();
        try ( PreparedStatement statement = con.prepareStatement(FINDBYPRODUCTID_SQL);){
            statement.setInt(1, id);
            rs= statement.executeQuery();
            productImages = convertToList(rs);
        }catch (SQLException ex) {
			Exceptions.printSQLException(ex);
		}
        return productImages;
	}

}

package com.SupplyChainManagementProject.Service.concretes;

import java.util.ArrayList;
import java.util.List;

import com.SupplyChainManagementProject.Core.DbConnection.PostgreSqlDbConnection;
import com.SupplyChainManagementProject.DAO.abstracts.IRetailDAO;
import com.SupplyChainManagementProject.DAO.abstracts.ISupplierDAO;
import com.SupplyChainManagementProject.DAO.abstracts.IUserDAO;
import com.SupplyChainManagementProject.DAO.abstracts.IUserUserTypeDAO;
import com.SupplyChainManagementProject.DAO.concretes.RetailDAOImpl;
import com.SupplyChainManagementProject.DAO.concretes.SupplierDAOImpl;
import com.SupplyChainManagementProject.DAO.concretes.UserDAOImpl;
import com.SupplyChainManagementProject.DAO.concretes.UserUserTypeDAOImpl;
import com.SupplyChainManagementProject.Model.Retail;
import com.SupplyChainManagementProject.Model.Supplier;
import com.SupplyChainManagementProject.Model.User;
import com.SupplyChainManagementProject.Model.UserUserType;
import com.SupplyChainManagementProject.Service.abstracts.IUserService;
import com.SupplyChainManagementProject.ViewModel.UserModel;

public class UserServiceImpl implements IUserService{
	private IUserDAO userDAO;
	private IRetailDAO retailDAO;
	private ISupplierDAO supplierDAO;
	private IUserUserTypeDAO userUserTypeDAO;

	public UserServiceImpl(IUserDAO userDAO, IRetailDAO retailDAO, ISupplierDAO supplierDAO,
			IUserUserTypeDAO userUserTypeDAO) {
		super();
		this.userDAO = new UserDAOImpl(PostgreSqlDbConnection.getConnection());
		this.retailDAO = new RetailDAOImpl(PostgreSqlDbConnection.getConnection());
		this.supplierDAO = new SupplierDAOImpl(PostgreSqlDbConnection.getConnection());
		this.userUserTypeDAO = new UserUserTypeDAOImpl(PostgreSqlDbConnection.getConnection());
	}

	@Override
	public UserModel login(String email, String password) {
		User user=new User();
		UserModel userModel=new UserModel();
		user=this.userDAO.userLogin(email, password);
		userModel.setUser(user);
		/*
		if(this.retailDAO.findByUserId(user.getUserId()).getRetailId()!=0) {
			userModel.setRetail(this.retailDAO.findByUserId(user.getUserId()));
		}else if(this.supplierDAO.findByUserId(user.getUserId()).getSupplierId()!=0) {
			userModel.setSupplier(this.supplierDAO.findByUserId(user.getUserId()));
		}
		*/
		if(this.userUserTypeDAO.findByUserId(user.getUserId()).getUserTypeId()==2) {
			userModel.setRetail(this.retailDAO.findByUserId(user.getUserId()));
		}else if(this.userUserTypeDAO.findByUserId(user.getUserId()).getUserTypeId()==3) {
			userModel.setSupplier(this.supplierDAO.findByUserId(user.getUserId()));
		}
		
		return userModel;
	}

	@Override
	public void add(User user, Retail retail, Supplier supplier) {
		// TODO Auto-generated method stub
		 UserUserType userUserType=new UserUserType();
		int userId=this.userDAO.add(user);
		if(retail!=null) {
			retail.setUserId(userId);
			this.retailDAO.add(retail);
			userUserType.setUserId(userId);
			userUserType.setUserTypeId(2);
			this.userUserTypeDAO.add(userUserType);
		}else if(supplier!=null) {
			supplier.setUserId(userId);
			this.supplierDAO.add(supplier);
			userUserType.setUserId(userId);
			userUserType.setUserTypeId(3);
			this.userUserTypeDAO.add(userUserType);
		}
		
	}

	@Override
	public void update(User user, Retail retail, Supplier supplier) {
		this.userDAO.update(user);
		if(retail!=null) {
			this.retailDAO.update(retail);
		}else if(supplier!=null) {
			this.supplierDAO.update(supplier);
		}
	}

	@Override
	public void delete(int id) {
		this.userDAO.delete(id);
		if(this.userUserTypeDAO.findByUserId(id).getUserTypeId()==2) {
			this.retailDAO.delete(this.retailDAO.findByUserId(id).getRetailId());
		}else if(this.userUserTypeDAO.findByUserId(id).getUserTypeId()==3) {
			this.retailDAO.delete(this.retailDAO.findByUserId(id).getRetailId());
		}
		this.userUserTypeDAO.delete(this.userUserTypeDAO.findByUserId(id).getUserUserTypeId());
		
	}

	@Override
	public UserModel findById(int id) {
		// TODO Auto-generated method stub
		UserModel userModel=new UserModel();
		User user=this.userDAO.findById(id);
		userModel.setUser(user);
		if(this.userUserTypeDAO.findByUserId(id).getUserTypeId()==2) {
			userModel.setRetail(this.retailDAO.findByUserId(user.getUserId()));
		}else if(this.userUserTypeDAO.findByUserId(id).getUserTypeId()==3) {
			userModel.setSupplier(this.supplierDAO.findByUserId(user.getUserId()));
		}
		return userModel;
	}

	@Override
	public List<UserModel> getAll() {
		List<UserModel> userModels=new ArrayList<UserModel>();
		UserModel userModel=new UserModel();
		List<User> users=this.userDAO.getAll();
		for(User user:users) {
			userModel.setUser(user);
			if(this.userUserTypeDAO.findByUserId(user.getUserId()).getUserTypeId()==2) {
				userModel.setRetail(this.retailDAO.findByUserId(user.getUserId()));
			}else if(this.userUserTypeDAO.findByUserId(user.getUserId()).getUserTypeId()==3) {
				userModel.setSupplier(this.supplierDAO.findByUserId(user.getUserId()));
			}
			userModels.add(userModel);
		}
		return userModels;
	}
}

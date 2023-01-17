package com.SupplyChainManagementProject.Core.DAO;

import java.util.List;

public interface IGenericDAO<T>
{
       int add(T model);
       void update(T model);
       void delete(int id);
       T findById(int id);
       List<T> getAll();
}

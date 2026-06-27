package dao;

import java.sql.SQLException;
import java.util.List;

import model.Admin;

public interface CRUD<T> {
	void Insert(T t) throws Exception;

	void Update(T t) throws Exception;

	T Read(int id) throws Exception;

	List<T> ReadPool() throws Exception;

	void Delete(int id) throws Exception;
}

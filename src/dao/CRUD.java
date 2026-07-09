package dao;

import java.util.List;

public interface CRUD<T> {
	void Insert(T t);

	void Update(T t);

	T Read(int id);

	List<T> ReadPool();

	void Delete(int id);
}

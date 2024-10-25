package dao;

import java.util.List;

import entity.DienThoai;

public interface DienThoaiDao {
	List<DienThoai> getAll();

	DienThoai getById(int id);

	boolean insert(DienThoai dt);

	boolean update(DienThoai dt);

	boolean delete(int id);
	
	List<DienThoai> getByName(String name);
	
	List<DienThoai> getByNCC(int maNCC);

}

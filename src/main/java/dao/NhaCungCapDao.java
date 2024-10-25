package dao;

import java.util.List;

import entity.NhaCungCap;

public interface NhaCungCapDao {
	List<NhaCungCap> getAll();
	
	NhaCungCap getById(int id);
	
	boolean insert(NhaCungCap ncc);
	
	boolean update(NhaCungCap ncc);
	
	boolean delete(int id);
	
	List<NhaCungCap> getByName(String name);
	
	List<NhaCungCap> getByAddress(String address);
	
	List<NhaCungCap> getByPhone(String phone);
	
	
}

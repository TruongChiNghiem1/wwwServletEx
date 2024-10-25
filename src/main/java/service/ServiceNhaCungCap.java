package service;

import java.util.List;
import java.util.Set;

import dao.NhaCungCapDao;
import entity.NhaCungCap;
import impl.NhaCungCapImpl;
import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class ServiceNhaCungCap {
	private static NhaCungCapDao nccDao;
	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator;

	public ServiceNhaCungCap(EntityManager eManager) {
		nccDao = new NhaCungCapImpl(eManager);
		validator = factory.getValidator();
	}

	public List<NhaCungCap> getAll() {
		return nccDao.getAll();
	}

	public NhaCungCap getById(int id) {
		return nccDao.getById(id);
	}

	public String insert(String tenNCC, String diaChi, String soDienThoai) {
		String message = "Thêm thành công";
		NhaCungCap ncc = new NhaCungCap(tenNCC, diaChi, soDienThoai);
		Set<ConstraintViolation<NhaCungCap>> violations = validator.validate(ncc);
		for (ConstraintViolation<NhaCungCap> violation : violations) {
			return violation.getMessage();
		}
		if (!nccDao.insert(ncc)) {
			message = "Failde";
		}
		return message;
	}

	public String update(int id, String tenNCC, String diaChi, String soDienThoai) {
		String message = "";
		NhaCungCap ncc = new NhaCungCap(id, tenNCC, diaChi, soDienThoai);
		Set<ConstraintViolation<NhaCungCap>> violations = validator.validate(ncc);
		for (ConstraintViolation<NhaCungCap> violation : violations) {
			return violation.getMessage();
		}
		if (!nccDao.update(ncc)) {
			message = "Failde";
		}
		return message;
	}

	public boolean delete(int id) {
		return nccDao.delete(id);
	}

	public List<NhaCungCap> getByName(String name) {
		return nccDao.getByName(name);
	}

	public List<NhaCungCap> getByAddress(String address) {
		return nccDao.getByAddress(address);
	}

	public List<NhaCungCap> getByPhone(String phone) {
		return nccDao.getByPhone(phone);
	}

}

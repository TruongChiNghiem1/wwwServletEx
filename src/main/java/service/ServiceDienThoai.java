package service;

import java.time.Year;
import java.util.List;
import java.util.Set;

import dao.DienThoaiDao;
import entity.DienThoai;
import entity.NhaCungCap;
import impl.DienThoaiImpl;
import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class ServiceDienThoai {
	private static DienThoaiDao dThoaiDao;
	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator;

	public ServiceDienThoai(EntityManager eManager) {
		dThoaiDao = new DienThoaiImpl(eManager);
		validator = factory.getValidator();
	}

	public List<DienThoai> getAll() {
		return dThoaiDao.getAll();
	}

	public DienThoai getById(int id) {
		return dThoaiDao.getById(id);
	}

	public String insert(String tenDT, Year namSx, String cauHinh, int maNcc, String hinhAnh) {
		NhaCungCap ncc = new NhaCungCap();
		ncc.setMaNCC(maNcc);
		String message = "Thêm thành công";
		DienThoai dienThoai = new DienThoai(tenDT, namSx, cauHinh, ncc, hinhAnh);
		Set<ConstraintViolation<DienThoai>> violations = validator.validate(dienThoai);
		for (ConstraintViolation<DienThoai> violation : violations) {
			return violation.getMessage();
		}
		if (!dThoaiDao.insert(dienThoai)) {
			message = "Failde";
		}
		return message;
	}

	public String update(int id, String tenDT, Year namSx, String cauHinh, int maNcc, String hinhAnh) {
		NhaCungCap ncc = new NhaCungCap();
		ncc.setMaNCC(maNcc);
		String message = "Cập nhật thành công";
		DienThoai dienThoai = new DienThoai(id, tenDT, namSx, cauHinh, ncc, hinhAnh);
		Set<ConstraintViolation<DienThoai>> violations = validator.validate(dienThoai);
		for (ConstraintViolation<DienThoai> violation : violations) {
			return violation.getMessage();
		}
		if (!dThoaiDao.update(dienThoai)) {
			message = "Failde";
		}
		return message;
	}

	public boolean delete(int id) {
		return dThoaiDao.delete(id);
	}

	public List<DienThoai> getByName(String name) {
		return dThoaiDao.getByName(name);
	}

	public List<DienThoai> getByNCC(int maNCC) {
		return dThoaiDao.getByNCC(maNCC);
	}

}

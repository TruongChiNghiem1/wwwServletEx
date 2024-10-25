package impl;

import java.util.List;

import dao.DienThoaiDao;
import entity.DienThoai;
import jakarta.persistence.EntityManager;

public class DienThoaiImpl implements DienThoaiDao {
	private EntityManager eManager;

	public DienThoaiImpl(EntityManager eManager) {
		this.eManager = eManager;
	}

	@Override
	public List<DienThoai> getAll() {
		return eManager.createQuery("FROM DienThoai", DienThoai.class).getResultList();
	}

	@Override
	public DienThoai getById(int id) {
		return eManager.find(DienThoai.class, id);
	}

	@Override
	public boolean insert(DienThoai dt) {
		try {
			eManager.getTransaction().begin();
			eManager.persist(dt);
			eManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			if (eManager.getTransaction().isActive()) {
				eManager.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(DienThoai dt) {
		try {
			eManager.getTransaction().begin();
			DienThoai exitPhone = eManager.find(DienThoai.class, dt.getId());

			if (exitPhone != null) {
				if (dt.getHinhAnh() == null || dt.getHinhAnh().isEmpty()) {
					dt.setHinhAnh(exitPhone.getHinhAnh());
				}
			}
			eManager.merge(dt);
			eManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			if (eManager.getTransaction().isActive()) {
				eManager.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(int id) {
		try {
			eManager.getTransaction().begin();
			DienThoai dt = eManager.find(DienThoai.class, id);
			if (dt != null) {
				eManager.remove(dt);
				eManager.getTransaction().commit();
				return true;
			}
			eManager.getTransaction().rollback();
		} catch (Exception e) {
			if (eManager.getTransaction().isActive()) {
				eManager.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<DienThoai> getByName(String name) {
		List<DienThoai> dienThoais = eManager
				.createQuery("SELECT dt FROM DienThoai dt WHERE dt.tenDT LIKE :name", DienThoai.class)
				.setParameter("name", "%" + name + "%").getResultList();
		return dienThoais;
	}

	@Override
	public List<DienThoai> getByNCC(int maNCC) {
		List<DienThoai> dienThoais = eManager
				.createQuery("SELECT dt FROM DienThoai dt WHERE dt.nhaCungCap.maNCC = :maNCC", DienThoai.class)
				.setParameter("maNCC", maNCC).getResultList();
		return dienThoais;
	}

}

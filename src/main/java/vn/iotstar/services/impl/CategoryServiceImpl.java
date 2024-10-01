package vn.iotstar.services.impl;

import java.util.List;

import vn.iotstar.dao.ICategoryDao;
import vn.iotstar.dao.impl.CategoryDaoImpl;
import vn.iotstar.models.CategoryModel;
import vn.iotstar.services.ICategoryService;

public class CategoryServiceImpl implements ICategoryService {
	ICategoryDao cateDao = new CategoryDaoImpl() ;
	
	@Override
	public List<CategoryModel> findAll() {
		return cateDao.findAll();
	}

	@Override
	public CategoryModel findById(int id) {
		return cateDao.findById(id);
	}

	@Override
	public CategoryModel findByname(String name) {
		return cateDao.findByname(name);
	}

	@Override
	public void insert(CategoryModel category) {
		CategoryModel cate = this.findByname(category.getCategoryname());
		if (cate.getCategoryname() == null) {
			cateDao.insert(category);
		}
		
	}

	@Override
	public void update(CategoryModel category) {
		cateDao.update(category);
	}

	@Override
	public void delete(int id) {
		cateDao.delete(id);
	}

	@Override
	public void updateStatus(int id, int status) {
		cateDao.updateStatus(id, status);
	}

}

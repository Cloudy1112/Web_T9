package vn.iotstar.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iotstar.models.CategoryModel;
import vn.iotstar.services.ICategoryService;
import vn.iotstar.services.impl.CategoryServiceImpl;

@MultipartConfig()
@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/add", "/admin/category/insert",
							"/admin/categories/delete", "/admin/categories/update"})
public class CategoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public ICategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();

		if (url.contains("/admin/category/add")) {
			req.getRequestDispatcher("/commons/admin/category-add.jsp").forward(req, resp);
		
		} else if (url.contains("/admin/categories")) {
			List<CategoryModel> list = cateService.findAll();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/commons/admin/category-list.jsp").forward(req, resp);
		
		} else if (url.contains("/admin/categories/delete")) {
			int id = Integer.parseInt(req.getParameter("id"));
			cateService.delete(id);
			resp.sendRedirect(req.getContentType()+"/admin/categories");
		
		} else if (url.contains("/admin/edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			CategoryModel category = cateService.findById(id);
			req.setAttribute("listcate", category);
			req.getRequestDispatcher("/commons/admin/category-edit.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();

		if (url.contains("/admin/category/insert")) {
			// lay du lieu tu view
			String categoryname = req.getParameter("categoryname");
			int status = Integer.parseInt(req.getParameter("status"));
			String images = req.getParameter("images");

			// dua vao model
			CategoryModel category = new CategoryModel();
			category.setCategoryname(categoryname);
			category.setImages(images);
			category.setStatus(status);

			// xu ly upload file
			
			String fname = "";
			String uploadPath = "C://uploadWeb";
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
					uploadDir.mkdir();
			}
			
			
			try {
				Part part = req.getPart("image1");
				if(part.getSize()>0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					//doi ten file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index+1);
					fname = System.currentTimeMillis() + "." + ext;
					
					//upload file
					part.write(uploadPath + "/" + fname); //co the them nhieu thu muc con khac nhau
					
					//ghi ten file vao data
					category.setImages(fname);
				} else if (images!= null) {
					category.setImages(images);
				} else {
					category.setImages("avatar.png");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			// truyen model vao service
			cateService.insert(category);

			// redirect
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
	}
}

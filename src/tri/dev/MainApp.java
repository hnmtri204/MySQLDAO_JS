package tri.dev;

import tri.dev.data.dao.CategoryDao;
import tri.dev.data.impl.CategoryImpl;
import tri.dev.data.model.Category;

public class MainApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CategoryDao categoryDao = new CategoryImpl();
		Category cat = new Category(2, "Ao Dai", "https://g");
//		categoryDao.insert(cat);
		
		Category find = categoryDao.find(121);
		System.out.println(find.name);
		find.name ="Ao qua dai";
		categoryDao.update(find);
		
		categoryDao.delete(find.id);
	}
}

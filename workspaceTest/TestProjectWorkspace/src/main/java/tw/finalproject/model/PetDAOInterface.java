package tw.finalproject.model;

import java.util.List;

public interface PetDAOInterface {

	public PetBean insert(PetBean petBean);
	
	public PetBean select(int petId);
	
	public boolean deleteById(int petId);
	
	public List<PetBean> selectAll();
	
	public PetBean updateOne(int petId,PetBean petBean);
	
}

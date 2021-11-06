package tw.springbootfinal.pet.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PetsRepository extends JpaRepository<Pets, Integer> {
	//查詢所有尚未領養寵物
	public List<Pets> findByAdoptStatus(String status);
	
	//使用性別與類別查詢
	public List<Pets> findBySexAndCategory(String sex ,String category);
	public List<Pets> findBySex(String sex);
	public List<Pets> findByCategory(String category);
	
	//使用ID與領養狀態尋找
	public Pets findByPetIdAndAdoptStatus(Integer petId,String status);
	
}

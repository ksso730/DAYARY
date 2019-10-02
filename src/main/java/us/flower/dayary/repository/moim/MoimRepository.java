package us.flower.dayary.repository.moim;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import us.flower.dayary.domain.Moim;
import us.flower.dayary.domain.MoimPeople;

public interface MoimRepository extends JpaRepository<Moim, Long>{

    boolean existsByImageName(String imageName);

    
    boolean existsByIdAndPeople_id(long id,long peopleId);
  



    //@Query("select a.email as email from People a inner join MoimPeople b on a.id=b.people where b.joinrole='study' and b.people=4")
//    @Query("select a.no as no from Moim a where a.no=1")
//	Optional<Moim> findPeopleOne(Long people_no);

	
     
   

}

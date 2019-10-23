package us.flower.dayary.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import us.flower.dayary.domain.VisitCheck;
import us.flower.dayary.repository.visitcheck.VisitCheckRepository;
@Component
public class VisitCountDAO {
	@Autowired
	VisitCheckRepository visitcheckRepository;
	        public VisitCheck insertVisitor(VisitCheck vo) throws Exception{
	            return visitcheckRepository.save(vo);
	        }
}

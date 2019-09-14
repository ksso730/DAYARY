package us.flower.dayary;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import us.flower.dayary.domain.Academy;
import us.flower.dayary.domain.BoardGroup;
import us.flower.dayary.domain.ComunityBoard;
import us.flower.dayary.repository.BoardGroupRepository;
import us.flower.dayary.repository.ComunityBoardRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardGroupTest {

	@Autowired
	BoardGroupRepository brdgrouprepo;
	
	@Autowired
	ComunityBoardRepository communityboardrepo;

	
	/*group board test*/
    @Test
    public void boardGroup() {
    	
    BoardGroup brdgrp=new BoardGroup();
    
    ComunityBoard comnibrd=new ComunityBoard();
    
    brdgrp.setNo(1L);
    brdgrp.setName("자유게시판");
    
 
    comnibrd.setNo(1L);
    comnibrd.setTitle("1번 타이틀 ");
    comnibrd.setMemo("1번 메모");
    comnibrd.setBoardGroup(brdgrp);
    comnibrd.setDeleteFlag('N');
    
    //brdgrouprepo.save(brdgrp);
    communityboardrepo.save(comnibrd);
    
    }
}

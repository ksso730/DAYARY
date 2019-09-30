package us.flower.dayary.domain;


import lombok.Data;
import org.apache.ibatis.annotations.Many;
import org.springframework.context.annotation.Configuration;
import us.flower.dayary.controller.moim.board.MoimNotiController;
import us.flower.dayary.domain.common.DateAudit;

import javax.persistence.*;

/**
 * 파일 객체 (MOIM_FILE, COMMUNIY_FILE)
 */
@Entity
@Data
@Table(name = "FILE")
public class File extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @ManyToOne
    @JoinColumn(name="COMMUNITY_BOARD_ID")
    private CommunityBoard communityBoard;

//    @Column(name = "MOIN_ID")
//    private Moim moim;
//
//    @Column(name="BOARD_GROUP_ID")
///   private BoardGroup boardGroup;

    // 오프모임키, 모임장 번호는 일단 제외

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name="REAL_NAME")
    private String realName;

    @Column(name="FILE_SIZE")
    private long fileSize;

    @Column(name="FILE_LOCATE")
    private String fileLocate;
}
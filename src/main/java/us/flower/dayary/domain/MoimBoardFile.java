package us.flower.dayary.domain;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name="Moim_Board_File")
@Data
public class MoimBoardFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
	private long id;
    //모임 카테고리
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MoimBoard_ID" ,referencedColumnName = "ID") 
	private MoimBoard moid_moard;
    @Column(name = "filename")
	private String filename;
    @Column(name = "REAL_NAME")
	private String real_name;
    @Column(name = "FILE_SIZE")
	private String file_size;
    @Column(name = "FILE_LOCATE")
	private String file_locate;
    @Column(name="representImage")
    private long representImage;
}

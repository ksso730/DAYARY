package us.flower.dayary.domain;

import lombok.Data;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JsonIgnore
	private MoimBoard moimBoard;
    @Column(name = "FILE_NAME")
	private String file_name;
    @Column(name = "REAL_NAME")
	private String real_name;
    @Column(name = "FILE_SIZE")
	private String file_size;
    @Column(name = "FILE_LOCATE")
	private String file_locate;
}

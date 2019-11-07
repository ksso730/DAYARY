package us.flower.dayary.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Moim_Board_File")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MoimBoardFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
	private long id;
    //모임 카테고리
    @ManyToOne(fetch = FetchType.LAZY , cascade={CascadeType.ALL})
    @JoinColumn(name = "MoimBoard_ID" ,referencedColumnName = "ID") 

    @Column(name = "filename")
	private String filename;

    @JsonIgnore
	private MoimBoard moimBoard;

    @Column(name = "REAL_NAME")
	private String real_name;
    @Column(name = "FILE_SIZE")
	private String file_size;
    @Column(name = "FILE_LOCATE")
	private String file_locate;
    @Column(name="representImage")
    private long representImage;
}

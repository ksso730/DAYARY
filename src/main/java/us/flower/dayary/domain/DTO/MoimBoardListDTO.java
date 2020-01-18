package us.flower.dayary.domain.DTO;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class MoimBoardListDTO {

    private long moimBoardId;

    private String boardTitle;

    private String peopleName;

    private LocalDateTime createdAt;

    private long heart;

    @QueryProjection
    public MoimBoardListDTO(long moimBoardId, String boardTitle, String peopleName, LocalDateTime createdAt,  long heart){
        this.moimBoardId = moimBoardId;
        this.boardTitle = boardTitle;
        this.peopleName = peopleName;
        this.createdAt = createdAt;
        this.heart = heart;
    }
}

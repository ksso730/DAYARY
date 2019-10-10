package us.flower.dayary.domain.DTO;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class BoardListDTO {

    private long boardId;

    private String boardTitle;

    private String peopleName;

    private LocalDateTime createdAt;

    private long viewCount;

    private long heart;

    private long replyCount;

    @QueryProjection
    public BoardListDTO(long boardId, String boardTitle, String peopleName, LocalDateTime createdAt, long viewCount, long heart, long replyCount){
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.peopleName = peopleName;
        this.createdAt = createdAt;
        this.viewCount = viewCount;
        this.heart = heart;
        this.replyCount = replyCount;
    }
}

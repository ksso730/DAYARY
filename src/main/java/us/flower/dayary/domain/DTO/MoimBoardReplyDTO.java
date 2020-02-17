package us.flower.dayary.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import us.flower.dayary.domain.CommunityBoardReply;
import us.flower.dayary.domain.MoimBoardReply;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class MoimBoardReplyDTO {
    private MoimBoardReply parent;
    private List<MoimBoardReply> children;
}


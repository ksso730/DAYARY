package us.flower.dayary.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import us.flower.dayary.domain.CommunityBoardReply;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class BoardReplyDTO {

    private CommunityBoardReply parent;
    private List<CommunityBoardReply> children;
}

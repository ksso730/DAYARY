package us.flower.dayary.domain.DTO;

import lombok.AllArgsConstructor;
import us.flower.dayary.domain.CommunityBoardReply;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class BoardReplyDTO {

    private CommunityBoardReply parent;
    private List<CommunityBoardReply> children = new ArrayList<>();
}

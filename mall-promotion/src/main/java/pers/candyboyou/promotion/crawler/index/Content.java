package pers.candyboyou.mallpromotion.crawler.index;

import lombok.Data;

import java.util.List;

@Data
public class Content {

    private Header header;

    private List<Section> sections;

    private GroupFloor groupFloor;

    private SpecialJump2 specialJump2;

    private Homev4TabChannel homev4TabChannel;

    private Homev4GlobalConfig homev4GlobalConfig;
}

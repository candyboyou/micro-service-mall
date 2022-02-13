package io.candyboyou.mallpromotion.crawler.index;

import lombok.Data;

@Data
public class Homev4TabChannel {

    private String viewType;

    private String stat;

    private String blockId;

    private Body body;

    private String spmC;

    private SpmStat spmStat;

    private String componentsName;
}

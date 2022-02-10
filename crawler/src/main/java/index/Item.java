package index;

import lombok.Data;

/**
 * "img_url": "https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/bb7e320711a3ade641ec46d8149dc253.jpg?w=1008&h=420&bg=141E28",
 *                 "action": {
 *                     "login": false,
 *                     "log_code": "31cxhomev4homev4_gallery005002#t=normal&act=other&page=homev4&page_id=17131&bid=3691379.1&bpm=15.279.3691379.1&cdm=31921.0.0.0",
 *                     "type": "activity",
 *                     "path": "/pages/channel/index?page_type=activity&page_title=全线拉满的冷血旗舰&page_id=23108&sign=04e551049c62ebf3c0b09197341693f1",
 *                     "url": "",
 *                     "uri": "/pages/channel/index?page_type=activity",
 *                     "extra":"{\"page_title\":\"\全\线\拉\满\的\冷\血\旗\舰\",\"page_id\":\"23108\",\"sign\":\"04e551049c62ebf3c0b09197341693f1\"}",
 *                     "spm_stat": {
 *                         "spm_code": "cms_17131.3691379.1",
 *                         "spm_params":"{\"component\":\"homev4_gallery\",\"component_name\":\"\首\页4.0 \焦\点\图\配\置\",\"title\":\"\全\线\拉\满\的\冷\血\旗\舰\",\"img\":646758}",
 *                         "scm": "cms.0.0.0.page.23108.0.0"
 *                     }
 *                 },
 */
@Data
public class Item {

    private String img_url;

    private Action action;

    private String type;

    private String bgColor;

    private int adLevelId;

    private int lotteryType;
}

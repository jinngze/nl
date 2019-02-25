package com.example.a0220.data.bean;

import java.util.List;

public class Good {


    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2019-02-14 00:01","description":"华声美女","picUrl":"http://image.hnol.net/c/2019-02/13/20/20190213205435161-1559530.jpg","title":"☆成熟女人\u2026\u2026[柏V作品 蔡译心 878p]\u2026\u2026第1168辑","url":"http://bbs.voc.com.cn/mm/meinv-8777305-0-1.html"},{"ctime":"2019-02-13 22:01","description":"华声美女","picUrl":"http://image.hnol.net/c/2019-02/13/20/20190213205435161-1559530.jpg","title":"☆成熟女人\u2026\u2026[柏V作品 蔡译心 840p]\u2026\u2026第1168辑","url":"http://bbs.voc.com.cn/mm/meinv-8777305-0-1.html"},{"ctime":"2019-02-12 23:01","description":"华声美女","picUrl":"http://image.hnol.net/c/2019-02/12/21/20190212213128261-1559530.jpg","title":"☆成熟女人\u2026\u2026[o痕o影作品 潘雅卉 243p]\u2026\u2026第1167辑","url":"http://bbs.voc.com.cn/mm/meinv-8776594-0-1.html"},{"ctime":"2019-02-12 23:01","description":"华声美女","picUrl":"http://image.hnol.net/c/2019-02/12/22/201902122253422631-1559530.jpg","title":"I邻家女孩\u2026\u2026[沈秋髌 杨宓凌 132p]\u2026\u2026第2008辑","url":"http://bbs.voc.com.cn/mm/meinv-8776626-0-1.html"},{"ctime":"2019-02-12 18:01","description":"华声美女","picUrl":"http://image.hnol.net/c/2019-02/12/15/20190212154600231-6011457.jpeg","title":"娃娃-新年大雪梅花","url":"http://bbs.voc.com.cn/mm/meinv-8776384-0-1.html"},{"ctime":"2019-02-12 13:00","description":"华声美女","picUrl":"http://image.hnol.net/c/2019-02/12/11/201902121151393221-239867.jpg","title":"重庆的妹子","url":"http://bbs.voc.com.cn/mm/meinv-8776228-0-1.html"},{"ctime":"2019-02-12 13:00","description":"华声美女","picUrl":"http://image.hnol.net/c/2019-02/12/11/201902121148508171-239867.jpg","title":"发牌女郎","url":"http://bbs.voc.com.cn/mm/meinv-8776224-0-1.html"},{"ctime":"2019-02-11 23:00","description":"华声美女","picUrl":"http://image.hnol.net/c/2019-02/11/22/20190211223011931-1559530.jpg","title":"★制服女生\u2026\u2026[廖廖廖作品 S德玄 143p]\u2026\u2026第259辑","url":"http://bbs.voc.com.cn/mm/meinv-8776015-0-1.html"},{"ctime":"2019-02-11 19:00","description":"华声美女","picUrl":"http://image.hnol.net/c/2019-02/11/17/201902111750231821-5960097.jpg","title":"小美女[贴图]","url":"http://bbs.voc.com.cn/mm/meinv-8775919-0-1.html"},{"ctime":"2019-02-10 17:00","description":"华声美女","picUrl":"http://image.hnol.net/c/2019-02/10/14/201902101453062421-1559530.jpg","title":"☆成熟女人\u2026\u2026[JackieYau作品 范有有等 388p]\u2026\u2026第1166辑","url":"http://bbs.voc.com.cn/mm/meinv-8775565-0-1.html"}]
     */

    private int code;
    private String msg;
    private List<NewslistBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
        /**
         * ctime : 2019-02-14 00:01
         * description : 华声美女
         * picUrl : http://image.hnol.net/c/2019-02/13/20/20190213205435161-1559530.jpg
         * title : ☆成熟女人……[柏V作品 蔡译心 878p]……第1168辑
         * url : http://bbs.voc.com.cn/mm/meinv-8777305-0-1.html
         */

        private String ctime;
        private String description;
        private String picUrl;
        private String title;
        private String url;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}

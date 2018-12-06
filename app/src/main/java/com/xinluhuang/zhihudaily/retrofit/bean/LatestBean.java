package com.xinluhuang.zhihudaily.retrofit.bean;

import java.util.List;

public class LatestBean {
    //http://news-at.zhihu.com/api/4/news/latest

    /**
     * date : 20181113
     * stories : [{"title":"作为游戏主角，同时被几百万人用弹幕控制，滋味真不好受","ga_prefix":"111319","images":["https://pic2.zhimg.com/v2-a6621b960328fc8ddb4fadeb0a589115.jpg"],"multipic":true,"type":0,"id":9700898},{"images":["https://pic4.zhimg.com/v2-b5e126873872213927da8e367634764f.jpg"],"type":0,"id":9701401,"ga_prefix":"111317","title":"中国「人造太阳」首次实现 1 亿 ℃ 运行，他们真的播种出了太阳"},{"images":["https://pic3.zhimg.com/v2-d30ff29e069fd60a8b67e31e27774c82.jpg"],"type":0,"id":9701415,"ga_prefix":"111315","title":"一见钟情的「一见」到底有多长？"},{"images":["https://pic4.zhimg.com/v2-39b61db5d08c9bd6756054c9489632c3.jpg"],"type":0,"id":9701326,"ga_prefix":"111313","title":"基因对你寿命的影响，还不如找个好对象对你的影响大"},{"images":["https://pic1.zhimg.com/v2-bbafdcb939a12e4637ce430f9f09d104.jpg"],"type":0,"id":9701259,"ga_prefix":"111312","title":"大误 · 美丽的谢大脚不知所终，而你终于学会君王之道"},{"images":["https://pic4.zhimg.com/v2-0c0e3cca51e47b4524569b3716f9bfaf.jpg"],"type":0,"id":9701411,"ga_prefix":"111311","title":"斯坦 · 李去世，漫威背后的恩怨情仇尘归尘，土归土"},{"images":["https://pic4.zhimg.com/v2-bab5a1793d071b600ff339a46f3158c7.jpg"],"type":0,"id":9701361,"ga_prefix":"111310","title":"在职场上，到底是「做多少事拿多少钱」，还是「拿多少钱做多少事」？"},{"images":["https://pic2.zhimg.com/v2-3c425479226a965e5055f22c95c8f835.jpg"],"type":0,"id":9701337,"ga_prefix":"111309","title":"婴儿一出生就会觅乳吃奶，是乳房分泌了什么气味吗？"},{"images":["https://pic4.zhimg.com/v2-730bc01fc371ffbb9a78ddfb929fd017.jpg"],"type":0,"id":9701335,"ga_prefix":"111308","title":"年底拼业绩的时候又到了，犯罪率也会提高吗？"},{"images":["https://pic3.zhimg.com/v2-59bed6936dbdeca3f0dcf060a79e7846.jpg"],"type":0,"id":9701269,"ga_prefix":"111307","title":"如果人类是被设计的，看着自己全身的 bug，我一定先把造物主打一顿"},{"images":["https://pic4.zhimg.com/v2-8bc13c1d3789839da76b2238933d1cdf.jpg"],"type":0,"id":9701309,"ga_prefix":"111307","title":"我们大口吃肉，吃商家的肉，也吃羊毛党的肉"},{"images":["https://pic1.zhimg.com/v2-de1401e8643f3148729daa81cd360cc8.jpg"],"type":0,"id":9701233,"ga_prefix":"111306","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic1.zhimg.com/v2-8e63a94bda3ca20feef52b5bbed60730.jpg","type":0,"id":9700898,"ga_prefix":"111319","title":"作为游戏主角，同时被几百万人用弹幕控制，滋味真不好受"},{"image":"https://pic2.zhimg.com/v2-0505cbed140f7a4862897e121d2245c1.jpg","type":0,"id":9701401,"ga_prefix":"111317","title":"中国「人造太阳」首次实现 1 亿 ℃ 运行，他们真的播种出了太阳"},{"image":"https://pic1.zhimg.com/v2-76aa0879feb646a4059c57c50446490c.jpg","type":0,"id":9701411,"ga_prefix":"111311","title":"斯坦 · 李去世，漫威背后的恩怨情仇尘归尘，土归土"},{"image":"https://pic4.zhimg.com/v2-a4f8851a990ca5957d556c48e5f06c07.jpg","type":0,"id":9701309,"ga_prefix":"111307","title":"我们大口吃肉，吃商家的肉，也吃羊毛党的肉"},{"image":"https://pic4.zhimg.com/v2-1d1c796ade8fee944ab1ec204473d15b.jpg","type":0,"id":9701361,"ga_prefix":"111310","title":"在职场上，到底是「做多少事拿多少钱」，还是「拿多少钱做多少事」？"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * title : 作为游戏主角，同时被几百万人用弹幕控制，滋味真不好受
         * ga_prefix : 111319
         * images : ["https://pic2.zhimg.com/v2-a6621b960328fc8ddb4fadeb0a589115.jpg"]
         * multipic : true
         * type : 0
         * id : 9700898
         */

        private String title;
        private String ga_prefix;
        private boolean multipic;
        private int type;
        private int id;
        private List<String> images;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image : https://pic1.zhimg.com/v2-8e63a94bda3ca20feef52b5bbed60730.jpg
         * type : 0
         * id : 9700898
         * ga_prefix : 111319
         * title : 作为游戏主角，同时被几百万人用弹幕控制，滋味真不好受
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}

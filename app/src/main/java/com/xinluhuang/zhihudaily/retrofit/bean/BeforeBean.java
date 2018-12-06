package com.xinluhuang.zhihudaily.retrofit.bean;

import java.util.List;

public class BeforeBean {
    /**
     * date : 20181130
     * stories : [{"images":["https://pic4.zhimg.com/v2-82de8dec9d1316979c29ef7add755033.jpg"],"type":0,"id":9702828,"ga_prefix":"113022","title":"小事 · 在动物园的日子"},{"images":["https://pic3.zhimg.com/v2-8d8a9867387392113b3bf1b4d09b1662.jpg"],"type":0,"id":9702686,"ga_prefix":"113021","title":"当悲伤（高级地）逆流成河"},{"images":["https://pic3.zhimg.com/v2-b60af5e31dd6bd14ed2460d63e18f4da.jpg"],"type":0,"id":9702822,"ga_prefix":"113019","title":"中国芯片制造迈出的这一步，请允许我「吹爆」"},{"images":["https://pic2.zhimg.com/v2-f5169e15eaa3014ebdc20da2e0882e4d.jpg"],"type":0,"id":9702744,"ga_prefix":"113018","title":"大疆「口红相机」评测：王境泽定律又一次灵验了"},{"images":["https://pic2.zhimg.com/v2-fce19f301e432a66b688c250f10a76fd.jpg"],"type":0,"id":9702806,"ga_prefix":"113016","title":"刚刚，我们发现一种从小喝奶长大的蜘蛛"},{"images":["https://pic4.zhimg.com/v2-4ba7f8c78ef4ed782f8b58a0034638bf.jpg"],"type":0,"id":9702803,"ga_prefix":"113013","title":"一提到恐龙「侏罗纪」就站 C 位，我家「白垩纪」也很有实力了解一下"},{"images":["https://pic3.zhimg.com/v2-ae7bd258b0a35f97cabad030269d254e.jpg"],"type":0,"id":9702736,"ga_prefix":"113012","title":"大误 · 你是西门庆派来的救兵吗？"},{"images":["https://pic1.zhimg.com/v2-4c1f5fcc20e259d1d380dbabaf3570e8.jpg"],"type":0,"id":9701379,"ga_prefix":"113010","title":"用五个盖子盖十口井，财务：「骚」起来我自己都害怕"},{"images":["https://pic3.zhimg.com/v2-85310b3bc6e4370d3a7b6e526a03cd72.jpg"],"type":0,"id":9702668,"ga_prefix":"113009","title":"一辈子做朝九晚六的工作，有什么可惜的呢？"},{"images":["https://pic1.zhimg.com/v2-663659d3277054d9d5b959430ca85b04.jpg"],"type":0,"id":9702723,"ga_prefix":"113008","title":"34 年前，中国人第一次拍到了哈雷彗星"},{"images":["https://pic2.zhimg.com/v2-6d6a7d7536039c146ea97c704524a46d.jpg"],"type":0,"id":9702575,"ga_prefix":"113007","title":"身为一个 HR，我觉得刷「面试技巧」不如想清楚这些问题"},{"title":"怒怼抢行汽车、排队过十字路口，没见过你这样的地铁","ga_prefix":"113007","images":["https://pic1.zhimg.com/v2-2b3fc78caa235e67ab17911d0e9a7428.jpg"],"multipic":true,"type":0,"id":9702161},{"images":["https://pic1.zhimg.com/v2-1c6dc66addfdd3d87fe7bddc008f9cb4.jpg"],"type":0,"id":9702599,"ga_prefix":"113006","title":"瞎扯 · 如何正确地吐槽"}]
     */

    private String date;
    private List<StoriesBean> stories;

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

    public static class StoriesBean {
        /**
         * images : ["https://pic4.zhimg.com/v2-82de8dec9d1316979c29ef7add755033.jpg"]
         * type : 0
         * id : 9702828
         * ga_prefix : 113022
         * title : 小事 · 在动物园的日子
         * multipic : true
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private boolean multipic;
        private List<String> images;

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

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}

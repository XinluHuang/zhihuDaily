package com.xinluhuang.zhihudaily.retrofit.bean;

import java.util.List;

public class LongComments {
    private List<CommentsBean> comments;

    public List<CommentsBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentsBean> comments) {
        this.comments = comments;
    }

    public static class CommentsBean {
        /**
         * author : 周缘
         * content : 一个将军的卫兵 在将军与其客人闲聊时总喜欢插嘴 于是将军就威胁他:格老子的再多嘴就毙了你！卫兵只能答应不插嘴
         结果第二天将军又有客人来，然后闲聊到世上什么树的叶子最大，将军说枫树叶，客人说梧桐叶，两人争执之际只听门外一声怒吼:
         枪毙就枪毙！芭蕉叶最大！
         * avatar : http://pic1.zhimg.com/da8e974dc_im.jpg
         * time : 1543451990
         * reply_to : {"content":"什么芭蕉叶啊好奇hhhhhh","status":0,"id":32600285,"author":"木思楒_"}
         * id : 32600488
         * likes : 37
         */

        private String author;
        private String content;
        private String avatar;
        private int time;
        private ReplyToBean reply_to;
        private int id;
        private int likes;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public ReplyToBean getReply_to() {
            return reply_to;
        }

        public void setReply_to(ReplyToBean reply_to) {
            this.reply_to = reply_to;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public static class ReplyToBean {
            /**
             * content : 什么芭蕉叶啊好奇hhhhhh
             * status : 0
             * id : 32600285
             * author : 木思楒_
             */

            private String content;
            private int status;
            private int id;
            private String author;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }
        }
    }
}

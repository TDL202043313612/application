package com.example.applications.entity;

public class VideoEntity {
    private Integer vid;
    private String vtitle;
    private String author;
    private String coverurl;
    private String headurl;
    private Integer commentNum;
    private Integer likeNum;
    private Integer collectNum;
    private String playurl;
    private String createTime;
    private String updateTime;
    private Integer categoryId;
    private String categoryName;
    private VideoSocialEntityDTO videoSocialEntity;

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public String getVtitle() {
        return vtitle;
    }

    public void setVtitle(String vtitle) {
        this.vtitle = vtitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCoverurl() {
        return coverurl;
    }

    public void setCoverurl(String coverurl) {
        this.coverurl = coverurl;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    public String getPlayurl() {
        return playurl;
    }

    public void setPlayurl(String playurl) {
        this.playurl = playurl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public VideoSocialEntityDTO getVideoSocialEntity() {
        return videoSocialEntity;
    }

    public void setVideoSocialEntity(VideoSocialEntityDTO videoSocialEntity) {
        this.videoSocialEntity = videoSocialEntity;
    }

    public static class VideoSocialEntityDTO {
        private Integer commentnum;
        private Integer likenum;
        private Integer collectnum;
        private Boolean flagLike;
        private Boolean flagCollect;

        public Integer getCommentnum() {
            return commentnum;
        }

        public void setCommentnum(Integer commentnum) {
            this.commentnum = commentnum;
        }

        public Integer getLikenum() {
            return likenum;
        }

        public void setLikenum(Integer likenum) {
            this.likenum = likenum;
        }

        public Integer getCollectnum() {
            return collectnum;
        }

        public void setCollectnum(Integer collectnum) {
            this.collectnum = collectnum;
        }

        public Boolean isFlagLike() {
            return flagLike;
        }

        public void setFlagLike(Boolean flagLike) {
            this.flagLike = flagLike;
        }

        public Boolean isFlagCollect() {
            return flagCollect;
        }

        public void setFlagCollect(Boolean flagCollect) {
            this.flagCollect = flagCollect;
        }
    }
}

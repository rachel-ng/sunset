package com.mergbw.core.network.factory.bean;

import java.util.List;

public class FactoryProductInfoBean {
    private List<GoodsInfo> goodsList;
    private int promotionId;
    private String promotionName;

    public int getPromotionId() {
        return this.promotionId;
    }

    public void setPromotionId(int i) {
        this.promotionId = i;
    }

    public String getPromotionName() {
        return this.promotionName;
    }

    public void setPromotionName(String str) {
        this.promotionName = str;
    }

    public List<GoodsInfo> getGoodsList() {
        return this.goodsList;
    }

    public void setGoodsList(List<GoodsInfo> list) {
        this.goodsList = list;
    }

    public static class GoodsInfo {
        private int goodsId;
        private String goodsLink;
        private String goodsName;
        private String goodsPicture;

        public int getGoodsId() {
            return this.goodsId;
        }

        public void setGoodsId(int i) {
            this.goodsId = i;
        }

        public String getGoodsName() {
            return this.goodsName;
        }

        public void setGoodsName(String str) {
            this.goodsName = str;
        }

        public String getGoodsPicture() {
            return this.goodsPicture;
        }

        public void setGoodsPicture(String str) {
            this.goodsPicture = str;
        }

        public String getGoodsLink() {
            return this.goodsLink;
        }

        public void setGoodsLink(String str) {
            this.goodsLink = str;
        }
    }
}

package com.mergbw.core.network;

public class BasePresenter {
    private int currentPage = 1;
    private int mTotalPageNum;
    private int mTotalSize;
    protected int tempPage = 1;

    public void resetPage() {
        this.currentPage = 1;
        this.tempPage = 1;
    }

    public void moreData() {
        this.tempPage = this.currentPage + 1;
    }

    public void calculatePageNum() {
        this.currentPage = this.tempPage;
    }
}

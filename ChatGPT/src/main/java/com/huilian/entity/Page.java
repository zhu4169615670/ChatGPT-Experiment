package com.huilian.entity;

import java.io.Serializable;

public class Page  implements Serializable {
    private static final long serialVersionUID = 1L;

    public Page(int pageSize, int pageNum) {
        if(pageSize > 0 ){
            this.pageSize = pageSize;
        }
        if(pageNum > 0){
            this.pageNum = pageNum;
        }
    }

    /**
     * 初始化各个值
     */
    public void initValue(){
        // 计算总共有多少页
        totlePages = (int)Math.ceil(totleElements * 1.0 / pageSize);

        if(totlePages == 0){
            totlePages = 1;
        }

        // 页码矫正
        if(pageNum <= 0){
            pageNum = 1;
        }
        if(pageNum > totlePages){
            pageNum = totlePages;
        }

        // 是否有上一页和首页
        if(pageNum > 1){
            hasPrevious = true;
            prePage = pageNum - 1;
        }

        // 是否有下一页和尾页
        if(totlePages > pageNum){
            hasNext = true;
            nextPage = pageNum + 1;
        }

        // 分页页码开始数值
        begin = Math.max(1, pageNum - paginationSize/2);

        // 分页页码结束数值
        end = Math.min(begin + (paginationSize - 1), totlePages);
    }

    /**
     * 分页下标最多显示多少页
     */
    private int paginationSize = 10;
    /**
     * 页码开始
     */
    private int begin = 1;
    /**
     * 页码结束
     */
    private int end = 1;
    /**
     * 每页显示多少条
     */
    private int pageSize = 15;
    /**
     * 当前页码
     */
    private int pageNum = 1;
    /**
     * 是否有下一页
     */
    private boolean hasNext = false;
    /**
     * 是否有上一页
     */
    private boolean hasPrevious = false;
    /**
     * 下一页的页码
     */
    private int nextPage;
    /**
     * 上一页的页码
     */
    private int prePage;
    /**
     * 总页数
     */
    private int totlePages = 1;
    /**
     * 总条数
     */
    private int totleElements = 0;

    public static int maxNum = 999999;

    public int getPaginationSize() {
        return paginationSize;
    }
    public void setPaginationSize(int paginationSize) {
        this.paginationSize = paginationSize;
    }
    public int getBegin() {
        return begin;
    }
    public void setBegin(int begin) {
        this.begin = begin;
    }
    public int getEnd() {
        return end;
    }
    public void setEnd(int end) {
        this.end = end;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getPageNum() {
        return pageNum;
    }
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    public boolean isHasNext() {
        return hasNext;
    }
    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }
    public boolean isHasPrevious() {
        return hasPrevious;
    }
    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }
    public int getNextPage() {
        return nextPage;
    }
    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }
    public int getPrePage() {
        return prePage;
    }
    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }
    public int getTotlePages() {
        return totlePages;
    }
    public void setTotlePages(int totlePages) {
        this.totlePages = totlePages;
    }
    public int getTotleElements() {
        return totleElements;
    }
    public void setTotleElements(int totleElements) {
        this.totleElements = totleElements;
        // 知道总条数之后，初始化一下其它值
        initValue();
    }

    public static void main(String[] args) {
        System.out.println(Math.min(1, 2));
    }

    /**
     * 从第几条开始查询
     * @return
     */
    public int getStartNum(){
        return (pageNum -1) * pageSize + 1;
    }

    /**
     * 到第几条结束
     * @return
     */
    public int getEndNum(){
        return pageNum * pageSize;
    }
}

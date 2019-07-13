package com.ayao.ayaoqianggou.page;

import java.io.Serializable;
import java.util.List;

/**
 * @author ：ayao
 * @date ：Created in 2019/6/19 11:22
 * @version:
 */
public class PageInfo<T> implements Serializable {
  /*
  当前页
   */
  private Integer pageNum;
  /*
  每页的数量
   */
  private Integer pageSize;
  /*
  总记录数
   */
  private Long total;
  /*
  总页数
   */
  private Integer pages;
  /*
  结果集
   */
  private List<T> list;
  /*
  是否为第一页
   */
  private boolean isFirstPage;
  /*
  是否为最后一页
   */
  private boolean isLastPage;

  public PageInfo(Integer pageNum, Integer pageSize) {
    super();
    this.pageNum = pageNum;
    this.pageSize = pageSize;
  }

  public Integer getPageNum() {
    return pageNum;
  }

  public void setPageNum(Integer pageNum) {
    this.pageNum = pageNum;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public Long getTotal() {
    return total;
  }

  public void setTotal(Long total) {
    this.total = total;
  }

  public Integer getPages() {
    return pages;
  }

  public void setPages(Integer pages) {
    this.pages = pages;
  }

  public List<T> getList() {
    return list;
  }

  public void setList(List<T> list) {
    this.list = list;
  }

  public boolean isFirstPage() {
    return isFirstPage;
  }

  public void setFirstPage(boolean firstPage) {
    isFirstPage = firstPage;
  }

  public boolean isLastPage() {
    return isLastPage;
  }

  public void setLastPage(boolean lastPage) {
    isLastPage = lastPage;
  }

  @Override
  public String toString() {
    return "PageInfo{" +
        "pageNum=" + pageNum +
        ", pageSize=" + pageSize +
        ", total=" + total +
        ", pages=" + pages +
        ", list=" + list +
        ", isFirstPage=" + isFirstPage +
        ", isLastPage=" + isLastPage +
        '}';
  }
}

package com.dongnao.workbench.common.page;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Page implements Serializable
{
protected Logger logger = LoggerFactory.getLogger(getClass());
private static final long serialVersionUID = 5192357666324600054L;
public static final String ASC = "asc";
public static final String DESC = "desc";
protected int pageNo = 1;
protected int pageSize = 15;
protected String orderBy = null;
protected String orderType = null;
protected boolean autoCount = true;
protected int start = 0;

protected List resultList = new ArrayList();
protected long totalCount = -1L;

public Page()
{
}

public Page(int paramInt)
{
  this.pageSize = paramInt;
}

public Page(int paramInt1, int paramInt2) {
  this.pageNo = paramInt1;
  this.pageSize = paramInt2;
}

public int getPageNo()
{
  return this.pageNo;
}

public void setPageNo(int pageNo)
{
  this.pageNo = pageNo;

  if (pageNo < 1)
    this.pageNo = 1;
}

public Page pageNo(int thePageNo)
{
  setPageNo(thePageNo);
  return this;
}

public int getPageSize()
{
  return this.pageSize;
}

public void setPageSize(int pageSize)
{
  this.pageSize = pageSize;

  if (pageSize < 1)
    this.pageSize = 1;
}

public Page pageSize(int thePageSize)
{
  setPageSize(thePageSize);
  return this;
}

public int getFirst()
{
  if (this.start != 0)
    return this.start + 1;
  return (this.pageNo - 1) * this.pageSize + 1;
}

public void setStart(int start) {
  this.start = start;
}

public String getOrderFields()
{
  return this.orderBy;
}

public void setOrderFields(String orderFields)
{
  this.orderBy = orderFields;
}

public Page orderFields(String theOrderFields) {
  setOrderFields(theOrderFields);
  return this;
}

public String getOrder()
{
  return this.orderType;
}

public void setOrder(String order)
{
  String[] arrayOfString = StringUtils.split(StringUtils.lowerCase(order), ',');

  for (int i = 0; i < arrayOfString.length; i++) {
    String str = arrayOfString[i];
    if ((!StringUtils.equals("desc", str)) && (!StringUtils.equals("asc", str)))
    {
      throw new IllegalArgumentException("排序方向" + str + "不是合法值");
    }
  }

  this.orderType = StringUtils.lowerCase(order);
}

public Page order(String theOrder) {
  setOrder(theOrder);
  return this;
}

public boolean isOrderFieldsSetted()
{
  return (StringUtils.isNotBlank(this.orderBy)) && (StringUtils.isNotBlank(this.orderType));
}

public boolean isAutoCount()
{
  return this.autoCount;
}

public void setAutoCount(boolean autoCount)
{
  this.autoCount = autoCount;
}

public Page autoCount(boolean theAutoCount) {
  setAutoCount(theAutoCount);
  return this;
}

public List getResult()
{
  return this.resultList;
}

public void setResult(List result)
{
  this.resultList = result;
}

public long getTotalCount()
{
  return this.totalCount;
}

public void setTotalCount(long totalCount)
{
  this.totalCount = totalCount;
}

public long getTotalPages()
{
  if (this.totalCount < 0L) {
    return -1L;
  }

  long l = this.totalCount / this.pageSize;
  if (this.totalCount % this.pageSize > 0L) {
    l += 1L;
  }
  return l;
}

public boolean isHasNext()
{
  return this.pageNo + 1 <= getTotalPages();
}

public int getNextPage()
{
  if (isHasNext()) {
    return this.pageNo + 1;
  }
  return this.pageNo;
}

public boolean isHasPre()
{
  return this.pageNo - 1 >= 1;
}

public int getPrePage()
{
  if (isHasPre()) {
    return this.pageNo - 1;
  }
  return this.pageNo;
}

public int getStart()
{
  return this.start;
}

public static String getPageCacheKey(String key)
{
  String str = "";
  if (key.startsWith("/")) {
    str = key.substring(1, key.length());
  }

  if (str.indexOf("/") != -1) {
    str = str.substring(0, str.indexOf("/"));
  }

  return str;
}

public String toJson() {
  StringBuffer localStringBuffer = new StringBuffer();
  localStringBuffer.append("{'pageNo':").append(this.pageNo).append(",'pageSize':").append(this.pageSize).append(",'start':").append(this.start).append(",'totalCount':").append(this.totalCount).append(",'orderBy':'").append(this.orderBy).append("','orderType':'").append(this.orderType).append("','result':[");
  try
  {
	  
    if ((null != this.resultList) && (!this.resultList.isEmpty())) {
      int i = this.resultList.size();
      for (int j = 0; j < i; j++) {
       
      
      }
    }
  } catch (Exception localException1) {
    this.logger.error(localException1.getMessage(), localException1);
  }

  String str = localStringBuffer.toString();
  if (str.endsWith(","))
    str = str.substring(0, str.length() - 1) + "]}";
  else {
    str = str + "]}";
  }
  return str;
}
}
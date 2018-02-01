package com.dongnao.workbench.common.exceptions;



import java.sql.SQLException;

import com.dongnao.workbench.common.util.StringUtil;

public class DAOException extends UnexpectedException
{
  private static final long serialVersionUID = 5071002648081458703L;
  private String title = "数据库操作异常";
  private String sql = "";
  private String sqlstate = "";

  public DAOException(String paramString)
  {
    super(paramString);
  }

  public DAOException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }

  public DAOException(String paramString1, Throwable paramThrowable, String paramString2) {
    super(paramString1, paramThrowable);
    this.errorCode = paramString2;
  }

  public DAOException(String paramString1, String paramString2, Throwable paramThrowable)
  {
    super(paramString2, paramThrowable);
    this.title = paramString1;
  }

  public DAOException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }

  public String getErrorTitle() {
    return this.title;
  }

  public String getSql() {
    return this.sql;
  }

  public String getErrorCode() {
    if ((getCause() != null) && ((getCause() instanceof SQLException))) {
      return ((SQLException)getCause()).getErrorCode() + "";
    }
    return this.errorCode;
  }

  public String getStatus() {
    if ((getCause() != null) && ((getCause() instanceof SQLException))) {
      return ((SQLException)getCause()).getSQLState();
    }
    return this.sqlstate;
  }

  public String getErrorDescription() {
    return StringUtil.format("数据库操作异常：<p>{0}：<p><strong>{1}</strong>", new String[] { getMessage(), getCause() == null ? "" : getCause().getMessage() });
  }
}
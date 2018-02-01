package com.dongnao.workbench.common.exceptions;

import org.springframework.core.NestedRuntimeException;
public class UnexpectedException extends NestedRuntimeException
{
  private static final long serialVersionUID = 1839620751050558396L;
  private boolean hasBeanRecorded = false;
  protected String errorCode;

  public UnexpectedException(String paramString, boolean paramBoolean)
  {
    super(paramString);
    recorded(paramBoolean);
  }

  public UnexpectedException(Throwable paramThrowable, boolean paramBoolean)
  {
    super("", paramThrowable);
    recorded(paramBoolean);
  }

  public UnexpectedException(String paramString, Throwable paramThrowable, boolean paramBoolean)
  {
    super(paramString, paramThrowable);
    recorded(paramBoolean);
  }

  public UnexpectedException(String paramString)
  {
    super(paramString);
  }

  public UnexpectedException(Throwable paramThrowable) {
    super(paramThrowable.getMessage(), paramThrowable);
  }

  public UnexpectedException(String paramString, Throwable paramThrowable) {
    super(paramString, paramThrowable);
  }

  public String getErrorCode()
  {
    return "-1";
  }

  public String getErrorTitle()
  {
    return "运行时错误";
  }

  public String getErrorDescription()
  {
    String str = getMessage();
    if ((str == null) || ("".equals(str))) {
      str = getCause().getMessage();
    }
    return str;
  }

  public boolean hasBeanRecorded()
  {
    return this.hasBeanRecorded;
  }

  public void recorded(boolean hasBeanRecorded)
  {
    this.hasBeanRecorded = hasBeanRecorded;
  }
}
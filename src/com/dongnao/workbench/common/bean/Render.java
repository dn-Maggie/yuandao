package com.dongnao.workbench.common.bean;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

public abstract class Render
{
  protected Logger logger;
  private String a;
  private String b;
  protected int status;
  protected boolean setStauts;

  public Render()
  {
    this.logger = LoggerFactory.getLogger(super.getClass());

    this.a = ("text/html;charset=UTF-8");

    this.status = 200;
    this.setStauts = false;
  }
  public void setUrl(String url) {
    this.b = url;
  }

  protected String getUrl() {
    return this.b;
  }

 

  public void setContentType(HttpServletResponse response, String contentType)
  {
    if (response.getContentType() == null)
      if (contentType != null)
        response.setContentType(contentType);
      else
        response.setContentType(contentType);
  }

  public void setContentType(String contentType)
  {
    this.a = contentType;
  }

  public String getContentType() {
    return this.a;
  }

  public void bindArgsToRender(Map model, HttpServletRequest request, HttpServletResponse response)
  {
    Iterator localIterator = model.entrySet().iterator();
    while (localIterator.hasNext()) {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (!(localEntry.getKey() instanceof String)) {
        throw new IllegalArgumentException("Invalid key [" + localEntry.getKey() + "] in model Map: only Strings allowed as model keys");
      }

      String str = (String)localEntry.getKey();
      Object localObject = localEntry.getValue();
      if (localObject != null) {
        request.setAttribute(str, localObject);
        if (this.logger.isDebugEnabled())
          this.logger.debug("Added model object '" + str + "' of type [" + localObject.getClass().getName() + "] to request in view ");
      }
      else
      {
        request.removeAttribute(str);
        if (this.logger.isDebugEnabled())
          this.logger.debug("Removed model object '" + str + "' from request in view");
      }
    }
  }

  public boolean supportsRenderTemplate()
  {
    return false;
  }

  public abstract void render(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse);
}
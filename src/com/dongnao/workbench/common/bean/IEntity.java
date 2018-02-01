package com.dongnao.workbench.common.bean;

import java.io.Serializable;

public abstract interface IEntity extends Serializable, Cloneable
{
  public abstract String toJson();
}

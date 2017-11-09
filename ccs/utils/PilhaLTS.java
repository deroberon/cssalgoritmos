package ccs.utils;

import java.util.*;

/**
 * <p>Title: Verificador Formal para CCS</p>
 * <p>Description: Implementação de duas técnicas de checagem de Bi simulation</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Andre Gustavo Andrade
 * @version 1.0
 */

public class PilhaLTS {

  Vector v;
  Vector l;
  int el;

  public PilhaLTS() {
    v=new Vector();
    l=new Vector();
    el=0;
  }

  public void empty()
  {
    el=0;
    v.clear();
    l.clear();
  }

  public void push(EstadoLTS e)
  {
    v.add(el,e);
    l.add(el,e.succ());
    el=el+1;
  }

  public EstadoLTS top()
  {
    if (el-1<0) return null;
    return (EstadoLTS)v.get(el-1);
  }

  public ListaTransicoes topLista()
  {
    if (el-1<0) return null;
    return (ListaTransicoes)l.get(el-1);
  }

  public void pop()
  {
    el=el-1;
    //System.out.println("POP: "+el);
  }

  public boolean inEmpty()
  {
    if (el==0) return true;
    return false;
  }

  public boolean isIn(EstadoLTS est)
  {
    //return v.contains(est);
    int i;
    for(i=0;i<el;i++)
    {
      if (((EstadoLTS)v.get(i)).toString().equals(est.toString())) return true;
    }
    return false;
  }

  public void debug()
  {
    int i;
    for(i=0;i<el;i++)
    {
      System.out.println("Lista pos: "+i+" da pilha: "+((ListaTransicoes)l.get(i)).realsize()+" : "+((ListaTransicoes)l.get(i)).toString());
      System.out.println("Lista pos: "+i+" da pilha: "+((EstadoLTS)v.get(i)).toString());
    }
  }
}
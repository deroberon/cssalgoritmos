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

public class ListaTransicoes {

  private Vector _trans;
  private int at;

  public ListaTransicoes() {
    _trans=new Vector();
    at=0;
  }

  public void add(EstadoLTS g)
  {
    _trans.add(g);
  }

  public boolean isempty()
  {
    if (at>=_trans.size()) return true;
    return false;
  }

  public EstadoLTS choose_and_remove()
  {
    at=at+1;
    return (EstadoLTS)_trans.get(at-1);
  }

  public int indexof(EstadoLTS g)
  {
    //return _trans.indexOf(g);
    int i;
    for (i=0;i<_trans.size();i++)
    {
      if (((EstadoLTS)_trans.get(i)).toString().equals(g.toString())) return i;
    }
    System.out.println("Problemas...");
    return 0;
  }

  public int size()
  {
    return _trans.size()-at;
  }

  public int realsize()
  {
    return _trans.size();
  }

  public String toString()
  {
    String n=new String("[");
    int j=0;
    for (Enumeration e = _trans.elements() ; e.hasMoreElements() ;)
    {
      if (j<at)
      {
         j++;
         EstadoLTS est=(EstadoLTS)e.nextElement();
      }
    else
    {
      EstadoLTS est=(EstadoLTS)e.nextElement();
      n=n.concat(est.toString());
    }
    }
    n=n.concat("]");
    return n;
  }

}
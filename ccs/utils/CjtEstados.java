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

public class CjtEstados {

  public CjtEstados() {
  }

  public static boolean isSame(Vector v1, Vector v2)
  {
    int i,j;
    boolean achou;
    if (v1.size()!=v2.size()) return false;
    for(i=0;i<v1.size();i++)
    {
      Estado s1=(Estado)v1.get(i);
      achou=false;
      for(j=0;j<v2.size();j++)
      {
        Estado s2=(Estado)v2.get(j);
        if (s1==s2)
        {
          achou=true;
          break;
        }
      }
      if (achou==false) return false;
    }
    return true;
  }

  public static void cup(Vector v1, Vector v2)
  {
    int i,j;
    for(i=0;i<v2.size();i++)
    {
      boolean has=false;
      for(j=0;j<v1.size();j++) if (((Estado)v1.get(j)).toString().equals(((Estado)v2.get(i)).toString())) has=true;
      if (has==false) v1.add(v2.get(i));
    }
  }

  public static void insert(Vector v1, Vector v2)
  {
    int i;
    boolean achei=false;
    for(i=0;i<v1.size();i++)
    {
      if (isSame((Vector)v1.get(i),v2)) achei=true;
    }
    if (!achei) v1.add(v2);
  }

  public static Vector clone(Vector v)
  {
    int i;
    Vector tmp=new Vector();
    for(i=0;i<v.size();i++)tmp.add(v.get(i));
    return tmp;
  }

  public static void split(Vector v1, Vector v2, Vector v3)
  {
    int i,j;
    for(i=0;i<v1.size();i++)
    {
      boolean has=false;
      for(j=0;j<v2.size();j++)
      {
        if (((Estado)v1.get(i)).toString().equals(((Estado)v2.get(j)).toString()))
        {
           has=true;
        }
      }
      if (has==false) v3.add(v1.get(i));
    }
  }

  public static Vector cjtInv(String act,Vector v)
  {
    int i;
    Vector tmp=new Vector();
    for(i=0;i<v.size();i++)
    {
      Estado e=(Estado)v.get(i);
      Hashtable he=e.get_inv();
      if (he.containsKey(act))
      {
        CjtEstados.cup(tmp,(Vector)he.get(act));
      }
    }
    return tmp;
  }

  public static String prt_cjt(Vector v)
  {
    int i;
    String tmp=new String("( ");
    for (i=0;i<v.size();i++)
    {
      tmp=tmp.concat(((Estado)v.get(i)).toString());
      tmp=tmp.concat(" ");
    }
    tmp=tmp.concat(")");
    return tmp;
  }

  public static String prt_cjtcjt(Vector v)
  {
    int i;
    String tmp=new String("[ ");
    for(i=0;i<v.size();i++)
    {
      tmp=tmp.concat(prt_cjt((Vector)v.get(i)));
      tmp=tmp.concat(" ");
    }
    tmp=tmp.concat("]");
    return tmp;
  }
}
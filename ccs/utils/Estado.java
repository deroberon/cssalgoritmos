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

public class Estado {

  private String _name;
  private Hashtable _trans;
  private Hashtable _invtrans;

  public Estado(String name) {
    _name=name;
    _trans=new Hashtable();
    _invtrans=new Hashtable();
  }

  public void add(String act, Estado e)
  {
    Vector v;
    if (_trans.containsKey(act))
    {
      v=(Vector)_trans.get(act);
      v.add(e);
    }
    else
    {
      v=new Vector();
      v.add(e);
      _trans.put(act,v);
    }
    e.add_inv(act,this);
  }

  public void add_inv(String act, Estado e)
  {
    Vector v;
    if (_invtrans.containsKey(act))
    {
      v=(Vector)_invtrans.get(act);
      v.add(e);
    }
    else
    {
      v=new Vector();
      v.add(e);
      _invtrans.put(act,v);
    }
  }

  public boolean canDo(String act)
  {
    if (_trans.containsKey(act)) return true;
    return false;
  }

  public Vector doAct(String act)
  {
    if (!_trans.containsKey(act)) return null;
    return (Vector)_trans.get(act);
  }

  public Enumeration act()
  {
    return _trans.keys();
  }

  public Hashtable get_inv()
  {
    return _invtrans;
  }

  public String toString()
  {
    return _name;
  }
}
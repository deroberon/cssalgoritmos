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

public class EstadoLTS {

  private Estado _q1;
  private Estado _q2;
  private boolean _isfail;

  public EstadoLTS(Estado q1, Estado q2) {
    _q1=q1;
    _q2=q2;
    _isfail=false;
  }

  public EstadoLTS()
  {
    _isfail=true;
  }

  public ListaTransicoes succ()
  {
    ListaTransicoes nova;
    nova=new ListaTransicoes();
    /* Algum tipo de processamento */
    Hashtable tmp=new Hashtable();
    for (Enumeration e = _q1.act() ; e.hasMoreElements() ;) {
      String act1=(String)e.nextElement();
      if (_q2.canDo(act1))
      {
        Vector v1=_q1.doAct(act1);
        Vector v2=_q2.doAct(act1);
        for (Enumeration e1 = v1.elements() ; e1.hasMoreElements() ;) {
          Estado es1=(Estado)e1.nextElement();
          for (Enumeration e2 = v2.elements() ; e2.hasMoreElements() ;) {
            Estado es2=(Estado)e2.nextElement();
            nova.add(new EstadoLTS(es1,es2));
            tmp.put(act1,"");
          }
        }
      }
      else
      {
        nova.add(new EstadoLTS());
      }
     }
     for (Enumeration e = _q2.act() ; e.hasMoreElements() ;) {
      String act2=(String)e.nextElement();
      if (!tmp.containsKey(act2)) nova.add(new EstadoLTS());
     }

    return nova;
  }

  public boolean isFail()
  {
    return _isfail;
  }

  public String toString()
  {
   if (!_isfail) return "(".concat(_q1.toString()).concat(",").concat(_q2.toString()).concat(")");
   return "(fail)";
  }

}
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

public class ConjuntoEstadosLTS {

  public Vector v;

  public ConjuntoEstadosLTS() {
    v=new Vector();
  }

  public void add(EstadoLTS est)
  {
    v.add(est);
  }

  public void setEmpty()
  {
    v.clear();
  }

  public boolean isIn(EstadoLTS est)
  {
    //return v.contains(est);
    int i;
    for(i=0;i<v.size();i++)
    {
      if (((EstadoLTS)v.get(i)).toString().equals(est.toString())) return true;
    }
    return false;
  }
}
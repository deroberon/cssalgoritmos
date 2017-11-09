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

public class PilhaBitArray {

  Vector v;
  int el;

  public PilhaBitArray() {
    v=new Vector();
    empty();
  }

  public void empty()
  {
    el=0;
    v.clear();
  }

  public void push(BitArray e)
  {
    v.add(el,e);
    el=el+1;
  }

  public BitArray top()
  {
    return (BitArray)v.get(el-1);
  }

  public BitArray pop()
  {
    el=el-1;
    return (BitArray)v.get(el);
  }

  public void debug()
  {
    int i;
    for(i=0;i<el;i++)
    {
      System.out.println("Pos: "+i+" da pilha: "+((BitArray)v.get(i)).size()+" : "+((BitArray)v.get(i)).toString());
    }
  }
}
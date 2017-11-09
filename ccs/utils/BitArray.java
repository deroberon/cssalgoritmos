package ccs.utils;

/**
 * <p>Title: Verificador Formal para CCS</p>
 * <p>Description: Implementação de duas técnicas de checagem de Bi simulation</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Andre Gustavo Andrade
 * @version 1.0
 */

public class BitArray {

  int val[];
  int _tam;

  public BitArray(int tam) {
    int i;
    val=new int[tam];
    _tam=tam;
    for(i=0;i<_tam;i++) setzero(i);
  }

  public void setone(int pos)
  {
    val[pos]=1;
  }

  public void setzero(int pos)
  {
    val[pos]=0;
  }

  public boolean isone()
  {
    int i;
    for(i=0;i<_tam;i++) if (val[i]!=1) return false;
    return true;
  }

  public int value(int pos)
  {
    return val[pos];
  }

  public int size()
  {
    return _tam;
  }

  public String toString()
  {
    int i;
    String n=new String("(");
    for (i=0;i<_tam;i++) n=n.concat(Integer.toString(val[i]));
    n=n.concat(")");
    return n;
  }
}
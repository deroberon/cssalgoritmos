package ccs.verificadores;

import java.util.*;
import ccs.utils.*;

/**
 * <p>Title: Verificador Formal para CCS</p>
 * <p>Description: Implementação de duas técnicas de checagem de Bi simulation</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Andre Gustavo Andrade
 * @version 1.0
 */

public class Refina {

  public Refina() {
  }

  public Vector doIt(Vector st, Vector acts)
  {
    int i,j;
    boolean change=true;
    Vector p=new Vector();
    p.add(st);

    while(change)
    {
      //System.out.println("wile");
      change=false;

      for(i=0;i<acts.size();i++)
      {
        for(j=0;j<p.size();j++)
        {
          Vector vnew=CjtEstados.cjtInv((String)acts.get(i),(Vector)p.get(j));
          //System.out.println(j+" : "+CjtEstados.prt_cjt(vnew)+" : "+CjtEstados.prt_cjt((Vector)p.get(j)));
          if (!((vnew.size()==0)||(CjtEstados.isSame(vnew,(Vector)p.get(j)))))
          {
            Vector tmp=new Vector();
            //System.out.println("Antes: "+CjtEstados.prt_cjtcjt(p));

            CjtEstados.split((Vector)p.get(j),vnew,tmp);
            if (!CjtEstados.isSame(tmp,(Vector)p.get(j)))
            {

              change=true;
              p.remove(j);
              j=j-1;
              CjtEstados.insert(p,vnew);
              if (tmp.size()!=0) CjtEstados.insert(p,tmp);
            }
            //System.out.println("Depois: "+CjtEstados.prt_cjtcjt(p));
          }

        }
      }
    }

    return p;
  }

}

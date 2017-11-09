package ccs.verificadores;

import ccs.utils.*;

/**
 * <p>Title: Verificador Formal para CCS</p>
 * <p>Description: Implementação de duas técnicas de checagem de Bi simulation</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Andre Gustavo Andrade
 * @version 1.0
 */

public class OnTheFly {

  ConjuntoEstadosLTS _V,_R,_W;
  PilhaLTS _St1;
  PilhaBitArray _St2;
  EstadoLTS inicial;

  public static final int DFS_FALSE=0;
  public static final int DFS_TRUE=1;
  public static final int DFS_UNRELIABLE=2;

  public OnTheFly() {
  }

  public boolean onTheFly(Estado q01, Estado q02)
  {
    int result;
    _W=new ConjuntoEstadosLTS();
    _R=new ConjuntoEstadosLTS();
    _V=new ConjuntoEstadosLTS();
    _St1=new PilhaLTS();
    _St2=new PilhaBitArray();
    inicial=new EstadoLTS(q01,q02);
    do
    {
      result=partial_DFS();
      //System.out.println(result);
    } while(result==DFS_UNRELIABLE);
    if (result==DFS_TRUE) return true;
    return false;
  }

  private int partial_DFS()
  {
    boolean stable;
    /*Linha 1-5*/
    _V.setEmpty();
    _R.setEmpty();
    stable=false;
    _St1.empty();
    _St2.empty();
    _St1.push(inicial);
    _St2.push(new BitArray(2));
    _St2.push(new BitArray(2*_St1.topLista().size()));
    /*Linha 6-10*/
    while (!_St1.inEmpty())
    {

      /**/
      //_St1.debug();
      //_St2.debug();
      //System.out.println("");
      /**/

      stable=true;
      ListaTransicoes l=_St1.topLista();
      EstadoLTS q=_St1.top();
      BitArray M=_St2.top();
      if (!l.isempty())
      {
        /*Linha 11-13 */
        EstadoLTS ql=l.choose_and_remove();
        if ((!_W.isIn(ql))&&(!_V.isIn(ql)))
        {
          /*Linha 13 - */
          if (!_St1.isIn(ql))
          {
            if (!ql.isFail())
            {
              _St1.push(ql);
              _St2.push(new BitArray(2*_St1.topLista().size()));
            }
          }
          /* Linha 18 - */
          else
          {
            _R.add(ql);
            /* Sujeito a otimizacao */
            M.setone(l.indexof(ql)*2);
            M.setone(l.indexof(ql)*2+1);
           //System.out.println("Confira: "+M.toString());
          }
        }
        /*Linha 22*/
        else
        {
          if (!_W.isIn(ql))
          {
            /* Sujeito a otimizacao */
            M.setone(l.indexof(ql)*2);
            M.setone(l.indexof(ql)*2+1);
          }
        }
      }
      /* Linha 27 */
      else
      {
        //_St1.debug();
        //_St2.debug();
        //System.out.println("");

        _St1.pop();
        _St2.pop();
        _V.add(q);

        /* Linha 30*/
        BitArray ml=_St2.top();
        //System.out.println("Vect: "+ml.toString());
        if (M.isone())
        {
            ListaTransicoes ltmp=_St1.topLista();
            if (ltmp==null)
            {
              ml.setone(0);
              ml.setone(1);
            }
            else
            {
              ml.setone(ltmp.indexof(q)*2);
              ml.setone(ltmp.indexof(q)*2+1);
            }
        }
        /* Linha 33*/
        else
        {
          _W.add(q);
          if (_R.isIn(q)) stable=false;
        }
      }
    }
    /* Linha 41 */
    BitArray M=_St2.top();
    if ((M.value(0)!=1)&&(M.value(1)!=1))
      return DFS_FALSE;
    else
    {
      if (stable) return DFS_TRUE;
      else
        return DFS_UNRELIABLE;
    }
    /* Linha 51 */
  }
}
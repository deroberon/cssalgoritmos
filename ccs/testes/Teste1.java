package ccs.testes;

import ccs.utils.*;
import ccs.verificadores.*;
import java.util.*;

/**
 * <p>Title: Verificador Formal para CCS</p>
 * <p>Description: Implementação de duas técnicas de checagem de Bi simulation</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Andre Gustavo Andrade
 * @version 1.0
 */

public class Teste1 {

  public Teste1() {
  }

  public static void main(String argvs[])
  {
    Estado A=new Estado("A");
    Estado B=new Estado("B");
    Estado C=new Estado("C");
    Estado D=new Estado("D");
    Estado E=new Estado("E");

    Estado A2=new Estado("A2");
    Estado B2=new Estado("B2");
    Estado C2=new Estado("C2");
    Estado D2=new Estado("D2");
    Estado E2=new Estado("E2");
    Estado F2=new Estado("F2");

    A.add("a",B);
    A.add("a",C);
    A.add("b",D);
    D.add("a",C);
    D.add("b",E);
    D.add("c",D);

    A2.add("a",B2);
    A2.add("a",C2);
    A2.add("b",D2);
    A2.add("a",F2);
    A2.add("z",D2);
    D2.add("a",C2);
    D2.add("b",E2);
    D2.add("c",D2);

    /* ALGUNS TESTES de funcoes basicas
    EstadoLTS AS=new EstadoLTS(A,A2);
    System.out.println("Estado LTS:");
    System.out.println(AS.toString());
    System.out.println("Sucessores LTS:");
    System.out.println(AS.succ().toString());
    */

    System.out.println("Testando o verificador On-The-Fly em sistemas Nao Equivalentes: ");

    OnTheFly tt=new OnTheFly();
    boolean isso=tt.onTheFly(A,A2);
    if (isso==true) System.out.println("Equiv");
    else System.out.println("Not Equiv");


    System.out.println("Testando o verificador On-The-Fly em sistemas Equivalentes: ");

    OnTheFly tt2=new OnTheFly();
    boolean isso2=tt2.onTheFly(A,A);
    if (isso2==true) System.out.println("Equiv");
    else System.out.println("Not Equiv");

    /**/
    System.out.println("Minimizando as Transicoes Anteriores: ");

    Vector bla=new Vector();
    Vector aac=new Vector();
    bla.add(A);
    bla.add(B);
    bla.add(C);
    bla.add(D);
    bla.add(E);
    aac.add("a");
    aac.add("b");
    aac.add("c");
    Refina ref=new Refina();
    System.out.println(CjtEstados.prt_cjtcjt(ref.doIt(bla,aac)));

    /**/
    System.out.println("Minimizando as Transicoes Do Exemplo de Aula: ");

    Estado EE1=new Estado("1");
    Estado EE2=new Estado("2");
    Estado EE3=new Estado("3");
    Estado EE4=new Estado("4");
    Estado EE5=new Estado("5");
    Estado EE0=new Estado("0");

    EE0.add("a",EE1);
    EE1.add("a",EE2);
    EE2.add("a",EE1);
    EE0.add("b",EE3);
    EE1.add("b",EE3);
    EE1.add("b",EE4);
    EE2.add("b",EE4);
    EE3.add("c",EE5);
    EE4.add("c",EE5);

    Vector bla2=new Vector();
    Vector aac2=new Vector();
    bla2.add(EE0);
    bla2.add(EE1);
    bla2.add(EE2);
    bla2.add(EE3);
    bla2.add(EE4);
    bla2.add(EE5);

    aac2.add("a");
    aac2.add("b");
    aac2.add("c");
    Refina ref2=new Refina();
    System.out.println(CjtEstados.prt_cjtcjt(ref2.doIt(bla2,aac2)));

    //ref2.doIt(bla2,aac2);
  }
}
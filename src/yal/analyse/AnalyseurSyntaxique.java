
//----------------------------------------------------
// The following code was generated by CUP v0.11a beta 20060608
// Tue Jan 28 17:49:52 CET 2020
//----------------------------------------------------

package yal.analyse;

import java.util.*;
import yal.arbre.*;
import yal.arbre.expressions.*;
import yal.arbre.instructions.*;
import yal.declaration.*;
import yal.declaration.entries.*;
import yal.declaration.symbols.*;
import yal.exceptions.AnalyseSyntaxiqueException;
import java_cup.runtime.*;

/** CUP v0.11a beta 20060608 generated parser.
  * @version Tue Jan 28 17:49:52 CET 2020
  */
public class AnalyseurSyntaxique extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public AnalyseurSyntaxique() {super();}

  /** Constructor which sets the default scanner. */
  public AnalyseurSyntaxique(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public AnalyseurSyntaxique(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\016\000\002\002\004\000\002\003\007\000\002\002" +
    "\004\000\002\002\003\000\002\004\004\000\002\004\003" +
    "\000\002\007\004\000\002\007\003\000\002\010\005\000" +
    "\002\005\006\000\002\005\005\000\002\005\005\000\002" +
    "\006\003\000\002\006\003" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\036\000\004\004\005\001\002\000\004\002\040\001" +
    "\002\000\004\013\006\001\002\000\004\005\007\001\002" +
    "\000\012\010\013\011\010\013\011\015\015\001\002\000" +
    "\006\013\033\014\032\001\002\000\004\012\031\001\002" +
    "\000\012\006\ufffc\010\ufffc\011\ufffc\013\ufffc\001\002\000" +
    "\004\013\027\001\002\000\004\006\026\001\002\000\004" +
    "\013\024\001\002\000\012\006\ufffe\010\013\011\010\013" +
    "\011\001\002\000\012\010\ufffa\011\ufffa\013\ufffa\015\015" +
    "\001\002\000\010\010\013\011\010\013\011\001\002\000" +
    "\012\006\uffff\010\013\011\010\013\011\001\002\000\012" +
    "\006\ufffd\010\ufffd\011\ufffd\013\ufffd\001\002\000\010\010" +
    "\ufffb\011\ufffb\013\ufffb\001\002\000\004\007\025\001\002" +
    "\000\012\010\ufff9\011\ufff9\013\ufff9\015\ufff9\001\002\000" +
    "\004\002\000\001\002\000\004\007\030\001\002\000\012" +
    "\006\ufff7\010\ufff7\011\ufff7\013\ufff7\001\002\000\006\013" +
    "\033\014\032\001\002\000\004\007\ufff4\001\002\000\004" +
    "\007\ufff5\001\002\000\004\007\035\001\002\000\012\006" +
    "\ufff8\010\ufff8\011\ufff8\013\ufff8\001\002\000\004\007\037" +
    "\001\002\000\012\006\ufff6\010\ufff6\011\ufff6\013\ufff6\001" +
    "\002\000\004\002\001\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\036\000\004\003\003\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\014\002\013\004\015" +
    "\005\011\007\017\010\016\001\001\000\004\006\035\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\004\005\021\001" +
    "\001\000\006\007\022\010\016\001\001\000\006\004\020" +
    "\005\011\001\001\000\004\005\021\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\004\006\033\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$AnalyseurSyntaxique$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$AnalyseurSyntaxique$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$AnalyseurSyntaxique$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}




    public void report_error(String message, Object info) {

        HashMap<Integer, String> lesTerminaux = new HashMap<>() ;
    
        lesTerminaux.put(new Integer(CodesLexicaux.DEBUT), "debut") ;
        lesTerminaux.put(new Integer(CodesLexicaux.FIN), "fin") ;
        lesTerminaux.put(new Integer(CodesLexicaux.POINTVIRGULE), ";") ;

        StringBuffer m = new StringBuffer() ;

        if (info instanceof java_cup.runtime.Symbol) {
            java_cup.runtime.Symbol s = ((java_cup.runtime.Symbol) info);

            if (s.left >= 0) {                
                m.append("\tligne : " + (s.left + 1)) ;
                if (s.right >= 0)                    
                    m.append(" colonne : " + (s.right+1)) ;
            }
            
            if (s.value != null) {
                lesTerminaux.put(CodesLexicaux.CSTENTIERE, "" + s.value) ;
            }

            if (lesTerminaux.containsKey(new Integer(s.sym))) {
                m.append(" dernier token lu : " + lesTerminaux.get(new Integer(s.sym))) ;
            }
            else {
                m.append(" expression non terminée") ;
            }

        }
        throw new AnalyseSyntaxiqueException("" + m) ;
    }

    public void report_fatal_error(String message, Object info) {
        report_error(message, info);
    }

}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$AnalyseurSyntaxique$actions {


             
  private final AnalyseurSyntaxique parser;

  /** Constructor */
  CUP$AnalyseurSyntaxique$actions(AnalyseurSyntaxique parser) {
    this.parser = parser;
  }

  /** Method with the actual generated action code. */
  public final java_cup.runtime.Symbol CUP$AnalyseurSyntaxique$do_action(
    int                        CUP$AnalyseurSyntaxique$act_num,
    java_cup.runtime.lr_parser CUP$AnalyseurSyntaxique$parser,
    java.util.Stack            CUP$AnalyseurSyntaxique$stack,
    int                        CUP$AnalyseurSyntaxique$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$AnalyseurSyntaxique$result;

      /* select the action based on the action number */
      switch (CUP$AnalyseurSyntaxique$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // EXP ::= CSTENTIERE 
            {
              Expression RESULT =null;
		int cleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).left;
		int cright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).right;
		String c = (String)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
		 RESULT = new ConstanteEntiere(c, cleft + 1); 
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("EXP",4, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // EXP ::= IDF 
            {
              Expression RESULT =null;
		int idfleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).left;
		int idfright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).right;
		String idf = (String)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
		 RESULT = new Variable(new VariableEntry(idf), idfleft + 1); 
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("EXP",4, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // INST ::= ECRIRE EXP POINTVIRGULE 
            {
              ArbreAbstrait RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).right;
		Expression e = (Expression)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).value;
		 RESULT = new Ecrire(e, eleft + 1) ; 
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("INST",3, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-2)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // INST ::= LIRE IDF POINTVIRGULE 
            {
              ArbreAbstrait RESULT =null;
		int idfleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).left;
		int idfright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).right;
		String idf = (String)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).value;
		  Variable var = new Variable(new VariableEntry(idf), idfleft + 1);
                    RESULT = new Lire(var, idfleft + 1); 
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("INST",3, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-2)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // INST ::= IDF EQUAL EXP POINTVIRGULE 
            {
              ArbreAbstrait RESULT =null;
		int idfleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-3)).left;
		int idfright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-3)).right;
		String idf = (String)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-3)).value;
		int eleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).right;
		Expression e = (Expression)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).value;
		  Variable var = new Variable(new VariableEntry(idf), idfleft + 1);
                        RESULT = new Affectation(var, e, eleft + 1); 
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("INST",3, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-3)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // DECL_VAR ::= TYPE IDF POINTVIRGULE 
            {
              Object RESULT =null;
		int idfleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).left;
		int idfright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).right;
		String idf = (String)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).value;
		 TDS.Instance().AddEntry(new VariableEntry(idf),
                     new IntSymbol(), idfleft + 1); 
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("DECL_VAR",6, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-2)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // DECL_LIST ::= DECL_VAR 
            {
              Object RESULT =null;

              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("DECL_LIST",5, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // DECL_LIST ::= DECL_VAR DECL_LIST 
            {
              Object RESULT =null;

              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("DECL_LIST",5, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // LINST ::= INST 
            {
              ArbreAbstrait RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).left;
		int iright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).right;
		ArbreAbstrait i = (ArbreAbstrait)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
		 BlocDInstructions b = new BlocDInstructions(ileft + 1);
                       b.ajouter(i);
                       RESULT = b; 
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("LINST",2, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // LINST ::= LINST INST 
            {
              ArbreAbstrait RESULT =null;
		int lileft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).left;
		int liright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).right;
		ArbreAbstrait li = (ArbreAbstrait)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).value;
		int ileft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).left;
		int iright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).right;
		ArbreAbstrait i = (ArbreAbstrait)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
		 ((BlocDInstructions)li).ajouter(i);
                        RESULT = li ; 
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("LINST",2, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // PROG_BODY ::= LINST 
            {
              ArbreAbstrait RESULT =null;
		int lileft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).left;
		int liright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).right;
		ArbreAbstrait li = (ArbreAbstrait)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
		 RESULT = li ; 
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("PROG_BODY",0, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // PROG_BODY ::= DECL_LIST LINST 
            {
              ArbreAbstrait RESULT =null;
		int lileft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).left;
		int liright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()).right;
		ArbreAbstrait li = (ArbreAbstrait)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.peek()).value;
		 RESULT = li ; 
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("PROG_BODY",0, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // PROG ::= PROGRAMME IDF DEBUT PROG_BODY FIN 
            {
              ArbreAbstrait RESULT =null;
		int progleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).left;
		int progright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).right;
		ArbreAbstrait prog = (ArbreAbstrait)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).value;
		 RESULT = prog ; 
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("PROG",1, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-4)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          return CUP$AnalyseurSyntaxique$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= PROG EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).right;
		ArbreAbstrait start_val = (ArbreAbstrait)((java_cup.runtime.Symbol) CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)).value;
		RESULT = start_val;
              CUP$AnalyseurSyntaxique$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.elementAt(CUP$AnalyseurSyntaxique$top-1)), ((java_cup.runtime.Symbol)CUP$AnalyseurSyntaxique$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$AnalyseurSyntaxique$parser.done_parsing();
          return CUP$AnalyseurSyntaxique$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}


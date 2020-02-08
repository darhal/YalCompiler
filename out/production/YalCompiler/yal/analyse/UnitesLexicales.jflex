package yal.analyse ;

import java_cup.runtime.*;
import yal.exceptions.AnalyseLexicaleException;
      
%%
   
%class AnalyseurLexical
%public

%line
%column
    
%type Symbol
%eofval{
        return symbol(CodesLexicaux.EOF) ;
%eofval}

%cup

%{

  private StringBuilder chaine ;

  private Symbol symbol(int type) {
	return new Symbol(type, yyline, yycolumn) ;
  }

  private Symbol symbol(int type, Object value) {
	return new Symbol(type, yyline, yycolumn, value) ;
  }
%}

idf = [A-Za-z_][A-Za-z_0-9]*

csteE = [0-9]+

finDeLigne = \r|\n
comment = \/\/.*{finDeLigne}
espace = {finDeLigne}  | [ \t\f] | {comment}


%%

"programme"            { return symbol(CodesLexicaux.PROGRAMME); }
"debut"                { return symbol(CodesLexicaux.DEBUT); }
"fin"              	   { return symbol(CodesLexicaux.FIN); }

"entier"               { return symbol(CodesLexicaux.TYPE); }

"="                    { return symbol(CodesLexicaux.EQUAL); }

"ecrire"               { return symbol(CodesLexicaux.ECRIRE); }

"lire"                 { return symbol(CodesLexicaux.LIRE); }

"tantque"              { return symbol(CodesLexicaux.TANTQUE); }

"repeter"              { return symbol(CodesLexicaux.REPETER); }

"fintantque"           { return symbol(CodesLexicaux.FINTANTQUE); }

"si"                   { return symbol(CodesLexicaux.SI); }

"alors"                { return symbol(CodesLexicaux.ALORS); }

"sinon"                { return symbol(CodesLexicaux.SINON); }

"finsi"                { return symbol(CodesLexicaux.FINSI); }

";"                    { return symbol(CodesLexicaux.POINTVIRGULE); }

"+"                    { return symbol(CodesLexicaux.OP_PLUS); }

"-"                    { return symbol(CodesLexicaux.OP_MINUS); }

"*"                    { return symbol(CodesLexicaux.OP_MULTIPLY); }

"/"                    { return symbol(CodesLexicaux.OP_DIVIDE); }

">"                    { return symbol(CodesLexicaux.OP_GREATER); }

"<"                    { return symbol(CodesLexicaux.OP_LESS); }

"=="                   { return symbol(CodesLexicaux.OP_EQUAL); }

"!="                   { return symbol(CodesLexicaux.OP_NEQUAL); }

"et"                   { return symbol(CodesLexicaux.OP_AND); }

"ou"                   { return symbol(CodesLexicaux.OP_OR); }

"non"                  { return symbol(CodesLexicaux.OP_NOT); }

"("                    { return symbol(CodesLexicaux.PARA_OPEN); }

")"                    { return symbol(CodesLexicaux.PARA_CLOSE); }

{csteE}      	       { return symbol(CodesLexicaux.CSTENTIERE, yytext()); }

{idf}      	           { return symbol(CodesLexicaux.IDF, yytext()); }

{espace}               {  }

.                      { throw new AnalyseLexicaleException(yyline, yycolumn, yytext()) ; }


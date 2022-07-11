import java_cup.runtime.*;
import java.io.*;
%%
%implements java_cup.runtime.Scanner
%type Symbol
%function next_token
%class A3Scanner
%eofval{return null;
%eofval}
%state CMT
%%
<YYINITIAL>\"[^\"]*\"   {return new Symbol(A3Symbol.QUOTE);}
<YYINITIAL>"IF" 		 { return new Symbol(A3Symbol.IF); }
<YYINITIAL>"ELSE" 		 { return new Symbol(A3Symbol.ELSE); }
<YYINITIAL>"WRITE" 	 { return new Symbol(A3Symbol.WRITE); }
<YYINITIAL>"READ" 		 { return new Symbol(A3Symbol.READ); }
<YYINITIAL>"RETURN" 	 { return new Symbol(A3Symbol.RETURN); }
<YYINITIAL>"BEGIN" 	 { return new Symbol(A3Symbol.BEGIN); }
<YYINITIAL>"END" 		 { return new Symbol(A3Symbol.END); }
<YYINITIAL>"MAIN" 		 { return new Symbol(A3Symbol.MAIN); }
<YYINITIAL>"INT" 		 { return new Symbol(A3Symbol.INT); }
<YYINITIAL>"REAL" 		 { return new Symbol(A3Symbol.REAL); }
<YYINITIAL>";"		 { return new Symbol(A3Symbol.SEMI); }
<YYINITIAL>","		 { return new Symbol(A3Symbol.COMMA); }
<YYINITIAL>"("		 { return new Symbol(A3Symbol.LPAREN); }
<YYINITIAL>")"		 { return new Symbol(A3Symbol.RPAREN); }
<YYINITIAL>"+"		 { return new Symbol(A3Symbol.PLUS); }
<YYINITIAL>"-"		 { return new Symbol(A3Symbol.MINUS); }
<YYINITIAL>"*"	         { return new Symbol(A3Symbol.TIMES); }
<YYINITIAL>"/"		 { return new Symbol(A3Symbol.DIVIDE); }
<YYINITIAL>":="		 { return new Symbol(A3Symbol.CEQUAL); }
<YYINITIAL>"=="		 { return new Symbol(A3Symbol.EEQUAL); }
<YYINITIAL>"!="		 { return new Symbol(A3Symbol.NEQUAL); }
<YYINITIAL>[0-9]+(.[0-9]+)? { return new Symbol(A3Symbol.NUMBER); }
<YYINITIAL>[a-zA-Z][a-zA-Z0-9]* { return  new Symbol(A3Symbol.ID);}
<CMT>"**/" { yybegin(YYINITIAL);}
<CMT>.|\n|\r {}
<YYINITIAL>"/**" {yybegin(CMT);}
<YYINITIAL>\t|" "|\r|\n {}
<YYINITIAL>. {return new Symbol(A3Symbol.error);}

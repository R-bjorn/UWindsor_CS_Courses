import java.nio.file.*;
import java.io.*;
%%
%{
	static int id, cmt, num, kw, qs;
	public static void main(String argv[]) throws java.io.IOException{
		A2 yy = new A2(new FileReader("A2.input"));
		while(yy.yylex() >= 0 ){}
		Files.write(Paths.get("A2.output"), ("identifiers: "+id+"\nkeywords: "+kw+"\nnumbers: "+num+"\ncomment: "+cmt+"\nquotedStrings: "+qs).getBytes());
	}
%}
%integer
%class A2
%state COMMENT

%%

<YYINITIAL> "/**" {yybegin(COMMENT);}
<YYINITIAL> INT|MAIN|BEGIN|STRING|REAL|WRITE|READ|IF|ELSE|RETURN|END {kw++;}
<YYINITIAL> [a-zA-Z][a-zA-Z0-9]* {id++;}
<YYINITIAL> [0-9]+(\.[0-9]+)? {num++;}
<YYINITIAL> \"[^\"]*\" {qs++;}
<COMMENT> "**/" {yybegin(YYINITIAL); cmt++;}
\r|\n|. {}
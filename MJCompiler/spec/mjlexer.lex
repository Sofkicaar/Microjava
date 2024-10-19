package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;

%%

%{

	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn, value);
	}

%}

%cup
%line
%column

%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%

" " 	{ }
"\b" 	{ }
"\t" 	{ }
"\r\n" 	{ }
"\f" 	{ }

"program"   { return new_symbol(sym.PROG, yytext()); }
"print" 	{ return new_symbol(sym.PRINT, yytext()); }
"return" 	{ return new_symbol(sym.RETURN, yytext()); }
"void" 		{ return new_symbol(sym.VOID, yytext()); }
"const"		{ return new_symbol(sym.CONST, yytext()); }
"class"		{ return new_symbol(sym.CLASS, yytext()); }
"extends"	{ return new_symbol(sym.EXTENDS, yytext()); }
"break"		{ return new_symbol(sym.BREAK, yytext()); }
"else"		{ return new_symbol(sym.ELSE, yytext()); }
"if"		{ return new_symbol(sym.IF, yytext()); }
"for"		{ return new_symbol(sym.FOR, yytext()); }
"new"		{ return new_symbol(sym.NEW, yytext()); }
"read"		{ return new_symbol(sym.READ, yytext()); }
"continue"	{ return new_symbol(sym.CONTINUE, yytext()); }
"this"		{ return new_symbol(sym.THIS, yytext()); }
"static"		{ return new_symbol(sym.STATIC, yytext()); }
"namespace"		{ return new_symbol(sym.NAMESPACE, yytext()); }
"using"         { return new_symbol(sym.USING, yytext());}

"+" 		{ return new_symbol(sym.PLUS, yytext()); }
"=" 		{ return new_symbol(sym.EQUAL, yytext()); }
";" 		{ return new_symbol(sym.SEMI, yytext()); }
"," 		{ return new_symbol(sym.COMMA, yytext()); }
"(" 		{ return new_symbol(sym.LPAREN, yytext()); }
")" 		{ return new_symbol(sym.RPAREN, yytext()); }
"{" 		{ return new_symbol(sym.LBRACE, yytext()); }
"}"			{ return new_symbol(sym.RBRACE, yytext()); }
"*" 		{ return new_symbol(sym.MUL, yytext()); }
"/" 		{ return new_symbol(sym.DIV, yytext()); }
"%" 		{ return new_symbol(sym.MOD, yytext()); }
"==" 		{ return new_symbol(sym.JEDNAKO, yytext()); }
"!=" 		{ return new_symbol(sym.NEJEDN, yytext()); }
">" 		{ return new_symbol(sym.VECE, yytext()); }
">="		{ return new_symbol(sym.VECE_JEDN, yytext()); }
"<" 		{ return new_symbol(sym.MANJE, yytext()); }
"<=" 		{ return new_symbol(sym.MANJE_JEDN, yytext()); }
"&&" 		{ return new_symbol(sym.AND, yytext()); }
"||" 		{ return new_symbol(sym.OR, yytext()); }
"++" 		{ return new_symbol(sym.INKR, yytext()); }
"--" 		{ return new_symbol(sym.DECR, yytext()); }
"." 		{ return new_symbol(sym.TACKA, yytext()); }
"[" 		{ return new_symbol(sym.LEVA_UGL, yytext()); }
"]"			{ return new_symbol(sym.DESNA_UGL, yytext()); }
":"			{ return new_symbol(sym.TACKA_TACKA, yytext()); }
"-"			{ return new_symbol(sym.MINUS, yytext()); }
"=>"		{ return new_symbol(sym.SLEDI, yytext()); }


<YYINITIAL> "//" 		     { yybegin(COMMENT); }
<COMMENT> .      { yybegin(COMMENT); }
<COMMENT> "\r\n" { yybegin(YYINITIAL); }

"true" | "false"	{ return new_symbol(sym.BOOLCONST, new Boolean(yytext())); }
[0-9]+ { return new_symbol(sym.NUMCONST, Integer.parseInt(yytext())); }
([a-z]|[A-Z])[a-z|A-Z|0-9|_]* 	{return new_symbol (sym.IDENT, yytext()); }

"'" [^'\n'] "'" { return new_symbol(sym.CHARCONST, new Character(yytext().charAt(1))); }

. { System.err.println("Leksicka greska ("+yytext()+") u liniji "+(yyline+1)); }








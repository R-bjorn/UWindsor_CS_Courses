import java.nio.file.*;
import java.io.*;


class A2 {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;
	public final int YYEOF = -1;

	static int id, cmt, num, kw, qs;
	public static void main(String argv[]) throws java.io.IOException{
		A2 yy = new A2(new FileReader("A2.input"));
		while(yy.yylex() >= 0 ){}
		Files.write(Paths.get("A2.output"), ("identifiers: "+id+"\nkeywords: "+kw+"\nnumbers: "+num+"\ncomment: "+cmt+"\nquotedStrings: "+qs).getBytes());
	}
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private boolean yy_at_bol;
	private int yy_lexical_state;

	A2 (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	A2 (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private A2 () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int COMMENT = 1;
	private final int yy_state_dtrans[] = {
		0,
		18
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NOT_ACCEPT,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NOT_ACCEPT,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NOT_ACCEPT,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NOT_ACCEPT,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NOT_ACCEPT,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"22:34,21,22:7,2,22:3,20,1,19:10,22:7,7,8,18,15,9,16,10,18,3,18:2,13,6,4,18:" +
"3,12,11,5,17,18,14,18:3,22:6,18:26,22:5,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,42,
"0,1,2,3,4,5,1:3,6,1,5,7:2,8,9,8,10,11,12,13,14,15,16,17,18,19,20,21,22,23,2" +
"4,25,26,27,28,29,30,31,32,33,34")[0];

	private int yy_nxt[][] = unpackFromString(35,23,
"1,2,10,3,11:2,32,11,38,25,11,41,33,11,39,11:4,4,10,14,10,-1:25,9,-1:23,11,1" +
"5,11:11,5,11:3,-1:22,4,13,-1:5,11:17,-1:5,7,-1:39,12,-1:4,16:20,6,16,-1:3,1" +
"1:2,5,11:14,-1:6,11:12,5,11:4,-1:3,1,10,24,10:20,-1:3,11,5,11:15,-1:4,8,-1:" +
"24,11:6,5,11:10,-1:6,11:10,5,11,5,11:4,-1:6,11:7,5,11:9,-1:5,20,-1:23,11,17" +
",11:8,27,11:6,-1:6,19,11:16,-1:6,11:8,21,11:8,-1:6,11:2,37,11,22,11:12,-1:6" +
",11:2,21,11:14,-1:6,11,23,11:15,-1:6,11:9,19,11:7,-1:6,11:4,26,11:12,-1:6,1" +
"1:6,28,11:10,-1:6,11:7,26,11:9,-1:6,29,11:16,-1:6,30,11:16,-1:6,11:14,31,11" +
":2,-1:6,11:6,34,11:10,-1:6,11:9,35,11:7,-1:6,11:9,36,11:7,-1:6,11:2,40,11:1" +
"4,-1:3");

	public int yylex ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {
				return YYEOF;
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{}
					case -3:
						break;
					case 3:
						{id++;}
					case -4:
						break;
					case 4:
						{num++;}
					case -5:
						break;
					case 5:
						{kw++;}
					case -6:
						break;
					case 6:
						{qs++;}
					case -7:
						break;
					case 7:
						{yybegin(COMMENT);}
					case -8:
						break;
					case 8:
						{yybegin(YYINITIAL); cmt++;}
					case -9:
						break;
					case 10:
						{}
					case -10:
						break;
					case 11:
						{id++;}
					case -11:
						break;
					case 12:
						{num++;}
					case -12:
						break;
					case 14:
						{}
					case -13:
						break;
					case 15:
						{id++;}
					case -14:
						break;
					case 17:
						{id++;}
					case -15:
						break;
					case 19:
						{id++;}
					case -16:
						break;
					case 21:
						{id++;}
					case -17:
						break;
					case 22:
						{id++;}
					case -18:
						break;
					case 23:
						{id++;}
					case -19:
						break;
					case 24:
						{}
					case -20:
						break;
					case 25:
						{id++;}
					case -21:
						break;
					case 26:
						{id++;}
					case -22:
						break;
					case 27:
						{id++;}
					case -23:
						break;
					case 28:
						{id++;}
					case -24:
						break;
					case 29:
						{id++;}
					case -25:
						break;
					case 30:
						{id++;}
					case -26:
						break;
					case 31:
						{id++;}
					case -27:
						break;
					case 32:
						{id++;}
					case -28:
						break;
					case 33:
						{id++;}
					case -29:
						break;
					case 34:
						{id++;}
					case -30:
						break;
					case 35:
						{id++;}
					case -31:
						break;
					case 36:
						{id++;}
					case -32:
						break;
					case 37:
						{id++;}
					case -33:
						break;
					case 38:
						{id++;}
					case -34:
						break;
					case 39:
						{id++;}
					case -35:
						break;
					case 40:
						{id++;}
					case -36:
						break;
					case 41:
						{id++;}
					case -37:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}

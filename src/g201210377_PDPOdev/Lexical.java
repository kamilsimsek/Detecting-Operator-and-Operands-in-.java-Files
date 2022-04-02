/**
*
* @author Kamil ÞÝMÞEK - kamil.simsek@ogr.sakarya.edu.tr
* @since 28.03.2022
* <p>
* Operatör-operand bilgilerinin tutulduðu sýnýf
* </p>
*/

package g201210377_PDPOdev;

import java.util.regex.*;
public class Lexical {
	private int ikilioperator=0;
	private int teklioperator=0;
	private int iliskiseloperator=0;
	private int mantiksaloperator=0;
	private int sayisaloperator=0;
	private int operand=0;
	
	boolean yorum = false;
	String line;
	String temp="";
	Lexical(){}
	
	
	Pattern iliskisel = Pattern.compile("<=|>=|<(?!\\{)(?!A)(?!I)(?!D)|>|==|!=");
	Pattern mantiksal = Pattern.compile("&&|\\|\\||[!][^=]");
	Pattern teklisayisal = Pattern.compile("-[-]{1}|(?<!\\w)(?<!\\-)-(?!\\s)(?!\\-)(?!\\=)|"
			+ "\\+[+]{1}|(?<!\\w)(?<!\\+)\\+(?!\\s)(?!\\+)(?!\\=)");
	Pattern ikilisayisal = Pattern.compile("(?<!\\+)\\+(?!\\+)(?!\\=)|(?<!-)-(?!-)(?=\\s)|(?<!\\*)(?<!\\/)(?<!\\.)\\*(?!=)(?!\\/)|(?<!\\*)(?<!\\/)\\/(?!\\/)(?!=)(?!\\*)|%(?!%)(?!=)|(?<!&)&(?!&)(?!=)|\\|(?!\\|)(?!=)|\\^(?!\\^)(?!=)|(?<!\\+)(?<!-)(?<!\\/)(?<!\\*)(?<!%)(?<!=)(?<!&)(?<!\\|)(?<!\\^)(?<!!)(?<!<)(?<!>)=(?!=)|\\+=|-=|\\/=|\\*=|%=|&=|\\|=|\\^=;");
	
	public void IkiliArttir(){	
		ikilioperator++;
	}
	public int IkiliGetir() {
		return ikilioperator;
	}
	public void TekliArttir(){	
		teklioperator++;
	}
	public int TekliGetir() {
		return teklioperator;
	}
	public void IlýskiselArttir(){	
		iliskiseloperator++;
	}
	public int IliskiselGetir() {
		return iliskiseloperator;
	}
	public void MantiksalArttir(){	
		mantiksaloperator++;
	}
	public int MantiksalGetir() {
		return mantiksaloperator;
	}
	
	public int Sayisal()
	{
		sayisaloperator = teklioperator + ikilioperator;
		return sayisaloperator;
	}
	public int Operand()
	{
		operand = 2*(ikilioperator+iliskiseloperator+mantiksaloperator)+teklioperator;
		return operand;
	}
}


/**
*
* @author Kamil ÞÝMÞEK - kamil.simsek@ogr.sakarya.edu.tr
* @since 28.03.2022
* <p>
* Programýn çalýþmasýný saðlayan dosya okuma ve gerekli iþlemlerin 
* içinde olduðu main fonksiyonunu bulunduran sýnýf
* </p>
*/
package g201210377_PDPOdev;
import java.io.*;

import java.util.regex.*;

public class Program {
	public static void main(String[] args) throws IOException {
		Lexical lex = new Lexical();
		File dosya = new File("hello.java");//Okunulacak dosya belirleniyor.
		if(!dosya.exists()) 
		{
			dosya.createNewFile();
		}
		FileReader dosyaoku = new FileReader(dosya);//Dosya okuma iþlemleri için gerekli atamalar yapýlýyor.
		BufferedReader okuyucu = new BufferedReader(dosyaoku);//Dosya okuma iþlemleri için gerekli atamalar yapýlýyor.
		Override: while((lex.line = okuyucu.readLine()) != null)//Satýr satýr dosya okuma iþlemi baþlatýlýyor.
		{
			for(int i=0;i<lex.line.length()-1;i++)//Satýrlar içerisinde karakter karakter okuma baþlatýlýyor.
			{
				if(lex.line.charAt(i)=='/'&&lex.line.charAt(i+1)=='/')
				{
					continue Override;
				}
				while((lex.line.indexOf("/*")!=i)&&i<lex.line.length()-1&&!lex.yorum)
				{
					lex.temp+=lex.line.charAt(i);
					i++;
				}
				if(lex.line.indexOf("/*")==i)//Yorum satýrý baþlangýç - bitiþ belirteçi
				{
					lex.yorum=true;
				}
				else if(lex.line.indexOf("*/")==i)//Yorum satýrý baþlangýç - bitiþ belirteçi
				{
					lex.yorum=false;
					i+=2;
				}
				while(i<lex.line.length()-1&&lex.yorum==false)
				{
					lex.temp+=lex.line.charAt(i);
					i++;
				}
				
			}
			lex.temp+=" ";//Temp stringine yorum satýrý dýþýndaki verileri atýlýyor.
		}
		okuyucu.close();//Dosya okuma kapatýldý.
			Matcher tekli = lex.teklisayisal.matcher(lex.temp);//Regex ifadeleri eþleþtiriliyor.
			Matcher ilis = lex.iliskisel.matcher(lex.temp);//Regex ifadeleri eþleþtiriliyor.
			Matcher man = lex.mantiksal.matcher(lex.temp);//Regex ifadeleri eþleþtiriliyor.
			Matcher ikili = lex.ikilisayisal.matcher(lex.temp);//Regex ifadeleri eþleþtiriliyor.
			while(tekli.find())//Bulunan ifadelere göre iþlemler yapýlýyor.
			{
				lex.TekliArttir();
			}
			while(ikili.find())//Bulunan ifadelere göre iþlemler yapýlýyor.
			{
				lex.IkiliArttir();
			}
			while(man.find())//Bulunan ifadelere göre iþlemler yapýlýyor.
			{
				lex.MantiksalArttir();
			}
			while(ilis.find())//Bulunan ifadelere göre iþlemler yapýlýyor.
			{
				lex.IlýskiselArttir();
			}
		//Ýstenilen çýktýlar
		System.out.println("Operatör Bilgisi : ");
		System.out.println("	Tekli Operatör Sayýsý : "+lex.TekliGetir());
		System.out.println("	Ýkili Operatör Sayýsý : "+lex.IkiliGetir());
		System.out.println("	Sayýsal Operatör Sayýsý : "+lex.Sayisal());
		System.out.println("	Ýliþkisel Operatör Sayýsý : "+lex.IliskiselGetir());
		System.out.println("	Mantýksal Operatör Sayýsý : "+lex.MantiksalGetir());
		System.out.println("Operand Bilgisi : ");
		System.out.println("	Toplam Operand Sayýsý : "+lex.Operand());
		
	}
}

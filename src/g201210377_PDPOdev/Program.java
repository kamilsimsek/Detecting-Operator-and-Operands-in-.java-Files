/**
*
* @author Kamil ��M�EK - kamil.simsek@ogr.sakarya.edu.tr
* @since 28.03.2022
* <p>
* Program�n �al��mas�n� sa�layan dosya okuma ve gerekli i�lemlerin 
* i�inde oldu�u main fonksiyonunu bulunduran s�n�f
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
		FileReader dosyaoku = new FileReader(dosya);//Dosya okuma i�lemleri i�in gerekli atamalar yap�l�yor.
		BufferedReader okuyucu = new BufferedReader(dosyaoku);//Dosya okuma i�lemleri i�in gerekli atamalar yap�l�yor.
		Override: while((lex.line = okuyucu.readLine()) != null)//Sat�r sat�r dosya okuma i�lemi ba�lat�l�yor.
		{
			for(int i=0;i<lex.line.length()-1;i++)//Sat�rlar i�erisinde karakter karakter okuma ba�lat�l�yor.
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
				if(lex.line.indexOf("/*")==i)//Yorum sat�r� ba�lang�� - biti� belirte�i
				{
					lex.yorum=true;
				}
				else if(lex.line.indexOf("*/")==i)//Yorum sat�r� ba�lang�� - biti� belirte�i
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
			lex.temp+=" ";//Temp stringine yorum sat�r� d���ndaki verileri at�l�yor.
		}
		okuyucu.close();//Dosya okuma kapat�ld�.
			Matcher tekli = lex.teklisayisal.matcher(lex.temp);//Regex ifadeleri e�le�tiriliyor.
			Matcher ilis = lex.iliskisel.matcher(lex.temp);//Regex ifadeleri e�le�tiriliyor.
			Matcher man = lex.mantiksal.matcher(lex.temp);//Regex ifadeleri e�le�tiriliyor.
			Matcher ikili = lex.ikilisayisal.matcher(lex.temp);//Regex ifadeleri e�le�tiriliyor.
			while(tekli.find())//Bulunan ifadelere g�re i�lemler yap�l�yor.
			{
				lex.TekliArttir();
			}
			while(ikili.find())//Bulunan ifadelere g�re i�lemler yap�l�yor.
			{
				lex.IkiliArttir();
			}
			while(man.find())//Bulunan ifadelere g�re i�lemler yap�l�yor.
			{
				lex.MantiksalArttir();
			}
			while(ilis.find())//Bulunan ifadelere g�re i�lemler yap�l�yor.
			{
				lex.Il�skiselArttir();
			}
		//�stenilen ��kt�lar
		System.out.println("Operat�r Bilgisi : ");
		System.out.println("	Tekli Operat�r Say�s� : "+lex.TekliGetir());
		System.out.println("	�kili Operat�r Say�s� : "+lex.IkiliGetir());
		System.out.println("	Say�sal Operat�r Say�s� : "+lex.Sayisal());
		System.out.println("	�li�kisel Operat�r Say�s� : "+lex.IliskiselGetir());
		System.out.println("	Mant�ksal Operat�r Say�s� : "+lex.MantiksalGetir());
		System.out.println("Operand Bilgisi : ");
		System.out.println("	Toplam Operand Say�s� : "+lex.Operand());
		
	}
}

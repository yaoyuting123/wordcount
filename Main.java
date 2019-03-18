package wordcont;


import java.util.Scanner;
 
import wordcont.WordCont;
 
public class Main {
 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("-----------------------------------");
		System.out.println("******--------词频统计----------*****");
		System.out.println("-----------------------------------");
		System.out.println("输入文件路径：\n");			
		Scanner in=new Scanner(System.in);
		String line=in.nextLine();
		String fileName= line.trim();
		WordCont wc = new WordCont();
		wc.displayWordCount(fileName);
		wc.displayFrequencyWord(fileName);
		
		
	}
 
}

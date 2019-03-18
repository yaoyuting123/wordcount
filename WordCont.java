package wordcont;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import wordcont.WordEntity;
 
public class WordCont {
 
	public void displayWordCount(String fileName){
		//字符统计
    	try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line = null;
	        TreeMap<String,Integer> tm = new TreeMap<String,Integer>();
	        
	        while((line=reader.readLine())!=null){
	        	line = line.toLowerCase();
	        	String str[] = line.split("\\s+");
	        	for(int i = 0; i<str.length; i++){
	        		String word = str[i].trim();
	        		if(tm.containsKey(word)){
	        			tm.put(word, tm.get(word)+1);
	        		}else{
	        			tm.put(word, 1);
	        		}
	        	}
	        }
	        //输出我们想要的字符串格式
            System.out.println("按字典序输出为：");
            Iterator<Entry<String, Integer>> iterator=tm.entrySet().iterator();
            while(iterator.hasNext())
            {
                System.out.println(iterator.next());
            }
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void displayFrequencyWord(String fileName){
    	//显示输出
    	try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String s;
            StringBuffer sb = new StringBuffer();
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            
            Map<String,Integer> map = new HashMap<String, Integer>();
            StringTokenizer st = new StringTokenizer(sb.toString(),",.! \n");
            while (st.hasMoreTokens()) {
                String letter = st.nextToken().trim();
                int count;
                if (!map.containsKey(letter)) {
                    count = 1;
                } else {
                    count = map.get(letter).intValue() + 1;
                }
                map.put(letter,count);
            }
            
            Set<WordEntity> set = new TreeSet<WordEntity>();
            for (String key : map.keySet()) {
                set.add(new WordEntity(key,map.get(key)));
            }
 
            System.out.println("本文中出现频率最高的单词为：");
            Iterator<WordEntity> it1 = set.iterator();
            int count=it1.next().getCount();
            for (Iterator<WordEntity> it = set.iterator(); it.hasNext(); ) {
                WordEntity w = it.next();
                
                if (w.getCount()==count)// 当输出3个后跳出循环
                    //break;
                
                System.out.println(w.getKey() + " 出现的次数为： "+ w.getCount());
                
            }
        } catch (FileNotFoundException e) {
            System.out.println("文件未找到~！");
        } catch (IOException e) {
            System.out.println("文件读异常~！");
        }
    	
    
  
    }    
    public void check(String fileName){
    	
    	Scanner sc = new Scanner(System.in);
        File file=new File("K:/Test/TestRead.txt");
        String str=null,src=null;
        if(file.canExecute()) {
            str=readFile(file);
        }else {
            System.out.println("文件不存在!");
            return;
        }
        while(true) {
            System.out.println("请输入想查找的单词:over为结束查找!");
            src=sc.nextLine();
            if(src.equalsIgnoreCase("over"))
                break;
            System.out.println("查找结果:"+look(src,str));
        }
         
    }
    private static String readFile(File file){
        BufferedReader br=null;
        StringBuilder stu=new StringBuilder();
        try {
                br=new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
                for(String str=br.readLine();str!=null;str=br.readLine()) {
                    stu.append(str);
                    stu.append(System.lineSeparator());
                }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if(br!=null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stu.toString();
    }
    private static int look(String regex,String str) {
        Matcher mat=Pattern.compile(regex).matcher(str);
        int count=0;
        while(mat.find()) {
            count++;
        }
        return count;
    }
    /*
    
  //柱状图的显示
  	public WordCont(){
  		super();
  		setTitle("绘制柱形图");
  		setBounds(3, 200, 450, 450);
  		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  	}
  	@Override
  	public void paint(Graphics g){
  		int Width = getWidth();
  		int Height = getHeight();
  		int leftMargin = 50;//柱形图左边界
  		int topMargin = 50;//柱形图上边界
  		Graphics2D g2 = (Graphics2D) g;
  		int ruler = Height-topMargin;
  		int rulerStep = ruler/20;//将当前的高度评分为20个单位
  		g2.setColor(Color.WHITE);//绘制白色背景
  		g2.fillRect(0, 0, Width, Height);//绘制矩形图
  		g2.setColor(Color.LIGHT_GRAY);
  		for(int i=0;i<rulerStep;i++){
  			g2.drawString((400-20*i)+"个", 8, topMargin+rulerStep*i);//绘制Y轴上的数据
  		}
  		g2.setColor(Color.PINK);
  		int m=0;
  		for(int i = 0;i<st.length;i++){
  			int value = tm.get(st[i]);
  			int step = (m+1)*40;//设置每隔柱形图的水平间隔为40
  			g2.fillRoundRect(leftMargin+step*2,Height-value, 40, value, 40, 10);//绘制每个柱状条
  			g2.drawString(st[i], leftMargin+step*2, Height-value-5);	//标识每个柱状条		
  			m++;
  		}*/
  	}

    
    
 
    
    
 



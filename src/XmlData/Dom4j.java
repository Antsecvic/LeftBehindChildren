import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import sun.misc.BASE64Decoder; 
/** 
* 
* @author hongliang.dinghl 
* Dom4j 生成XML文档与解析XML文档 
 * @param <News>
*/ 

public class Dom4j implements XmlDocument { 
	public void modifyXml(String fileName,News modifiedNews){ 
		try { 
			SAXReader sr = new SAXReader();
	        Document document = sr.read(fileName);
			Element arrayOfNewsData = document.getRootElement();
	        List newsList = arrayOfNewsData.elements();
	        boolean ifFinded = false;
	        Element news = null;
	        for (int i = 0; i < newsList.size(); i++) {
	            news = (Element) newsList.get(i);
	            for (Iterator it = news.elementIterator(); it.hasNext();) {
	                Element node = (Element) it.next();
	                String type = node.getName();
	                if ("ID".equals(type)&& node.getData().equals(modifiedNews.getID())) {
	                	ifFinded = true;
	                    break;
	                }
	            }
	            if(ifFinded){
	            	break;
	            }
	        }
	        
	        for (Iterator it = news.elementIterator(); it.hasNext();) {
	            Element node = (Element) it.next();
	            String type = node.getName();
	            if ("Tags".equals(type)) {
	      
	            	
	            	
	            	//将标签写入xml文件
	                node.setText(modifiedNews.getTags());
	                
	       
	            }
	        }
			OutputFormat format = OutputFormat.createPrettyPrint();  
			format.setEncoding("UTF-8");//应和xml文档的编码格式一致  			  
			XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(fileName), format);
			xmlWriter.write(document); 
			xmlWriter.close(); 
		} catch (Exception e) { 
			System.out.println(e.getMessage()); 
		}
	} 
	public void parserXml(String fileName,Map<String,News> map) { 
		File inputXml=new File(fileName); 
		SAXReader saxReader = new SAXReader(); 
		try { 
			Document document = saxReader.read(inputXml); 
			Element arrayOfNewsData = document.getRootElement(); 
			for(Iterator i = arrayOfNewsData.elementIterator(); i.hasNext();){ 
				News news = new News();
				Element newsData = (Element) i.next(); 
				news.setNewsData(newsData.getText());
				String[] temp = new String[14];
				int k = 0;
				for(Iterator j = newsData.elementIterator(); j.hasNext();){ 
					Element node=(Element) j.next(); 
					temp[k]=node.getText();
					k++;
//					System.out.println(node.getName()+":"+node.getText()); 
				} 
				news.setTagIts(temp[0]);
				news.setIsLoad(temp[1]);
				news.setIsDeleted(temp[2]);
				news.setMemo(temp[3]);
				news.setTitle(temp[4]);
				news.setDate(temp[5]);
				news.setLocation(temp[6]);
				news.setUrl(temp[7]);
				news.setType(temp[8]);
				news.setWordCount(temp[9]);
				news.setID(temp[10]);
				news.setTrueUrl(temp[11]);
				news.setTags(temp[12]);
				news.setEncodedContent(decodeContent(temp[13]));
				
				//IsLoad的作用尚不清楚，先注释掉
//				if(news.getEncodedContent().equals("")){
//					news.setIsLoad("false");
//				}
				
				
				map.put(news.getID(),news);
			} 
		} catch (DocumentException e) { 
			System.out.println(e.getMessage()); 
		} 
		System.out.println("dom4j parserXml"); 
	}
	// 解析EncodedContent，使其成为可读字符串
	  private String decodeContent(String encodedContent) {
	    byte[] b = null;
	    BASE64Decoder decoder = new BASE64Decoder();

	    try {
	      b = decoder.decodeBuffer(encodedContent);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }

	    // 奇偶交换数组中的元素
	    for (int i = 0; i < b.length; i += 2) {
	      byte m = b[i];
	      b[i] = b[i + 1];
	      b[i + 1] = m;
	    }

	    String str = null;
	    try {
	      str = new String(b, "utf-16");
	    } catch (UnsupportedEncodingException e) {
	      e.printStackTrace();
	    }
	    //除去头尾的标签
	    str = str.replace("<body>", "").replace("</body>", "").replace("<P>", "").replace("</P>", "");
	    str = str.replace("<html>", "").replace("</html>", "");
	    return str;
	  }
}
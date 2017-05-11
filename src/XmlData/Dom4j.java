package XmlData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
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
	
	public static Logger logger = LogManager.getLogger(Dom4j.class.getName());
	
	@SuppressWarnings("unchecked")
	public void initXml(String fileName){ 
		try { 
			logger.info(fileName+" "+"初始化xml数据");
			SAXReader sr = new SAXReader();
			File f = new File(fileName);
			InputStream in = new FileInputStream(f);
	        Document document = sr.read(in);
			Element arrayOfNewsData = document.getRootElement();
	        List<News> newsList = arrayOfNewsData.elements();
	        Element news = null;
	        for (int i = 0; i < newsList.size(); i++) {
	            news = (Element) newsList.get(i);
	            for (Iterator<News> it = news.elementIterator(); it.hasNext();) {
	                Element node = (Element) it.next();
	                String type = node.getName();
	                if (type.equals("Tags")) {
	                	if(node.elements().size() == 0){
	                		node.addElement("Type");
	                		node.addElement("Theme");
							node.addElement("Source");
							node.addElement("Showing");
							node.addElement("Reason");
							node.addElement("MainBody");
							node.addElement("HelpType");
							node.addElement("Gender");
	                	}
	                }
	            }
	        }
			OutputFormat format = OutputFormat.createPrettyPrint();  
			format.setEncoding("UTF-8");//应和xml文档的编码格式一致  			  
			XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(fileName), format);
			xmlWriter.write(document); 
			xmlWriter.close(); 
		} catch (Exception e) { 
			logger.error(fileName+" "+"初始化失败");
			System.out.println(e.getMessage()); 
		}
	} 
	
	
	@SuppressWarnings("rawtypes")
	public void modifyXml(News modifiedNews){ 
		String fileName = null;
		try { 
			if(modifiedNews.getLocation().contains("光明日报")) {
				fileName = "assets/guangming.xml";
			}else if(modifiedNews.getLocation().contains("南方日报")) {
				fileName = "assets/nanfangdaily.xml";
			}else if(modifiedNews.getLocation().contains("四川日报")) {
				fileName = "assets/sichuan.xml";
			}else {
				logger.error("新闻--"+modifiedNews.getTitle()+"找不到源xml文件");
			}
			if(!fileName.equals(null)){
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
		            if ("TagIts".equals(type)){
		            	//将TagIts标签写入xml文件,用于指示是否已分类
		            	node.setText(modifiedNews.getTagIts());
		            }else if("IsDeleted".equals(type)){
		            	//将IsDeleted标签写入xml文件,用于指示是否已删除
		            	node.setText(modifiedNews.getIsDeleted());
		            }else if("Tags".equals(type)){
		            	//将标签写入xml文件
		            	
		            	for (Iterator i = node.elementIterator(); i.hasNext();){	
		            		
		            		Element tagNode = (Element)i.next();
		            		String tagType = tagNode.getName();
		            		if("Type".equals(tagType)){
		            			tagNode.setText(modifiedNews.getTags().getType());
		            		}else if ("Theme".equals(tagType)){
		            			tagNode.setText(modifiedNews.getTags().getTheme());
		            		}else if("Source".equals(tagType)){
		            			tagNode.setText(modifiedNews.getTags().getSource());
		    	            }else if("Showing".equals(tagType)){
		    	            	tagNode.setText(modifiedNews.getTags().getShowing());
		    	            }else if("Reason".equals(tagType)){
		    	            	tagNode.setText(modifiedNews.getTags().getReason());
		    	            }else if("MainBody".equals(tagType)){
		    	            	tagNode.setText(modifiedNews.getTags().getMainBody());
		    	            }else if("HelpType".equals(tagType)){
		    	            	tagNode.setText(modifiedNews.getTags().getHelpType());
		    	            }else if("Gender".equals(tagType)){
		    	            	tagNode.setText(modifiedNews.getTags().getGender());
		    	            }
		            	}
		            }
		        }
				OutputFormat format = OutputFormat.createPrettyPrint();  
				format.setEncoding("UTF-8");//应和xml文档的编码格式一致  			  
				XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(fileName), format);
				xmlWriter.write(document); 
				xmlWriter.close(); 
			}
		} catch (Exception e) { 
			logger.error(fileName+" "+modifiedNews.getTitle()+"插入标签失败");
			System.out.println(e.getMessage()); 
		}
	} 
	
	@SuppressWarnings("unchecked")
	public void parserXml(String fileName,List<News> newsList) { 
		File inputXml=new File(fileName); 
		SAXReader saxReader = new SAXReader(); 
		try {
			logger.info("解析"+fileName);
			Document document = saxReader.read(inputXml); 
			Element arrayOfNewsData = document.getRootElement(); 
			for(Iterator<News> i = arrayOfNewsData.elementIterator(); i.hasNext();){ 
				News news = new News();
				Element newsData = (Element) i.next(); 
				news.setNewsData(newsData.getText());
				String[] temp = new String[21];
				int k = 0;
				for(Iterator<News> j = newsData.elementIterator(); j.hasNext();){ 
					Element node=(Element) j.next(); 
					if(node.getName().equals("Tags")){
						for(Iterator<News> l = node.elementIterator();l.hasNext();){
							Element tagNode = (Element)l.next();
							temp[k] = tagNode.getText();
							k++;
						}
					}else if(node.getName().equals("TagIts")){
//						System.out.println(node.getText().equals(""));
						if(node.getText().equals("")){
							temp[k]="";
						}else{
							temp[k]=node.getText();
						}
						k++;
					}
					else{
						temp[k]=node.getText();
						k++;
					}
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
				
				Tags tempTags = new Tags();
				tempTags.setType(temp[12]);
				tempTags.setTheme(temp[13]);
				tempTags.setSource(temp[14]);
				tempTags.setShowing(temp[15]);
				tempTags.setReason(temp[16]);
				tempTags.setMainBody(temp[17]);
				tempTags.setHelpType(temp[18]);
				tempTags.setGender(temp[19]);
				news.setTags(tempTags);
				
				news.setEncodedContent(decodeContent(temp[20]));
				
				//IsLoad的作用尚不清楚，先注释掉
//				if(news.getEncodedContent().equals("")){
//					news.setIsLoad("false");
//				}
				
				
				newsList.add(news);
			} 
		} catch (DocumentException e) {
			logger.error("解析"+fileName+"失败");
			System.out.println(e.getMessage()); 
		} 
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
	  	  
	  public void modifyAll(List<News> listNews){
		  for(News news : listNews){
			  modifyXml(news);
		  }
	  }
}
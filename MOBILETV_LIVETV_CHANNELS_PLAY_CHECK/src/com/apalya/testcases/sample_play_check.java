package com.apalya.testcases;

import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.apalya.utils.Xls_Reader;
import com.apalya.utils.constants;
import com.apalya.utils.keywords;
import com.experitest.client.*;

public class sample_play_check {

	public static keywords key=null;
	public static Xls_Reader xls=null;
	@Test
	public void sample_play(){
		key=new keywords();
		key.setup();
		key.open_app("adb:C6902-Sony Experia","com.apalya.myplex/.LoginActivity");
		xls=new Xls_Reader("F:\\workspace\\Channels_Content_Play\\src\\com\\apalya\\xlsxfile\\channels_check.xlsx");
          int rows=xls.getRowCount("sheet1");
          System.out.println("No of rows are :"+rows);
          //Deleting old data
          for(int t=2;t<=rows;t++){
          xls.setCellData("sheet1", "Channel_Name",t , "");
          xls.setCellData("sheet1", "Comments",t , "");
          xls.setCellData("sheet1", "Status",t , "");
          
          }
          key.setup();
    	  key.open_app("adb:C6902-Sony Experia","com.apalya.myplex/.LoginActivity" );
    	  key.click_native("id=customactionbar_drawer");
    	  key.waitforelement_native("text=live tv", 10000);
    	  key.click_native("text=live tv");
    	  key.sleep(2000);
          for (int i=2;i<=rows;i++){
        	   if(i==2){
        		   boolean b=play_check(i);
        		   if(b==true){
        			   System.out.println("Test Pass");
        			   xls.setCellData("sheet1", "Status", i, "Pass");
        		   }else{
        			   System.out.println("Test Fail");
        			   xls.setCellData("sheet1", "Status", i, "Fail");
        			   
        		   }
        		   
        	   
        	   }else{
        		   key.swipe("Down",1100,1000);
        		   key.sleep(3000);
        		   boolean c=play_check(i);
        		   if(c==true){
        			   System.out.println("Test Pass");
        			   xls.setCellData("sheet1", "Status", i, "Pass");
        		   }else{
        			   System.out.println("Test Fail");
        			   xls.setCellData("sheet1", "Status", i, "Fail");
        		   }
		    }
        	 System.out.println("*****************************************");  
        	if(xls.getCellData("sheet1", "Channel_Name", i).equalsIgnoreCase(" cnn")){
        		System.out.println("Tested completed");
        		break;
        	}
          }
          
	}
	 public boolean play_check(int number){
        try{
         key.sleep(1000);
         System.out.println("Channel number :"+(number-1));
         key.click_w_r_t_otherelement("NATIVE", constants.card_top_label, "Down", "NATIVE", constants.card_image);
         key.sleep(1000);
		  if(key.waitforelement_native(constants.description_image, 10000)==false){
			  key.click_w_r_t_otherelement("NATIVE", constants.card_top_label, "Down", "NATIVE", constants.card_image);
		  }
		  key.click_native(constants.description_image);
		  String name=key.get_text("NATIVE", constants.get_movie_name);
		  System.out.println("Channel name is :"+name);
		  xls.setCellData("sheet1", "Channel_Name", number, name);
		  key.sleep(3000);
		  if(key.iselement_found_native(constants.wifi_3G_error_check)==true){
			  key.waitforelement_native("xpath=//*[@text='retry now']", 10000);
			  key.click_native("xpath=//*[@text='retry now']");
			  xls.setCellData("sheet1", "Comments", number, "wifi link or 3G is not playing");
			  key.sleep(5000);
			  try{
				  String[] s= key.get_all_text("NATIVE","xpath=//*[@id='cardmediasubitem_retrytext']","text");
					 for(int n=0;n<s.length;n++){
						 System.out.println("searched string in :"+s[n]);
						 if(s[n].indexOf("poor network conditions")!=-1){
							  System.out.println("nither 3g or 2g link is not playing");
						
							  xls.setCellData("sheet1", "Comments", number, "neither wifi,3g or 2g links are not playing");
						  }
					 }}catch(Exception e){
						 System.out.println("error :"+e.getMessage());
						 e.printStackTrace();
					 }
			  }  
		  key.sleep(5000);
		  key.waitforelement_native("xpath=//*[@id='carddetail_videolayout']", 10000);
		  key.click_native("xpath=//*[@id='carddetail_videolayout']");
		  if(key.iselement_found_native("xpath=//*[@text='network is not available']")==true){
			    key.sent_text("{ESC}");
			  xls.setCellData("sheet1", "Comments", number, "Network not Available");
			  return false;
			  
		  }
		  key.waitforelement_native("xpath=//*[@id='carddetail_videolayout']", 10000);
		  key.click_native("xpath=//*[@id='carddetail_videolayout']");
		  if(key.iselement_found_native("id=playpauseimage")==true){
			 
			  key.sleep(2000);
			  key.iselement_blank("NATIVE", "xpath=//*[@id='cardmediasubitemvideo_videopreview']", 1,number);
			  key.sent_text("{ESC}");
			  //key.click_native("xpath=//*[@text='␡' and ../self::*[@id='cardmedia_mini']]");
			  return true;
		  }else{
			  key.sent_text("{ESC}");
			  return false;
		  }
		  
        }catch(Throwable e){
        	System.out.println("error :"+e.getMessage());
        	e.printStackTrace();
        	return false;
        }
		}
	 
}




	
	
	


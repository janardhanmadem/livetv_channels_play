package com.apalya.utils;

import com.experitest.client.Client;

public class keywords {
	 
	  public static Client client = null;
	
	public void client_deregistration(String adb_devicemodel,String app_id){
	
	setup();
	app_clear_data(app_id);
	open_app(adb_devicemodel, app_id);
	waitforelement_native("partial_text=browse as guest",1000);
	click_native("text=browse as guest");
	sleep(2000);
	//click_native("text=cancel");
	click_native("text=sign into myplex");
	select_elements_from_list("text=settings");
	click_native("text=settings");
	waitforelement_native("partial_text=App Settings",5000);
	sleep(2000);
	click_native("text=App Settings");
	
	click_native("text=App Settings");
	
	click_native("text=App Settings");
	
	click_native("text=App Settings");
	
	click_native("text=App Settings");
	
	click_native("text=App Settings");
	
	click_native("text=App Settings");
	
	click_native("text=App Settings");
	sleep(2000);
	waitforelement_native("text=Deregister device",10000);
	click_native("text=Deregister device");
	sleep(2000);
	sent_text("{ESC}");
	sent_text("{ESC}");
	sent_text("{ESC}");
	sent_text("{ESC}");
	app_clear_data(app_id);
	//app_close(app_id);

   
}
	//setting up of project
	public void setup(){
		client = new Client(constants.host, constants.port);
	      client.setProjectBaseDirectory( constants.projectBaseDirectory);
	      System.out.println("Object is creted and project directory is set");
	      System.out.println("host address :"+constants.host);
	      System.out.println("port no is :"+constants.port);
	}
	
	//open application
	public void open_app(String adb_devicemodel,String app_id){
		System.out.println("set device is :"+adb_devicemodel);
		System.out.println("application id is :"+app_id);
		client.setDevice(adb_devicemodel);
		client.launch(app_id, true, false);
		
		
	}
	//send text to web application
	public void send_text_web(String property,String value){
		System.out.println("property is"+property);
		System.out.println("entered string is :"+value);
		client.elementSendText("WEB", property, 0, value);
		
	}
	//send text to native app
	public void send_text_native(String property,String value){
		System.out.println("property is"+property);
		System.out.println("entered string is :"+value);
		client.elementSendText("NATIVE", property, 0, value);
	}
	//click on native element
	public void click_native(String property){
		System.out.println("property is"+property);
		 client.click("NATIVE", property, 0, 1);
	}
	
	//click on css element
		public void click_css(String property){
			System.out.println("property is"+property);
			 client.click("NATIVE", property, 0, 1);
		}
	
	
	
	
	
	
	//click on web element
	public void click_web(String property){
		System.out.println("property is"+property);
		 client.click("WEB", property, 0, 1);
	}
	//check element present in native
	public boolean iselement_found_native(String property){
		System.out.println("property is"+property);
		try{
		 client.verifyElementFound("NATIVE",property , 0);
		 return true;
		}catch(Exception e){
			
			//System.out.println("error"+e.getMessage());
			//e.printStackTrace();
			return false;
		}
			 
		 
	}
	
	//check element present in Web
		public void iselement_found_web(String property){
			System.out.println("property is"+property);
			 client.verifyElementFound("WEB",property , 0);
		}
		
	//check element not present in native
		public void iselement_not_found_native(String property){
			System.out.println("property is"+property);
			client.verifyElementNotFound("NATIVE", property, 0);
		}
	//check element not present in web
		public void iselement_not_found_web(String property){
			System.out.println("property is"+property);
			client.verifyElementNotFound("WEB", property, 0);
		}
	
	//Waiting for the  native element 
	public boolean waitforelement_native(String property,int value){
		System.out.println("waiting for the element"+property);
		if(client.waitForElement("NATIVE", property, 0, value)){
	          System.out.println("Native element found");
	          return true;
	      }
		return false;
	}
	//Waiting for the  web element
	public boolean waitforelement_web(String property,int value){
		System.out.println("waiting for the element"+property);
		if(client.waitForElement("WEB", property, 0, value)){
			System.out.println("WEB element found");
			return true;
	      }
		return false;
	}
	//sleep execution for some time
	public void sleep(int value){
		System.out.println("sleeping for:"+ value+"milliseconds");
		client.sleep(value);
	}
	//select element from the list
	public void select_elements_from_list(String property){
		System.out.println("selecting element"+property);
		client.elementListSelect("", property, 0, false);
	}
	//application close
	public void  app_close(String app_id){
	client.applicationClose(app_id);
			System.out.println("Application is closed"+app_id);
			
	}
	//application clear data
	public void app_clear_data(String app_id){
		System.out.println("Clearing application data");
		client.applicationClearData(app_id);
		System.out.println("Cleared application data");
	}
	//send text to  box
	public void sent_text(String text){
		client.sendText(text);
	}
	//search element with reference to other element
	public void search_element_w_r_t_other_element(String reference_property,String regerence_text,String search_direction,String search_property,String search_text ){
		
		 client.verifyIn(reference_property, regerence_text, 0, search_direction, search_property, search_text, 0, 0);
	}
	public boolean swipe_while_not_found(String direction,String zone,String element_to_find,int no_of_swipes,boolean click_true_false){
		if(client.swipeWhileNotFound(direction, 200, 5000, zone, element_to_find, 0, 1000, no_of_swipes, click_true_false)){
		return true;
		}
		return false;
	}
	public void click_w_r_t_otherelement(String ref_ele_zone,String ref_element,String direction,String search_ele_zone,String search_ele ){
		client.clickIn(ref_ele_zone, ref_element, 0, direction, search_ele_zone, search_ele, 0, 0, 0, 2);
	}
	public String get_text(String zone,String property){
		String str = client.elementGetText(zone, property, 0);
		return str;
	}
	
	public String[] get_all_text(String zone,String element_property,String property){
		String[] str = client.getAllValues(zone, element_property, property);
		return str;
	}
	
	public void iselement_blank(String zone,String property,int Color_count,int row_number){
		 if(client.isElementBlank("NATIVE", "xpath=//*[@id='cardmediasubitemvideo_videopreview']", 0, 1)){
	            System.out.println("Audio is playing but video is not playing");
	            Xls_Reader xls=new Xls_Reader("F://workspace//Channels_Content_Play//src//com//apalya//xlsxfile//channels_check.xlsx");
	            xls.setCellData("sheet1", "Comments", row_number, "Audio is playing but video is not playing");
	           
	        }else{
	        	System.out.println("Audio and video is working fine");
	        }
	}
	
   public void swipe(String Direction,int offset,int swipetime)	{
	   client.swipe(Direction, 1100, 1000);
   }
	
	public void long_click(String zone,String property,int click_count){
		 client.longClick(zone, property, 0, click_count, 45, 350);
	}
	
}
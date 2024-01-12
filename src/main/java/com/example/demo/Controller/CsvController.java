package com.example.demo.Controller;

import com.example.demo.entity.CsvCall;
import com.example.demo.entity.CsvData;
//import com.example.demo.entity.UserInvoiceResponce;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
 
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/csv")
public class CsvController {
	@SuppressWarnings("null")
	@GetMapping("/call")
    public List<CsvCall> getCsvcall() throws IOException, CsvException {
    	List<CsvCall> list = new ArrayList<>();
//    	File file = new File("C:\\Users\\lokesh.gp\\OneDrive - Prodapt Solutions Private Limited\\InvoiceData.csv");
    	File file = new File("C:\\Users\\poornesh.ck\\OneDrive - Prodapt Solutions Private Limited\\cdr_data (14) 1.csv");
    	try(    			FileReader reader = new FileReader(file);
    			BufferedReader bf = new BufferedReader(reader);)
    	{
    		String data ="";
    		String[] invoiceData;
    		String[] tempArr = null;
    		while((data = bf.readLine())!=null)
    		{
    			CsvCall userData = CsvCall.builder().build();
    			invoiceData = data.split(",");
    			int i=1;
    			for(String tempData:invoiceData)
    			{
    				switch(i)
    				{
    				case 1: userData.setCallcost(tempData);break;
    				case 2: userData.setCallEndTime(tempData);break;
    				case 3: userData.setDialedno(tempData);break;
    				case 4: userData.setDuration(tempData);break;
//    				case 5: userData.setEmail(tempData);break;
//    				case 6: userData.setPhone(tempData);break;
//    				case 7: userData.setPlan_name(tempData);break;
//    				case 8: userData.setActivation_date(tempData);break;
//    				case 9: userData.setExpiry_date(tempData);break;
//    				case 10:userData.setPrice(tempData);break;
//    				case 1: userData.setInvoiceId(tempData);break;
//    				case 2: userData.setCustomerfName(tempData);break;
//    				case 3: userData.setCustomerlName(tempData);break;
//    				case 4: userData.setCustomerEmail(tempData);break;
//    				case 5: userData.setCustomerPhone(tempData);break;
//    				case 6: userData.setInvoiceDate(tempData);break;
//    				case 7: userData.setPaymentdueDate(tempData);break;
//    				case 8: userData.setPayment(tempData);break;
    				}
    				i++;
    			}
    			list.add(userData);

    			System.out.println();
    		}
    		list.stream().forEach(temp->{System.out.println(temp);});
    	}
        return list;
    }
	
	@SuppressWarnings("null")
	@GetMapping("/data")
    public List<CsvData> getCsvData() throws IOException, CsvException {
    	List<CsvData> list = new ArrayList<>();
//    	File file = new File("C:\\Users\\lokesh.gp\\OneDrive - Prodapt Solutions Private Limited\\InvoiceData.csv");
    	File file = new File("C:\\Users\\poornesh.ck\\OneDrive - Prodapt Solutions Private Limited\\data-cdr.csv");
    	try(    			FileReader reader = new FileReader(file);
    			BufferedReader bf = new BufferedReader(reader);)
    	{
    		String data ="";
    		String[] invoiceData;
    		String[] tempArr = null;
    		while((data = bf.readLine())!=null)
    		{
    			CsvData userData = CsvData.builder().build();
    			invoiceData = data.split(",");
    			int i=1;
    			for(String tempData:invoiceData)
    			{
    				switch(i)
    				{
    				case 1: userData.setData(tempData);break;
    				case 2: userData.setTime(tempData);break;
//    				case 3: userData.setDialedno(tempData);break;
//    				case 4: userData.setDuration(tempData);break;
//    				case 5: userData.setEmail(tempData);break;
//    				case 6: userData.setPhone(tempData);break;
//    				case 7: userData.setPlan_name(tempData);break;
//    				case 8: userData.setActivation_date(tempData);break;
//    				case 9: userData.setExpiry_date(tempData);break;
//    				case 10:userData.setPrice(tempData);break;
//    				case 1: userData.setInvoiceId(tempData);break;
//    				case 2: userData.setCustomerfName(tempData);break;
//    				case 3: userData.setCustomerlName(tempData);break;
//    				case 4: userData.setCustomerEmail(tempData);break;
//    				case 5: userData.setCustomerPhone(tempData);break;
//    				case 6: userData.setInvoiceDate(tempData);break;
//    				case 7: userData.setPaymentdueDate(tempData);break;
//    				case 8: userData.setPayment(tempData);break;
    				}
    				i++;
    			}
    			list.add(userData);

    			System.out.println();
    		}
    		list.stream().forEach(temp->{System.out.println(temp);});
    	}
        return list;
    }
 
 

}

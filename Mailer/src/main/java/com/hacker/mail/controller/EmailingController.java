package com.hacker.mail.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.apache.http.client.utils.URIBuilder;
import com.hacker.mail.dao.Email;
import com.hacker.mail.dao.Feedback;

@Configuration
@EnableBatchProcessing
public class EmailingController {
	@Autowired
	FeedbackController feed;

	@Autowired
	private JavaMailSender emailSender;
	@Bean  
	public  void sendEmail() throws EncryptedDocumentException, IOException{

		String unRegisteredFilePath= "/Volunteer_Enrollment Details_Unregistered.xlsx";
		String notAttendedFilePath = "/Volunteer_Enrollment Details_Not_Attend.xlsx";
		String volunteersFilePath = "/OutReach Event Information.xlsx";
		System.out.println("Spring Mail - Sending Simple Email with JavaMailSender Example");
		mailComposer(unRegisteredFilePath);
		mailComposer(notAttendedFilePath);
		mailComposer(volunteersFilePath);
	}





	private void mailComposer (String fileName) {

		Workbook workbook = null;
		try {//File file1= ResourceUtils.getFile("classpath:fileName");
		//InputStream file = new FileInputStream(file1);
			InputStream file = getClass().getResourceAsStream(fileName);
			
			workbook = WorkbookFactory.create(file);
		} catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		// Getting the Sheet at index zero
		Sheet sheet = workbook.getSheetAt(0);

		// Create a DataFormatter to format and get each cell's value as String
		DataFormatter dataFormatter = new DataFormatter();

		if(fileName.contains("OutReach Event Information.xlsx")) {
			System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
			for (Row row: sheet) { 
				if(0!= row.getRowNum())
				{
					Email mail = new Email();
					URL url =null;
					row.getCell(0);
					Feedback feedback = new Feedback();
				//	String cellValue = dataFormatter.formatCellValue(row.getCell(5));
					//System.out.print(cellValue + "\t");
					try {
						URIBuilder builder = new URIBuilder();
								builder.setScheme("http");
								builder.setHost("172.18.2.50");
								builder.setPort(3000);
								builder.setPath("/feedback1");
								builder.addParameter("empID", dataFormatter.formatCellValue(row.getCell(7)));
								builder.addParameter("eventID", dataFormatter.formatCellValue(row.getCell(0)));
								builder.addParameter("bu", dataFormatter.formatCellValue(row.getCell(12)));
								builder.addParameter("beneficiary", dataFormatter.formatCellValue(row.getCell(2)));
								 url = builder.build().toURL();
					
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					catch (URISyntaxException ex) {
						
						ex.printStackTrace();
					}
					mail.setFrom("priya.raju1003@gmail.com");
					mail.setTo("priya.raju3@cognizant.com");
					mail.setSubject("Feedback for Outreach Event");
					mail.setContent("As per our records we see that you had registered for the event " +row.getCell(4) +". Please provide rate your experience by providing your feedback \n"+url);

					SimpleMailMessage message = new SimpleMailMessage();
					message.setSubject(mail.getSubject());
					message.setText(mail.getContent());
					message.setTo(mail.getTo());
					message.setFrom(mail.getFrom());

					feedback.setEmpID(Integer.parseInt(dataFormatter.formatCellValue(row.getCell(7))));
					feedback.setEventid(dataFormatter.formatCellValue(row.getCell(0)));
					feedback.setBusinessUnit(dataFormatter.formatCellValue(row.getCell(12)));
					feedback.setBeneficiary(dataFormatter.formatCellValue(row.getCell(2)));
					feed.createFeedBack(feedback);
					try {
						emailSender.send(message);
					}
					catch (Exception exception) {
						// Output unexpected IOExceptions.
						System.out.println("Unable to send emails");
					}

				}
			}


		}else {
			// iterate over the rows and columns
			System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
			for (Row row: sheet) { 
				if(0!= row.getRowNum())
				{
					Email mail = new Email();
					row.getCell(0);
					Feedback feedback = new Feedback();
					String cellValue = dataFormatter.formatCellValue(row.getCell(5));
					System.out.print(cellValue + "\t");
					
					mail.setFrom("priya.raju1003@gmail.com");
					mail.setTo("priya.raju3@cognizant.com");
					mail.setSubject("Feedback for Outreach Event");
					mail.setContent("As per our records we see that you had registered for the event " +row.getCell(1) +". But later unregistered. \n Please click on the link to provide your a suitable reason for your absence. \n http://172.18.2.50:3000/feedback2?empID="+dataFormatter.formatCellValue(row.getCell(5))+"&eventID="+dataFormatter.formatCellValue(row.getCell(0)));

					SimpleMailMessage message = new SimpleMailMessage();
					message.setSubject(mail.getSubject());
					message.setText(mail.getContent());
					message.setTo(mail.getTo());
					message.setFrom(mail.getFrom());

					feedback.setEmpID(Integer.parseInt(dataFormatter.formatCellValue(row.getCell(5))));
					feedback.setEventid(dataFormatter.formatCellValue(row.getCell(0)));

					feed.createFeedBack(feedback);
					try {
						emailSender.send(message);
					}
					catch (Exception exception) {
						// Output unexpected IOExceptions.
						System.out.println("Unable to send emails");
					}

				}
			}}
	}	
}
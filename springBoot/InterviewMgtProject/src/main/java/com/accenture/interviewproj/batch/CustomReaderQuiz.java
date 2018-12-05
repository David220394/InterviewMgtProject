package com.accenture.interviewproj.batch;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.IteratorItemReader;
/**
 *Custom Reader class 
 * To read input from batch process
 */
public class CustomReaderQuiz implements ItemReader<File> {
	
	private ItemReader<File> delegate;
	
	String path;
	
	public CustomReaderQuiz(String path){
		this.path = path;
	}

	@Override
	public File read()
			throws Exception {
		if(delegate == null) {
			delegate = new IteratorItemReader<>(quiz());
		}
		return delegate.read();
	}
	
	/**
	 * Method to obtain every row from every excel files
	 */
	private List<File> quiz() throws EncryptedDocumentException, 
	InvalidFormatException, IOException{
		List<File> fileList = new ArrayList<>();
		File folder = new File(this.path);
		if(folder.isDirectory()) {
			File[] files = folder.listFiles();
			for (File file : files) {
				fileList.add(file);
			}
		}
		return fileList;
		
	}
}
